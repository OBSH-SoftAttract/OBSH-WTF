package data.dao;
/**
 * @author bxh
 */

import java.util.List;

import po.CreditPo;

public interface CreditDao {
	
	/**
	 * @param userId
	 * @return 该客户的所有信用记录
	 */
	public List<CreditPo> getCredit(int userId);
	
	/**
	 * @param creditPo
	 * @return  添加客户每次变更的信用信息
	 */
	public void addCredit(CreditPo creditPo);

	/**
	 * 
	 * @param creditPo
	 * @return  录入客户初始的信用信息
	 */
	public boolean setCredit(CreditPo creditPo);
	
}

