package data.datahelper;

import java.util.Map;

import po.UserPo;

public interface HotelStaffDataHelper {
	
	/**
	 * @return	从数据库中读取用户数据
	 */
	public Map<Integer, UserPo> getHotelStaffData();
	
	/**
	 * 更新用户数据
	 * @param list
	 */
	public void updateHotelStaffData(Map<Integer, UserPo> map);

	/**
	 * 向数据库中写入新的用户数据
	 * @param 返回为新用户的id
	 */
	public void addHotelStaffData(UserPo userPo);

}
