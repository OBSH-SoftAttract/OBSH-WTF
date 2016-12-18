package presentation.view;

import java.rmi.RemoteException;

import ResultMessage.ResultMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.controller.UserViewControllerImpl;

public class UserView {
	private Button login;
	private Button register = new Button("注册");
	private Scene scene;
	private Scene searchscene;
	private TextField usernametf = new TextField();
	private PasswordField passwordtf = new PasswordField();

	private UserViewControllerService userviewService;

	public UserView(UserViewControllerImpl controller) {
		this.userviewService = controller;
		
		Main();
	}

	public Stage Main() {
		Stage primaryStage = new Stage();
		usernametf.setPromptText("username");
		usernametf.setMaxSize(150, 20);

		primaryStage.initStyle(StageStyle.TRANSPARENT);

		passwordtf.setPromptText("password");
		passwordtf.setMaxSize(150, 20);

		register.setFont(Font.font("黑体", 15));
		register.setTextFill(Color.WHITE);
		register.setMaxSize(65, 15);
		login = new Button("登录");
		login.setFont(Font.font("黑体", 15));
		login.setTextFill(Color.WHITE);
		login.setMaxSize(65, 15);
		register.setStyle("-fx-background-color:#7bbfea;");
		login.setStyle("-fx-background-color:#7bbfea;");

		HBox hbBtn = new HBox(20);
		hbBtn.getChildren().add(login);
		hbBtn.getChildren().add(register);
		hbBtn.setSpacing(20);
		hbBtn.setMaxSize(300, 200);
		hbBtn.setAlignment(Pos.CENTER);

		HBox top = new HBox();
		Button close = new Button();
		close.setMaxSize(30, 30);
		close.setMinSize(30, 30);
		top.getChildren().add(close);
		top.setMaxHeight(30);
		top.setPadding(new Insets(0, 0, 0, 0));
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		VBox loginvb = new VBox(usernametf, passwordtf, hbBtn);
		BorderPane root = new BorderPane();
		loginvb.setSpacing(30);
		root.setTop(top);
		top.setAlignment(Pos.TOP_LEFT);
		loginvb.setAlignment(Pos.CENTER);
		root.setCenter(loginvb);
		scene = new Scene(root);
		scene.getStylesheets().add("presentation/view/application.css");
		close.getStyleClass().add("CloseButton");
		InitStage(primaryStage, scene);

		// 注册按钮的事件
		register.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// 跳转至注册界面
				BorderPane register = jumptoRegisterFrame(primaryStage);
				Scene registerscene = new Scene(register);
				registerscene.getStylesheets().add("presentation/view/Register.css");
				primaryStage.setScene(registerscene);
				// InitStage是将所有的stage都设置成一个大小的方法
				InitStage(primaryStage, registerscene);
			}
		});
		// 登录按钮的事件
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String userId = usernametf.getText();
				String password = passwordtf.getText();

				ResultMessage re = null;
				try {
					re = userviewService.Login(userId, password);
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				/*switch (re) {
				case FormatWrong:
					LoginFail("用户名错误，请重新输入", primaryStage);
					break;
				case NotExist:
					LoginFail("用户名不存在，请重新输入", primaryStage);
					break;
				case WrongPassword:
					LoginFail("密码错误，请重新输入", primaryStage);
					break;
				case NULL:
					LoginFail("请输入完整", primaryStage);
					break;*/
				//default://登录成功
					UserMainFrame umf = new UserMainFrame(userviewService);
					BorderPane mainFrame = umf.jumptoUserMainFrame();
					searchscene = new Scene(mainFrame);
					searchscene.getStylesheets().add("presentation/view/login.css");
					primaryStage.setScene(searchscene);
					// InitStage是将所有的stage都设置成一个大小的方法
					InitStage(primaryStage, searchscene);
				}
			//}
		});
		return primaryStage;
	}

	public void LoginFail(String s, Stage primaryStage) {
		// 显示登录失败
		BorderPane failbp = new BorderPane();
		usernametf.setDisable(true);
		passwordtf.setDisable(true);
		VBox tip = new VBox();
		VBox vb = new VBox();
		tip.setAlignment(Pos.CENTER);
		vb.setAlignment(Pos.CENTER);
		Button confirm = new Button();
		confirm.setText("确定");
		confirm.getStyleClass().add("Button");
		vb.setSpacing(10);
		vb.getChildren().addAll(addText("登录失败"), addText(s));
		tip.setSpacing(10);
		tip.getChildren().addAll(vb, confirm);
		VBox vbox = new VBox(usernametf, passwordtf, tip);
		vbox.setSpacing(40);
		vbox.setAlignment(Pos.CENTER);
		HBox top = new HBox();
		Button close = new Button();
		close.setMaxSize(30, 30);
		close.setMinSize(30, 30);
		top.getChildren().add(close);
		top.setMaxHeight(30);
		top.setPadding(new Insets(0, 0, 0, 0));
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		failbp.setTop(top);
		failbp.setCenter(vbox);
		Scene failloginscene = new Scene(failbp);
		failloginscene.getStylesheets().add("presentation/view/faillogin.css");
		close.getStyleClass().add("CloseButton");
		InitStage(primaryStage, failloginscene);

		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// 返回登录界面
				primaryStage.close();
				usernametf.setDisable(false);
				passwordtf.setDisable(false);
				Main();
			}
		});
	}

	// 对text的字体设定
	public Text addText(String s) {
		Text text = new Text(s);
		text.setFont(Font.font("冬青黑体简体中文", 15));
		return text;
	}

	public HBox addTop() {
		HBox top = new HBox();
		top.setMaxHeight(30);
		top.setMinHeight(30);
		top.setPadding(new Insets(5, 700, 5, 840));
		return top;
	}

	public void InitStage(Stage primaryStage, Scene scene) {
		primaryStage.setScene(scene);
		primaryStage.setTitle("酒店线上预订系统OBSH");
		primaryStage.setHeight(700);
		primaryStage.setWidth(900);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	// 回到登录主界面
	public void backtoMain(Stage primaryStage) {
		usernametf = new TextField();
		usernametf.setPromptText("username");
		usernametf.setMaxSize(150, 20);

		passwordtf = new PasswordField();
		passwordtf.setPromptText("password");
		passwordtf.setMaxSize(150, 20);

		register = new Button("注册");
		register.setFont(Font.font("黑体", 15));
		register.setTextFill(Color.WHITE);
		register.setMaxSize(65, 15);
		login = new Button("登录");
		login.setFont(Font.font("黑体", 15));
		login.setTextFill(Color.WHITE);
		login.setMaxSize(65, 15);
		register.setStyle("-fx-background-color:#7bbfea;");
		login.setStyle("-fx-background-color:#7bbfea;");

		HBox hbBtn = new HBox(20);
		hbBtn.getChildren().add(login);
		hbBtn.getChildren().add(register);
		hbBtn.setSpacing(20);
		hbBtn.setMaxSize(300, 200);
		hbBtn.setAlignment(Pos.CENTER);

		VBox root = new VBox(usernametf, passwordtf, hbBtn);
		root.setMaxSize(300, 200);
		root.setSpacing(40);
		root.setAlignment(Pos.CENTER);
		scene = new Scene(root);
		scene.getStylesheets().add("presentation/view/application.css");
		primaryStage.setScene(scene);
	}

	// 跳转到注册界面
	public BorderPane jumptoRegisterFrame(Stage primaryStage) {
		TextField userNametf;
		TextField passwtf;
		TextField conpasstf;
		TextField phonenumtf;

		GridPane grid = new GridPane();
		grid.setVgap(15);
		grid.setHgap(15);
		grid.setAlignment(Pos.CENTER);
		Text username = new Text("名称");
		Text phonenum = new Text("手机号");
		Text password = new Text("密码");
		Text confirmpassword = new Text("确认密码");
		Text notion = new Text("提示：内容不可为空");
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
		button.setPadding(new Insets(20, 0, 20, 0));
		button.setAlignment(Pos.CENTER);
		Button confirm = new Button("确定");
		button.getChildren().addAll(confirm);
		button.setMaxSize(200, 20);

		HBox top = new HBox();
		Button close = new Button();
		close.setMaxSize(30, 30);
		close.setMinSize(30, 30);
		top.getChildren().add(close);
		top.setMaxHeight(30);
		top.setPadding(new Insets(0, 0, 0, 0));
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(230, 0, 0, 0));
		root.getChildren().addAll(grid, button, notion);
		confirm.getStyleClass().add("Button");
		close.getStyleClass().add("CloseButton");
		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String username = userNametf.getText();
				String password = passwtf.getText();
				String conpassword = conpasstf.getText();
				String phonenum = phonenumtf.getText();
				// 处理用户输入
				if (!password.equals(conpassword)) {
					notion.setText("密码不一致");
				} else if (password.length() > 12)
					notion.setText("密码位数过长");
				else {
					int userId = 0;
					ResultMessage re = null;
					try {
						userId = userviewService.NewClientID();
						re = userviewService.Register(username, password, phonenum, userId);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					switch (re) {
					case NULL:
						notion.setText("内容不能为空");
						break;
					case Success:
						registerSuccess(userId);
					default:
						notion.setText("抱歉，系统错误");
					}
				}
			}
		});
		BorderPane border = new BorderPane();
		border.setTop(top);
		border.setCenter(root);
		return border;
	}
	
	public void registerSuccess(int userID){
		Stage stage = new Stage();
		Text text = new Text("注册成功！欢迎您："+userID+"（请记住账号）");
		Button button = new Button("确定");
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.getChildren().addAll(text,button);
		vb.setMinSize(200, 200);
		vb.setMaxSize(200, 200);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb);
		stage.setScene(scene);
		stage.show();
		button.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				stage.close();
				UserViewControllerImpl user=new UserViewControllerImpl();
				UserView umf = new UserView(user);
				Stage stage = umf.Main();
				stage.show();
			}
		});
	}
}