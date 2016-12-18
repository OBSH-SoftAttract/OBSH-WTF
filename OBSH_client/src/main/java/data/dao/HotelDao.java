package data.dao;
import java.util.List;

/**
 * @author bxh
 */
import po.HotelPo;

public interface HotelDao {
	/**
	 * @param hotelId
	 * @return	获取酒店信息
	 */
	public HotelPo getHotel(int hotelId);
	
	/**
	 * @param hotelPo
	 * @return	更新酒店信息
	 */
	public boolean updateHotel(HotelPo hotelPo);
	
	/**
	 * @param hotelPo
	 * @return	添加酒店
	 */
	public boolean addHotelPo(HotelPo hotelPo);
	
	/**
	 * @param hotelPo
	 * @return	删除酒店
	 */
	public boolean deleteOrderPo(int hotelId);

	/**
	 * 
	 * @param location
	 * @return  按地址商圈搜索酒店
	 */
	public List<HotelPo> searchHotelByLocation(String location);

	/**
	 * 
	 * @return  获得所有酒店信息
	 */
	public List<HotelPo> getAllHotel();

	/**
	 * 
	 * @param location
	 * @return  按酒店名称搜索酒店
	 */
	public HotelPo searchHotelByName(String hotelName);

}
