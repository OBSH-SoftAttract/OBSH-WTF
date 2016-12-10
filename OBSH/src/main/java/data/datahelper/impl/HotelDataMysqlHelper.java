package data.datahelper.impl;
/**
 * getHotel返回为map包含所有的hotel表内容
 * @author bxh
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import data.datahelper.HotelDataHelper;
import po.HotelPo;


public class HotelDataMysqlHelper implements HotelDataHelper {
	
	static String sql = null;  
	
	static JDBCHelper db1 = null;  
	
	static ResultSet ret = null; 
	
	static int sta;  
	
	@Override
	public  Map<Integer, HotelPo> getHotel() {
		// TODO Auto-generated method stub
		
	    sql = "select *from hotel";//SQL语句  
	    db1 = new JDBCHelper(sql);//创建DBHelper对象  
	    Map<Integer, HotelPo> map = new HashMap<Integer, HotelPo>();
	    
	    try {  
	    	ret = db1.pst.executeQuery();//执行语句，得到结果集  
	        while (ret.next()) {  
	        	int id = ret.getInt(1);  
	            String hotelName = ret.getString(2);  
	            int startLevel = ret.getInt(3);  
	            String location = ret.getString(4); 
	            String summary = ret.getString(5);
	            String[] s = summary.split(",");
	            List<String> listSummary = Arrays.asList(s);
	            String service = ret.getString(6);
	            String roomInfo = ret.getString(7);
	            double score = ret.getDouble(8);
	            String briefInfo = ret.getString(9);
	        	HotelPo hotelPo=new HotelPo(id, hotelName, startLevel, location, listSummary, service, roomInfo, score, briefInfo); 
	            map.put(id, hotelPo);
	        }//显示数据  
	        ret.close();  
	        db1.close();//关闭连接  
	     } catch (SQLException e) {  
	        e.printStackTrace();  
	     }  
		return map;
	}

	@Override
	public void updateHotel(Map<Integer, HotelPo>  map) {
		// TODO Auto-generated method stub
		
		Iterator<Map.Entry<Integer, HotelPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer, HotelPo> entry = iterator.next();
			HotelPo hotelPo = entry.getValue();

			sql = "update hotel set hotelname = '"+hotelPo.getName()+
					"',starlevel = "+hotelPo.getStar()+
					",location = '"+hotelPo.getLocation()+
					"',summary = '"+hotelPo.getSummary()+
					"',services = '"+hotelPo.getServices()+
					"',roominfo = '"+hotelPo.getRoomInfo()+
					"',score = "+hotelPo.getScore()+
					",briefInfo = '"+hotelPo.getBriefInfo()+
					"' where id = "+hotelPo.getHotelID();//SQL语句 
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
	public void addHotelPo(HotelPo hotelPo) {
		// TODO Auto-generated method stub

		sql = "insert into hotel value("+hotelPo.getHotelID()+
				",'"+hotelPo.getName()+
				"',"+hotelPo.getStar()+
				",'"+hotelPo.getLocation()+
				"','"+hotelPo.getSummary()+
				"','"+hotelPo.getServices()+
				"','"+hotelPo.getRoomInfo()+
				"',"+hotelPo.getScore()+
				",'"+hotelPo.getBriefInfo()+"')";
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
	public void deleteHotelPo(int hotelId) {
		// TODO Auto-generated method stub
		
		sql = "delete from hotel where id = "+hotelId;
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
