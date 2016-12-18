package data.dao;
/**
 * @author bxh
 */
import java.util.List;

import po.PromotionPo;

public interface PromotionDao {
	
	/**
	 * @param id //这里的id指的是酒店或网站营销人员的id+促销策略序号
	 * @return	获取促销策略信息
	 */
	public PromotionPo getPromotion(int id);
	
	/** 
	 * @return  获取促销策略列表
	 */
	public List<PromotionPo> getLineItem();
	
	/**
	 * @param PromotionPo
	 * @return	更新促销策略信息
	 */
	public boolean updatePromotion(PromotionPo promotionPo);
	
	/**
	 * @param PromtionPo
	 * @return	添加促销策略
	 */
	public boolean addPromotionPo(PromotionPo promotionPo);
	
	/**
	 * @param itemName
	 * @return	删除促销策略
	 */
	public boolean deletePromotionPo(String itemName);

}

