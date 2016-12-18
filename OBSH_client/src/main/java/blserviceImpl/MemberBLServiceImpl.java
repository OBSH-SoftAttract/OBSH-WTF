package blserviceImpl;

import java.rmi.RemoteException;
import java.sql.Date;
import blservice.CreditBLService;
import blservice.MemberBLService;
import data.dao.MemberDao;
import data.dao.impl.MemberDaoImpl;
import po.MemberPo;

public class MemberBLServiceImpl implements MemberBLService{

	private MemberDao memberdao;
	private CreditBLService creditbl;
	private final double Discount[]={1,0.975,0.95,0.925,0.88};
	public MemberBLServiceImpl(){
		memberdao = MemberDaoImpl.getInstance();
		creditbl=new CreditBLServiceImpl();
	}
	
	@Override
	public void createByPersonal(int id, Date birthday) throws RemoteException{	
		MemberPo memberPo = new MemberPo();
		memberPo.setUserId(id);
		memberPo.setBirthday(birthday);
		memberPo.setRank(getMemberRank(id));
		memberPo.setMemberInfo("Person");
		memberdao.addMemberPo(memberPo);
		
	}

	@Override
	public boolean createByBusiness(int id, String name) throws RemoteException {		
		MemberPo memberPo = new MemberPo();
		memberPo.setUserId(id);
		memberPo.setCorpoatename(name);
		memberPo.setRank(getMemberRank(id));
		memberPo.setMemberInfo("Company");
		return memberdao.addMemberPo(memberPo);
		
	}

	@Override
	public int getMemberRank(int id) throws RemoteException {
		int rank = 0;
		double credit = creditbl.getCredit(id).getCreditResult();
		if(credit>=1000){
			credit-=1000;
			while(credit>0&&rank<=4){
				credit-=3000;
				rank++;
			}
			return rank;
		}
		else		
		  return rank;
	}
	
	

	@Override
	public boolean isMember(int id) {
		return memberdao.isMember(id);
	}

	@Override
	public double getRankDiscount(int rank) {	
		return Discount[rank];
	}

	@Override
	public MemberPo getMemberInfo(int id) throws RemoteException {
		return memberdao.getMember(id);
	}

}
