package presentation.view;

import javafx.application.Application;
import javafx.stage.Stage;
import presentation.controller.UserViewControllerImpl;

public class MainHHH extends Application{
	public static void main(String[]args){	
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		UserViewControllerImpl user=new UserViewControllerImpl();
		new UserView(user);
	}
}
