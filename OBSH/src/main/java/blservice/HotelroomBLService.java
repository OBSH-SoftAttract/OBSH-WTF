package blservice;

import java.rmi.RemoteException;

import vo.HotelroomVo;

public interface HotelroomBLService {
	
	/**
	 * 
	 * @param room
	 * @return 添加客房
	 */
	public boolean Addroom(HotelroomVo vo)throws RemoteException;
	
	/**
	 * 
	 * @param vo
	 * 更新客户入住时间
	 */
	public void CheckIn(HotelroomVo vo);
	
	/**
	 * 
	 * @param vo
	 * 更新客户退房时间
	 */
	public void CheckOut(HotelroomVo vo);
	
	/**
	 * 
	 * @param vo
	 * 修改客房的价格
	 */
	public void ModifyPrice(HotelroomVo vo);
}
