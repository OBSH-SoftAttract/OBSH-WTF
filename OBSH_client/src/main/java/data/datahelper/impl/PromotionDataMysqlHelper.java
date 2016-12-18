package data.datahelper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import data.datahelper.PromotionDataHelper;
import po.PromotionPo;

public class PromotionDataMysqlHelper implements PromotionDataHelper {
	
	static String sql = null;  
	
	static JDBCHelper db1 = null;  
	
	static ResultSet ret = null; 
	
	static int sta;
	
	@Override
	public Map<Integer, PromotionPo> getPromotionData() {
		// TODO Auto-generated method stub

	    sql = "select *from promotion";//SQL语句  
	    db1 = new JDBCHelper(sql);//创建DBHelper对象  
	    Map<Integer, PromotionPo> map = new HashMap<Integer, PromotionPo>();
	    
	    try {  
	    	ret = db1.pst.executeQuery();//执行语句，得到结果集  
	        while (ret.next()) {  
	        	int id = ret.getInt(1);  
	            String itemName = ret.getString(2);  
	            Timestamp startTime = ret.getTimestamp(3);
	            Timestamp endTime = ret.getTimestamp(4); 
	            String promotionInfo = ret.getString(5);
	            double discount = ret.getDouble(6);
	            
	            PromotionPo promotionPo=new PromotionPo(id, itemName, startTime, endTime, promotionInfo, discount); 
	            map.put(id, promotionPo);
	        }//显示数据  
	        ret.close();  
	        db1.close();//关闭连接  
	     } catch (SQLException e) {  
	        e.printStackTrace();  
	     }  
		return map;
	}

	@Override
	public void updatePromotionData(Map<Integer, PromotionPo> map) {
		// TODO Auto-generated method stub
		
		Iterator<Entry<Integer, PromotionPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, PromotionPo> entry = iterator.next();
			PromotionPo promotionPo = entry.getValue();

			sql = "update promotion set itemName = '"+promotionPo.getItemName()+
					"',starttime = '"+promotionPo.getStartTime()+
					"',endtime = '"+promotionPo.getEndTime()+
					"',promotioninfo = '"+promotionPo.getPromotionInfo()+
					"’ where id = "+promotionPo.getID();//SQL语句 
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
	public void addPromotion(PromotionPo promotionPo) {
		// TODO Auto-generated method stub

		sql = "insert into promotion value("+promotionPo.getID()+
				",'"+promotionPo.getItemName()+
				"','"+promotionPo.getStartTime()+
				"','"+promotionPo.getEndTime()+
				"','"+promotionPo.getPromotionInfo()+
				"',"+promotionPo.getDiscount()+")";
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
	public void deletePromotionData(int id) {
		// TODO Auto-generated method stub
		
		sql = "delete from promotion where id = "+id;
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

