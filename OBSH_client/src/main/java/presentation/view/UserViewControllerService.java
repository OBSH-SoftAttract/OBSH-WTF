package presentation.view;

import java.rmi.RemoteException;
import java.util.List;

import ResultMessage.ResultMessage;
import vo.CreditVo;
import vo.HotelVo;
import vo.OrderVo;
import vo.UserVo;

public interface UserViewControllerService {
	public ResultMessage Login(String id, String password) throws RemoteException;

	public ResultMessage Register(String id, String password, String phone,int userid) throws RemoteException;
	
	public int NewClientID()throws RemoteException;
	
	public void ModifyPassword(int userid,String passwords) throws RemoteException;
	
	public UserVo GetPresentUserInfo()throws RemoteException;
	
	public void ModifyMessage(UserVo vo)throws RemoteException;
	
	public List<CreditVo> getHistoryCredit(int id)throws RemoteException;
	
	public CreditVo getPresentCreditInfo(int id)throws RemoteException;
	
	public List<HotelVo> getHotels(String loaction,String commercial)throws RemoteException;
	
	public ResultMessage MemberMessgae(int userid)throws RemoteException;
	
	public void PersonRegisterMember(int userID,String birth)throws RemoteException;
	
	public boolean CompanyRegisterMember(int userID,String company)throws RemoteException;
	
	public List<OrderVo> getAllHistoryOrdersByUserID(int userID)throws RemoteException;
	
	public String getHotelNameByHotelID(int hotelID)throws RemoteException;
	
	public ResultMessage ProduceOrder(String hotelname,String start,String end,String deadline,String type,int roomNum,int userID)throws RemoteException;
	
	public ResultMessage Assess(String orderID,String comment,int score,int hotelID)throws RemoteException;
	
	public void Cancellation(String orderID)throws RemoteException;
	
	public HotelVo getHotelInfoByName(String hotelname)throws RemoteException;
}
