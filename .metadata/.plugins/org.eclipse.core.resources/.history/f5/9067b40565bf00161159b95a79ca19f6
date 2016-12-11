package data.datahelper.impl;
/**
 * @author bxh
 * 基本实现了user数据层 包括getdata setdata 和update
 * 问题：1.对于update是将所有数据进行更新还是单独更新某一项 即拆分方法
 * 		2.对于update使用map是否遍历所有数据
 * 		3.setdata中未使用map 仅提供一次set是否合理 这样又直接访问了数据库
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import data.datahelper.UserDataHelper;
import po.UserPo;

public class UserDataMysqlHelper implements UserDataHelper {
	static String sql = null;  
	static JDBCHelper db1 = null;  
	static ResultSet ret = null;  
	static int sta;  
	
	@Override
	public Map<Integer, UserPo> getUserData() {
		// TODO Auto-generated method stub
		  
	    sql = "select *from user";//SQL语句  
	    db1 = new JDBCHelper(sql);//创建DBHelper对象  
	    Map<Integer, UserPo> map = new HashMap<Integer, UserPo>();
	    
	    try {  
	    	ret = db1.pst.executeQuery();//执行语句，得到结果集  
	        while (ret.next()) {  
	        	int id = ret.getInt(1);  
	            String name = ret.getString(2);  
	            String password = ret.getString(3);  
	            String contactinfo = ret.getString(4);
	        	UserPo userPo=new UserPo(id, password, contactinfo, name);
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
	public void addUserData(UserPo userPo) {
		// TODO Auto-generated method stub

		sql = "insert into user value("+userPo.getID()+", '"+userPo.getUsername()+"' ,"+userPo.getPassword()+","+userPo.getPhone()+")";
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
	public void updateUserData(Map<Integer, UserPo> map) {
		// TODO Auto-generated method stub
		Iterator<Map.Entry<Integer, UserPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer, UserPo> entry = iterator.next();
			UserPo userPo = entry.getValue();

			sql = "update user set name = '"+userPo.getUsername()+
					"', password = '"+userPo.getPassword()+
					"',contactinfo = '"+userPo.getPhone()+
					"’ where id = "+userPo.getID();//SQL语句 
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

