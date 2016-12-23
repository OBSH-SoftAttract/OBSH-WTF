package blservice;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ResultMessage.ResultMessage;
import po.PromotionPo;
import vo.PromotionVo;
public interface PromotionBLService extends Remote{
	/**
	 * 
	 * @param id
	 * @return 确定营销策略表所属为酒店还是网站,返回0是酒店，1是网站
	 */
	public int judge (int id)throws RemoteException;
	
	
	/**
	 * 
	 * @param vo
	 * @return 增加营销策略
	 */
	public ResultMessage createNewItem(PromotionVo vo)throws RemoteException;
	
	/**
	 * 
	 * @param vo
	 * @return 系统更新营销策略列表
	 */
	public ResultMessage Del(PromotionVo vo)throws RemoteException;
	
	
	/**
	 * 
	 * @return 返回营销策略信息
	 */
	public List<PromotionPo> getPromotions()throws RemoteException;
}
