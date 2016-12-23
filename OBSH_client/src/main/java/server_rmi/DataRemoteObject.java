package server_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;

import ResultMessage.ResultMessage;
import blservice.*;
import blserviceImpl.*;
import po.OrderPo;
import po.PromotionPo;
import po.UserPo;
import po.CreditPo;
import po.HotelPo;
import po.HotelroomPo;
import po.MemberPo;
import vo.HotelVo;
import vo.HotelroomVo;
import vo.OrderVo;
import vo.PromotionVo;
import vo.UserVo;

public class DataRemoteObject extends UnicastRemoteObject implements UserBLService, HotelroomBLService,
		PromotionBLService, CreditBLService, HotelBLService, OrderBLService, MemberBLService {

	private static final long serialVersionUID = 5058520815236841487L;

	private UserBLService userblservice;
	private PromotionBLService promotionblservice;
	private CreditBLService creditblservice;
	private HotelBLService hotelblservice;
	private OrderBLService orderblservice;
	private MemberBLService memberblservice;
	private HotelroomBLService hotelroomblservice;

	protected DataRemoteObject() throws RemoteException {
		userblservice = new UserBLServiceImpl();
		promotionblservice = new PromotionBLServiceImpl();
		creditblservice = new CreditBLServiceImpl();
		hotelblservice = new HotelBLServiceImpl();
		orderblservice = new OrderBLServiceImpl();
		memberblservice = new MemberBLServiceImpl();
		hotelroomblservice = new HotelroomBLServiceImpl();
	}

	@Override
	public ResultMessage login(int id, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.login(id, password);
	}

	@Override
	public ResultMessage ModifyMessage(UserVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.ModifyMessage(vo);
	}

	@Override
	public ResultMessage ModifyHotelMessage(HotelVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.ModifyHotelMessage(vo);
	}

	@Override
	public ResultMessage ModifyPassword(UserVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.ModifyPassword(vo);
	}

	@Override
	public int AddClient(UserVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.AddClient(vo);
	}

	@Override
	public List<OrderPo> GetOrderHistory(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.GetOrderHistory(id);
	}

	@Override
	public int getMemberRank(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return memberblservice.getMemberRank(id);
	}

	@Override
	public boolean isMember(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return memberblservice.isMember(id);
	}

	@Override
	public ResultMessage Cancellation(OrderVo ordervo) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.Cancellation(ordervo);
	}

	@Override
	public ResultMessage IFpassTime(OrderVo ordervo) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.IFpassTime(ordervo);
	}

	@Override
	public void CancelKillCredit(OrderVo ordervo) throws RemoteException {
		// TODO Auto-generated method stub
		orderblservice.CancelKillCredit(ordervo);
	}

	@Override
	public ResultMessage AddOrder(OrderVo ordervo) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.AddOrder(ordervo);
	}

	@Override
	public void Solve(OrderVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		orderblservice.Solve(vo);
	}

	@Override
	public ResultMessage ComplainDeal(OrderVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.ComplainDeal(vo);
	}

	@Override
	public OrderPo ViewByOrderID(String orderid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.ViewByOrderID(orderid);
	}

	@Override
	public List<OrderPo> ViewByCustom(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.ViewByCustom(id);
	}

	@Override
	public List<OrderVo> ViewByDaily(List<OrderVo> list) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.ViewByDaily(list);
	}

	@Override
	public List<OrderPo> ViewByState(int state, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.ViewByState(state, id);
	}

	@Override
	public List<OrderVo> TimeSort(List<OrderVo> orderlist) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.TimeSort(orderlist);
	}

	@Override
	public String CreateID(int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.CreateID(hotelid);
	}

	@Override
	public void addCredit(int id, double value) throws RemoteException {
		// TODO Auto-generated method stub
		creditblservice.addCredit(id, value);
	}

	@Override
	public void recoverCredit(int id, double price, int tag, String orderid) throws RemoteException {
		// TODO Auto-generated method stub
		creditblservice.recoverCredit(id, price, tag, orderid);
	}

	@Override
	public int judge(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionblservice.judge(id);
	}

	@Override
	public ResultMessage createNewItem(PromotionVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionblservice.createNewItem(vo);
	}

	@Override
	public ResultMessage Del(PromotionVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionblservice.Del(vo);
	}

	@Override
	public List<PromotionPo> getPromotions() throws RemoteException {
		// TODO Auto-generated method stub
		return promotionblservice.getPromotions();
	}

	@Override
	public List<OrderPo> ViewByHotel(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.ViewByHotel(id);
	}

	@Override
	public List<CreditPo> getHistoryCredit(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.getHistoryCredit(id);
	}

	@Override
	public void CutCreditForCancel(int id, double price, String orderid) throws RemoteException {
		// TODO Auto-generated method stub
		creditblservice.CutCreditForCancel(id, price, orderid);
	}

	@Override
	public ResultMessage AddHotelMember(UserVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.AddHotelMember(vo);
	}

	@Override
	public ResultMessage AddMarketer(UserVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.AddMarketer(vo);
	}

	@Override
	public ResultMessage Addhotel(HotelVo hotelvo) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelblservice.Addhotel(hotelvo);
	}

	@Override
	public HotelPo SearchByName(String hotel) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelblservice.SearchByName(hotel);
	}

	@Override
	public List<HotelPo> Views(String address, String commercialDistrict) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelblservice.Views(address, commercialDistrict);
	}

	@Override
	public void createByPersonal(int id, Date birthday) throws RemoteException {
		// TODO Auto-generated method stub
		memberblservice.createByPersonal(id, birthday);
	}

	@Override
	public CreditPo getCredit(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return creditblservice.getCredit(id);
	}

	@Override
	public double getRankDiscount(int rank) throws RemoteException {
		// TODO Auto-generated method stub
		return memberblservice.getRankDiscount(rank);
	}

	@Override
	public boolean Addroom(HotelroomVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelroomblservice.Addroom(vo);
	}

	@Override
	public void CheckIn(HotelroomVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		hotelroomblservice.CheckIn(vo);
	}

	@Override
	public void CheckOut(HotelroomVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		hotelroomblservice.CheckOut(vo);
	}

	@Override
	public void ModifyPrice(HotelroomVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		hotelroomblservice.ModifyPrice(vo);
	}

	@Override
	public HotelPo SearchByID(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelblservice.SearchByID(id);
	}

	@Override
	public ResultMessage AddAssess(int score, String s, int hotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelblservice.AddAssess(score, s, hotelID);
	}

	@Override
	public UserPo GetUserByID(int ID) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.GetUserByID(ID);
	}


	@Override
	public double getHotelMinPrice(int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelblservice.getHotelMinPrice(hotelid);
	}

	@Override
	public boolean createByBusiness(int id, String name) throws RemoteException {
		// TODO Auto-generated method stub
		return memberblservice.createByBusiness(id, name);
	}

	@Override
	public MemberPo getMemberInfo(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return memberblservice.getMemberInfo(id);
	}

	@Override
	public ResultMessage Assess(String orderID, int score, String comment, int hotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.Assess(orderID, score, comment, hotelID);
	}

	@Override
	public PromotionPo CalPromotion() throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.CalPromotion();
	}

	@Override
	public double getHotelRoomPriceByType(String type, String hotelname) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelblservice.getHotelRoomPriceByType(type, hotelname);
	}

	@Override
	public ResultMessage CreditCheck(int userid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.CreditCheck(userid);
	}

	@Override
	public double CalDiscount(int userID) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.CalDiscount(userID);
	}

	@Override
	public List<OrderPo> ViewByStateHotel(int state, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return orderblservice.ViewByStateHotel(state, id);
	}

	@Override
	public void CheckOut(int hotelID, int roomID) throws RemoteException {
		// TODO Auto-generated method stub
		hotelroomblservice.CheckOut(hotelID, roomID);
	}

	@Override
	public List<HotelroomPo> getHotelroomByType(String roomType) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelroomblservice.getHotelroomByType(roomType);
	}

	@Override
	public List<HotelroomPo> getHotelroomByID(int hotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelroomblservice.getHotelroomByID(hotelID);
	}

	@Override
	public ResultMessage loginHotel(int id, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.loginHotel(id, password);
	}

	@Override
	public UserPo GetHotelStaffByID(int ID) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.GetHotelStaffByID(ID);
	}

	@Override
	public List<PromotionPo> getPromotionByHotel(int hotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return promotionblservice.getPromotionByHotel(hotelID);
	}

	@Override
	public List<HotelVo> FilterByType(String roomType, List<HotelVo> hotellist) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelblservice.FilterByType(roomType, hotellist);
	}

	@Override
	public List<HotelVo> FilterByName(String name, List<HotelVo> list) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelblservice.FilterByName(name, list);
	}

	@Override
	public List<HotelVo> FilterByStar(int hotelStar, List<HotelVo> hotellist) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelblservice.FilterByStar(hotelStar, hotellist);
	}

	@Override
	public void comprehensiveSort(List<HotelVo> list, boolean[] Sort)
			throws RemoteException {
		// TODO Auto-generated method stub
		hotelblservice.comprehensiveSort(list, Sort);
	}

	@Override
	public void AddRoom(int hotelid, int num, String type, double price) throws RemoteException {
		// TODO Auto-generated method stub
		hotelblservice.AddRoom(hotelid, num, type, price);
	}

	@Override
	public void ModifyPrice(int hotelID, String type, double price) throws RemoteException {
		// TODO Auto-generated method stub
		hotelblservice.ModifyPrice(hotelID, type, price);
	}

	@Override
	public void ModifyRoomNumByOrder(int hotelID, int num, String type) throws RemoteException {
		// TODO Auto-generated method stub
		hotelblservice.ModifyRoomNumByOrder(hotelID, num, type);
	}

	@Override
	public void logout(int id) throws RemoteException {
		// TODO Auto-generated method stub
		userblservice.logout(id);
	}

	@Override
	public ResultMessage ModifyHotelStaffPassword(UserVo vo) throws RemoteException {
		// TODO Auto-generated method stub
		return userblservice.ModifyHotelStaffPassword(vo);
	}

}
