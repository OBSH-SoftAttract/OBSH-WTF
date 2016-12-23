package presentation.view;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.controller.UserViewControllerImpl;

public class RegisterFrame {
	private TextField userNametf;
	private TextField passwtf;
	private TextField conpasstf;
	private TextField phonenumtf;
	private UserViewControllerImpl controller;

	public RegisterFrame(UserViewControllerImpl controller) {
		this.controller = controller;
	}

	// 跳转到注册界面
	public void show() {
		Stage primaryStage = new Stage();
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
		Button cancel = new Button("取消");
		button.getChildren().addAll(confirm, cancel);
		button.setSpacing(15);
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
		cancel.getStyleClass().add("Button");
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
					try {
						userId = controller.Register(username, password, phonenum);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					if (username.equals("") || password.equals("") || phonenum.equals(""))
						notion.setText("内容不能为空");
					else {
						registerSuccess(userId);
					}
				}
			}
		});
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
				UserView umf = new UserView(controller);
				Stage stage = umf.Main();
				stage.show();
			}
		});
		BorderPane border = new BorderPane();
		border.setTop(top);
		border.setCenter(root);
		Scene scene = new Scene(border);
		primaryStage.setScene(scene);
		primaryStage.setHeight(700);
		primaryStage.setWidth(900);
		// primaryStage.setResizable(false);
		scene.getStylesheets().add("mainframe/Register.css");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	}

	public void registerSuccess(int userID) {
		Stage stage = new Stage();
		Text text = new Text("注册成功！欢迎您：" + userID + "（请记住账号）");
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
				UserViewControllerImpl user = new UserViewControllerImpl();
				UserView umf = new UserView(user);
				Stage stage = umf.Main();
				stage.show();
			}
		});
	}
}
