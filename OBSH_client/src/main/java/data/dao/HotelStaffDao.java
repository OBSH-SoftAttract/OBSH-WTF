package data.dao;

import po.UserPo;

public interface HotelStaffDao {

	public UserPo getHotelStaff(int hotelstaffId);
	
	public boolean addHotelStaff(UserPo userPo);
	
	public boolean updateHotelStaff(UserPo userPo);
}
