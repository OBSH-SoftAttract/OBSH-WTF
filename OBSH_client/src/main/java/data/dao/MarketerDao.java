package data.dao;

import po.UserPo;

public interface MarketerDao {
	
	public UserPo getMarketer(int marketerId);
	
	public boolean addMarketer(UserPo userPo);
	
	public boolean updateMarketer(UserPo userPo);
}
