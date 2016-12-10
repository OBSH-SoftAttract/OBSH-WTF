package data.datahelper.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import data.datahelper.MemberDataHelper;
import po.MemberPo;

public class MemberDataMysqlHelper implements MemberDataHelper {
	static String sql = null;  
	static JDBCHelper db1 = null;  
	static ResultSet ret = null;  
	static int sta; 
	
	@Override
	public Map<Integer,MemberPo> getMember() {
		// TODO Auto-generated method stub
		sql = "select *from member";//SQL语句  
	    db1 = new JDBCHelper(sql);//创建DBHelper对象  
	    Map<Integer, MemberPo> map = new HashMap<Integer, MemberPo>();
	    
	    try {  
	    	ret = db1.pst.executeQuery();//执行语句，得到结果集  
	        while (ret.next()) {  
	        	int id = ret.getInt(1);  
	        	int rank = ret.getInt(2); 
	            Date birthday = ret.getDate(3);
	            String corpoateName = ret.getString(4);  
	            String memberInfo = ret.getString(5);

	        	MemberPo memberPo=new MemberPo(id, birthday, corpoateName, rank, memberInfo);
				map.put(id, memberPo);
	            
	        }//显示数据  
	        ret.close();  
	        db1.close();//关闭连接  
	     } catch (SQLException e) {  
	        e.printStackTrace();  
	     }  
		return map;
	}

	@Override
	public void addMemberPo(MemberPo memberPo) {
		// TODO Auto-generated method stub
		sql = "insert into member value("+memberPo.getUserId()+","+memberPo.getRank()+",'"+memberPo.getBirthday()+"',"+memberPo.getCorpoatename()+",'"+memberPo.getMemberInfo()+"')";
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
	public boolean updateMemberData(Map<Integer, MemberPo> map) {
		// TODO Auto-generated method stub
		Iterator<Entry<Integer, MemberPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, MemberPo> entry = iterator.next();
			MemberPo memberPo = entry.getValue();

			sql = "update member set birthday = '"+memberPo.getBirthday()+"',corpoateName = '"+memberPo.getCorpoatename()+"',rank = "+memberPo.getRank()+",memberInfo = '"+memberPo.getMemberInfo()+"' where id = "+memberPo.getUserId();//SQL语句 
			db1 = new JDBCHelper(sql);//创建DBHelper对象  
			try {
				sta = db1.pst.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		db1.close();//关闭连接
		return false;
	}

}

