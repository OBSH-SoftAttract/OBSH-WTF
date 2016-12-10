package blservice;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ResultMessage.ResultMessage;
import po.OrderPo;
import po.PromotionPo;
import vo.OrderVo;
import vo.PromotionVo;

public interface OrderBLService extends Remote{
	
	/**
	 * @param   ordervo
	 * @return	将订单状态改为已撤销
	 * @throws RemoteException 
	 */
	public ResultMessage Cancellation(OrderVo ordervo) throws RemoteException;
	
	/**
	 * @param   ordervo
	 * @return  订单是否超时
	 * @throws RemoteException 
	 */
	public ResultMessage IFpassTime (OrderVo ordervo) throws RemoteException;
	
	/**
	 * @param   ordervo
	 * @return  扣除用户的信用值
	 * @throws RemoteException 
	 */
	public void CancelKillCredit(OrderVo ordervo) throws RemoteException;
	
	/**
	 * 
	 * @param score
	 * @param comment
	 * @param ordervo
	 * @return 提交订单评语
	 * @throws RemoteException 
	 */
	public ResultMessage Assess(int score,String comment,int hotelID) throws RemoteException;
	
	/**
	 * 
	 * @param ordervo
	 * @return 添加订单
	 * @throws RemoteException 
	 */
	public ResultMessage Add(OrderVo ordervo) throws RemoteException; 
	
	/**
	 * 
	 * @param ordervo
	 * @return 检查信用值是否满足下单要求
	 * @throws RemoteException 
	 */
	public ResultMessage CreditCheck(OrderVo ordervo) throws RemoteException ;
	
	/**
	 * 
	 * @param userid
	 * @return 获得符合该用户的最优策略
	 * @throws RemoteException 
	 */
	public PromotionPo CalPromotion(int userid) throws RemoteException;
	
	/**
	 * 
	 * @param vo
	 * @return 获得订单价格
	 * @throws RemoteException 
	 */
	public double CalPrice(OrderVo vo) throws RemoteException;
	
	/**
	 * 
	 * @param vo
	 * @return 	处理订单	
	 * @throws RemoteException 
	 */
	public void Solve(OrderVo vo) throws RemoteException;
	
	/**
	 * 
	 * @param vo
	 * @return 处理用户申诉
	 * @throws RemoteException 
	 */
	public ResultMessage ComplainDeal(OrderVo vo) throws RemoteException;
	
	/**
	 * 
	 * @param orderid
	 * @return 获得对应ID的订单对象
	 * @throws RemoteException 
	 */
	public OrderPo ViewByOrderID (String orderid) throws RemoteException;
	
		/**
	 * 
	 * @param id
	 * @return 获得对应酒店的订单
	 * @throws RemoteException
	 */
	public List<OrderPo> ViewByHotel(int id) throws RemoteException;
	/**
	 * 
	 * @param id
	 * @return 获得对应客户的订单
	 * @throws RemoteException 
	 */
	
	public List<OrderPo> ViewByCustom(int id) throws RemoteException;
	
	/**
	 * 
	 * @param date
	 * @return 获得对应日期的订单列表
	 * @throws RemoteException 
	 */
	public List<OrderVo> ViewByDaily(List<OrderVo> list) throws RemoteException;
	
	/**
	 * 
	 * @param state
	 * @return 获得
	 * @throws RemoteException 
	 */
	public List<OrderPo> ViewByState(int state,int id) throws RemoteException;
	
	/**
	 * 
	 * @param orderlist
	 * @return 获得按时间排序
	 * @throws RemoteException 
	 */
	public List<OrderVo> TimeSort (List<OrderVo> orderlist) throws RemoteException;
	
	/**
	 * 
	 * @return 获得一个新的订单号
	 * @throws RemoteException 
	 */
	public String CreateID(int hotel) throws RemoteException;
}
