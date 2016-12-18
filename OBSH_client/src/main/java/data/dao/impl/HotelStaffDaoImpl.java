package data.dao.impl;
/**
 * @author bxh
 * getInstance() 得到一个userDaoImpl
 * getUser 得到用户数据
 * setUser 新建用户数据
 * updateUser 更新用户数据
 */
import java.util.Map;

import po.UserPo;
import data.dao.HotelStaffDao;
import data.datahelper.DataFactory;
import data.datahelper.HotelStaffDataHelper;
import data.datahelper.impl.DataFactoryImpl;

public class HotelStaffDaoImpl implements HotelStaffDao{
	
	private Map<Integer,UserPo> map;
	
	private HotelStaffDataHelper hotelstaffDataHelper;
	
	private DataFactory dataFactory;
	
	private static HotelStaffDaoImpl hotelstaffDataServiceImpl;
	
	public static HotelStaffDaoImpl getInstance(){
		if(hotelstaffDataServiceImpl == null){
			hotelstaffDataServiceImpl = new HotelStaffDaoImpl();
		}
		return hotelstaffDataServiceImpl;
	}
	
	public HotelStaffDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			hotelstaffDataHelper = dataFactory.getHotelStaffDataHelper();
			map = hotelstaffDataHelper.getHotelStaffData();
		}
	}
	
	public UserPo getHotelStaff(int hotelstaffId) {
		return map.get(hotelstaffId);
	}

	public boolean addHotelStaff(UserPo userPo) {
		int hotelstaffID = userPo.getId();
		if(map.get(hotelstaffID)==null) {
			hotelstaffDataHelper.addHotelStaffData(userPo);
			return true;
		}
		return false;
	}
	
	public boolean updateHotelStaff(UserPo userPo) {
		int hotelstaffID = userPo.getId();
		if(map.get(hotelstaffID) != null){
			map.put(hotelstaffID, userPo);
			hotelstaffDataHelper.updateHotelStaffData(map);
			return true;
		}
		return false;

	}

}
