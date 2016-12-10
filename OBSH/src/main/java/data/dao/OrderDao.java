package data.dao;
/**
 * @author bxh
 */
import java.util.List;

import po.OrderPo;

public interface OrderDao {
	
	/**
	 * @param string
	 * @return	获取订单信息
	 */
	public OrderPo getOrder(String string);
	
	/**
	 * @return	获取酒店订单列表
	 */
	public List<OrderPo> getOrders(int hotelId);
	
	/**
	 * @param orderPo
	 * @return	更新订单信息
	 */
	public boolean updateOrder(OrderPo orderPo);
	
	/**
	 * @param orderPo
	 * @return	添加订单
	 */
	public boolean addOrderPo(OrderPo orderPo);
	
	/**
	 * @param orderId
	 * @return	删除订单
	 */
	public boolean deleteOrderPo(String orderId);

	/**
	 * 
	 * @param userID
	 * @return 获取客户订单信息
	 */
	public List<OrderPo> getOrderByUserID(int userID);

}



