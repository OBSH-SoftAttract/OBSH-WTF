package data.datahelper;

import java.util.Map;

import po.OrderPo;

public interface OrderDataHelper {
	
	/**
	 * @return	从数据库中读取订单数据
	 */
	public Map<String, OrderPo> getOrderData();
	
	/**
	 * 向数据库中写入订单数据
	 * @param list	
	 */
	public void updateOrderData(Map<String, OrderPo> map);

	/**
	 * 添加订单
	 * @param orderPo
	 */
	public void addOrderData(OrderPo orderPo);

	/**
	 * 删除订单
	 * @param orderId
	 */
	public void deleteOrderData(String orderId);

}
