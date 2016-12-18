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
import data.dao.MarketerDao;
import data.datahelper.DataFactory;
import data.datahelper.MarketerDataHelper;
import data.datahelper.impl.DataFactoryImpl;

public class MarketerDaoImpl implements MarketerDao{
	
	private Map<Integer,UserPo> map;
	
	private MarketerDataHelper marketerDataHelper;
	
	private DataFactory dataFactory;
	
	private static MarketerDaoImpl marketerDataServiceImpl;
	
	public static MarketerDaoImpl getInstance(){
		if(marketerDataServiceImpl == null){
			marketerDataServiceImpl = new MarketerDaoImpl();
		}
		return marketerDataServiceImpl;
	}
	
	public MarketerDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			marketerDataHelper = dataFactory.getMarketerDataHelper();
			map = marketerDataHelper.getMarketerData();
		}
	}
	
	public UserPo getMarketer(int marketerId) {
		return map.get(marketerId);
	}

	public boolean addMarketer(UserPo userPo) {
		int marketerID = userPo.getId();
		if(map.get(marketerID)==null) {
			marketerDataHelper.addMarketerData(userPo);
			return true;
		}
		return false;
	}
	
	public boolean updateMarketer(UserPo userPo) {
		int marketerID = userPo.getId();
		if(map.get(marketerID) != null){
			map.put(marketerID, userPo);
			marketerDataHelper.updateMarketerData(map);
			return true;
		}
		return false;

	}

}
