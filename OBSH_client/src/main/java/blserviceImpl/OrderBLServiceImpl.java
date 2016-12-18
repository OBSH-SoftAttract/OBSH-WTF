package blserviceImpl;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ResultMessage.ResultMessage;
import blservice.HotelBLService;
import blservice.MemberBLService;
import blservice.OrderBLService;
import blservice.PromotionBLService;
import data.dao.CreditDao;
import data.dao.OrderDao;
import data.dao.impl.CreditDaoImpl;
import data.dao.impl.OrderDaoImpl;
import po.CreditPo;
import po.OrderPo;
import po.PromotionPo;
import vo.OrderVo;


public class OrderBLServiceImpl implements OrderBLService{

	private CreditDao creditdao;
	private OrderDao orderdao;
	private HotelBLService hotelbl;
	private PromotionBLService promotionbl;
	private MemberBLService memberbl;
	
	private PresentTimeGet nowtime;
	private final double CREDIT=0;
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final long AttemptedTime=21600000;//6小时
	
	private CreditBLServiceImpl cre;
	
	public OrderBLServiceImpl(){
		creditdao=CreditDaoImpl.getInstance();
		orderdao=OrderDaoImpl.getInstance();
		hotelbl=new HotelBLServiceImpl();
		promotionbl=new PromotionBLServiceImpl();
		memberbl=new MemberBLServiceImpl();
		cre=new CreditBLServiceImpl();
		nowtime=new PresentTimeGet();
	}
	
	@Override
	public ResultMessage Cancellation(OrderVo ordervo){
        ordervo.setOrderState(2);
		OrderPo po=orderdao.getOrder(ordervo.getOrderID());
		po.setOrderState(2);
		
		if(nowtime.NowTime().getTime()-ordervo.getStartTime().getTime()>AttemptedTime){
			cre.CutCreditForCancel(ordervo.getUserID(),ordervo.getPrice(),ordervo.getOrderID());
		}
		if(orderdao.updateOrder(po))return ResultMessage.UpdateSuccess;
		return ResultMessage.UpdateFail;
	}

	@Override
	public ResultMessage IFpassTime(OrderVo ordervo) {
		Timestamp deadline=ordervo.getLastTime();		

		if(deadline.getTime()<nowtime.NowTime().getTime())return ResultMessage.InTime;	
		return ResultMessage.OverTime;
	}
	

	@Override
	public void CancelKillCredit(OrderVo ordervo) {
		double precredit=cre.getCredit(ordervo.getUserID()).getCreditResult();

		CreditPo creditpo=new CreditPo(ordervo.getUserID(),nowtime.NowTime(),precredit-ordervo.getPrice());
		creditdao.addCredit(creditpo);
	}

	@Override
	public ResultMessage Assess(String orderID,int score, String comment, int hotelID) throws RemoteException {
		if(orderdao.getOrder(orderID).isEvaluate())
			return ResultMessage.NULL;
		return hotelbl.AddAssess(score,comment,hotelID);
	}

	@Override
	public ResultMessage Add(OrderVo ordervo) {
		OrderPo orderPo=new OrderPo(ordervo);
		orderPo.setStartTime(nowtime.NowTime());
		
		if(orderdao.addOrderPo(orderPo))return ResultMessage.Success;
		return ResultMessage.IDExsit;
	}

	@Override
	public ResultMessage CreditCheck(OrderVo ordervo) {
		int id=ordervo.getUserID();
		double credit=cre.getCredit(id).getCreditResult();
		if(credit>=CREDIT)return ResultMessage.CreditMet;
		return ResultMessage.CreditWrong;
	}

	@Override
	public PromotionPo CalPromotion(int userid) throws RemoteException {
		List<PromotionPo> list=promotionbl.getPromotions();
		Iterator<PromotionPo> ite=list.iterator();
		
		Timestamp now=nowtime.NowTime();
		double best=1;
		PromotionPo res=null;
		while(ite.hasNext()){
			PromotionPo temp=ite.next();
			if(temp.getEndTime().getTime()<=now.getTime()&&
					now.getTime()>=temp.getStartTime().getTime()){
				if(temp.getDiscount()<best){
					best=temp.getDiscount();
					res=temp;
				}
					
			}
		}	
		return res;
	}

	@Override
	public double CalPrice(OrderVo vo) throws RemoteException{
		double discount=1;
		if(memberbl.isMember(vo.getUserID())){
			double memberDiscount=memberbl.getRankDiscount(memberbl.getMemberRank(vo.getUserID()));
			discount=memberDiscount;
		}
		
		PromotionPo promotion=CalPromotion(vo.getUserID());
		if(promotion!=null&&promotion.getDiscount()<discount)
			discount=promotion.getDiscount();
		   
		return discount*vo.getPrice();
	}

	@Override
	public void Solve(OrderVo vo) {
		OrderPo orderpo=orderdao.getOrder(vo.getOrderID());//先修改状态
		orderpo.setOrderState(1);
		orderdao.updateOrder(orderpo);
		
		double incre=orderpo.getPrice();
		CreditPo po=cre.getCredit(orderpo.getUserID());
		po.setCreditResult((incre+cre.getCredit(orderpo.getUserID()).getCreditResult()));
		creditdao.addCredit(po);
	}

	@Override
	public ResultMessage ComplainDeal(OrderVo vo) {
		OrderPo po=orderdao.getOrder(vo.getOrderID());
		if(po.getOrderState()!=3)
		return ResultMessage.StateWrong;
		else{
			po.setOrderState(2);
			if(orderdao.updateOrder(po))return ResultMessage.UpdateSuccess;
			return ResultMessage.UpdateFail;
		}
	}

	@Override
	public OrderPo ViewByOrderID(String orderid) {
		return orderdao.getOrder(orderid);	
	}
	
	@Override
	public List<OrderPo> ViewByHotel(int id) {
		return orderdao.getOrders(id);
	}

	@Override
	public List<OrderPo> ViewByCustom(int id){
		return orderdao.getOrderByUserID(id);
	}

	@Override
	public List<OrderVo> ViewByDaily(List<OrderVo> list) {
		for(int i=0;i<list.size()-1;i++){
			for(int j=1;j<list.size();j++){
				if(list.get(i).getStartTime().getTime()<list.get(j).getStartTime().getTime()){
					OrderVo vo=list.get(i);
					list.set(i, list.get(j));
					list.set(j, vo);
				}
			}
		}
		return list;
	}
	
	
	@SuppressWarnings("null")
	@Override
	public List<OrderPo> ViewByState(int state,int id) {
		List<OrderPo> orderList=ViewByCustom(id);
		Iterator<OrderPo> ite=orderList.iterator();
		List<OrderPo> orderAfter = null;
		while(ite.hasNext()){
			OrderPo po=ite.next();
			if(po.getOrderState()==state)
				orderAfter.add(po);
		  }			
		return orderAfter;
	}


	@Override
	public List<OrderVo> TimeSort(List<OrderVo> orderlist){
		
		if(orderlist.size()<=1) return orderlist;
		for(int i=0;i<orderlist.size()-1;i++){
			for(int j=1;j<orderlist.size();j++){
				OrderVo vo;
				if(orderlist.get(i).getStartTime().getTime()<orderlist.get(j).getStartTime().getTime()){
					vo=orderlist.get(i);
					orderlist.set(i, orderlist.get(j));
					orderlist.set(j, vo);
				}
			}
		}
		return orderlist;
	}

	@Override
	public String CreateID(int userID) {
		String s="";
		try{
			Date date = new Date();
			s=df.format(date);
			s=s.substring(0,4)+s.substring(5, 7)+s.substring(8, 10)+s.substring(11, 13)+s.substring(14, 16)+
					s.substring(17, 19);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		s=s+Integer.toString(userID);
		return  s;
	}


	
}
