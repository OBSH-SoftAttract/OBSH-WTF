package presentation.controller;


import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import presentation.view.HotelStaff;
/**
 * Hotelstaff在javafx下的控制器
 * @author bxh
 *
 */
public class HotelStaffController {
	
	@FXML
	private Text welcome;
	
	@FXML
	private Label l1;
	
	@FXML
	private Label l2;
	
	@FXML
	private TextField name;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Button minimize;
	
	@FXML
	private Button close;
	
	@FXML
	private Button sign;

	@FXML
	private Label failOutput;
	
	private HotelStaff main;
	
	/**
	 * 验证登录
	 * @throws RemoteException 
	 */
	@FXML
	private void confirmAction() throws RemoteException{
		String userID = name.getText();
		String enterpassword = password.getText();
		
		if(userID.equals("")||enterpassword.equals("")) {
			failOutput.setText("用户名密码不能为空!");
		} 

	}
	
	@FXML
	private void CloseStage(){
		main.getPrimaryStage().close();
	}

	@FXML
	private void minimizeStage() {
		 main.getPrimaryStage().setIconified(true); 
	}
	
	public void setLoginFrame(HotelStaff main) {
		// TODO Auto-generated method stub
		this.main = main;
		
	}


}
