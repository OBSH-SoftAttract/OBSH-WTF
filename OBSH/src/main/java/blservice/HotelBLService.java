package blservice;
import java.rmi.RemoteException;
import java.util.List;

import ResultMessage.ResultMessage;
import po.HotelPo;
import po.HotelroomPo;
import vo.HotelVo;
public interface HotelBLService {
	/**
	 * 
	 * @param hotelNo
	 * @return 添加酒店
	 */
	public ResultMessage Addhotel (HotelVo hotelvo)throws RemoteException;

	/**
	 * 
	 * @param address
	 * @param commercialDistrict
	 * @return 按地址和商圈
	 */
	public List<HotelPo> Views(String address, String commercialDistrict)throws RemoteException;
	
	/**
	 * 
	 * @param hotelid
	 * @return 详细信息：地址+简介+设施服务+客房类型+价格
	 */
	public String ViewHotelDetail(int hotelid)throws RemoteException;
		/**
	 * 
	 * @param star
	 * @return 按星级搜索
	 */
	public List<HotelPo> FilterByStar(int star,List<HotelPo> list)throws RemoteException;
	
	/**
	 * 
	 * @param list
	 * @return 按星级排序（由高到低）
	 */
	public List<HotelPo> SortByStar(List<HotelPo> list)throws RemoteException;
	
	/**
	 * 
	 * @param min
	 * @param max
	 * @return 按原始价格区间搜索
	 */
	public List<HotelPo> FilterByPrice(double min, double max,List<HotelPo> list)throws RemoteException;
	
	/**
	 * 
	 * @param list
	 * @return 按价格排序（由低到高）
	 */
	public List<HotelPo> SortByPrice(List<HotelPo> list)throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @return 按酒店ID搜索
	 * @throws RemoteException
	 */
	public HotelPo SearchByID(int id) throws RemoteException;
	
	/**
	 * 
	 * @param s
	 * @param hotelID
	 * @return 添加评价
	 * @throws RemoteException
	 */
	public ResultMessage AddAssess(int score,String s,int hotelID)throws RemoteException;
	/**
	 * 
	 * @param min
	 * @param max
	 * @return 按评价区间搜索
	 */
	public List<HotelPo> FilterByScore(double min, double max,List<HotelPo> list)throws RemoteException;
	
	/**
	 * 
	 * @param list
	 * @return 按评分排序（由高到低）
	 */
	public List<HotelPo> SortByScore(List<HotelPo> list)throws RemoteException;
	
	/**
	 * 
	 * @param type
	 * @return 按房间类型搜索
	 */
	public List<HotelPo> FilterByRoomType(String type,List<HotelPo> list)throws RemoteException;
	
	/**
	 * 
	 * @param hotel
	 * @return 直接搜索酒店名称
	 */
	public HotelPo SearchByName(String hotel)throws RemoteException;

	/**
	 * 
	 * @param list
	 * @return 
	 */
	public List<HotelroomPo> SortByTime(List<HotelroomPo> list)throws RemoteException;
}
