package data.datahelper;

import java.util.Map;

import po.MemberPo;

public interface MemberDataHelper {
	
	/**
	 * 获取会员信息
	 * @return
	 */
	public Map<Integer, MemberPo> getMember();
	
	/**
	 * 增加会员
	 * @param memberPo
	 */
	public void addMemberPo(MemberPo memberPo);

	/**
	 * 更新会员信息
	 * @param map
	 * @return
	 */
	public boolean updateMemberData(Map<Integer, MemberPo> map);

	
}
