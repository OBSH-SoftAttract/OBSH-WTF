package blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.List;

import ResultMessage.ResultMessage;
import po.HotelPo;
import vo.HotelVo;

public interface HotelBLService extends Remote{

	/**
	 * 添加酒店 使用对象是网站管理人员
	 * @param hotelvo
	 * @return
	 */
	public ResultMessage Addhotel(HotelVo hotelvo)throws RemoteException;

	/**
	 * 根据地址商圈获取酒店
	 * @param address
	 * @param commercialDistrict
	 * @return
	 */
	public List<HotelPo> Views(String address, String commercialDistrict)throws RemoteException;

	/**
	 * 获得酒店房间的最低价格
	 * @param hotelid
	 * @return
	 */
	public double getHotelMinPrice(int hotelid)throws RemoteException;

	/**
	 * 通过酒店名称获取酒店信息
	 * @param hotel
	 * @return
	 */
	public HotelPo SearchByName(String hotel)throws RemoteException;

	/**
	 * 通过酒店ID获取酒店信息
	 * @param id
	 * @return
	 */
	public HotelPo SearchByID(int id)throws RemoteException;

	/**
	 * 根据房间类型过滤搜索
	 * @param roomType
	 * @param hotellist
	 * @return
	 */
	public List<HotelVo> FilterByType(String roomType, List<HotelVo> hotellist)throws RemoteException;

	/**
	 * 根据酒店名称搜索
	 * @param name
	 * @param list
	 * @return
	 */
	public List<HotelVo> FilterByName(String name, List<HotelVo> list)throws RemoteException;

	/**
	 * 搜索指定星级的酒店
	 * @param hotelStar
	 * @param hotellist
	 * @return
	 */
	public List<HotelVo> FilterByStar(int hotelStar, List<HotelVo> hotellist)throws RemoteException;

	/**
	 * 广泛地排序 啥玩意
	 * @param list
	 * @param comList
	 * @param Sort
	 */
	public void comprehensiveSort(List<HotelVo> list, boolean Sort[]) throws RemoteException;

	/**
	 * 添加计算评分
	 * @param score
	 * @param comment
	 * @param hotelID
	 * @return
	 */
	public ResultMessage AddAssess(int score, String comment, int hotelID)throws RemoteException;

	/**
	 * 获取指定房间类型的价格
	 * @param type
	 * @param hotelname
	 * @return
	 */
	public double getHotelRoomPriceByType(String type, String hotelname)throws RemoteException;

	/**
	 * 添加酒店房间 胡渣渣版
	 * @param hotelid
	 * @param num
	 * @param type
	 * @param price
	 */
	public void AddRoom(int hotelid, int num, String type, double price)throws RemoteException;

	/**
	 * 修改指定房间类型的价格
	 * @param hotelID
	 * @param type
	 * @param price
	 */
	public void ModifyPrice(int hotelID, String type, double price)throws RemoteException;

	/**
	 * 根据订单修改房间数目 我还是认为通过hotelroom在数据库get更好
	 * @param hotelID
	 * @param num
	 * @param type
	 */
	public void ModifyRoomNumByOrder(int hotelID, int num, String type)throws RemoteException;

	/**
	 * 最后一个才是修改酒店信息
	 * @param vo
	 * @return
	 */
	public ResultMessage ModifyHotelMessage(HotelVo vo)throws RemoteException;

}
