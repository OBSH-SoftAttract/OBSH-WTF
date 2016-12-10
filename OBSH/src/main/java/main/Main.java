package main;
import javafx.application.Application;
import javafx.stage.Stage;
import presentation.controller.UserViewControllerImpl;
import presentation.view.UserView;
import presentation.view.UserViewControllerService;

public class Main extends Application{
	public static void main(String[]args){	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		int userid = 151250046;
		UserViewControllerService controller = new UserViewControllerImpl(userid);
		UserView view = new UserView(controller);
		primaryStage = view.Main();
		controller.setView(view);
	}
}	


