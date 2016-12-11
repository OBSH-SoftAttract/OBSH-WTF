package presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.view.BasicMessage;
import presentation.view.HotelStaff;
/**
 * BasicMessage在javafx下的控制器
 * @author bxh
 *
 */
public class BasicMessageController {
	

	
	private BasicMessage basicMessage;
	
	@FXML
	private void CloseStage(){
		basicMessage.getPrimaryStage().close();
	}

	@FXML
	private void minimizeStage() {
		basicMessage.getPrimaryStage().setIconified(true); 
	}
	
	@FXML
	private void BackToSignIn() {
		basicMessage.getPrimaryStage().close();
		HotelStaff m = new HotelStaff();
		m.start(new Stage());
	}
	
	public void setBasicMessage(BasicMessage basicMessage) {
		// TODO Auto-generated method stub
		this.basicMessage = basicMessage;
		
	}


}
