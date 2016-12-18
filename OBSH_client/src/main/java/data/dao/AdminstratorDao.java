package data.dao;

import po.UserPo;

public interface AdminstratorDao {
	
	public UserPo getAdminstrator(int AdminstratorId);
	
	public boolean addAdminstrator(UserPo userPo);
	
	public boolean updateAdminstrator(UserPo userPo);
	

}
