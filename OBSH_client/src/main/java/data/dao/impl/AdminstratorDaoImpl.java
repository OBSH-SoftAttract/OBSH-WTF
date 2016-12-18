package data.dao.impl;
/**
 * @author bxh
 * getInstance() 得到一个userDaoImpl
 * getUser 得到用户数据
 * setUser 新建用户数据
 * updateUser 更新用户数据
 */
import java.util.Map;

import po.UserPo;
import data.dao.AdminstratorDao;
import data.datahelper.DataFactory;
import data.datahelper.AdminstratorDataHelper;
import data.datahelper.impl.DataFactoryImpl;

public class AdminstratorDaoImpl implements AdminstratorDao{
	
	private Map<Integer,UserPo> map;
	
	private AdminstratorDataHelper adminstratorDataHelper;
	
	private DataFactory dataFactory;
	
	private static AdminstratorDaoImpl adminstratorDataServiceImpl;
	
	public static AdminstratorDaoImpl getInstance(){
		if(adminstratorDataServiceImpl == null){
			adminstratorDataServiceImpl = new AdminstratorDaoImpl();
		}
		return adminstratorDataServiceImpl;
	}
	
	public AdminstratorDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			adminstratorDataHelper = dataFactory.getAdminstratorDataHelper();
			map = adminstratorDataHelper.getAdminstratorData();
		}
	}
	
	public UserPo getAdminstrator(int AdminstratorId) {
		return map.get(AdminstratorId);
	}

	public boolean addAdminstrator(UserPo userPo) {
		int AdminstratorID = userPo.getId();
		if(map.get(AdminstratorID)==null) {
			adminstratorDataHelper.addAdminstratorData(userPo);
			return true;
		}
		return false;
	}
	
	public boolean updateAdminstrator(UserPo userPo) {
		int AdminstratorID = userPo.getId();
		if(map.get(AdminstratorID) != null){
			map.put(AdminstratorID, userPo);
			adminstratorDataHelper.updateAdminstratorData(map);
			return true;
		}
		return false;

	}

}