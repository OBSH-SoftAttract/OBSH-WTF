package server_runner;

import server_rmi.RemoteHelper;

public class Runner {
	
	public Runner() {
		new RemoteHelper();
	}
	
	public static void main(String[] args) {
		new Runner();
	}
}
