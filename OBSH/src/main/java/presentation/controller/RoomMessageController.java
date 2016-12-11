package presentation.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import presentation.view.HotelStaff;
import presentation.view.RoomMessage;

public class RoomMessageController {
	private RoomMessage roomMessage;
	
	
	@FXML
	private void CloseStage(){
		roomMessage.getPrimaryStage().close();
	}

	@FXML
	private void minimizeStage() {
		roomMessage.getPrimaryStage().setIconified(true); 
	}
	
	@FXML
	private void BackToSignIn() {
		roomMessage.getPrimaryStage().close();
		HotelStaff m = new HotelStaff();
		m.start(new Stage());
	}

	public void setHotelStaff(RoomMessage roomMessage) {
		// TODO Auto-generated method stub
		this.roomMessage = roomMessage;
		
	}

}
