package data.datahelper;

import java.util.Map;

import po.HotelroomPo;

public interface HotelroomDataHelper {
	
	/**
	 * @return	从数据库中读取酒店房间信息
	 */
	public Map<String,HotelroomPo> getHotelroomData();
	
	/**
	 * 向数据库中写入酒店房间信息
	 * @param list	
	 */
	public void updateHotelroomData(Map<String,HotelroomPo> map);

	/**
	 * 录入酒店房间
	 * @param hotelroomPo
	 */
	public void addHotelroom(HotelroomPo hotelroomPo);

	/**
	 * 删除酒店房间
	 * @param hotelroomId
	 */
	public void deleteHotelroom(int hotelID, int roomID);


}
