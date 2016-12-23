package vo;

import java.util.List;

public class HotelDeliverVo {

	public static int hotelstaffID;
	
	public static HotelVo hotelVo;
	
	public static List<PromotionVo> promotions;
	
	public static void setPromotion(List<PromotionVo> promotion) {
		promotions = promotion;
	}
	
	public static List<PromotionVo> getPromotion() {
		return promotions;
	}
	
	public static void setHotelVo(HotelVo vo) {
		hotelVo = vo;
	}
	
	public static HotelVo getHotelVo() {
		return hotelVo;
	}
	
	public static void setHotelStaffID(int ID) {
		hotelstaffID = ID;
	}
	
	public static int getHotelStaffID() {
		return hotelstaffID;
	}
}
