package presentation.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ResultMessage.ResultMessage;
import blservice.HotelBLService;
import blservice.OrderBLService;
import blservice.UserBLService;
import client_rmi.RemoteHelper_client;
import javafx.stage.Stage;
import po.HotelPo;
import presentation.view.UserView;
import presentation.view.UserViewControllerService;
import vo.UserVo;
import vo.HotelVo;

public class UserViewControllerImpl implements UserViewControllerService{
	private OrderBLService orderService;	
	private UserBLService userService;
	private HotelBLService hotelService;
	
	public UserViewControllerImpl(){
		orderService = RemoteHelper_client.getInstance().getOrderBLService();
		userService =  RemoteHelper_client.getInstance().getUserBLService();
		hotelService=  RemoteHelper_client.getInstance().getHotelBLService();
	}

	@Override
	public ResultMessage successLogin(String userId, String password) throws RemoteException{
		return userService.login(userId, password);
	}

	public List<HotelVo> Views(String address, String commercialDistrict) throws RemoteException {
		List<HotelVo> list=new ArrayList<HotelVo>();
		List<HotelPo> hotelList= hotelService.Views(address, commercialDistrict);
		for(HotelPo hotelpo :hotelList){
			int hotelID=hotelpo.getHotelID();
			String name=hotelpo.getName();
			int star=hotelpo.getStar();
			String briefInro=hotelpo.getBriefInfo();
			String location=hotelpo.getLocation();
			List<String> summary=hotelpo.getSummary();
			String services=hotelpo.getServices();
			String roomInfo=hotelpo.getRoomInfo();
			double score=hotelpo.getScore();
			HotelVo hotelvo=new HotelVo(hotelID,name,star,location,summary,services,roomInfo,score,briefInro);
			
			list.add(hotelvo);
		}
		return list;
	}

}