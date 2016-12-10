package data.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import data.dao.PromotionDao;
import data.datahelper.DataFactory;
import data.datahelper.PromotionDataHelper;
import data.datahelper.impl.DataFactoryImpl;
import po.PromotionPo;

public class PromotionDaoImpl implements PromotionDao {
	
	private Map<Integer,PromotionPo> map;
	
	private PromotionDataHelper promotionDataHelper;
	
	private DataFactory dataFactory;
	
	private static PromotionDaoImpl promotionDataServiceImpl;
	
	public static PromotionDaoImpl getInstance(){
		if(promotionDataServiceImpl == null){
			promotionDataServiceImpl = new PromotionDaoImpl();
		}
		return promotionDataServiceImpl;
	}
	
	public PromotionDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			promotionDataHelper = dataFactory.getPromotionDataHelper();
			map = promotionDataHelper.getPromotionData();
		}
	}
	
	@Override
	public PromotionPo getPromotion(int id) {
		// TODO Auto-generated method stub
		return map.get(id);
	}

	@Override
	public List<PromotionPo> getLineItem() {
		// TODO Auto-generated method stub
		
		List<PromotionPo> lineItem = new ArrayList<PromotionPo>();
		Iterator<Entry<Integer, PromotionPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, PromotionPo> entry = iterator.next();
			PromotionPo promotionPo = entry.getValue();
			lineItem.add(promotionPo);
		}
		return lineItem;
	}

	@Override
	public boolean updatePromotion(PromotionPo promotionPo) {
		// TODO Auto-generated method stub
		int id = promotionPo.getID();
		if(map.get(id) != null){
			map.put(id, promotionPo);
			promotionDataHelper.updatePromotionData(map);;;
			return true;
		}
		return false;
	}

	@Override
	public boolean addPromotionPo(PromotionPo promotionPo) {
		// TODO Auto-generated method stub
		
		int promotionId = promotionPo.getID();
		if(map.get(promotionId)==null){
			promotionDataHelper.addPromotion(promotionPo);;
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePromotionPo(String itemName) {
		// TODO Auto-generated method stub
		int id = 0;
		Iterator<Entry<Integer, PromotionPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, PromotionPo> entry = iterator.next();
			PromotionPo promotionPo = entry.getValue();
			if(promotionPo.getItemName().equals(itemName)) 
				id = promotionPo.getID();
		}
		if(map.get(id)!=null){
			promotionDataHelper.deletePromotionData(id);
			return true;
		}
		return false;
			
	}

}

