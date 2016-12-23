package data.datahelper.impl;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ResultMessage.ResultMessage;
import blservice.HotelBLService;
import blservice.HotelroomBLService;
import blservice.UserBLService;
import blserviceImpl.HotelBLServiceImpl;
import blserviceImpl.HotelroomBLServiceImpl;
import blserviceImpl.UserBLServiceImpl;
import data.dao.CreditDao;
import data.dao.HotelDao;
import data.dao.HotelStaffDao;
import data.dao.HotelroomDao;
import data.dao.MemberDao;
import data.dao.OrderDao;
import data.dao.UserDao;
import data.dao.impl.CreditDaoImpl;
import data.dao.impl.HotelDaoImpl;
import data.dao.impl.HotelStaffDaoImpl;
import data.dao.impl.HotelroomDaoImpl;
import data.dao.impl.MemberDaoImpl;
import data.dao.impl.OrderDaoImpl;
import data.dao.impl.UserDaoImpl;
import data.datahelper.DataFactory;
import data.datahelper.HotelroomDataHelper;
import po.CreditPo;
import po.HotelPo;
import po.HotelroomPo;
import po.MemberPo;
import po.OrderPo;
import po.UserPo;
import presentation.controller.UserViewControllerImpl;
import presentation.view.UserViewControllerService;
import vo.HotelVo;
import vo.HotelroomVo;
import vo.PromotionVo;  

public class jdbcdemo{  

  /*static String sql = null;  
  static JDBCHelper db1 = null;  
  static ResultSet ret = null; 
  static int sta;
	private static DataFactory dataFactory = new DataFactoryImpl();;
  static UserPo userPo = new UserPo(1001, "233333","15050526299", "小画家", false);*/
  /*static MemberPo memberPo = new MemberPo(151250004,Date.valueOf("1996-06-01"),null,2,"蔡赵辰+2600");
  static OrderPo orderPo = new OrderPo("110",0,Timestamp.valueOf("2016-11-29 15:45:00"),Timestamp.valueOf("2016-11-30 05:00:00"),
		  Timestamp.valueOf("2016-11-30 10:08:04"),151250004,500,1001,"商务房",false);*/
  //static CreditPo creditPo = new CreditPo(151250004, 2000, "-500+2016-11-29");
  public static void main(String[] args) {
	 /* CreditDao credit = CreditDaoImpl.getInstance();
	  CreditPo po = new CreditPo(151250005, Timestamp.valueOf("2016-12-22 00:57:00"), 1000);
	  System.out.println(credit.setCredit(po));*/
	 /* HotelDao hotelDao = HotelDaoImpl.getInstance();
	  HotelPo po  = hotelDao.getHotel(1001);
	  System.out.println(po.getBriefInfo());*/
	  HotelBLService hotelbl = new HotelBLServiceImpl();
	  try {
		HotelPo po = hotelbl.SearchByID(1001);
		System.out.println(po.getBriefInfo().equals(""));
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  //UserDao userDao = UserDaoImpl.getInstance();
	  //HotelStaffDao hotelstaffDao = HotelStaffDaoImpl.getInstance();
	 // HotelDao hotelDao = HotelDaoImpl.getInstance();
	  //HotelroomDao hotelroom = HotelroomDaoImpl.getInstance();
	  //List<HotelroomPo> rooms = hotelroom.getHotelroomByHotelID(1001);
	  //System.out.println(rooms.size());
	 /* HotelroomBLService hotelbl = new HotelroomBLServiceImpl();
	  List<HotelroomPo> list;
	try {
		list = hotelbl.getHotelroomByID(1001);
		System.out.println(list.size());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	  
	// List<String> s = new ArrayList<String>() ;
	// s.add("very bad");
	 // HotelPo po = new HotelPo(1001,"英尊酒店",3,"栖霞区+仙林大学城",s,"提供早餐拒绝咸鱼","标准间+3+280",4.50,"本店不卖咸鱼你为啥是空啊",0);
	 /*UserViewControllerImpl controller = new UserViewControllerImpl();
	 try {
		controller.setUserID(1001);
		HotelroomVo vo = new HotelroomVo(302,1001,"标准间",290);
		boolean re = controller.addRoom(vo);
		System.out.println(re);
		List<HotelroomVo> rooms = controller.getHotelRoomByHotelID();
		System.out.print(rooms.size());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	/* HotelroomDataHelper hotelroomDataHelper = dataFactory.getHotelroomDataHelper();;
	 Map<String, HotelroomPo> map = hotelroomDataHelper.getHotelroomData();
	 System.out.println(map.get(1001).getRoomType());*/
	 /*Timestamp a = Timestamp.valueOf("2016-11-15 00:00:00");System.out.println(a);
	 Timestamp b = Timestamp.valueOf("2018-11-25 00:00:00");System.out.println(b);
	 PromotionVo vo = new PromotionVo(1001,0.7,"生日特惠折扣",a,b,"全体用户");
	 try {
		 controller.setUserID(1001);
		System.out.println(controller.AddPromotion(vo));
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// HotelBLService hoelbl = new HotelBLServiceImpl();
	// HotelVo vo;
	
		//controller.setUserID(1001);
		//vo = controller.getHotelInfoByID(1001);
		// System.out.println(vo.getLocation());

	  //HotelVo vo = controller.getHotelInfoByID(1001);
	// hotelDao.updateHotel(po);
//	ResultMessage re = null;
	//UserPo po = hotelstaffDao.getHotelStaff(1001);
//	re = hotelstaff.loginHotel(1001, "233333");
	//System.out.println(hotelDao.updateHotel(po));

	  //List<HotelPo> po =hotelDao.searchHotelByLocation("栖霞区+仙林大学城");
	  //System.out.println(po.get(0).getBriefInfo());
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

