package presentation.view;

import java.rmi.RemoteException;

import ResultMessage.ResultMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import presentation.controller.UserViewControllerImpl;
import vo.UserVo;

public class MaintainPersonalInfo {

	private TextField nametf = new TextField();
	private TextField idtf = new TextField();
	private TextField phonetf = new TextField();
	private TextField credittf = new TextField();
	private TextField membertf = new TextField();
	//跳至维护个人信息信息主界面
	
	private UserViewControllerImpl controller;
	
	public  MaintainPersonalInfo(UserViewControllerImpl controller){
		this.controller=controller;
	}
	
	public GridPane jumptoMaintainPersonalInfo(BorderPane mainFrame,UserVo uservo){
		GridPane gridpane = new GridPane();
		String name = uservo.getUsername();
		nametf.setText(name);
		String id = String.valueOf(uservo.getID());
		idtf.setText(id);
		String phone = uservo.getPhone();
		phonetf.setText(phone);
		String credit = null;
		try {
			credit = String.valueOf(controller.getPresentCreditInfo(uservo.getID()).getCreditResult());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		credittf.setText(credit);
		String[]member = {"无","普通会员","企业会员"};
		ResultMessage re = null;
		try {
			re=controller.MemberMessgae(uservo.getID());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		switch(re){
		case Common:membertf.setText(member[0]);break;
		case Person:membertf.setText(member[1]);break;
		case Company:membertf.setText(member[2]);break;
		default: System.out.println("wrong with member");
		}
		
		Button modifyname = new Button("修改");
		Button modifyphone = new Button("修改");
		Button modifyPassword = new Button("修改密码");
		Button check = new Button("查看信用记录");
		Button registerMember = new Button("注册会员");
		Button confirmname = new Button("确定");
		Button confirmphone = new Button("确定");
		
		if(!membertf.toString().equals("member[0]"))//已经是会员则不需要注册
			registerMember.setDisable(true);
		
		gridpane.setVgap(20);
		gridpane.setHgap(20);
		idtf.setDisable(true);
		credittf.setDisable(true);
		membertf.setDisable(true);
		//可修改的
		nametf.setDisable(true);
		phonetf.setDisable(true);
		gridpane.add(addText("名称："),0,0);
		gridpane.add(nametf,1,0);
		gridpane.add(modifyname, 2, 0);
		gridpane.add(confirmname, 3,0);
		gridpane.add(addText("账号："), 0, 1);
		gridpane.add(idtf, 1, 1);
		gridpane.add(modifyPassword, 2, 1);
		gridpane.add(addText("联系方式"), 0,2);
		gridpane.add(phonetf, 1, 2);
		gridpane.add(confirmphone, 3,2);
		gridpane.add(modifyphone, 2, 2);
		gridpane.add(addText("信用值"), 0, 4);
		gridpane.add(credittf, 1, 4);
		gridpane.add(check, 2, 4);
		gridpane.add(addText("会员"), 0, 5);
		gridpane.add(membertf, 1, 5);
		gridpane.add(registerMember, 2, 5);
		gridpane.setAlignment(Pos.CENTER);
		confirmname.setVisible(false);
		confirmphone.setVisible(false);
		
		modifyname.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				nametf.setDisable(false);
				phonetf.setDisable(true);
				modifyname.setVisible(false);	
				confirmname.setVisible(true);
			}
		});
		modifyphone.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				phonetf.setDisable(false);
				nametf.setDisable(true);
				modifyphone.setVisible(false);
				confirmphone.setVisible(true);
			}
		});
		modifyPassword.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				ModifyPassword mp = new ModifyPassword(controller);
				GridPane gr = mp.modifyPassword(mainFrame);
				mainFrame.setCenter(gr);
			}
		});
		check.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//查看信用记录
				CheckCreditHistory cch = new CheckCreditHistory(controller);
				BorderPane cchb = cch.addCreditHistory();
				mainFrame.setCenter(cchb);
			}
		});
		registerMember.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				RegisterMember rm = new RegisterMember(controller);
				GridPane rmgp = rm.registerMember(mainFrame);
				mainFrame.setCenter(rmgp);
			}
		});
		confirmname.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				nametf.setDisable(true);
				String smodifyname = nametf.getText();
				confirmname.setVisible(false);
				modifyname.setVisible(true);
				//controller保存并更新modifyname
				UserVo vo;
				try {
					vo = controller.GetPresentUserInfo();
					vo.setUsername(smodifyname);
				    controller.ModifyMessage(vo);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		confirmphone.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				phonetf.setDisable(true);
				String smodifyphone = phonetf.getText();
				confirmphone.setVisible(false);
				modifyphone.setVisible(true);
				//controller保存并更新modifyphone
				UserVo vo;
				try {
					vo = controller.GetPresentUserInfo();
					vo.setPhone(smodifyphone);
				    controller.ModifyMessage(vo);
				} catch (RemoteException e) {
					e.printStackTrace();
				}

			}
		});
		return gridpane;
	}		
	//对text的字体设定
	public Text addText(String s){
		Text text = new Text(s);
		text.setFont(Font.font("冬青黑体简体中文",15));
		return text;
	}
}
