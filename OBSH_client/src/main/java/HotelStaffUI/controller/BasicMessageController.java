package HotelStaffUI.controller;

import java.awt.Panel;
import java.rmi.RemoteException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.controller.UserViewControllerImpl;
import vo.HotelDeliverVo;
import vo.HotelVo;

public class BasicMessageController {
	
	private UserViewControllerImpl controller = new UserViewControllerImpl();
	
	private HotelDeliverVo hoteldeliver;
	
	private HotelVo hotelVo;

	@FXML
	private TextField hotelName;
	
	@FXML
	private TextField address;
	
	@FXML
	private TextField tradingArea;
	
	@FXML
	private TextField company;
	
	@FXML
	private TextArea services;
	
	@FXML
	private TextArea briefInfo;
	
	@FXML
	private ChoiceBox<String> star;
	
	@FXML
	private Button roommessage;
	
	@FXML
	private Button orderdeal;
	
	@FXML
	private Button addpromotion;
	
	@FXML
	private Button basic;
	
	@FXML
	private Button change;
	
	@FXML
	private Label tip; //这个是修改成功失败的提示域
	
	/**
	 * 这是一个初始化的方法 
	 * 里面要调逻辑层得到目前酒店的基本信息
	 * @throws RemoteException 
	 */
	@SuppressWarnings("static-access")
	@FXML
	private void initialize() throws RemoteException {
		//controller.setUserID(hotelID);
		//hotelstaffVo = controller.GetPresentHotelStaffInfo();
		//hotelVo = controller.getHotelInfoByID(hotelID);
		hotelVo = hoteldeliver.getHotelVo();
		String[] lo = hotelVo.getLocation().split("[+]");
		String[] co = null;
		String s="";
		if(!(hotelVo.getCompanies()==null)) {
			co = hotelVo.getCompanies().split(" ");
			for(int i=0;i<co.length;i++) {
				s=s+co[i]+" ";
			}
			company.setText(s.substring(0,s.length()-1));
		} else {
			company.setText(s);
		}
		hotelName.setText(hotelVo.getName());
		hotelName.setDisable(true);
		address.setText(lo[0]);
		address.setDisable(true);
		tradingArea.setText(lo[1]);
		tradingArea.setDisable(true);


		company.setDisable(true);
		services.setText(hotelVo.getServices());
		services.setDisable(true);
		briefInfo.setText(hotelVo.getBriefInfo());
		briefInfo.setDisable(true);

		if(hotelVo.getStar()==1)
			star.setValue("一星级");
		if(hotelVo.getStar()==2)
			star.setValue("二星级");
		if(hotelVo.getStar()==3)
			star.setValue("三星级");
		if(hotelVo.getStar()==4)
			star.setValue("四星级");
		if(hotelVo.getStar()==5)
			star.setValue("五星级");
		star.setDisable(true);
		star.getItems().addAll("一星级","两星级","三星级","四星级","五星级");
		
	}
	
	/**
	 * 酒店工作人员修改酒店信息
	 * @throws RemoteException 
	 */
	@FXML
	private void modifyHotel() throws RemoteException {
		if(change.getText().equals("修改")) {
			hotelName.setDisable(false);
			address.setDisable(false);
			tradingArea.setDisable(false);
			services.setDisable(false);
			briefInfo.setDisable(false);
			star.setDisable(false);
			company.setDisable(false);
			change.setText("确定");
		} else {
			String hotelname = hotelName.getText();      //酒店名称
			String hotelstar = star.getValue();          //酒店星级
			String hoteladdress = address.getText();     //地址
			String hotelTrading = tradingArea.getText(); //商圈
			String service = services.getText();         //服务
			String brief = briefInfo.getText();          //简介
			String companies = company.getText();        //合作企业
			
			BorderPane test = (BorderPane) hotelName.getParent().getParent();
			Pane a = (Pane) test.getTop();
			
			ObservableList<Node> b =a.getChildren();
			Text changename = (Text) b.get(2);
			changename.setText(hotelname);
			
			String[] com;
			String co = "";
			if(!companies.equals("")) {
				com = companies.split(" ");
				for(int i=0;i<com.length;i++) {
					co = co+com[i]+" ";
				}
				System.out.println(co);
				hotelVo.setCompanies(co.substring(0,co.length()-1));
			}else{
				hotelVo.setCompanies(co);
			}
			hotelVo.setName(hotelname);
			hotelVo.setLocation(hoteladdress+"+"+hotelTrading);
			hotelVo.setServices(service);
			hotelVo.setBriefInfo(brief);
			
			if(hotelstar.equals("一星级"))
				hotelVo.setStar(1);
			if(hotelstar.equals("二星级"))
				hotelVo.setStar(2);
			if(hotelstar.equals("三星级"))
				hotelVo.setStar(3);
			if(hotelstar.equals("四星级"))
				hotelVo.setStar(4);
			if(hotelstar.equals("五星级"))
				hotelVo.setStar(5);
			//System.out.println(hotelVo.getBriefInfo());
			if(controller.ModifyHotelMessage(hotelVo)) {

				/**
				 * 到底怎么去改下面界面的东西啊
				 */
				hotelName.setDisable(true);
				address.setDisable(true);
				tradingArea.setDisable(true);
				services.setDisable(true);
				briefInfo.setDisable(true);
				star.setDisable(true);
				company.setDisable(true);
				
				Stage stage = new Stage();
				Text text = new Text("修改成功");
				Button button = new Button("确定");
				VBox vb = new VBox();
				vb.setSpacing(10);
				vb.getChildren().addAll(text, button);
				vb.setMinSize(200, 200);
				vb.setMaxSize(200, 200);
				vb.setAlignment(Pos.CENTER);
				Scene scene = new Scene(vb);
				stage.setScene(scene);
				stage.show();
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						stage.close();
					}
				});
				change.setText("修改");
			}
		}
	}

}
