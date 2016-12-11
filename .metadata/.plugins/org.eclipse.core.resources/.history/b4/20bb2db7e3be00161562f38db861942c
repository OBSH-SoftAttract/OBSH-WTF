package presentation.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.controller.HotelStaffController;

public class HotelStaff extends Application {
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("客户登录界面.fxml"));
			BorderPane frame = (BorderPane) loader.load();

			Scene scene = new Scene(frame);
			primaryStage.setScene(scene);
			primaryStage.show();
			HotelStaffController controller = loader.getController();
			controller.setLoginFrame(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}