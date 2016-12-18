package client_rmi;

import java.rmi.Remote;

import blservice.CreditBLService;
import blservice.HotelBLService;
import blservice.HotelroomBLService;
import blservice.MemberBLService;
import blservice.OrderBLService;
import blservice.PromotionBLService;
import blservice.UserBLService;

public class RemoteHelper_client {
	private static RemoteHelper_client remotehelper = new RemoteHelper_client();
	private Remote remote;

	public static RemoteHelper_client getInstance() {
		return remotehelper;
	}

	public void setRemote(Remote remote) {
		this.remote = remote;
	}

	public UserBLService getUserBLService() {
		return (UserBLService) remote;
	}

	public PromotionBLService getPromotionBLService() {
		return (PromotionBLService) remote;
	}

	public CreditBLService getCreditBLService() {
		return (CreditBLService) remote;
	}

	public HotelBLService getHotelBLService() {
		return (HotelBLService) remote;
	}

	public OrderBLService getOrderBLService() {
		return (OrderBLService) remote;
	}

	public MemberBLService getMemberBLService() {
		return (MemberBLService) remote;
	}

	public HotelroomBLService getHotelroomBLService() {
		return (HotelroomBLService) remote;
	}
}
