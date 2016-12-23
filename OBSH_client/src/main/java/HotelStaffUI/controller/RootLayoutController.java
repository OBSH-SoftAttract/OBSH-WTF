package HotelStaffUI.controller;

import java.io.IOException;
import java.rmi.RemoteException;

import HotelStaffUI.controller.RoomMessageController.Room;
import HotelStaffUI.view.MainLogin;
import HotelStaffUI.view.RootLayout;
import data.dao.impl.CreditDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.controller.UserViewControllerImpl;
import presentation.view.HandlePasswordNotEqual;
import presentation.view.MaintainPersonalInfo;
import presentation.view.UserViewControllerService;
import vo.HotelDeliverVo;
import vo.HotelroomVo;
import vo.UserVo;
/**
 * 酒店工作人员的工作页面背景
 * @author 毕潇晗
 *
 */
public class RootLayoutController {
	
	private UserViewControllerImpl controller = new UserViewControllerImpl();
	
	private UserVo hotelstaffVo;
	
	private RootLayout rootLayout;
	
	@FXML
	private Text hotelname;
	
	@FXML
	private Text staffname;
	
	@FXML
	private Button roommessage;
	
	@FXML
	private Button orderdeal;
	
	@FXML
	private Button addpromotion;
	
	@FXML
	private Button basic;
	
	@FXML
	private Pane changepane;
	
	@FXML
	private AnchorPane root;
	
	@FXML
	private BorderPane Broot;
	
	private HotelDeliverVo hoteldeliver;
	
	/*private static RootLayout creditDataServiceImpl;
	
	public static CreditDaoImpl getInstance(){
		if(creditDataServiceImpl == null){
			creditDataServiceImpl = new CreditDaoImpl();
		}
		return creditDataServiceImpl;
	}*/
	/**
	 * 初始化界面
	 * 传入酒店工作人员信息及酒店信息
	 * @throws RemoteException 
	 */
	@FXML
	private void initialize() throws RemoteException {
		int hotelID = hoteldeliver.getHotelStaffID();
		controller.setUserID(hotelID);
		hotelstaffVo = controller.GetPresentHotelStaffInfo();
		staffname.setText(hotelstaffVo.getUsername());
		String name = controller.getHotelNameByHotelID(hotelstaffVo.getID());
		hotelname.setText(name);
		roommessage.setId("room-h");
		/*try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("RoomMessage.fxml"));
			root = (AnchorPane) loader.load();
			Broot.setRight(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	/**
	 * 实现窗口关闭
	 */
	@FXML
	private void CloseStage(){
		rootLayout.getPrimaryStage().close();
	}

	/**
	 * 实现窗口最小化
	 */
	@FXML
	private void minimizeStage() {
		rootLayout.getPrimaryStage().setIconified(true); 
	}
	
	/**
	 * Sign out
	 * 这里有个数据库的退出？？？
	 * @throws Exception 
	 */
	@FXML
	private void BackToSignIn() throws Exception {
		rootLayout.getPrimaryStage().close();
		MainLogin m = new MainLogin();
		m.start(new Stage());
	}
	
	/**
	 * 打开客房管理界面
	 */
	@FXML
	private void jumpToRoomMessage() {
		roommessage.setId("room-h");
		orderdeal.setId("dealorder");
		addpromotion.setId("promotion");
		basic.setId("maintenance");
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("RoomMessage.fxml"));
			root = (AnchorPane) loader.load();
			Broot.setRight(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 打开订单处理界面
	 */
	@FXML
	private void jumpToDealOrder() {
		roommessage.setId("room");
		orderdeal.setId("dealorder-h");
		addpromotion.setId("promotion");
		basic.setId("maintenance");

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("DealOrder.fxml"));
			root = (AnchorPane) loader.load();
			Broot.setRight(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 打开促销策略界面
	 */
	@FXML
	private void jumpToPromotionItem() {
		roommessage.setId("room");
		orderdeal.setId("dealorder");
		addpromotion.setId("promotion-h");
		basic.setId("maintenance");
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("PromotionItem.fxml"));
			root = (AnchorPane) loader.load();
			Broot.setRight(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 打开维护酒店信息界面
	 */
	@FXML
	private void jumpToBasicMessage() {
		roommessage.setId("room");
		orderdeal.setId("dealorder");
		addpromotion.setId("promotion");
		basic.setId("maintenance-h");
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("BasicMessage.fxml"));
			root = (AnchorPane) loader.load();
			Broot.setRight(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void changePassword() {
		Stage stage = new Stage();
		Text oldpasswordt = new Text("旧密码：");
		PasswordField oldpasswordtf = new PasswordField();
		oldpasswordtf.setMaxWidth(120);
		
		Text newpasswordt = new Text("新密码：");
		PasswordField newpasswordtf = new PasswordField();
		newpasswordtf.setMaxWidth(120);
		
		Text confirmpassewordt = new Text("确认密码：");
		PasswordField confirmpassewordtf = new PasswordField();
		confirmpassewordtf.setMaxWidth(120);
		
		Button confirm = new Button("确定");		
		Button cancel = new Button("取消");		
		HBox hb = new HBox();
		hb.getChildren().addAll(confirm,cancel);
		hb.setSpacing(20);
		GridPane KeyInRoomgrid = new GridPane();
		KeyInRoomgrid.add(oldpasswordt, 0, 0);
		KeyInRoomgrid.add(oldpasswordtf, 1, 0);
		KeyInRoomgrid.add(newpasswordt, 0, 1);
		KeyInRoomgrid.add(newpasswordtf, 1, 1);
		KeyInRoomgrid.add(confirmpassewordt, 0, 2);
		KeyInRoomgrid.add(confirmpassewordtf, 1, 2);
		KeyInRoomgrid.add(hb, 1, 3);
		hb.setAlignment(Pos.CENTER_RIGHT);
		KeyInRoomgrid.setVgap(20);
		KeyInRoomgrid.setHgap(20);
		KeyInRoomgrid.setMaxSize(400, 400);
		KeyInRoomgrid.setMinSize(400, 400);
		KeyInRoomgrid.setAlignment(Pos.CENTER);
		Scene keyinroomscene = new Scene(KeyInRoomgrid);
		stage.setScene(keyinroomscene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
		keyinroomscene.getStylesheets().add("HotelStaffUI/view/keyinroom.css");

		confirm.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				
				int hotelID = hoteldeliver.getHotelStaffID();
				try {
					controller.setUserID(hotelID);
					hotelstaffVo = controller.GetPresentHotelStaffInfo();
					//controller更新密码
					String oldpassword = oldpasswordtf.getText();
					String newpassword = newpasswordtf.getText();
					String confrimpassword = confirmpassewordtf.getText();
					
					if(!oldpassword.equals(hotelstaffVo.getPassword())) {
						//密码错误
					}else {
						if(newpassword.equals(confrimpassword)){
							controller.ModifyHotelStaffPassword(hotelID, newpassword);
						} else {
							//两次不一致
						}
					
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			});
		
		cancel.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
			});
	}
	
	public void setRootLayout(RootLayout rootLayout) {
		// TODO Auto-generated method stub
		this.rootLayout = rootLayout;
	}

}
