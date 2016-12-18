package data.datahelper;

import java.util.Map;

import po.HotelPo;

public interface HotelDataHelper {

	/**
	 * @return	从数据库中读取酒店信息
	 */
	public Map<Integer, HotelPo> getHotel();;

	/**
	 * @return	更新酒店信息
	 */
	public void updateHotel(Map<Integer, HotelPo> map);

	/**
	 * @return	录入新酒店
	 */
	public void addHotelPo(HotelPo hotelPo);

	/**
	 * 删除酒店
	 * @param hotelId
	 */
	public void deleteHotelPo(int hotelId);

	

}
