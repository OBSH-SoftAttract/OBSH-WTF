package blserviceImpl;

import java.util.List;

import blservice.HotelroomBLService;
import data.dao.HotelroomDao;
import data.dao.impl.HotelroomDaoImpl;
import po.HotelroomPo;
import vo.HotelroomVo;

public class HotelroomBLServiceImpl implements HotelroomBLService{

	HotelroomDao hotelroomdao;
	
	PresentTimeGet nowtime;
	
	public HotelroomBLServiceImpl(){
		hotelroomdao=HotelroomDaoImpl.getInstance();
		nowtime=new PresentTimeGet();
	}
	
	@Override
	public boolean Addroom(HotelroomVo vo) {
		HotelroomPo po=new HotelroomPo(vo);
		po.setIfOccupied(false);		
		return hotelroomdao.addHotelroomPo(po);
	}
	
	@Override
	public void CheckIn(HotelroomVo vo){
		HotelroomPo po=new HotelroomPo(vo);
		po.setTimeCheckIn(nowtime.NowTime());
		hotelroomdao.updateHotelroom(po);
	}

	@Override
	public void CheckOut(HotelroomVo vo) {
		HotelroomPo po=new HotelroomPo(vo);
		po.setTimeCheckOut(nowtime.NowTime());
		po.setIfOccupied(false);
		hotelroomdao.updateHotelroom(po);
	}
	
	@Override
	public void CheckOut(int hotelID, int roomID) {
		HotelroomPo po = hotelroomdao.getHotelroom(hotelID, roomID);
		po.setAttemptedLeaveTime(null);
		po.setTimeCheckIn(null);
		po.setTimeCheckOut(null);
		po.setIfOccupied(false);
		hotelroomdao.updateHotelroom(po);
	}

	@Override
	public void ModifyPrice(HotelroomVo vo) {
		HotelroomPo po=new HotelroomPo(vo);
		po.setPrice(vo.getPrice());
		hotelroomdao.updateHotelroom(po);
	}
	
	@Override
	public List<HotelroomPo> getHotelroomByType(String roomType) {
		List<HotelroomPo>  hotelroom;
		hotelroom = hotelroomdao.getHotelroomByroomType(roomType);
		return hotelroom;
	}
	
	@Override
	public List<HotelroomPo> getHotelroomByID(int hotelID) {
		List<HotelroomPo>  hotelroom;
		hotelroom = hotelroomdao.getHotelroomByHotelID(hotelID);
		System.out.println(hotelroom.size());
		return hotelroom;
	}

}
