package data.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import data.dao.OrderDao;
import data.datahelper.DataFactory;
import data.datahelper.OrderDataHelper;
import data.datahelper.impl.DataFactoryImpl;
import po.OrderPo;

public class OrderDaoImpl implements OrderDao {
	
	private Map<String,OrderPo> map;
	
	private OrderDataHelper orderDataHelper;
	
	private DataFactory dataFactory;
	
	private static OrderDaoImpl orderDataServiceImpl;
	
	public static OrderDaoImpl getInstance(){
		if(orderDataServiceImpl == null){
			orderDataServiceImpl = new OrderDaoImpl();
		}
		return orderDataServiceImpl;
	}
	
	public OrderDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			orderDataHelper = dataFactory.getOrderDataHelper();
			map = orderDataHelper.getOrderData();
		}
	}

	@Override
	public OrderPo getOrder(String orderId) {
		// TODO Auto-generated method stub
		return map.get(orderId);
	}
	
	@Override
	public List<OrderPo> getOrders(int hotelId) { 
		// TODO Auto-generated method stub
		
		List<OrderPo> orders = new ArrayList<OrderPo>();
		Iterator<Entry<String, OrderPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, OrderPo> entry = iterator.next();
			OrderPo orderPo = entry.getValue();
			
			if(orderPo.getHotelID()==hotelId)
				orders.add(orderPo);
		}
		return orders;
	}
	
	@Override
	public List<OrderPo> getOrderByUserID(int userID) { 
		// TODO Auto-generated method stub
		
		List<OrderPo> orders = new ArrayList<OrderPo>();
		Iterator<Entry<String, OrderPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, OrderPo> entry = iterator.next();
			OrderPo orderPo = entry.getValue();
			
			if(orderPo.getUserID()==userID)
				orders.add(orderPo);
		}
		return orders;
	}
	
	@Override
	public boolean updateOrder(OrderPo orderPo) {
		// TODO Auto-generated method stub
		
		String orderId = orderPo.getOrderID();
		if(map.get(orderId) != null){
			map.put(orderId, orderPo);
			orderDataHelper.updateOrderData(map);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addOrderPo(OrderPo orderPo) {
		// TODO Auto-generated method stub
		
		String orderId = orderPo.getOrderID();
		if(map.get(orderId)==null){
			orderDataHelper.addOrderData(orderPo);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteOrderPo(String orderId) {
		// TODO Auto-generated method stub
		
		if(map.get(orderId)!=null) {
			orderDataHelper.deleteOrderData(orderId);
			return true;
		}
		return false;
	}
}

