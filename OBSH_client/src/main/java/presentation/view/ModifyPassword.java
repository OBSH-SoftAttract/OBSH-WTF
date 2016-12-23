package presentation.view;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import presentation.controller.UserViewControllerImpl;
import vo.UserVo;

public class ModifyPassword {
	
	private UserViewControllerImpl controller;
	
	public ModifyPassword(UserViewControllerImpl controller){
		this.controller=controller;
	}
	
	public GridPane modifyPassword(BorderPane mainFrame){
		GridPane grid = new GridPane ();
		PasswordField password1 = new PasswordField();
		PasswordField password2 = new PasswordField();
		HBox button = new HBox();
		button.setSpacing(20);
		Button confirm = new Button("确定");
		Button cancel = new Button("取消");
		button.getChildren().addAll(confirm,cancel);
		grid.add(addText("密码"),0, 0);
		grid.add(password1, 1, 0);
		grid.add(addText("确认密码"),0, 1);
		grid.add(password2, 1, 1);
		grid.add(button, 1, 2);
		confirm.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//controller更新密码
				String passwordone = password1.getText();
				String passwordtwo = password2.getText();
				if(passwordone.equals(passwordtwo)){
					MaintainPersonalInfo mpi = new MaintainPersonalInfo(controller);
					GridPane g = null;
					try {
						UserVo vo=controller.GetPresentUserInfo();
						controller.ModifyPassword(vo.getID(),passwordone);
						g = mpi.jumptoMaintainPersonalInfo(mainFrame,controller.GetPresentUserInfo());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					mainFrame.setCenter(g);
				}
				else{
					HandlePasswordNotEqual hpne = new HandlePasswordNotEqual();
					hpne.passwordnotEqual();
					password1.setText("");
					password2.setText("");
				}
			}
		});
		cancel.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//controller更新密码
				MaintainPersonalInfo mpi = new MaintainPersonalInfo(controller);
				GridPane g = null;
				try {
					g = mpi.jumptoMaintainPersonalInfo(mainFrame,controller.GetPresentUserInfo());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				mainFrame.setCenter(g);
			}
		});
		grid.setVgap(20);
		grid.setHgap(20);
		grid.setAlignment(Pos.CENTER);
		return grid;
	}
	//对text的字体设定
		public Text addText(String s){
			Text text = new Text(s);
			text.setFont(Font.font("冬青黑体简体中文",15));
			return text;
		}
}
