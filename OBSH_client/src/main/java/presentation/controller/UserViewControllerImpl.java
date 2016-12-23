package presentation.controller;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ResultMessage.ResultMessage;
import blservice.CreditBLService;
import blservice.HotelBLService;
import blservice.HotelroomBLService;
import blservice.MemberBLService;
import blservice.OrderBLService;
import blservice.PromotionBLService;
import blservice.UserBLService;
import blserviceImpl.PresentTimeGet;
import client_rmi.RemoteHelper_client;
import po.CreditPo;
import po.HotelPo;
import po.HotelroomPo;
import po.MemberPo;
import po.OrderPo;
import po.PromotionPo;
import po.UserPo;
import presentation.view.UserViewControllerService;
import vo.CreditVo;
import vo.HotelVo;
import vo.HotelroomVo;
import vo.OrderVo;
import vo.PromotionVo;
import vo.UserVo;


public class UserViewControllerImpl implements UserViewControllerService {
	private OrderBLService orderService;
	private UserBLService userService;
	private HotelBLService hotelService;
	private CreditBLService creditservice;
	private MemberBLService memberservice;
	private HotelroomBLService hotelroomservice;
	private PromotionBLService promotionservice;
	
	private int UserID;
	
	public UserViewControllerImpl() {
		orderService = RemoteHelper_client.getInstance().getOrderBLService();
		userService = RemoteHelper_client.getInstance().getUserBLService();
		creditservice = RemoteHelper_client.getInstance().getCreditBLService();
		memberservice = RemoteHelper_client.getInstance().getMemberBLService();
		hotelService=RemoteHelper_client.getInstance().getHotelBLService();
		hotelroomservice = RemoteHelper_client.getInstance().getHotelroomBLService();
		promotionservice = RemoteHelper_client.getInstance().getPromotionBLService();
	}

	public int getUserID(){
		return this.UserID;
	}
	
	@Override
	public ResultMessage Login(String id, String password) throws  RemoteException {
		// TODO Auto-generated method stub
		if(id.equals("")||password.equals(""))return ResultMessage.NULL;
		if (!isNum(id)||id.length()!=9)return ResultMessage.FormatWrong;
			return userService.login(Integer.parseInt(id), password);
	}
	
	@Override
	public ResultMessage LoginHotel(String id, String password) throws  RemoteException {
		// TODO Auto-generated method stub
		if(id.equals("")||password.equals(""))return ResultMessage.NULL;
		if (!isNum(id)||id.length()!=4)return ResultMessage.FormatWrong;
			return userService.loginHotel(Integer.parseInt(id), password);
	}
	
	@Override
	public int Register(String name, String password, String phone) throws  RemoteException{
		// TODO Auto-generated method stub
		UserVo vo=new UserVo();
		vo.setUsername(name);
		vo.setPassword(password);
		vo.setPhone(phone);
		
		return userService.AddClient(vo);
	}
	
	private static boolean isNum(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void ModifyPassword(int userid,String passwords) throws RemoteException {
		// TODO Auto-generated method stubs
		UserVo uservo=new UserVo();	
		uservo.setID(userid);
		uservo.setPassword(passwords);
		userService.ModifyPassword(uservo);
	}
	
	@Override
	public void ModifyHotelStaffPassword(int hotelstaffid,String passwords) throws RemoteException {
		// TODO Auto-generated method stubs
		UserVo uservo=new UserVo();	
		uservo.setID(hotelstaffid);
		uservo.setPassword(passwords);
		userService.ModifyHotelStaffPassword(uservo);
	}

	
	
	@Override
	public UserVo GetPresentUserInfo() throws RemoteException {
		// TODO Auto-generated method stub
		UserPo po=userService.GetUserByID(this.UserID);
		UserVo uservo=new UserVo();
		uservo.setID(po.getId());
		uservo.setPassword(po.getPassword());
		uservo.setPhone(po.getPhone());
		uservo.setUsername(po.getUsername());
		return uservo;
	}
	
	@Override
	public UserVo GetPresentHotelStaffInfo() throws RemoteException {
		// TODO Auto-generated method stub
		
		UserPo po=userService.GetHotelStaffByID(this.UserID);
		UserVo uservo=new UserVo();
		uservo.setID(po.getId());
		uservo.setPassword(po.getPassword());
		uservo.setPhone(po.getPhone());
		uservo.setUsername(po.getUsername());
		return uservo;
	}

	@Override
	public void ModifyMessage(UserVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		userService.ModifyMessage(vo);
	}

	@Override
	public List<CreditVo> getHistoryCredit(int id) throws RemoteException {
		List<CreditPo> list=userService.getHistoryCredit(id);
		List<CreditVo> creditlist = new ArrayList<CreditVo>();

		for (CreditPo creditPo : list) {
			int userID=creditPo.getUserID();			
			Timestamp  time=creditPo.getTime();	
			int action=creditPo.getAction();	
			String CreditChange=creditPo.getCreditChange();	
			double CreditResult=creditPo.getCreditResult();
			String orderID=creditPo.getOrderID();
			CreditVo creditVo = new CreditVo(userID,time,action,CreditChange,CreditResult,orderID);
			creditlist.add(creditVo);
		}
		return creditlist;
	}

	@Override
	public CreditVo getPresentCreditInfo(int id) throws RemoteException {
		// TODO Auto-generated method stub 
		CreditPo po=creditservice.getCredit(id);
		CreditVo vo=new CreditVo();
		vo.setAction(po.getAction());
		vo.setCreditChange(po.getCreditChange());
		vo.setCreditResult(po.getCreditResult());
		vo.setOrderID(po.getOrderID());
		vo.setUserId(po.getUserID());
		vo.setTime(po.getTime());
		return vo;
	}

	@Override
	public List<HotelVo> getHotels(String Address,String Commercial,String hotelName,String roomType,
			int hotelStar,boolean PriceSort,boolean StarSort,boolean ScoreSort) throws RemoteException {
		// TODO Auto-generated method stub
		List<HotelPo> list=hotelService.Views(Address, Commercial);
		List<HotelVo> hotellist=new ArrayList<HotelVo>();
		
		for(HotelPo hotelpo: list){
			int hotelID=hotelpo.getHotelID();
			String name=hotelpo.getName();
			int star=hotelpo.getStar();
			String briefInfo=hotelpo.getBriefInfo(); 
			String location=hotelpo.getLocation();  
			List<String> summary=hotelpo.getSummary(); 
			String services=hotelpo.getServices();  
			String roomInfo=hotelpo.getRoomInfo();
			double score=hotelpo.getScore();
			int count=hotelpo.getScoreCount();
			String companys=hotelpo.getCompanies();
			
			HotelVo vo=new HotelVo(hotelID,name,star,location,summary,
					services,roomInfo,score,briefInfo,count,companys);
			hotellist.add(vo);
		}
		
		hotellist=hotelService.FilterByName(hotelName, hotellist);
		
		hotellist=hotelService.FilterByType(roomType, hotellist);

		hotellist=hotelService.FilterByStar(hotelStar, hotellist);
		
		comprehensiveSort(hotellist,PriceSort,StarSort,ScoreSort);
		
		return hotellist;
	}

	@Override
	public ResultMessage MemberMessgae(int userid) throws RemoteException {
		// TODO Auto-generated method stub
		if(!memberservice.isMember(userid)){
			return ResultMessage.Common;
		}
		MemberPo po=memberservice.getMemberInfo(userid);
		if(po.getMemberInfo().equals("Company"))return ResultMessage.Company;
		return ResultMessage.Person;
	}

	@Override
	public void PersonRegisterMember(int userID, String birth) throws RemoteException {
		// TODO Auto-generated method stub
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date utilDate = null;
		try {
			utilDate = f.parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());  

		memberservice.createByPersonal(userID, sqlDate);
	}

	@Override
	public boolean CompanyRegisterMember(int userID, String company) throws RemoteException {
		// TODO Auto-generated method stub
		return memberservice.createByBusiness(userID, company);
	}

	@Override
	public List<OrderVo> getAllHistoryOrdersByUserID(int userID) throws RemoteException {
		// TODO Auto-generated method stub
		List<OrderVo> orderList=new ArrayList<OrderVo>();
		List<OrderPo> list=userService.GetOrderHistory(userID);
		for(OrderPo orderpo:list){
	        String orderID=orderpo.getOrderID();
            int orderState=orderpo.getOrderState();
			Timestamp StartTime=orderpo.getStartTime();
			Timestamp EndTime=orderpo.getEndTime();
			Timestamp lastTime=orderpo.getLastTime();
			Timestamp at=orderpo.getAttemptedLeaveTime();
			Timestamp in=orderpo.getTimeCheckIn();
			Timestamp out=orderpo.getTimeCheckOut();
			double price=orderpo.getPrice();
			int hotelID=orderpo.getHotelID();
			String roomInfo=orderpo.getRoomInfo();
			boolean e=orderpo.isEvaluate();
			
			OrderVo vo=new OrderVo(orderID,orderState,StartTime, EndTime,lastTime,at,in,out,
			userID,price,hotelID,roomInfo,e);
			
			orderList.add(vo);
		}
		return orderList;
	}

	@Override
	public String getHotelNameByHotelID(int hotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelService.SearchByID(hotelID).getName();
	}

	@Override
	public ResultMessage CheckIfCreditMet(int userid)throws RemoteException{
		return orderService.CreditCheck(userid);
	}
	
	@Override
	public ResultMessage ProduceOrder(String hotelname,String start,String deadline,
			String attemptt,String type,int roomNum,int userID)throws RemoteException {	    		
			String orderID=orderService.CreateID(userID);
		    
			int orderState=0;
			Timestamp orderStart=new PresentTimeGet().NowTime();
			Timestamp StartTime= Timestamp.valueOf(start);
			Timestamp lastTime= Timestamp.valueOf(deadline);
			Timestamp attempt=Timestamp.valueOf(attemptt);
			
			double price=hotelService.getHotelRoomPriceByType(type,hotelname);
			price*=roomNum;
			
			price*=orderService.CalDiscount(userID);
			int hotelID=hotelService.SearchByName(hotelname).getHotelID();
			String roomInfo=type+"+"+String.valueOf(roomNum);
            boolean evaluate=false;
			
		    OrderVo ordervo=new OrderVo(orderID,orderState,orderStart,StartTime,lastTime,attempt,userID,price,hotelID,roomInfo,evaluate);
		    return orderService.AddOrder(ordervo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultMessage Assess(String orderID,String comment, int score,int hotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return orderService.Assess(orderID,score, comment, hotelID);
	}

	@Override
	public void Cancellation(String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		OrderPo po=orderService.ViewByOrderID(orderID);
		int orderState=po.getOrderState();
		Timestamp StartTime=po.getStartTime();
		Timestamp EndTime=po.getEndTime();
		Timestamp lastTime=po.getLastTime();
		Timestamp at=po.getAttemptedLeaveTime();
		Timestamp in=po.getTimeCheckIn();
		Timestamp out=po.getTimeCheckOut();
		int userID=po.getUserID();
		double price=po.getPrice();
		int hotelID=po.getHotelID();
		String roomInfo=po.getRoomInfo();
        boolean evaluate=po.isEvaluate();
        OrderVo ordervo=new OrderVo(orderID,orderState,StartTime,EndTime,lastTime,at,in,out,userID,
        		price,hotelID,roomInfo,evaluate);
		ResultMessage re=orderService.IFpassTime(ordervo);
		if(re== ResultMessage.OverTime){orderService.Cancellation(ordervo);}
		orderService.Cancellation(ordervo);
	}

	@Override
	public HotelVo getHotelInfoByName(String hotelname) throws RemoteException {
		// TODO Auto-generated method stub
		HotelPo po=hotelService.SearchByName(hotelname);
		int hotelID=po.getHotelID();
		String name=po.getName();
		int star=po.getStar();
		String briefInfo=po.getBriefInfo();
		String location=po.getLocation(); 
		List<String> summary=po.getSummary(); 
		String services=po.getServices(); 
		String roomInfo=po.getRoomInfo();
		double score=po.getScore();
		int scoreCount=po.getScoreCount();

		String companies = po.getCompanies();
		
		return new HotelVo(hotelID,name,star,location,summary,services,roomInfo,score,briefInfo,scoreCount,companies);
	}
	
	@Override
	public HotelVo getHotelInfoByID() throws RemoteException {
		// TODO Auto-generated method stub
		
		HotelPo po=hotelService.SearchByID(this.UserID);
		
		String name=po.getName();
		int star=po.getStar();
		String briefInfo=po.getBriefInfo();
		String location=po.getLocation(); 
		List<String> summary=po.getSummary(); 
		String services=po.getServices(); 
		String roomInfo=po.getRoomInfo();
		double score=po.getScore();
		int scoreCount=po.getScoreCount();
		String companies = po.getCompanies();
		return new HotelVo(this.UserID,name,star,location,summary,services,roomInfo,score,briefInfo,scoreCount,companies);
	}

	@Override
	public void setUserID(int id) throws RemoteException {
		// TODO Auto-generated method stub
		this.UserID=id;
		
	}

	@Override
	public double BestPromotionDiscount(int userid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderService.CalDiscount(userid);
	}
	
	/**
	 * 这个拆分到酒店controller里
	 * @throws RemoteException 
	 */
	@Override
	public boolean ModifyHotelMessage(HotelVo hotelVo) throws RemoteException {
		/**
		 * 把这里的VO拆成你一个个数据 检查biref问题
		 */
		ResultMessage re = null;
		re = hotelService.ModifyHotelMessage(hotelVo);
		if(re == ResultMessage.UpdateSuccess)
			return true;
		else
			return false;
	}
	/**
	 * 这个拆分到房间controller里
	 * @throws RemoteException 
	 */
	@Override
	public List<HotelroomVo> getHotelRoomByType(String roomType) throws RemoteException {
		List<HotelroomPo> rooms = hotelroomservice.getHotelroomByType(roomType);
		List<HotelroomVo> roomlist = new ArrayList<HotelroomVo>();
		for(HotelroomPo hotelroomPo:rooms) {
			int roomID = hotelroomPo.getRoomID();
			Timestamp TimeCheckIn = hotelroomPo.getTimeCheckIn();
			int hotelID = hotelroomPo.getHotelID();
			Timestamp AttemptedLeaveTime = hotelroomPo.getAttemptedLeaveTime();
			Timestamp TimeCheckOut = hotelroomPo.getTimeCheckOut();
			double price = hotelroomPo.getPrice();
			boolean IfOccupied = hotelroomPo.isIfOccupied();
			
			HotelroomVo vo = new HotelroomVo(roomID,TimeCheckIn,hotelID,AttemptedLeaveTime,
    		TimeCheckOut,roomType,price,IfOccupied);
			roomlist.add(vo);
		}
		return roomlist;
	}
	
	@Override
	public List<HotelroomVo> getHotelRoomByHotelID() throws RemoteException {
		//HotelroomBLService hotelroombl=new HotelroomBLServiceImpl();
		List<HotelroomPo> rooms = hotelroomservice.getHotelroomByID(this.UserID);
   
		List<HotelroomVo> roomlist = new ArrayList<HotelroomVo>();
		for(HotelroomPo hotelroomPo:rooms) {
			int roomID = hotelroomPo.getRoomID();
			Timestamp TimeCheckIn = hotelroomPo.getTimeCheckIn();
			Timestamp AttemptedLeaveTime = hotelroomPo.getAttemptedLeaveTime();
			Timestamp TimeCheckOut = hotelroomPo.getTimeCheckOut();
			String roomType = hotelroomPo.getRoomType();
			double price = hotelroomPo.getPrice();
			boolean IfOccupied = hotelroomPo.isIfOccupied();
			HotelroomVo vo = new HotelroomVo(roomID,TimeCheckIn,this.UserID,AttemptedLeaveTime,
    		TimeCheckOut,roomType,price,IfOccupied);
			roomlist.add(vo);
		}
		return roomlist;
	}
	

	@Override
	public boolean addRoom(HotelroomVo vo) throws RemoteException {
		return hotelroomservice.Addroom(vo);
			
	}
	
	@Override
	public void CheckIn(HotelroomVo vo) throws RemoteException {
		hotelroomservice.CheckIn(vo);;
	}
	
	/**
	 * 这个可以换一个写法
	 * checkout里是vo
	 */
	@Override
	public void CheckOut(int hotelID, int roomID) throws RemoteException {
		hotelroomservice.CheckOut(hotelID, roomID);;
	}
	
	@Override
	public List<OrderVo> getOrdersByHotelID(int hotelID) throws RemoteException{
		List<OrderPo> orders = orderService.ViewByHotel(hotelID);
		List<OrderVo> vo = new ArrayList<OrderVo>();
		for(OrderPo po:orders) {
	        String orderID=po.getOrderID();
            int orderState=po.getOrderState();
			Timestamp StartTime=po.getStartTime();
			Timestamp EndTime=po.getEndTime();
			Timestamp lastTime=po.getLastTime();
			Timestamp at=po.getAttemptedLeaveTime();
			Timestamp in=po.getTimeCheckIn();
			Timestamp out=po.getTimeCheckOut();
			double price=po.getPrice();
			String roomInfo=po.getRoomInfo();
			boolean e=po.isEvaluate();
			
			OrderVo ordervo=new OrderVo(orderID,orderState,StartTime, EndTime,lastTime,at,in,out,
			this.UserID,price,hotelID,roomInfo,e);
			vo.add(ordervo);
		}

		return vo;
		
	}
	
	@Override
	public List<OrderVo> getOrdersByState(int state, int hotelID) throws RemoteException{
		List<OrderPo> orders = orderService.ViewByStateHotel(state, hotelID);
		List<OrderVo> vo = new ArrayList<OrderVo>();
		for(OrderPo po:orders) {
	        String orderID=po.getOrderID();
            int userID=po.getUserID();
			Timestamp StartTime=po.getStartTime();
			Timestamp EndTime=po.getEndTime();
			Timestamp lastTime=po.getLastTime();
			Timestamp at=po.getAttemptedLeaveTime();
			Timestamp in=po.getTimeCheckIn();
			Timestamp out=po.getTimeCheckOut();
			double price=po.getPrice();
			String roomInfo=po.getRoomInfo();
			boolean evaluate=po.isEvaluate();
			OrderVo ordervo=new OrderVo(orderID,state,StartTime, EndTime,lastTime,at,in,out,
					userID,price,hotelID,roomInfo,evaluate);
			vo.add(ordervo);
		}

		return vo;
		
	}
	
	@Override
	public boolean DelPromotion(PromotionVo vo)throws RemoteException {
		ResultMessage re = promotionservice.Del(vo);
		if(re==ResultMessage.UpdateFail)
			return false;
		else
			return true;

	}
	
	@Override
	public boolean AddPromotion(String dis, String itemName,String start,String end,String info)throws RemoteException {
		ResultMessage re = null;
		Timestamp startTime = Timestamp.valueOf(start);
		Timestamp endTime = Timestamp.valueOf(end);
		double discount = Double.parseDouble(dis);
		PromotionVo vo = new PromotionVo(this.UserID ,discount, itemName,  startTime, endTime, info);
		re = promotionservice.createNewItem(vo);
		if(re==ResultMessage.UpdateSuccess)
			return true;
		else 
			return false;

	}

	@Override
	public List<PromotionVo> getPromotions(int hotelID) throws RemoteException {
		List<PromotionPo> promotions = promotionservice.getPromotionByHotel(hotelID);
		List<PromotionVo> vo = new ArrayList<PromotionVo>();
		for(PromotionPo po:promotions) {
			int promotionID = po.getID();
			String itemName=po.getItemName();
			Timestamp startTime = po.getStartTime();
			Timestamp endTime = po.getEndTime();
			String info = po.getPromotionInfo();
			double discount = po.getDiscount();
			PromotionVo promotionvo=new PromotionVo(promotionID,discount,itemName,startTime,endTime,info);
			vo.add(promotionvo);
		}

		return vo;
		
	}
	
	
	@Override
	public void comprehensiveSort(List<HotelVo> list,boolean Price, boolean Star,
			boolean Score) throws RemoteException {
		// TODO Auto-generated method stub
		if(Price||Star||Score){
			boolean Sort[]={Price,Star,Score};
			hotelService.comprehensiveSort(list,Sort);
		}
		
	}
	
	public void Logout(int userid) throws RemoteException{
		// TODO Auto-generated method stub
		userService.logout(userid);
	}
	

}