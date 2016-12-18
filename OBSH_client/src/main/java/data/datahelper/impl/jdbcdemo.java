package data.datahelper.impl;

import java.sql.Date;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import data.dao.CreditDao;
import data.dao.HotelDao;
import data.dao.MemberDao;
import data.dao.OrderDao;
import data.dao.UserDao;
import data.dao.impl.CreditDaoImpl;
import data.dao.impl.HotelDaoImpl;
import data.dao.impl.MemberDaoImpl;
import data.dao.impl.OrderDaoImpl;
import data.dao.impl.UserDaoImpl;
import po.CreditPo;
import po.HotelPo;
import po.MemberPo;
import po.OrderPo;
import po.UserPo;  

public class jdbcdemo {  

  static String sql = null;  
  static JDBCHelper db1 = null;  
  static ResultSet ret = null; 
  static int sta;
  static UserPo userPo = new UserPo(1001, "233333","15050526299", "小画家", false);
  /*static MemberPo memberPo = new MemberPo(151250004,Date.valueOf("1996-06-01"),null,2,"蔡赵辰+2600");
  static OrderPo orderPo = new OrderPo("110",0,Timestamp.valueOf("2016-11-29 15:45:00"),Timestamp.valueOf("2016-11-30 05:00:00"),
		  Timestamp.valueOf("2016-11-30 10:08:04"),151250004,500,1001,"商务房",false);*/
  //static CreditPo creditPo = new CreditPo(151250004, 2000, "-500+2016-11-29");
  public static void main(String[] args) {
	  UserDao userDao = UserDaoImpl.getInstance();
	 // userDao.addOthers(userPo);
	 // int test = userDao.addUser(userPo);
	 // System.out.println(test);
	  /*CreditDao creditDao = CreditDaoImpl.getInstance();
	  System.out.println(creditDao.getCredit(creditPo.getUserId()).getCreditInfo());
	  OrderDao orderDao = OrderDaoImpl.getInstance();
	  boolean b = orderDao.deleteOrderPo(orderPo.getOrderID());
	  List<OrderPo> list = orderDao.getOrderByUserID(151250004);
	  MemberDao memberDao = MemberDaoImpl.getInstance();
	  MemberPo m = memberDao.getMember(memberPo.getUserId());
	  boolean b = memberDao.updateMember(memberPo);
	  MemberPo m = memberDao.getMember(memberPo.getUserId());
	  System.out.println(m.getMemberInfo());
	  	int isVIP ;
		if(userPo.getifVIP())
			isVIP = 1;
		else 
			isVIP = 0;
		sql = "update user set name = '"+userPo.getUsername()+"', password = '"+userPo.getPassword()+"',contactinfo = '"+userPo.getPhone()+"',credit = "+userPo.getCredit()+", isVIP = "+isVIP+" where id = "+userPo.getID();//SQL语句 
		db1 = new JDBCHelper(sql);//创建DBHelper对象  
		try {
			sta = db1.pst.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		db1.close();//关闭连接*/
      /*sql = "select *from member";//SQL语句  
      db1 = new JDBCHelper(sql);//创建DBHelper对象  

      try {  
          ret = db1.pst.executeQuery();//执行语句，得到结果集  
          while (ret.next()) {  
        	  int id = ret.getInt(1);  
	            String birthday = ret.getString(2);  
	            String corpoateName = ret.getString(3);  
	            int rank = ret.getInt(4); 
	            String memberInfo = ret.getString(5);
              System.out.println(id + "\t" + birthday + "\t"+ corpoateName + "\t" +rank+ "\t" +memberInfo );  
          }//显示数据  
          ret.close();  
          db1.close();//关闭连接  
      } catch (SQLException e) {  
          e.printStackTrace();  
      }*/ 
  }  

}  

