package presentation.view;

import java.rmi.RemoteException;
import java.util.List;

import ResultMessage.ResultMessage;
import po.HotelPo;

public interface UserViewControllerService {

	public ResultMessage successLogin(int id, String password)throws RemoteException;


}


