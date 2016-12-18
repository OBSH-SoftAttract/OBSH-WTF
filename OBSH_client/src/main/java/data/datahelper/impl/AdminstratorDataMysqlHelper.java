package data.datahelper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import data.datahelper.AdminstratorDataHelper;
import po.UserPo;

public class AdminstratorDataMysqlHelper implements AdminstratorDataHelper {
	static String sql = null;  
	static JDBCHelper db1 = null;  
	static ResultSet ret = null;  
	static int sta;  
	
	@Override
	public Map<Integer, UserPo> getAdminstratorData() {
		// TODO Auto-generated method stub
		  
	    sql = "select *from Adminstrator";//SQL语句  
	    db1 = new JDBCHelper(sql);//创建DBHelper对象  
	    Map<Integer, UserPo> map = new HashMap<Integer, UserPo>();
	    
	    try {  
	    	ret = db1.pst.executeQuery();//执行语句，得到结果集  
	        while (ret.next()) {  
	        	int id = ret.getInt(1);  
	            String name = ret.getString(2);  
	            String password = ret.getString(3);  
	            String contactinfo = ret.getString(4);
	            boolean login = ret.getBoolean(5);
	        	UserPo userPo=new UserPo(id, password, contactinfo, name, login);
				map.put(id, userPo);
	            
	        }//显示数据  
	        ret.close();  
	        db1.close();//关闭连接  
	     } catch (SQLException e) {  
	        e.printStackTrace();  
	     }  
		return map;
	}

	@Override
	public void addAdminstratorData(UserPo userPo) {
		// TODO Auto-generated method stub

		sql = "insert into Adminstrator value("+userPo.getId()+", '"+userPo.getUsername()+"' ,"+userPo.getPassword()+","+userPo.getPhone()+")";
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
	public void updateAdminstratorData(Map<Integer, UserPo> map) {
		// TODO Auto-generated method stub
		Iterator<Map.Entry<Integer, UserPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer, UserPo> entry = iterator.next();
			UserPo userPo = entry.getValue();

			sql = "update Adminstrator set name = '"+userPo.getUsername()+
					"', password = '"+userPo.getPassword()+
					"',contactinfo = '"+userPo.getPhone()+
					"’ where id = "+userPo.getId();//SQL语句 
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

}

