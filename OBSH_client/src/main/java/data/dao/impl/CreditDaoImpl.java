package data.dao.impl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import data.dao.CreditDao;
import data.datahelper.CreditDataHelper;
import data.datahelper.DataFactory;
import data.datahelper.impl.DataFactoryImpl;
import po.CreditPo;

public class CreditDaoImpl implements CreditDao {
	
	private Map<Integer,CreditPo> map;
	
	private CreditDataHelper creditDataHelper;
	
	private DataFactory dataFactory;
	
	private static CreditDaoImpl creditDataServiceImpl;
	
	public static CreditDaoImpl getInstance(){
		if(creditDataServiceImpl == null){
			creditDataServiceImpl = new CreditDaoImpl();
		}
		return creditDataServiceImpl;
	}
	
	public CreditDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			creditDataHelper = dataFactory.getCreditDataHelper();
			map = creditDataHelper.getCreditData();
		}
	}
	
	@Override
	public List<CreditPo> getCredit(int userID) {
		// TODO Auto-generated method stub
		
		List<CreditPo> creditAll = new ArrayList<CreditPo>();
		Iterator<Entry<Integer, CreditPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, CreditPo> entry = iterator.next();
			CreditPo creditPo = entry.getValue();
			
			if(creditPo.getUserID()==userID)
				creditAll.add(creditPo);
		}
		return creditAll;
	}
	
	@Override
	public void addCredit(CreditPo creditPo) {
		// TODO Auto-generated method stub
		
		creditDataHelper.addCreditData(creditPo);
	}
	
	@Override
	public boolean setCredit(CreditPo creditPo) {
		// TODO Auto-generated method stub
		
		int userId = creditPo.getUserID();
		if(map.get(userId)==null) {
			creditDataHelper.setCreditData(creditPo);
			return true;
		}
		return false;
	}
}
