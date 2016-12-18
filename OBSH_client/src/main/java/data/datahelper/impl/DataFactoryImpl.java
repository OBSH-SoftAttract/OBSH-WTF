package data.datahelper.impl;

import data.datahelper.AdminstratorDataHelper;
import data.datahelper.CreditDataHelper;
import data.datahelper.DataFactory;
import data.datahelper.HotelDataHelper;
import data.datahelper.HotelStaffDataHelper;
import data.datahelper.HotelroomDataHelper;
import data.datahelper.MarketerDataHelper;
import data.datahelper.MemberDataHelper;
import data.datahelper.OrderDataHelper;
import data.datahelper.PromotionDataHelper;
import data.datahelper.UserDataHelper;


public class DataFactoryImpl implements DataFactory{
	
	@Override
	public OrderDataHelper getOrderDataHelper() {
		// TODO Auto-generated method stub
		OrderDataHelper OrderDao = new OrderDataMysqlHelper();
		return OrderDao;
	}
	
	@Override
	public UserDataHelper getUserDataHelper() {
		// TODO Auto-generated method stub
		UserDataHelper UserDao = new UserDataMysqlHelper();
		return UserDao;
	}

	@Override
	public CreditDataHelper getCreditDataHelper() {
		// TODO Auto-generated method stub
		CreditDataHelper CreditDao = new CreditDataMysqlHelper();
		return CreditDao;
	}

	@Override
	public PromotionDataHelper getPromotionDataHelper() {
		// TODO Auto-generated method stub
		PromotionDataHelper PromotionDao = new PromotionDataMysqlHelper();
		return PromotionDao;
	}

	@Override
	public HotelroomDataHelper getHotelroomDataHelper() {
		// TODO Auto-generated method stub
		HotelroomDataHelper HotelroomDao = new HotelroomDataMysqlHelper();
		return HotelroomDao;
	}

	@Override
	public HotelDataHelper getHotelDataHelper() {
		// TODO Auto-generated method stub
		HotelDataHelper HotelDao = new HotelDataMysqlHelper();
		return HotelDao;
	}

	@Override
	public MemberDataHelper getMemberDataHelper() {
		// TODO Auto-generated method stub
		MemberDataHelper MemberDao = new MemberDataMysqlHelper();
		return MemberDao;
	}

	@Override
	public HotelStaffDataHelper getHotelStaffDataHelper() {
		// TODO Auto-generated method stub
		HotelStaffDataHelper HotelStaffDao = new HotelStaffDataMysqlHelper();
		return HotelStaffDao;
	}

	@Override
	public MarketerDataHelper getMarketerDataHelper() {
		// TODO Auto-generated method stub
		MarketerDataHelper MarketerDao = new MarketerDataMysqlHelper();
		return MarketerDao;
	}

	@Override
	public AdminstratorDataHelper getAdminstratorDataHelper() {
		// TODO Auto-generated method stub
		AdminstratorDataHelper AdminstratorDao = new AdminstratorDataMysqlHelper();
		return AdminstratorDao;
	}

	

}

