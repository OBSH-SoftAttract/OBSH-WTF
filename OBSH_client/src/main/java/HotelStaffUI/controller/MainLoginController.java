package HotelStaffUI.controller;

import java.rmi.RemoteException;
import java.util.List;

import HotelStaffUI.view.MainLogin;
import HotelStaffUI.view.RootLayout;
import ResultMessage.ResultMessage;
import blservice.UserBLService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.controller.UserViewControllerImpl;
import presentation.view.UserViewControllerService;
import vo.HotelDeliverVo;
import vo.HotelVo;
import vo.PromotionVo;

public class MainLoginController {
	
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
	
	private MainLogin login;
	
	private HotelDeliverVo hoteldeliver;
	
	private UserViewControllerImpl controller = new UserViewControllerImpl();
	
	/**
	 * 验证登录
	 * @throws RemoteException 
	 */
	@FXML
	private void confirmAction(){
		String hotelstaffID = name.getText();
		String enterpassword = password.getText();
		
		if(hotelstaffID.equals("")||enterpassword.equals("")) {
			failOutput.setText("用户名密码不能为空!");
		}else {
			ResultMessage re = null;
			try {
				re = controller.LoginHotel(hotelstaffID, enterpassword);


			switch (re) {
			case FormatWrong:
				failOutput.setText("请输入正确的用户名！");
				break;
			case NotExist:
				failOutput.setText("用户名不存在，请重新输入!");
				break;
			case HasExist:
				failOutput.setText("该用户已登录！");
				break;
			case WrongPassword:
				failOutput.setText("密码错误，请重新输入");
				break;
			default:
				controller.setUserID(Integer.parseInt(hotelstaffID));
					/**
					 * 这里缺少一个把loginin改成true的方法
					 */
				hoteldeliver.setHotelStaffID(Integer.parseInt(hotelstaffID));
				HotelVo vo = controller.getHotelInfoByID();
				List<PromotionVo> promotions = controller.getPromotions(Integer.parseInt(hotelstaffID));
				hoteldeliver.setHotelVo(vo);
				hoteldeliver.setPromotion(promotions);
				login.getPrimaryStage().close();
				RootLayout r = new RootLayout();
				r.start(new Stage());
			}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void CloseStage(){
		login.getPrimaryStage().close();
	}

	@FXML
	private void minimizeStage() {
		login.getPrimaryStage().setIconified(true); 
	}
	
	public void setLoginFrame(MainLogin login) {
		// TODO Auto-generated method stub
		this.login = login;
		
	}
	
	public UserViewControllerImpl getController() {
		return this.controller;
	}


}
