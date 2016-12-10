package blservice;

import java.rmi.RemoteException;

import po.CreditPo;

public interface CreditBLService {
	
	/**
	 * 
	 * @param id
	 * @return 获得用户信用值
	 */
	public CreditPo getCredit (int id) throws RemoteException;
	
	/**
	 * 
	 * @param value
	 * @return 添加用户的信用值
	 */
	public void addCredit(int id,double value)throws RemoteException;
	
	
	/**
	 * 
	 * @param tag 0代表一半，1代表全部
	 * @param price
	 * @return 处理申诉恢复信用值
	 */
	public void recoverCredit (int id,double price, int tag)throws RemoteException;
	
	/**
	 * 
	 * @param id
	 * @param price
	 * @return 处理未满6小时撤销扣除的信用值
	 */
	public void CutCreditForCancel(int id,double price)throws RemoteException;

}
