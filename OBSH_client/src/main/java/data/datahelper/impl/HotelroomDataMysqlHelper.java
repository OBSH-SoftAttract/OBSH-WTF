package data.datahelper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import data.datahelper.HotelroomDataHelper;
import po.HotelroomPo;

public class HotelroomDataMysqlHelper implements HotelroomDataHelper {

	static String sql = null;  
	
	static JDBCHelper db1 = null;  
	
	static ResultSet ret = null; 
	
	static int sta;  
	
	@Override
	public Map<String, HotelroomPo> getHotelroomData() {
		// TODO Auto-generated method stub
		
		 sql = "select *from hotelroom";//SQL语句  
		 db1 = new JDBCHelper(sql);//创建DBHelper对象  
		 Map<String, HotelroomPo> map = new HashMap<String, HotelroomPo>();
		    
		 try {  
			 ret = db1.pst.executeQuery();//执行语句，得到结果集  
		     while (ret.next()) {  
		     int roomID = ret.getInt(1);
		     Timestamp timeCheckIn = ret.getTimestamp(2);
		     int hotelID = ret.getInt(3);
		     Timestamp attemptedLeaveTime = ret.getTimestamp(4);
		     Timestamp timeCheckOut = ret.getTimestamp(5);
		     String roomType = ret.getString(6);
		     double price = ret.getDouble(7);
		     boolean isEmpty = ret.getBoolean(8);
		     String hotelroomID = String.valueOf(hotelID)+String.valueOf(roomID); 
		     
		     HotelroomPo hotelroomPo=new HotelroomPo(roomID, timeCheckIn, hotelID, attemptedLeaveTime, timeCheckOut, roomType, price, isEmpty); 
		     map.put(hotelroomID, hotelroomPo);
		 }//显示数据  
		     ret.close();  
		     db1.close();//关闭连接  
		 } catch (SQLException e) {  
		     e.printStackTrace();  
		     }  
		 return map;
	}

	@Override
	public void updateHotelroomData(Map<String, HotelroomPo> map) {
		// TODO Auto-generated method stub
		
		Iterator<Map.Entry<String, HotelroomPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, HotelroomPo> entry = iterator.next();
			HotelroomPo hotelroomPo = entry.getValue();

			int Empty;
			if(hotelroomPo.isIfOccupied())
				Empty = 1;
			else
				Empty = 0;
			
			sql = "update hotelroom set + TimeCheckIn = '"+hotelroomPo.getTimeCheckIn()+
					"',AttemptedLeaveTime = '"+hotelroomPo.getAttemptedLeaveTime()+
					"',TimeCheckOut = '"+hotelroomPo.getTimeCheckOut()+
					"',roomType = '"+hotelroomPo.getRoomType()+
					"',price = "+hotelroomPo.getPrice()+
					", isEmpty = "+Empty+
					",where roomID = "+hotelroomPo.getRoomID()+
					"and hotelID = "+hotelroomPo.getHotelID();//SQL语句 
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
	public void addHotelroom(HotelroomPo hotelroomPo) {
		// TODO Auto-generated method stub

		sql = "insert into hotelroom value("+hotelroomPo.getRoomID()+
				",'"+hotelroomPo.getTimeCheckIn()+
				"',"+hotelroomPo.getHotelID()+
				",'"+hotelroomPo.getAttemptedLeaveTime()+
				"','"+hotelroomPo.getTimeCheckOut()+
				"','"+hotelroomPo.getRoomType()+
				"',"+hotelroomPo.getPrice()+
				","+hotelroomPo.isIfOccupied()+")";
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
	public void deleteHotelroom(int hotelID, int roomID) {
		// TODO Auto-generated method stub
		
		sql = "delete from hotelroom where roomid = "+roomID +"and hotelID = "+hotelID;
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