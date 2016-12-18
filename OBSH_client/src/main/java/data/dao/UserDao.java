package data.dao;
/**
 * @author bxh
 */
import po.UserPo;

public interface UserDao {
	
	/**
	 * @param userId
	 * @return	获取用户信息
	 */
	public UserPo getUser(int userId);
	
	/**
	 * @param userPo
	 * @return	新建用户
	 */
	public int addUser(UserPo userPo) ;
	
	/**
	 * @param userPo
	 * @return	更新用户信息
	 */
	public boolean updateUser(UserPo userPo);

}

