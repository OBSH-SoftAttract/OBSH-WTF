package data.datahelper.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import data.datahelper.CreditDataHelper;
import po.CreditPo;

public class CreditDataMysqlHelper implements CreditDataHelper {
	
	static String sql = null;  
	
	static JDBCHelper db1 = null;  
	
	static ResultSet ret = null; 
	
	static int sta; 
	
	@Override
	public Map<Integer, CreditPo> getCreditData() {
		// TODO Auto-generated method stub
		
		sql = "select *from credit";//SQL语句  
		db1 = new JDBCHelper(sql);//创建DBHelper对象  
		Map<Integer, CreditPo> map = new HashMap<Integer, CreditPo>();
		    
		try {  
			ret = db1.pst.executeQuery();//执行语句，得到结果集  
		    while (ret.next()) {  
		    	int userId = ret.getInt(1);  
		    	String orderId = ret.getString(2);
		    	Timestamp time = ret.getTimestamp(3);
		    	int action = ret.getInt(4);
		        String creditChange = ret.getString(5); 
		        double creditResult = ret.getDouble(6);
		        
		        CreditPo creditPo=new CreditPo(userId, orderId, time, action, creditChange, creditResult); 
		        map.put(userId, creditPo);
		     }//显示数据  
		     ret.close();  
		     db1.close();//关闭连接  
		     } catch (SQLException e) {  
		    	 e.printStackTrace();  
			 }  
		return map;		
	}

	/*public void updateCreditData(Map<Integer, CreditPo> map) {
		// TODO Auto-generated method stub
		
		Iterator<Entry<Integer, CreditPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, CreditPo> entry = iterator.next();
			CreditPo creditPo = entry.getValue();

			sql = "update credit set time = '"+creditPo.getTime()+
					"',action = "+creditPo.getAction()+
					", credit_change = '"+creditPo.getCreditChange()+
					"',credit_result = "+creditPo.getCreditResult()+
					"  where userID = "+creditPo.getUserID();//SQL语句 
			db1 = new JDBCHelper(sql);//创建DBHelper对象  
			try {
				sta = db1.pst.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		db1.close();//关闭连接
	}*/
	
	@Override
	public void setCreditData(CreditPo creditPo) {
		// TODO Auto-generated method stub

		sql = "insert into credit value("+creditPo.getUserID()+
				",'"+creditPo.getTime()+
				"',"+creditPo.getCreditResult()+")";
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
	public void addCreditData(CreditPo creditPo) {
		// TODO Auto-generated method stub

		sql = "insert into credit value("+creditPo.getUserID()+
				",'"+creditPo.getOrderID()+
				"','"+creditPo.getTime()+
				"',"+creditPo.getAction()+
				"','"+creditPo.getCreditChange()+
				"',"+creditPo.getCreditResult()+")";
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


