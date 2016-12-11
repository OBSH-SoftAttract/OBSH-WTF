package presentation.view;

import java.rmi.RemoteException;

import ResultMessage.ResultMessage;

public interface UserViewControllerService {

	public ResultMessage successLogin(String id, String password)throws RemoteException;


}


