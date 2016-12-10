package presentation.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import po.HotelPo;

public class UserView{
	private Button login = new Button("登录");
	private Button register = new Button("注册");
	private UserViewControllerService controller;
	private TextField usernametf = new TextField();
	private PasswordField passwordtf = new PasswordField();
	private Scene scene;
	private Scene searchscene;
	private TextField locationtf = new TextField();
	private TextField commercialtf = new TextField();
	private Button searchIcon;
	private Text locationl = new Text("地址");
	private Text commerciall = new Text("商圈");
	private Text locationll = new Text("地址");
	private Text commercialll = new Text("商圈");
	private Button button;
	private DatePicker checkinDatePicker;
	private DatePicker checkoutDatePicker;
	private TextField userNametf;
	private TextField passwtf;
	private TextField conpasstf;
	private TextField phonenumtf;
	private final String pattern = "yyyy-MM-dd";
	private TextField nametf = new TextField();
	private TextField idtf = new TextField();
	private TextField phonetf = new TextField();
	private TextField credittf = new TextField();
	private TextField membertf = new TextField();
	private ToggleButton tb1 = new ToggleButton("未执行正常订单");
	private ToggleButton tb2 = new ToggleButton("已执行正常订单");
	private ToggleButton tb3 = new ToggleButton("撤销订单");
	private ToggleButton tb4 = new ToggleButton("异常订单");

	public UserView(UserViewControllerService controller){
		this.controller = controller;
	}
	
	public Stage Main(){
		Stage primaryStage = new Stage();		

	    usernametf.setPromptText("username");
	    usernametf.setMinSize(150, 20);
	    usernametf.setMaxSize(150, 20);
	    
	    passwordtf.setPromptText("password");
	    passwordtf.setMaxSize(150, 20);
	    passwordtf.setMinSize(150, 20);

	    register.setFont(Font.font("黑体",15));
	    register.setTextFill(Color.WHITE);
	    register.setMinSize(65, 15);
	    login.setFont(Font.font("黑体",15));
	    login.setTextFill(Color.WHITE);
	    login.setMinSize(65, 15);
	    register.setStyle("-fx-background-color:#7bbfea;");
	    login.setStyle("-fx-background-color:#7bbfea;");
	    
	    HBox hbBtn = new HBox(20);
	    hbBtn.getChildren().add(login);	
	    hbBtn.getChildren().add(register);	  
	    hbBtn.setSpacing(20);
	    hbBtn.setMaxSize(300, 200);
	    hbBtn.setAlignment(Pos.CENTER);
	    
	    VBox root = new VBox(usernametf,passwordtf,hbBtn);
	    root.setMaxSize(300, 200);
	    root.setSpacing(40); 
	    root.setAlignment(Pos.CENTER);
	    scene = new Scene(root);
	    scene.getStylesheets().add("main/application.css");
	    InitStage(primaryStage,scene);
	   
	    //注册按钮的事件
	    register.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//跳转至注册界面
				jumptoRegisterFrame(primaryStage);
			}	    	
	    });
	  //登录按钮的事件
	    login.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				String userId = usernametf.getText();
		    	String password = passwordtf.getText();
		    	int id = Integer.parseInt(userId);
		    	int length = userId.length();
		    	switch(controller.successLogin(id,password)){
		    	case:
		    	}
		    	else{
		    		//显示登录失败
		    		VBox tip = new VBox();
		    		VBox vb = new VBox();
		    		tip.setAlignment(Pos.CENTER);
		    		vb.setAlignment(Pos.CENTER);
		    		Button confirm = new Button();
		    		confirm.setText("确定");
		    		confirm.getStyleClass().add("Button");
		    		vb.setSpacing(10);
		    		vb.getChildren().addAll(addText("登录失败"),
		    		addText("用户名或密码错误，请重新输入"));
		    		tip.setSpacing(10);
		    		tip.getChildren().addAll(vb,confirm);
		    		VBox vbox = new VBox(usernametf,passwordtf,tip);
		    		vbox.setSpacing(40);
		    		vbox.setAlignment(Pos.CENTER);
		    		Scene failloginscene = new Scene(vbox);
		    		failloginscene.getStylesheets().add("main/faillogin.css");
		    		InitStage(primaryStage,failloginscene);
		    		
		    		confirm.setOnAction(new EventHandler<ActionEvent>(){
						@Override
						public void handle(ActionEvent event) {
							//返回登录界面
							primaryStage.close();
							Main();						
						}
		    		});
		    	}
			}
			//跳转到网站管理人员主界面
			private void jumptoWebAdministratiorMainFrame(Stage primaryStage) {
				Text text = new Text("用户管理");
				text.setFont(Font.font("冬青黑体简体中文",32));
				Button user = new Button("用户");
				user.setFont(Font.font("冬青黑体简体中文",16));
				Button hotel = new Button("酒店&酒店工作人员");
				hotel.setFont(Font.font("冬青黑体简体中文",16));
				Button webmarketing = new Button("网站营销人员");
				webmarketing.setFont(Font.font("冬青黑体简体中文",16));
				VBox vb = new VBox();
				vb.getChildren().addAll(text,user,hotel,webmarketing);
				vb.setSpacing(20);
				vb.setPadding(new Insets(0,40,0,0));
				vb.setAlignment(Pos.CENTER);
				BorderPane border = new BorderPane();					
				border.setLeft(vb);
				Scene scene = new Scene(border);
				scene.getStylesheets().add("main/WebAdministration.css");
				primaryStage.setScene(scene);		
				user.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						Text searchUser = new Text("查找用户");
						TextField searchUsertf = new TextField();
						searchUsertf.setPromptText("输入用户账号");
						Button confirm = new Button("确定");
						HBox hb = new HBox();
						hb.setSpacing(20);
						hb.getChildren().addAll(searchUser,searchUsertf,confirm);
						hb.setAlignment(Pos.CENTER);
						border.setCenter(hb);
						
						confirm.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent event) {
								String userId = searchUsertf.getText();
								if(controller.succeedtoFindUser(userId)){
									searchUser.setVisible(false);
									searchUsertf.setVisible(false);
									confirm.setVisible(false);
									Text id = new Text("账户：");
									TextField idtf = new TextField();
									Text name = new Text("名称：");
									TextField nametf = new TextField();
									Text phone = new Text("联系方式：");
									TextField phonetf = new TextField();
									Text credit = new Text("信用值：");
									TextField credittf = new TextField();
									Button modify = new Button("修改");
									
									GridPane grid = new GridPane();
									grid.add(id,0, 0);
									grid.add(idtf, 1, 0);
									grid.add(name, 0, 1);
									grid.add(nametf, 1, 1);
									grid.add(phone, 0, 2);
									grid.add(phonetf, 1, 2);
									grid.add(credit, 0, 3);
									grid.add(credittf,1, 3);
									grid.add(modify, 2, 3);
									grid.setAlignment(Pos.CENTER);
									grid.setHgap(20);
									grid.setVgap(20);
									border.setCenter(grid);
									
									idtf.setText(userId);
									nametf.setText(controller.getUserName(userId));
									phonetf.setText(controller.getUserPhone(userId));
									credittf.setText(controller.getUserCredit(userId));
									idtf.setDisable(true);
									nametf.setDisable(true);
									phonetf.setDisable(true);
									credittf.setDisable(true);
									
									modify.setOnAction(new EventHandler<ActionEvent>(){
										@Override
										public void handle(ActionEvent event) {
											nametf.setDisable(false);
											phonetf.setDisable(false);
											credittf.setDisable(false);
											modify.setVisible(false);
											Button confirm = new Button("确定");
											grid.add(confirm, 2, 3);
											confirm.setOnAction(new EventHandler<ActionEvent>(){
												@Override
												public void handle(ActionEvent event) {
													String name = nametf.getText();
													String phone = phonetf.getText();
													String credit = credittf.getText();
													controller.modifyUserInfo(name,phone,credit);
												}
											});											
										}
									});
								}
								else{
									//没有找到该用户
									Stage stage = new Stage();
									BorderPane border = new BorderPane();
									searchUser.setVisible(false);
									searchUsertf.setVisible(false);
									confirm.setVisible(false);
									Text text = new Text("该账号不存在！");
									Button confirmb = new Button("确定");
									VBox vb = new VBox();
									vb.getChildren().addAll(text,confirmb);
									vb.setSpacing(20);
									vb.setAlignment(Pos.CENTER);
									border.setCenter(vb);
									border.setMinSize(100, 100);
									border.setMaxSize(100, 100);
									stage.setScene(new Scene(border));
									stage.show();
									confirmb.setOnAction(new EventHandler<ActionEvent>(){
										@Override
										public void handle(ActionEvent event) {
											stage.close();
											searchUser.setVisible(true);
											searchUsertf.setVisible(true);
											searchUsertf.setText("");
											confirm.setVisible(true);
										}
									});
								}
							}
						});						
					}							
				});
				hotel.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){
						Button addHotelWorker = new Button("添加酒店工作人员");
						Button modifyHotelWorkerInfo = new Button("修改酒店工作人员信息");
						addHotelWorker.setPrefSize(150, 150);
						addHotelWorker.setFont(Font.font("冬青黑体简体中文",20));
						modifyHotelWorkerInfo.setPrefSize(150, 150);
						modifyHotelWorkerInfo.setFont(Font.font("冬青黑体简体中文",20));
						addHotelWorker.getStyleClass().add("AddHotelWorkerButton");
						modifyHotelWorkerInfo.getStyleClass().add("ModifyHotelWorkerInfo");
						HBox hb = new HBox();
						hb.getChildren().addAll(addHotelWorker,modifyHotelWorkerInfo);
						hb.setSpacing(40);
						hb.setAlignment(Pos.CENTER);
						border.setCenter(hb);
						
						addHotelWorker.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent event){
								HBox hb1 = new HBox();
								Text hotelName = new Text("酒店名称");
								Text hotelnametf = new Text();
								Button continueb = new Button("继续");
								Button cancelb = new Button("取消");
								hb1.getChildren().addAll(hotelName,hotelnametf);
								HBox hb2 = new HBox();
								hb2.getChildren().addAll(continueb,cancelb);
								VBox vb1 = new VBox();
								vb1.getChildren().addAll(hb1,hb2);
								border.setCenter(vb1);
								continueb.setOnAction(new EventHandler<ActionEvent>(){
									@Override
									public void handle(ActionEvent event) {
										String hotelname = hotelnametf.getText();
										boolean isRegistered = controller.checkHotelWorker(hotelname);
										if(isRegistered){
											//该酒店工作人员已经注册过
											Text tip1 = new Text("该酒店工作人员已经注册过！");
											Button button3 = new Button("确定");
											VBox vb2 = new VBox();
											vb2.getChildren().addAll(tip1,button3);
											vb2.setSpacing(20);
											vb2.setAlignment(Pos.CENTER);
											border.setCenter(vb2);
											button3.setOnAction(new EventHandler<ActionEvent>(){
												@Override
												public void handle(ActionEvent event) {
													vb2.setVisible(false);
													border.setCenter(vb1);
												}
											});
										}
										else{
											//该酒店工作人员未注册过
											String id = controller.setHotelId(hotelname);
											Text HotelWorkerid = new Text("账号：");
											Text name = new Text("名称：");
											Text HotelWorkerPassword = new Text("密码：");
											Text HotelWorkerPasswordConfirm = new Text("确认密码：");
											TextField HotelWorkeridtf = new TextField();
											HotelWorkeridtf.setText(id);
											TextField nametf = new TextField();
											TextField HotelWorkerPasswordtf = new TextField();
											TextField HotelWorkerPasswordConfirmtf = new TextField();
											Button confirmbutton = new Button("确定");
											Button cancelbutton = new Button("取消");
											GridPane gridpane = new GridPane();
											gridpane.add(HotelWorkerid, 0, 0);
											gridpane.add(HotelWorkeridtf, 1, 0);
											gridpane.add(name, 0, 1);
											gridpane.add(nametf, 1, 1);
											gridpane.add(HotelWorkerPassword, 0, 2);
											gridpane.add(HotelWorkerPasswordtf, 1, 2);
											gridpane.add(HotelWorkerPasswordConfirm, 0, 3);
											gridpane.add(HotelWorkerPasswordConfirmtf, 1, 3);
											HBox hbbutton = new HBox();
											hbbutton.getChildren().addAll(confirmbutton,cancelbutton);
											hbbutton.setSpacing(20);
											gridpane.add(hbbutton, 1, 4);
											confirmbutton.setOnAction(new EventHandler<ActionEvent>(){
												@Override
												public void handle(ActionEvent event) {
													String hotelworkerpassword = HotelWorkerPasswordtf.getText();
													String hotelworkerpasswordconfirm = HotelWorkerPasswordConfirmtf.getText();
													boolean checkpasswordequal = controller.checkpasswordequal(hotelworkerpassword,hotelworkerpasswordconfirm);
													if(checkpasswordequal){
														controller.saveHotelWorkerInfo(hotelname,hotelworkerpassword);
													}								
												}
											});
										}
									}
							});	
								cancelb.setOnAction(new EventHandler<ActionEvent>(){
									@Override
									public void handle(ActionEvent event) {
										border.setCenter(null);
									}
								});	
							}
						});
					}
				});
			}
			//跳转到网站营销人员主界面
			private void jumptoWebMarketingMainFrame(Stage primaryStage) {							
			}
			//跳转到酒店工作人员主界面
			private void jumptoHotelWorkerMainFrame(Stage primaryStage) {				
			}
	    });
	    return primaryStage;
	}
	//对text的字体设定
	public Text addText(String s){
		Text text = new Text(s);
		text.setFont(Font.font("冬青黑体简体中文",15));
		return text;
	}
	/*客户主界面
	  * 包括退出的链接，跳转到搜索酒店界面，查看订单界面，维护个人信息界面的按钮
	  *  以及要求用户输入关于地址商圈信息的搜索框
	  */
	public void jumptoUserMainFrame(Stage primaryStage) {
		VBox mainFrame = new VBox();
		BorderPane second = new BorderPane();
		Text obsh = new Text();
		obsh.setText("酒店线上预订系统OBSH");
		obsh.setFill(Color.WHITE);
		obsh.setFont(Font.font("冬青黑体简体中文",40));
		second.setCenter(obsh);
		second.setMinHeight(150);
		second.setMaxHeight(150);
		//三个用于界面跳转的按钮
		Button searchHotel = new Button("搜索酒店信息");
		searchHotel.setMaxSize(150, 150);
		searchHotel.setMinSize(150, 150);
		Button checkOrder = new Button("查看订单信息");
		checkOrder.setMaxSize(140, 140);
		checkOrder.setMinSize(140, 140);
		Button maintainPersonalInfo = new Button("维护个人信息");
		maintainPersonalInfo.setMaxSize(140, 140);
		maintainPersonalInfo.setMinSize(140, 140);
		HBox center = new HBox();
		center.setAlignment(Pos.CENTER);
		center.setSpacing(15);
		center.getChildren().addAll(checkOrder,searchHotel,maintainPersonalInfo);
		//搜索框
		BorderPane search = new BorderPane();
		HBox searchhb = new HBox();
		locationl.setText("地址");
		locationtf.setMinSize(150, 30);
		locationtf.setMaxSize(150, 30);
		commerciall.setText("商圈");
		commercialtf.setMinSize(150, 30);
		commercialtf.setMaxSize(150, 30);
		searchhb.setSpacing(15);
		searchhb.getChildren().addAll(locationl,locationtf,commerciall,commercialtf);
		searchhb.setPadding(new Insets(100,150,100,150));
		searchhb.setSpacing(10);
		searchhb.setAlignment(Pos.CENTER);
		button = new Button();
		button.setMinSize(25, 25);
		button.setAlignment(Pos.CENTER);
		searchhb.getChildren().add(button);
		search.setCenter(searchhb);			
		mainFrame.getChildren().addAll(second,center,search);
		searchscene = new Scene(mainFrame);
		//给各个组件添加css样式
		searchscene.getStylesheets().add("main/login.css");
		second.getStyleClass().add("Second");
		center.getStyleClass().add("Center");
		searchHotel.getStyleClass().add("SearchHotel");
		checkOrder.getStyleClass().add("CheckOrder");
		maintainPersonalInfo.getStyleClass().add("MaintainPersonalInfo");
		searchIcon.getStyleClass().add("SearchButton");
		//InitStage是将所有的stage都设置成一个大小的方法
		InitStage(primaryStage,searchscene);
		
		//搜索按钮的事件
		button.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {			
				//跳至搜索酒店信息主界面
				jumptoSearchHotelMainFrame(primaryStage);
			}
		});	
		//搜索按钮的事件
		searchIcon.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//跳至搜索酒店信息主界面
				jumptoSearchHotelMainFrame(primaryStage);
				address = locationtf.getText();
				commercial = commercialtf.getText();
				//显示相应的酒店列表
			}
		});	
		//searchIcon(搜索按钮)和searchHotel(搜索酒店按钮)跳转到的是同一界面
		searchHotel.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//跳至搜索酒店信息主界面
				jumptoSearchHotelMainFrame(primaryStage);
				}
			});
		checkOrder.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//跳至查看订单信息主界面
				jumptoCheckOrder(primaryStage);
			}
		});
		maintainPersonalInfo.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//跳至维护个人信息信息主界面
				jumptoMaintainPersonalInfo(primaryStage);
			}
		});
	}
	//跳至查看订单信息主界面
	public void jumptoCheckOrder(Stage primaryStage){		
		VBox vb = new VBox();
		final ToggleGroup tgroup = new ToggleGroup();
		tb1.setToggleGroup(tgroup);
		tb2.setToggleGroup(tgroup);
		tb3.setToggleGroup(tgroup);
		tb4.setToggleGroup(tgroup);
		vb.getChildren().addAll(tb1,tb2,tb3,tb4);
		vb.setSpacing(100);
		vb.setPadding(new Insets(100,60,0,60));
		BorderPane border = new BorderPane();
	    TableView table = new TableView();       
	    TableColumn orderNum = new TableColumn("订单号");
        TableColumn reserveHotel = new TableColumn("预定酒店");
        TableColumn reverseTime = new TableColumn("预订时间");
        TableColumn orderState = new TableColumn("订单状态");
        table.getColumns().addAll(orderNum, reserveHotel, reverseTime,orderState);
        orderNum.setMinWidth(100);
        reserveHotel.setMinWidth(100);
        reverseTime.setMinWidth(100);
        orderState.setMinWidth(100);
        border.setCenter(table);
        border.setPadding(new Insets(100,180,100,30));
		GridPane grid = new GridPane();
		grid.add(vb, 0, 0);
		grid.add(border, 1, 0);
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("main/CheckOrder.css");
        tb1.getStyleClass().add("ToggleButton");
        tb2.getStyleClass().add("ToggleButton");
        tb3.getStyleClass().add("ToggleButton");
        tb4.getStyleClass().add("ToggleButton");
        tb1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tb1.getStyleClass().add("SelectedButton");
	    		tb2.getStyleClass().add("ToggleButton");
		        tb3.getStyleClass().add("ToggleButton");
		        tb4.getStyleClass().add("ToggleButton");
			}	        
        });
        tb2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tb1.getStyleClass().add("ToggleButton");
	    		tb2.getStyleClass().add("SelectedButton");
		        tb3.getStyleClass().add("ToggleButton");
		        tb4.getStyleClass().add("ToggleButton");
			}
        });
        tb3.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tb1.getStyleClass().add("ToggleButton");
		        tb2.getStyleClass().add("ToggleButton");
	    		tb3.getStyleClass().add("SelectedButton");
		        tb4.getStyleClass().add("ToggleButton");
			}
        });
        tb4.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tb1.getStyleClass().add("ToggleButton");
		        tb2.getStyleClass().add("ToggleButton");
		        tb3.getStyleClass().add("ToggleButton");
	    		tb4.getStyleClass().add("SelectedButton");
			}
        });			            
	}
	//跳至维护个人信息信息主界面
	public void jumptoMaintainPersonalInfo(Stage primaryStage){
		GridPane gridpane = new GridPane();
		Button modifyname = new Button("修改");
		Button modifyphone = new Button("修改");
		Button modifyPassword = new Button("修改密码");
		Button check = new Button("查看");
		Button registerMember = new Button("注册会员");
		gridpane.setVgap(20);
		gridpane.setHgap(20);
		idtf.setDisable(true);
		credittf.setDisable(true);
		membertf.setDisable(true);
		gridpane.add(addText("名称："),0,0);
		gridpane.add(nametf,1,0);
		gridpane.add(modifyname, 2, 0);
		gridpane.add(addText("账号："), 0, 1);
		gridpane.add(idtf, 1, 1);
		gridpane.add(modifyPassword, 2, 1);
		gridpane.add(addText("联系方式"), 0,2);
		gridpane.add(phonetf, 1, 2);
		gridpane.add(modifyphone, 2, 2);
		gridpane.add(addText("信用值"), 0, 4);
		gridpane.add(credittf, 1, 4);
		gridpane.add(check, 2, 4);
		gridpane.add(addText("会员"), 0, 5);
		gridpane.add(membertf, 1, 5);
		gridpane.add(registerMember, 2, 5);
		gridpane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridpane);
		primaryStage.setScene(scene);		
	}
	public Button AddSearchButton(){
		searchIcon = new Button();
		searchIcon.setMinSize(25, 25);
		searchIcon.setAlignment(Pos.CENTER);
		return searchIcon;
	}
	public HBox addTop(){
		HBox top = new HBox();
		top.setMaxHeight(30);
		top.setMinHeight(30);
		top.setPadding(new Insets(5,700,5,840));
		return top;
	}
	public void InitStage(Stage primaryStage,Scene scene){
		primaryStage.setScene(scene);
		primaryStage.setTitle("酒店线上预订系统OBSH");
		primaryStage.setHeight(700);
		primaryStage.setWidth(900);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	//对text的字体设定
	public void addText(Text text){
		text.setFont(Font.font("冬青黑体简体中文",15));
	}
	//跳转到搜索酒店主界面
	public void jumptoSearchHotelMainFrame(Stage primaryStage){		
		VBox v = new VBox();
		HBox top = new HBox();
		Button returntoUserMainFrame = new Button();
		returntoUserMainFrame.setMaxSize(28, 28);
		returntoUserMainFrame.setMinSize(28, 28);
		top.getChildren().add(returntoUserMainFrame);
		VBox vb = new VBox();
		vb.setPrefSize(1200, 700);
		vb.setPadding(new Insets(20,20,10,10));
		vb.setSpacing(10);
		HBox searchLineone = new HBox();
		locationl.setText("地址");
		locationtf.setMinSize(150, 30);
		locationtf.setMaxSize(150, 30);
		commerciall.setText("商圈");
		commercialtf.setMinSize(150, 30);
		commercialtf.setMaxSize(150, 30);
		searchLineone.setSpacing(15);
		searchLineone.getChildren().addAll(locationll,locationtf,commercialll,commercialtf);
		HBox searchLinetwo = new HBox();
		Text checkin = new Text("入住");
		Text checkout = new Text("退房");
		checkinDatePicker = new DatePicker();
		checkinDatePicker.setValue(LocalDate.now());
		checkoutDatePicker = new DatePicker();			
	    checkoutDatePicker.setValue(checkinDatePicker.getValue().plusDays(1));
		Button searchIcon = AddSearchButton();
		searchLinetwo.getChildren().addAll(checkin,addDatePicker(checkinDatePicker),
				checkout,addDatePicker(checkoutDatePicker),searchLineone,searchIcon);
		searchLinetwo.setSpacing(10);
		vb.getChildren().add(searchLinetwo);
		//价格
		HBox price = new HBox();
		RadioButton rb1 = new RadioButton();
		rb1.setText("￥150以下");
		RadioButton rb2 = new RadioButton();
		rb2.setText("￥150-300");
		RadioButton rb3 = new RadioButton();
		rb3.setText("￥301-450");
		RadioButton rb4 = new RadioButton();
		rb4.setText("￥451-600");
		RadioButton rb5 = new RadioButton();
		rb5.setText("￥600以上");
		RadioButton rb6 = new RadioButton();
		rb6.setText("不限");
		price.setSpacing(10);
		Text pricet = new Text("价格");
		price.getChildren().addAll(pricet,rb1,rb2,rb3,rb4,rb5,rb6);
		vb.getChildren().add(price);
		final ToggleGroup tgroup = new ToggleGroup();
		rb1.setToggleGroup(tgroup);
		rb2.setToggleGroup(tgroup);
		rb3.setToggleGroup(tgroup);
		rb4.setToggleGroup(tgroup);
		rb5.setToggleGroup(tgroup);
		rb6.setToggleGroup(tgroup);
		
		//房间类型
		HBox roomType = new HBox();
		Text room = new Text("房间类型");
		RadioButton c1 = new RadioButton("大床房");
		RadioButton c2 = new RadioButton("双床房");
		RadioButton c3 = new RadioButton("家庭房/三人");
		RadioButton c4 = new RadioButton("套间");
		RadioButton c5 = new RadioButton("不限");
		roomType.setSpacing(10);
		roomType.getChildren().addAll(room,c1,c2,c3,c4,c5);
		final ToggleGroup roomTypeGroup = new ToggleGroup();
		c1.setToggleGroup(roomTypeGroup);
		c2.setToggleGroup(roomTypeGroup);
		c3.setToggleGroup(roomTypeGroup);
		c4.setToggleGroup(roomTypeGroup);
		c5.setToggleGroup(roomTypeGroup);
		vb.getChildren().add(roomType);             	
	    
		//排序
		HBox sequence = new HBox();		
		Button popular = new Button("最受欢迎");	
		ObservableList<String> pricelist = FXCollections.observableArrayList(
		    "价格", "由高到低", "由低到高");
		ComboBox<String> price1 = new ComboBox<String>();
		price1.getItems().addAll(pricelist);
		price1.getSelectionModel().select(0);
		ObservableList<String> marklist = FXCollections.observableArrayList(
			    "评分", "由高到低", "由低到高");
		ComboBox<String> mark = new ComboBox<String>();
		mark.getItems().addAll(marklist);
		mark.getSelectionModel().select(0);
		ObservableList<String> starlist = FXCollections.observableArrayList(
			    "星级", "由高到低", "由低到高");
		ComboBox<String> starr = new ComboBox<String>();
		starr.getItems().addAll(starlist);
		starr.getSelectionModel().select(0);
		Button history = new Button("预定历史");
		sequence.setSpacing(10);
		sequence.getChildren().addAll(popular,price1,mark,starr,history);
		vb.getChildren().add(sequence);
		vb.getChildren().add(addHotel());	
		
		v.getChildren().addAll(top,vb);
		Scene scene = new Scene(v);
		scene.getStylesheets().add("main/SearchButton.css");
		searchIcon.getStyleClass().add("SearchButton");
		returntoUserMainFrame.getStyleClass().add("ReturnButton");
		primaryStage.setScene(scene);
		
		returntoUserMainFrame.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(searchscene);			
				locationl.setVisible(false);
				commerciall.setVisible(false);
				button.setVisible(false);
			}				
		});
		popular.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				controller.SortbyPopularity();					
			}				
		});
		price1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(price1.getSelectionModel().getSelectedIndex() == 1){
					//价格由高到低排序
				}
				if(price1.getSelectionModel().getSelectedIndex() == 2){
					//价格由低到高排序
				}
			}			
		});
		mark.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(mark.getSelectionModel().getSelectedIndex() == 1){
					//评分由高到低排序
				}
				if(mark.getSelectionModel().getSelectedIndex() == 2){
					//评分由低到高排序
				}
			}		
		});
		starr.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(starr.getSelectionModel().getSelectedIndex() == 1){
					//星级由高到低排序
				}
				if(starr.getSelectionModel().getSelectedIndex() == 2){
					//星级由低到高排序
				}
			}			
		});
		history.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//显示预定历史				
			}
		});
		searchIcon.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				checkindate = checkinDatePicker.getValue();
				checkoutdate = checkoutDatePicker.getValue();
				address = locationtf.getText();
				commercial = commercialtf.getText();
				priceSelectIndex = 6;
				tgroup.selectedToggleProperty().addListener(
					    (ObservableValue<? extends Toggle> ov, Toggle old_Toggle,
					    Toggle new_Toggle) -> {
					        if (tgroup.getSelectedToggle() == rb1) {
					        	priceSelectIndex = 1;
					        }
					        else if (tgroup.getSelectedToggle() == rb2) {
					        	priceSelectIndex = 2;
					        }
					        else if (tgroup.getSelectedToggle() == rb3) {
					        	priceSelectIndex = 3;
					        }
					        else if (tgroup.getSelectedToggle() == rb4) {
					        	priceSelectIndex = 4;
					        }
					        else if (tgroup.getSelectedToggle() == rb5) {
					        	priceSelectIndex = 5;
					        }
					        else if (tgroup.getSelectedToggle() == rb6) {
					        	priceSelectIndex = 6;
					        }
					});
				roomTypeSelectIndex = 5;
				roomTypeGroup.selectedToggleProperty().addListener(
					    (ObservableValue<? extends Toggle> ov, Toggle old_Toggle,
					    Toggle new_Toggle) -> {
					        if (roomTypeGroup.getSelectedToggle() == c1) {
					        	 roomTypeSelectIndex = 1;
					        }
					        else if (roomTypeGroup.getSelectedToggle() == c2) {
					        	 roomTypeSelectIndex = 2;
					        }
					        else if (roomTypeGroup.getSelectedToggle() == c3) {
					        	 roomTypeSelectIndex = 3;
					        }
					        else if (roomTypeGroup.getSelectedToggle() == c4) {
					        	 roomTypeSelectIndex = 4;
					        }
					        else if (roomTypeGroup.getSelectedToggle() == c5) {
					        	 roomTypeSelectIndex = 5;
					        }
					});
			}				
		});
	}
	/*未完成
	 * 
	 */
	public HBox addHotel(){
		HBox hb = new HBox();
		//获得酒店列表
		String[]hotelName = controller.getHotelName();
		String[]hotelStarLevel = controller.getRelatedHotelStarLevel();
		String[]hotelPrice = controller.getRelatedHotelPrice();
		String[]hotelMark = controller.getRelatedHotelMark();	
		address = locationtf.getText();
		commercial = commercialtf.getText();
		List<HotelPo> hotellist = controller.Views(address, commercial);
		//显示相应的酒店列表
	    TreeItem<String> room1 = new TreeItem<>("大床房");
	    TreeItem<String> room2 = new TreeItem<>("双床房");
	    TreeItem<String> room3 = new TreeItem<>("家庭房");	
	    TreeItem<String> room4 = new TreeItem<>("套间");	
	    final TreeItem<String> root = new TreeItem<>("");//获得酒店名称
	    root.setExpanded(true);   
	    root.getChildren().addAll(room1, room2, room3,room4);        			    			   
	    final TreeTableView<String> treeTableView = new TreeTableView<>(root);
	    treeTableView.setPrefWidth(152);
	    treeTableView.setShowRoot(true);
		return hb;
	}
	//跳转到注册界面
	public void jumptoRegisterFrame(Stage primaryStage){
		GridPane grid = new GridPane();
		grid.setVgap(15);
		grid.setHgap(15);
		grid.setAlignment(Pos.CENTER);
		Text username = new Text("名称");
		Text phonenum = new Text("手机号");
		Text password = new Text("密码");
		Text confirmpassword = new Text("确认密码");
		userNametf = new TextField();
		passwtf = new TextField();
		conpasstf = new TextField();
		phonenumtf = new TextField();
		grid.add(username, 0, 0);
		grid.add(userNametf, 0, 1);
		grid.add(phonenum, 1, 0);
		grid.add(phonenumtf, 1, 1);
		grid.add(password, 0, 2);
		grid.add(passwtf, 0, 3);
		grid.add(confirmpassword, 1, 2);
		grid.add(conpasstf, 1, 3);
		HBox button = new HBox();
		button.setSpacing(15);
		button.setPadding(new Insets(20,0,20,0));
		button.setAlignment(Pos.CENTER);
		Button confirm = new Button("确定");
		button.getChildren().add(confirm);
		button.setMaxSize(200, 20);
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(230,0,0,0));
		root.getChildren().addAll(grid,button);
		Scene registerScene = new Scene(root);
		registerScene.getStylesheets().add("main/Register.css");
		confirm.getStyleClass().add("Button");
		primaryStage.setScene(registerScene);
		
		confirm.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				String username = userNametf.getText();
				String password = passwtf.getText();
				String conpassword = conpasstf.getText();
				String phonenum = phonenumtf.getText();
				/*处理用户输入
				 * 
				 * 
				 */	
				controller.registeruser(username,password,conpassword,phonenum);
				jumptoRegisterToLoginFrame(primaryStage);
			}

			private void jumptoRegisterToLoginFrame(Stage primaryStage) {
				root.setVisible(false);
				Text text1 = new Text("注册成功！");
				Text text2 = new Text("是否登录？");
				VBox vb = new VBox();
				vb.getChildren().add(text1);
				vb.getChildren().add(text2);
				Button loginbutton = new Button("确定");
				Button cancel  = new Button("取消");
				HBox hb = new HBox();
				hb.getChildren().addAll(loginbutton,cancel);
			}			
		});
	}
	//回到登录主界面
	private DatePicker addDatePicker(DatePicker datePicker){
		datePicker.setMaxSize(150, 10);
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
	    	DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern(pattern);
	    	@Override
	    	public String toString(LocalDate date) {
	    		if (date != null) {
	    			return dateFormatter.format(date);
	    		} else {
	    				return "";
	    			}
	    	}	    	
	    	@Override
	    	public LocalDate fromString(String string) {
	    		if (string != null && !string.isEmpty()) {
	    			return LocalDate.parse(string, dateFormatter);
	    			} else {
	    				return null;
	    				}
	    		}
	    	};     
	    datePicker.setConverter(converter);
	    datePicker.setPromptText(pattern.toLowerCase());
	    datePicker.requestFocus();
	    	
	    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
	    	@Override
	    	public DateCell call(final DatePicker datePicker) {
	    		return new DateCell() {
	    			@Override
	    			public void updateItem(LocalDate item, boolean empty) {
	    				super.updateItem(item, empty);
	    				
	    				if(datePicker == checkinDatePicker){
	    					if (item.isBefore(
	    							checkinDatePicker.getValue().plusDays(0))
	    							) {
	    						setDisable(true);
	    						setStyle("-fx-background-color: #ffc0cb;");
	    						}
	    					}
	    				else if(datePicker == checkoutDatePicker){
	    					if (item.isBefore(
	    							checkinDatePicker.getValue().plusDays(1))
	    							) {
	    						setDisable(true);
	    						setStyle("-fx-background-color: #ffc0cb;");
	    						}   
	    					}
	    				};
	    			};
	    		}	
	    };
	    datePicker.setDayCellFactory(dayCellFactory);
	    return datePicker;
	    }
	}