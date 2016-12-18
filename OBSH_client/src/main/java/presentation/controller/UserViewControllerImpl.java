package presentation.controller;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import ResultMessage.ResultMessage;
import blservice.CreditBLService;
import blservice.HotelBLService;
import blservice.MemberBLService;
import blservice.OrderBLService;
import blservice.UserBLService;

import client_rmi.RemoteHelper_client;
import po.CreditPo;
import po.HotelPo;
import po.MemberPo;
import po.OrderPo;
import po.UserPo;
import presentation.view.UserViewControllerService;
import vo.CreditVo;
import vo.HotelVo;
import vo.OrderVo;
import vo.UserVo;


public class UserViewControllerImpl implements UserViewControllerService {
	private OrderBLService orderService;
	private UserBLService userService;
	private HotelBLService hotelService;
	private CreditBLService creditservice;
	private MemberBLService memberservice;
	
	private int UserID;
	
	public UserViewControllerImpl() {
		orderService = RemoteHelper_client.getInstance().getOrderBLService();
		userService = RemoteHelper_client.getInstance().getUserBLService();
		creditservice=RemoteHelper_client.getInstance().getCreditBLService();
		memberservice=RemoteHelper_client.getInstance().getMemberBLService();
	}

	@Override
	public ResultMessage Login(String id, String password) throws  RemoteException {
		// TODO Auto-generated method stub
		if(id.equals("")||password.equals(""))return ResultMessage.NULL;
		if (!isNum(id)||id.length()!=6)return ResultMessage.FormatWrong;
			return userService.login(Integer.parseInt(id), password);
	}
	
	@Override
	public ResultMessage Register(String name, String password, String phone,int userid) throws  RemoteException{
		// TODO Auto-generated method stub
		if(name.equals("")||password.equals("")||phone.equals(""))return ResultMessage.NULL;
		UserVo vo=new UserVo();
		vo.setUsername(name);
		vo.setPassword(password);
		vo.setPhone(phone);
		vo.setID(userid);
		
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
	public int NewClientID() throws RemoteException {
		return userService.GetNewClientID();
	}

	@Override
	public void ModifyPassword(int userid,String passwords) throws RemoteException {
		// TODO Auto-generated method stubs
		UserVo uservo=new UserVo();	
		uservo.setID(uservo.getID());
		uservo.setPassword(passwords);
		userService.ModifyPassword(uservo);
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
	public List<HotelVo> getHotels(String loaction, String commercial) throws RemoteException {
		// TODO Auto-generated method stub
		List<HotelPo> list=hotelService.Views(loaction, commercial);
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

			HotelVo vo=new HotelVo(hotelID,name,star,location,summary,
					services,roomInfo,score,briefInfo,count);
			hotellist.add(vo);
		}
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
			Timestamp lastTime=orderpo.getlastTime();
			double price=orderpo.getPrice();
			int hotelID=orderpo.getHotelID();
			String roomInfo=orderpo.getroomInfo();
			boolean e=orderpo.isEvaluate();
			
			OrderVo vo=new OrderVo(orderID,orderState,StartTime, EndTime,lastTime,
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
	public ResultMessage ProduceOrder(String hotelname,String start, String end, String deadline,
			String type, int roomNum,int userID)throws RemoteException {	    		
			String orderID=orderService.CreateID(userID);
		    
		    String orderid=orderID;
			int orderState=0;
			Timestamp StartTime= Timestamp.valueOf(start);
			Timestamp EndTime= Timestamp.valueOf(end);
			Timestamp lastTime= Timestamp.valueOf(deadline);
			
			int userid=userID;
			double price=hotelService.getHotelRoomPriceByType(type,hotelname);
			int hotelID=hotelService.SearchByName(hotelname).getHotelID();
			String roomInfo=type+"+"+String.valueOf(roomNum);
            boolean evaluate=false;
			
		    OrderVo ordervo=new OrderVo(orderid,orderState,StartTime,EndTime,lastTime,userid,price,hotelID,roomInfo,evaluate);
		    return orderService.Add(ordervo);
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
		Timestamp lastTime=po.getlastTime();
		int userID=po.getUserID();
		double price=po.getPrice();
		int hotelID=po.getHotelID();
		String roomInfo=po.getroomInfo();
        boolean evaluate=po.isEvaluate();
        OrderVo ordervo=new OrderVo(orderID,orderState,StartTime,EndTime,lastTime,userID,
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
		
		return new HotelVo(hotelID,name,star,location,summary,services,roomInfo,score,briefInfo,scoreCount);
	}
	
	

}