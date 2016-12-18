package data.dao.impl;

import java.util.Map;

import data.dao.MemberDao;
import data.datahelper.DataFactory;
import data.datahelper.MemberDataHelper;
import data.datahelper.impl.DataFactoryImpl;
import po.MemberPo;

public class MemberDaoImpl implements MemberDao{
	
	private Map<Integer,MemberPo> map;
	
	private MemberDataHelper memberDataHelper;
	
	private DataFactory dataFactory;
	
	private static MemberDaoImpl memberDataServiceImpl;
	
	public static MemberDaoImpl getInstance(){
		if(memberDataServiceImpl == null){
			memberDataServiceImpl = new MemberDaoImpl();
		}
		return memberDataServiceImpl;
	}
	
	public MemberDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			memberDataHelper = dataFactory.getMemberDataHelper();
			map = memberDataHelper.getMember();
		}
	}
	
	@Override
	public MemberPo getMember(int memberId) {
		// TODO Auto-generated method stub
		return map.get(memberId);
	}

	@Override
	public boolean updateMember(MemberPo memberPo) {
		// TODO Auto-generated method stub
		int memberId = memberPo.getUserId();
		if(map.get(memberId) != null){
			map.put(memberId, memberPo);
			memberDataHelper.updateMemberData(map);
			return true;
		}
		return false;
	}

	@Override
	public boolean addMemberPo(MemberPo memberPo) {
		// TODO Auto-generated method stub
		int ID = memberPo.getUserId();
		if(map.get(ID)==null) {
			memberDataHelper.addMemberPo(memberPo);
			return true;
		}
		return false;
		
	}

	@Override
	public boolean isMember(int ID) {
		// TODO Auto-generated method stub
		if(map.get(ID)==null) 
			return false;
		else
			return true;
	}
}
