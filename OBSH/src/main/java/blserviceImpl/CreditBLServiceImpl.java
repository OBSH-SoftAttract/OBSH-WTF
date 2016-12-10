package blserviceImpl;


import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import blservice.CreditBLService;
import data.dao.CreditDao;
import data.dao.impl.CreditDaoImpl;
import po.CreditPo;

public class CreditBLServiceImpl implements CreditBLService{

	private CreditDao creditdao;
	private PresentTimeGet nowTime;
	public CreditBLServiceImpl(){
		creditdao=CreditDaoImpl.getInstance();
	}
	
	@Override
	public void addCredit(int id,double value) throws RemoteException{	
		double afterOperation=this.getCredit(id).getCreditResult()+value;
		String CreditChange="+"+String.valueOf(value);
		CreditPo po=new CreditPo(id,nowTime.NowTime(),3,CreditChange,afterOperation);
		creditdao.addCredit(po);		
	}

	@Override
	public void recoverCredit (int id,double price, int tag)throws RemoteException {
		double afterOperation=this.getCredit(id).getCreditResult();
		String CreditChange="+";
		if(tag==0){
			afterOperation+=price/2;
			CreditChange+=String.valueOf(price/2);
		}			
		else{
			afterOperation+=price;
			CreditChange+=String.valueOf(price);
		}
			
		CreditPo po=new CreditPo(id,nowTime.NowTime(),2,CreditChange,afterOperation);
		creditdao.addCredit(po);
	}

	@Override
	public CreditPo getCredit(int id)throws RemoteException {
		List<CreditPo> list=creditdao.getCredit(id);
		CreditPo latest = null;
		try{
			latest=list.get(0);
			for(int i=0;i<list.size();i++){
				if(list.get(i).getTime().getTime()>latest.getTime().getTime())
					latest=list.get(i);
			}
		}catch(NullPointerException e){
			e.getStackTrace();
		}
		return latest;
	}

	@Override
	public void CutCreditForCancel(int id, double price) throws RemoteException{
		double afterOperation=this.getCredit(id).getCreditResult()-price/2;
		String CreditChange="-"+String.valueOf(price/2);
		CreditPo po=new CreditPo(id,nowTime.NowTime(),2,CreditChange,afterOperation);
		creditdao.addCredit(po);	
	}

}
