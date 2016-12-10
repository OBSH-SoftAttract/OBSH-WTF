package data.dao.impl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 关于gethotelroom方法 由于唯一键值是roomid所以传入map的暂时是roomid 不知道能否有hotelid确定 之前确定roomid格式是hotelid+房间号
 *@author bxh
 */
import java.util.Map;
import java.util.Map.Entry;

import data.dao.HotelroomDao;
import data.datahelper.DataFactory;
import data.datahelper.HotelroomDataHelper;
import data.datahelper.impl.DataFactoryImpl;
import po.HotelroomPo;
import po.OrderPo;

public class HotelroomDaoImpl implements HotelroomDao {
	
	private Map<String,HotelroomPo> map;
	
	private HotelroomDataHelper hotelroomDataHelper;
	
	private DataFactory dataFactory;
	
	private static HotelroomDaoImpl hotelroomDataServiceImpl;
	
	public static HotelroomDaoImpl getInstance(){
		if(hotelroomDataServiceImpl == null){
			hotelroomDataServiceImpl = new HotelroomDaoImpl();
		}
		return hotelroomDataServiceImpl;
	}
	
	public HotelroomDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			hotelroomDataHelper = dataFactory.getHotelroomDataHelper();
			map = hotelroomDataHelper.getHotelroomData();
		}
	}
	
	@Override
	public HotelroomPo getHotelroom(int hotelID, int roomID) {
		// TODO Auto-generated method stub
		
		String hotelroomId = String.valueOf(hotelID)
				+String.valueOf(roomID);
		return map.get(hotelroomId);
	}
	
	@Override
	public List<HotelroomPo> getHotelroomByHotelID(int hotelID) {
		// TODO Auto-generated method stub
		
		List<HotelroomPo> rooms = new ArrayList<HotelroomPo>();
		Iterator<Entry<String, HotelroomPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, HotelroomPo> entry = iterator.next();
			HotelroomPo hotelroomPo = entry.getValue();
			
			if(hotelroomPo.getHotelID()==hotelID)
				rooms.add(hotelroomPo);
		}
		return rooms;
	}

	@Override
	public boolean updateHotelroom(HotelroomPo hotelroomPo) {
		// TODO Auto-generated method stub
		String hotelroomId = String.valueOf(hotelroomPo.getHotelID())
				+String.valueOf(hotelroomPo.getRoomID());
		if(map.get(hotelroomId) != null){
			map.put(hotelroomId, hotelroomPo);
			hotelroomDataHelper.updateHotelroomData(map);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addHotelroomPo(HotelroomPo hotelroomPo) {
		// TODO Auto-generated method stub
		String hotelroomId = String.valueOf(hotelroomPo.getHotelID())
				+String.valueOf(hotelroomPo.getRoomID());
		if(map.get(hotelroomId)==null){
			hotelroomDataHelper.addHotelroom(hotelroomPo);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteHotelroom(int hotelID, int roomID) {
		// TODO Auto-generated method stub
		String hotelroomId = String.valueOf(hotelID)
				+String.valueOf(roomID);
		if(map.get(hotelroomId)!=null) {
			hotelroomDataHelper.deleteHotelroom(roomID, hotelID);;
			return true;
		}
		return false;
	}

}

