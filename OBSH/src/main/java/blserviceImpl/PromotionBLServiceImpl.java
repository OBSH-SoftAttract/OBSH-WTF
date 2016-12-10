package blserviceImpl;

import java.rmi.RemoteException;
import java.util.List;

import ResultMessage.ResultMessage;
import blservice.PromotionBLService;
import data.dao.PromotionDao;
import data.dao.impl.PromotionDaoImpl;
import po.PromotionPo;
import vo.PromotionVo;

public class PromotionBLServiceImpl implements PromotionBLService{

	
	private PromotionDao promotiondao;
	
	public PromotionBLServiceImpl(){
		promotiondao=PromotionDaoImpl.getInstance();
	}
	@Override
	public int judge(int id) throws RemoteException{
		if(id<1000)return 1;
		return 0;
	}

	@Override
	public ResultMessage createNewItem(PromotionVo vo) throws RemoteException{
		if(promotiondao.addPromotionPo(new PromotionPo(vo)))return ResultMessage.UpdateSuccess;
		return ResultMessage.UpdateFail;
	}

	@Override
	public ResultMessage Del(PromotionVo vo) throws RemoteException{
		if(promotiondao.deletePromotionPo(vo.getItemName()))return ResultMessage.UpdateSuccess;
		return ResultMessage.UpdateFail;
	}

	@Override
	public List<PromotionPo> getPromotions() throws RemoteException{		
		return promotiondao.getLineItem();
	}

}
