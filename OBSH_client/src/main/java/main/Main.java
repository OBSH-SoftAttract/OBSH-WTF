package main;
import client_rmi.client_run;
import javafx.application.Application;
import javafx.stage.Stage;
import presentation.controller.UserViewControllerImpl;
import presentation.view.UserView;


public class Main extends Application{
	public static void main(String[]args){	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new client_run().linkToServer();
		UserViewControllerImpl user=new UserViewControllerImpl();
		UserView client=new UserView(user);
		primaryStage = client.Main();
	}
}	


