package vo;

/**
 * id                 酒店或者网站营销人员id
 * itemName           策略名称
 * startTime          策略适用开始时间
 * endTime            策略适用结束时间
 * promotionInfo      策略详细信息
 * discount           折扣 
 * @author bxh
 */
import po.PromotionPo;
import po.UserPo;

import java.sql.Timestamp;

import po.HotelPo;

public class PromotionVo {
	
	private int id;
	
	private String itemName;
	
	private Timestamp startTime;
	
	private Timestamp endTime;
	
	private String promotionInfo;
	
	private double discount;
		
	public PromotionVo(int id, double discount, String itemName, Timestamp startTime, Timestamp endTime, String promotionInfo){
		this.id = id;
		this.discount =discount;
		this.itemName = itemName;
		this.promotionInfo = promotionInfo;
		this.startTime = startTime;
		this.endTime  = endTime;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Timestamp getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	public Timestamp getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	public String getPromotionInfo() {
		return promotionInfo;
	}
	
	public void setPromotionInfo(String promotionInfo) {
		this.promotionInfo = promotionInfo;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	


}