package presentation.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.controller.HotelStaffController;

public class HotelStaff extends Application {
	private Stage primaryStage;
	private double xOffset = 0;
	private double yOffset = 0;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("客户登录界面.fxml"));
			BorderPane frame = (BorderPane) loader.load();
			enableDragging(frame);
			
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
	 * 实现窗口可拖拽
	 * @param root
	 */
	private void enableDragging(BorderPane root) {

		root.setOnMousePressed(new EventHandler<MouseEvent>() {  
            @Override  
            public void handle(MouseEvent event) {  
                xOffset = event.getSceneX();  
                yOffset = event.getSceneY();  
            }  
        });  
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {  
            @Override  
            public void handle(MouseEvent event) {  
                primaryStage.setX(event.getScreenX() - xOffset);  
                primaryStage.setY(event.getScreenY() - yOffset);  
            }  
        }); 
	}

	/**
	 * 得到mainStage
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * 酒店工作人员的main运行入口
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}