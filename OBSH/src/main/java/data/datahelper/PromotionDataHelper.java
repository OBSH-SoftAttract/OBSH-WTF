package data.datahelper;

import java.util.Map;

import po.PromotionPo;

public interface PromotionDataHelper {
	
	/**
	 * @return	从数据库中读取促销策略
	 */
	public Map<Integer,PromotionPo> getPromotionData();
	
	/**
	 * 更新促销策略信息
	 * @param list	
	 */
	public void updatePromotionData(Map<Integer,PromotionPo> map);

	/**
	 * 删除促销策略
	 * @param id
	 */
	public void deletePromotionData(int id);

	/**
	 * 添加促销策略
	 * @param promotionPo
	 */
	public void addPromotion(PromotionPo promotionPo);

}
