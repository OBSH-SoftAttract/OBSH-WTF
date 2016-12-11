package data.datahelper;

import java.util.Map;

import po.UserPo;

public interface UserDataHelper {
	
	/**
	 * @return	从数据库中读取用户数据
	 */
	public Map<String, UserPo> getUserData();
	
	/**
	 * 更新用户数据
	 * @param list
	 */
	public void updateUserData(Map<String, UserPo> map);

	/**
	 * 向数据库中写入新的用户数据
	 * @param list
	 */
	public void addUserData(UserPo userPo);

}

