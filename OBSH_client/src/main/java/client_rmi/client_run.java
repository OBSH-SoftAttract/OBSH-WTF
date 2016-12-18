package client_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class client_run {
	RemoteHelper_client remotehelper;
	
	public void linkToServer() {
		try {
			remotehelper=RemoteHelper_client.getInstance();
			remotehelper.setRemote(Naming.lookup("rmi://localhost:4450/DataRemoteObject"));
			System.out.println("the client has linked");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
