package blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ResultMessage.ResultMessage;
import po.CreditPo;
import po.OrderPo;
import po.UserPo;
import vo.HotelVo;
import vo.UserVo;

public interface UserBLService extends Remote{
	
	/**
	 * 
	 * @param id
	 * @param password
	 * @return 客户登录
	 */
	public ResultMessage login(int id, String password)throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @param password
	 * @return 酒店工作人员登录
	 * @throws RemoteException
	 */
	public ResultMessage loginHotel(int id, String password)throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @return 获得信用记录
	 */
	public List<CreditPo> getHistoryCredit(int id)throws RemoteException;
	
	
	/**
	 * 
	 * @param vo
	 * @return 修改客户信息
	 */
	public ResultMessage ModifyMessage(UserVo vo)throws RemoteException;
	
	/**
	 * 
	 * @param vo
	 * @return 修改酒店信息
	 */
	public ResultMessage ModifyHotelMessage(HotelVo vo)throws RemoteException;
	
	/**
	 * 
	 * @param vo
	 * @return 修改密码
	 */
	public ResultMessage ModifyPassword(UserVo vo)throws RemoteException;
	
	/**
	 * 
	 * @param ID
	 * @return 获取对应ID的用户信息
	 * @throws RemoteException
	 */
	public UserPo GetUserByID(int ID)throws RemoteException;
	/**
	 * 
	 * @param vo
	 * @return 添加用户
	 */
	public int AddClient(UserVo vo)throws RemoteException;
	
	/**
	 * 
	 * @param vo
	 * @return 添加酒店工作人员(需要先添加酒店)
	 */
	public ResultMessage AddHotelMember(UserVo vo)throws RemoteException;
	
	/**
	 * 
	 * @param vo
	 * @return 添加营销人员
	 */
	public ResultMessage AddMarketer(UserVo vo)throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @return 获得历史订单记录
	 */
	public List<OrderPo> GetOrderHistory (int id)throws RemoteException	;

	/**
	 * 
	 * @param ID
	 * @return 获取酒店工作人员
	 * @throws RemoteException
	 */
	public UserPo GetHotelStaffByID(int ID) throws RemoteException;

	/**
	 * 登出
	 * @param id
	 * @throws RemoteException
	 */
	public void logout(int id) throws RemoteException;

	public ResultMessage ModifyHotelStaffPassword(UserVo vo)throws RemoteException;


}
