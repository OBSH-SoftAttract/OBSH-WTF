package blserviceImpl;

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
	public void ModifyPrice(HotelroomVo vo) {
		HotelroomPo po=new HotelroomPo(vo);
		po.setPrice(vo.getPrice());
		hotelroomdao.updateHotelroom(po);
	}

}
