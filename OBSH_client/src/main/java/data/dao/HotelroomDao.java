package data.dao;

import java.util.List;

import po.HotelroomPo;

public interface HotelroomDao {
	/**
	 * @param hotelId
	 * @return	获取酒店某一房间信息
	 */
	public HotelroomPo getHotelroom(int hotelID, int roomID);
	
	/**
	 * 
	 * @param hotelID
	 * @return  获取某一酒店的全部房间信息
	 */
	public List<HotelroomPo> getHotelroomByHotelID(int hotelID);
	
	/**
	 * @param hotelroomPo
	 * @return	更新酒店房间信息
	 */
	public boolean updateHotelroom(HotelroomPo hotelroomPo);
	
	/**
	 * @param hotelroomPo
	 * @return	添加酒店房间
	 */
	public boolean addHotelroomPo(HotelroomPo hotelroomPo);
	
	/**
	 * @param hotelroomPo
	 * @return	删除酒店房间
	 */
	public boolean deleteHotelroom(int hotelID, int roomID);

}
