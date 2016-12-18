package data.datahelper;

import java.util.Map;

import po.UserPo;

public interface MarketerDataHelper {
	/**
	 * @return	从数据库中读取用户数据
	 */
	public Map<Integer, UserPo> getMarketerData();
	
	/**
	 * 更新用户数据
	 * @param list
	 */
	public void updateMarketerData(Map<Integer, UserPo> map);

	/**
	 * 向数据库中写入新的用户数据
	 * @param 返回为新用户的id
	 */
	public void addMarketerData(UserPo userPo);
}
