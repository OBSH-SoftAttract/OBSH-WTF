package data.datahelper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import data.datahelper.OrderDataHelper;
import po.OrderPo;

public class OrderDataMysqlHelper implements OrderDataHelper {
	
	static String sql = null;  
	
	static JDBCHelper db1 = null;  
	
	static ResultSet ret = null; 
	
	static int sta; 
	
	@Override
	public Map<String, OrderPo> getOrderData() {
		// TODO Auto-generated method stub
		
	    sql = "select *from orderbl";//SQL语句  
	    db1 = new JDBCHelper(sql);//创建DBHelper对象  
	    Map<String, OrderPo> map = new HashMap<String, OrderPo>();
	    
	    try {  
	    	ret = db1.pst.executeQuery();//执行语句，得到结果集  
	        while (ret.next()) {  
	        	String orderId = ret.getString(1);  
	            int orderstate = ret.getInt(2);  
	            Timestamp startTime = ret.getTimestamp(3) ;
	            Timestamp endTime = ret.getTimestamp(4); 
	            Timestamp lastTime = ret.getTimestamp(5); 
	            int userId = ret.getInt(6);
	            double price = ret.getDouble(7);
	            int hotelId = ret.getInt(8);
	            String roomInfo = ret.getString(9);
	            
	        	OrderPo orderPo=new OrderPo(orderId, orderstate, startTime, endTime, lastTime, userId, price, hotelId, roomInfo); 
	            map.put(orderId, orderPo);
	        }//显示数据  
	        ret.close();  
	        db1.close();//关闭连接  
	     } catch (SQLException e) {  
	        e.printStackTrace();  
	     }  
		return map;
	}
	
	@Override
	public void updateOrderData(Map<String, OrderPo> map) {
		// TODO Auto-generated method stub
		
		Iterator<Entry<String, OrderPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, OrderPo> entry = iterator.next();
			OrderPo orderPo = entry.getValue();

			sql = "update orderbl set orderstate = "+orderPo.getOrderState()+
					",starttime = '"+orderPo.getStartTime()+
					"',endtime = '"+orderPo.getEndTime()+
					"',lasttime = '"+orderPo.getlastTime()+
					"',userid = "+orderPo.getUserID()+
					",price = "+orderPo.getPrice()+
					",hotelid = "+orderPo.getHotelID()+
					",roominfo = '"+orderPo.getroomInfo()+
					"' where orderid = '"+orderPo.getOrderID()+"'";//SQL语句 
			db1 = new JDBCHelper(sql);//创建DBHelper对象  
			try {
				sta = db1.pst.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		db1.close();//关闭连接
	}
	
	@Override
	public void addOrderData(OrderPo orderPo) {
		// TODO Auto-generated method stub

		sql = "insert into orderbl value('"+orderPo.getOrderID()+"',"+orderPo.getOrderState()+",'"+orderPo.getStartTime()+"','"+orderPo.getEndTime()+"','"+orderPo.getlastTime()+"',"+orderPo.getUserID()+","+orderPo.getPrice()+","+orderPo.getHotelID()+",'"+orderPo.getroomInfo()+"')";
		db1 = new JDBCHelper(sql);//创建DBHelper对象  
		try {
			sta = db1.pst.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db1.close();//关闭连接
	}

	@Override
	public void deleteOrderData(String orderId) {
		// TODO Auto-generated method stub
		
		sql = "delete from orderbl where orderid = "+orderId;
		db1 = new JDBCHelper(sql);//创建DBHelper对象  
		try {
			sta = db1.pst.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db1.close();//关闭连接
	}

}
