package po;

import java.sql.Timestamp;

import vo.PromotionVo;

/**
 * id                 自动判断id是属于酒店还是网站营销人员
 * itemName           策略名称
 * startTime          策略适用开始时间
 * endTime            策略适用结束时间
 * promotionInfo      策略详细信息
 * discount            打折的折扣 如0.88
 * @author bxh
 */
public class PromotionPo {

	private int id;
	
	private String itemName;
	
	private Timestamp startTime;
	
	private Timestamp endTime;
	
	private String promotionInfo;
	
	private double discount;
	
	public PromotionPo(){
		super();
	}
	
	public PromotionPo(int id, String itemName, Timestamp startTime, Timestamp endTime, String promotionInfo, double discount) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.promotionInfo = promotionInfo;
		this.discount = discount;
	}
	
	public PromotionPo(PromotionVo vo) {
		this.id = vo.getId();
		this.itemName = vo.getItemName();
		this.startTime = vo.getStartTime();
		this.endTime = vo.getEndTime();
		this.promotionInfo =vo.getPromotionInfo();
		this.discount=vo.getDiscount();
	}

	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
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
