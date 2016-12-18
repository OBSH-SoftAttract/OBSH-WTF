package presentation.view;

import java.rmi.RemoteException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import vo.UserVo;

public class RegisterMember {
	
	UserViewControllerService controller;
	
	public RegisterMember(UserViewControllerService controller) {
		this.controller = controller;

	}
	public GridPane registerMember(BorderPane mainFrame){
		GridPane rmgp = new GridPane();
		Button commonmember = new Button("注册普通会员");
		Button enterprisemember = new Button("注册企业会员");
		commonmember.setMaxSize(200,200);
		enterprisemember.setMaxSize(200,200);
		commonmember.setMinSize(200,200);
		enterprisemember.setMinSize(200,200);
		rmgp.add(commonmember, 0, 0);
		rmgp.add(enterprisemember, 1, 0);
		rmgp.setHgap(100);
		rmgp.setAlignment(Pos.CENTER);
		rmgp.setMaxSize(300, 300);
		commonmember.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				GridPane rcm = registerCommonMember(mainFrame);
				mainFrame.setCenter(rcm);
			}
		});
		enterprisemember.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				GridPane rem = registerEnterpriseMember(mainFrame);
				mainFrame.setCenter(rem);
			}
		});
		return rmgp;
	}
	public GridPane registerCommonMember(BorderPane mainFrame){
		GridPane gp = new GridPane();
		Text text = new Text("请输入您的生日日期：");
		DatePicker dateDatePicker = new DatePicker();
		dateDatePicker.setMaxWidth(120);
		dateDatePicker.setValue(LocalDate.of(2000, 01, 01));
		Button confirm = new Button("确定");
		Button cancel = new Button("取消");
		HBox hb = new HBox();
		hb.setSpacing(30);
		hb.getChildren().addAll(confirm,cancel);
		gp.add(text, 0, 0);
		gp.add(dateDatePicker, 1, 0);
		gp.add(hb, 1, 1);
		gp.setVgap(30);
		gp.setAlignment(Pos.CENTER);
		
		confirm.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//还要修改维护个人信息界面的会员情况
				String date=dateDatePicker.getValue().toString();
				
				int userID;
				try {
					userID = controller.GetPresentUserInfo().getID();
					controller.PersonRegisterMember(userID, date);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				MaintainPersonalInfo mpi = new MaintainPersonalInfo(controller);
				UserVo vo = null;
				try {
					vo = controller.GetPresentUserInfo();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				mpi.jumptoMaintainPersonalInfo(mainFrame,vo);
			}
		});
		cancel.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				GridPane rm = registerMember(mainFrame);
				mainFrame.setCenter(rm);
			}
		});
		return gp;
	}
	public GridPane registerEnterpriseMember(BorderPane mainFrame){
		GridPane remgp = new GridPane();
		Text textenterprise = new Text("请输入您的企业名称：");
		TextField tf = new TextField();
		tf.setMaxWidth(120);
		Button confirm1 = new Button("确定");
		Button cancel1 = new Button("取消");
		HBox hb1 = new HBox();
		hb1.setSpacing(30);
		hb1.getChildren().addAll(confirm1,cancel1);
		remgp.add(textenterprise, 0,0);
		remgp.add(tf, 0, 1);
		remgp.add(hb1, 1, 1);
		remgp.setVgap(20);
		remgp.setAlignment(Pos.CENTER);
		remgp.setMaxSize(300, 300);
		confirm1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//还要修改维护个人信息界面的会员情况
				
				String company=tf.getText().toString();
				if(!company.equals("")){
					int userID;
					
					try {
						userID = controller.GetPresentUserInfo().getID();
						controller.CompanyRegisterMember(userID, company);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					UserVo vo=null;
					try {
						vo = controller.GetPresentUserInfo();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					MaintainPersonalInfo mpi = new MaintainPersonalInfo(controller);
					GridPane mpigridpane = mpi.jumptoMaintainPersonalInfo(mainFrame,vo);
					mainFrame.setCenter(mpigridpane);
				}

			}
		});
		cancel1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				GridPane rm = registerMember(mainFrame);
				mainFrame.setCenter(rm);
			}
		});
		return remgp;
	}
}
