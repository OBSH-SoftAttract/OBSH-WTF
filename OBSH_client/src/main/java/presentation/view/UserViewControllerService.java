package presentation.view;

import java.rmi.RemoteException;
import java.util.List;

import ResultMessage.ResultMessage;
import vo.CreditVo;
import vo.HotelVo;
import vo.HotelroomVo;
import vo.OrderVo;
import vo.PromotionVo;
import vo.UserVo;

public interface UserViewControllerService {
	public ResultMessage Login(String id, String password) throws RemoteException;

	public int Register(String id, String password, String phone) throws RemoteException;
	
	public void ModifyPassword(int userid,String passwords) throws RemoteException;
	
	public UserVo GetPresentUserInfo()throws RemoteException;
	
	public void ModifyMessage(UserVo vo)throws RemoteException;
	
	public List<CreditVo> getHistoryCredit(int id)throws RemoteException;
	
	public CreditVo getPresentCreditInfo(int id)throws RemoteException;
	
	public ResultMessage MemberMessgae(int userid)throws RemoteException;
	
	public void PersonRegisterMember(int userID,String birth)throws RemoteException;
	
	public boolean CompanyRegisterMember(int userID,String company)throws RemoteException;
	
	public List<OrderVo> getAllHistoryOrdersByUserID(int userID)throws RemoteException;
	
	public String getHotelNameByHotelID(int hotelID)throws RemoteException;
	
	public ResultMessage ProduceOrder(String hotelname,String start,String end,String deadline,String type,int roomNum,int userID)throws RemoteException;
	
	public ResultMessage Assess(String orderID,String comment,int score,int hotelID)throws RemoteException;
	
	public void Cancellation(String orderID)throws RemoteException;
	
	public HotelVo getHotelInfoByName(String hotelname)throws RemoteException;
	
	public void setUserID(int id)throws RemoteException;
	
	public ResultMessage CheckIfCreditMet(int userid)throws RemoteException;
	
	public double BestPromotionDiscount(int userid)throws RemoteException;

	public boolean ModifyHotelMessage(HotelVo hotelVo) throws RemoteException;

	public List<HotelroomVo> getHotelRoomByType(String roomType) throws RemoteException;

	public boolean addRoom(HotelroomVo vo) throws RemoteException;

	public void CheckIn(HotelroomVo vo) throws RemoteException;

	public void CheckOut(int hotelID, int roomID) throws RemoteException;

	public List<OrderVo> getOrdersByHotelID(int hotelID) throws RemoteException;

	public List<OrderVo> getOrdersByState(int state, int hotelID) throws RemoteException;

	public boolean DelPromotion(PromotionVo vo) throws RemoteException;

	public ResultMessage LoginHotel(String id, String password) throws RemoteException;

	public boolean AddPromotion(String dis, String itemName,String start,String end,String info) throws RemoteException;

	public List<HotelroomVo> getHotelRoomByHotelID() throws RemoteException;

	public HotelVo getHotelInfoByID() throws RemoteException;

	public UserVo GetPresentHotelStaffInfo() throws RemoteException;

	public List<PromotionVo> getPromotions(int hotelID) throws RemoteException;

	public void comprehensiveSort(List<HotelVo> list, boolean Price, boolean Star, boolean Score) throws RemoteException;

	public List<HotelVo> getHotels(String Address, String Commercial, String hotelName, String roomType, int hotelStar,
			boolean PriceSort, boolean StarSort, boolean ScoreSort) throws RemoteException;

	public void ModifyHotelStaffPassword(int hotelstaffid, String passwords) throws RemoteException;
}
