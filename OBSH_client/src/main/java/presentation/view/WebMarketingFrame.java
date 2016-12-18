package presentation.view;

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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WebMarketingFrame {
	//跳转到网站管理人员主界面
	public BorderPane jumptoWebAdministratiorMainFrame() {
		BorderPane waborder = new BorderPane();	
		VBox vb = new VBox();
		Text userAdministration = new Text();
		userAdministration.setText("用户管理");
		Button user = new Button("查询更改信息");
		user.setFont(Font.font("冬青黑体简体中文",16));
		Button hotel = new Button("添加酒店&酒店工作人员");
		hotel.setFont(Font.font("冬青黑体简体中文",16));
		Button webmarketing = new Button("添加网站营销人员");
		webmarketing.setFont(Font.font("冬青黑体简体中文",16));
		vb.getChildren().addAll(userAdministration,user,webmarketing,hotel);
		vb.setSpacing(20);
		vb.setPadding(new Insets(0,40,0,0));
		vb.setAlignment(Pos.CENTER_RIGHT);				
		waborder.setLeft(vb);
		userAdministration.setId("Text");
		user.getStyleClass().add("Usertag");
		hotel.getStyleClass().add("Hoteltag");
		webmarketing.getStyleClass().add("Webtag");
		user.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Text search  = new Text("查找");
				TextField searchtf = new TextField();
				searchtf.setPromptText("输入账号");
				Button confirm = new Button("确定");
				HBox hb = new HBox();
				hb.setSpacing(20);
				hb.getChildren().addAll(search,searchtf,confirm);
				hb.setAlignment(Pos.CENTER);
				waborder.setCenter(hb);
				
				confirm.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						String Id = searchtf.getText();
						if(Id.equals("151250046")){
							search.setVisible(false);
							searchtf.setVisible(false);
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
							waborder.setCenter(grid);
							
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
											//controller
										}
									});											
								}
							});
						}
						else{
							//没有找到该用户
							Stage stage = new Stage();
							BorderPane fborder = new BorderPane();
							search.setVisible(false);
							searchtf.setVisible(false);
							confirm.setVisible(false);
							Text text = new Text("该账号不存在！");
							Button confirmb = new Button("确定");
							VBox vb = new VBox();
							vb.getChildren().addAll(text,confirmb);
							vb.setSpacing(20);
							vb.setAlignment(Pos.CENTER);
							fborder.setCenter(vb);
							fborder.setMinSize(100, 100);
							fborder.setMaxSize(100, 100);
							stage.setScene(new Scene(fborder));
							stage.show();
							confirmb.setOnAction(new EventHandler<ActionEvent>(){
								@Override
								public void handle(ActionEvent event) {
									stage.close();
									search.setVisible(true);
									searchtf.setVisible(true);
									searchtf.setText("");
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
			public void handle(ActionEvent event) {
				Button addHotelWorker = new Button("添加酒店工作人员");
				addHotelWorker.setPrefSize(180, 180);
				addHotelWorker.setFont(Font.font("冬青黑体简体中文",18));
				addHotelWorker.getStyleClass().add("AddHotelWorkerButton");
				HBox hb = new HBox();
				hb.getChildren().add(addHotelWorker);
				hb.setAlignment(Pos.CENTER);
				waborder.setCenter(hb);
				
				addHotelWorker.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){
						HBox hb1 = new HBox();
						Text hotelName = new Text("酒店名称");
						TextField hotelnametf = new TextField();
						Button continueb = new Button("继续");
						Button cancelb = new Button("取消");
						hb1.getChildren().addAll(hotelName,hotelnametf);
						hb1.setSpacing(20);
						hb1.setAlignment(Pos.CENTER);
						HBox hb2 = new HBox();
						hb2.getChildren().addAll(continueb,cancelb);
						hb2.setAlignment(Pos.CENTER);
						hb2.setSpacing(20);
						VBox vb1 = new VBox();
						vb1.getChildren().addAll(hb1,hb2);
						vb1.setSpacing(20);
						vb1.setAlignment(Pos.CENTER);
						waborder.setCenter(vb1);
						continueb.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent event) {
								String hotelname = hotelnametf.getText();
								//判断是否注册过
								//controller.
								boolean isRegistered = false;
								if(isRegistered){
									//该酒店工作人员已经注册过
									Text tip1 = new Text("该酒店工作人员已经注册过！");
									Button button3 = new Button("确定");
									VBox vb2 = new VBox();
									vb2.getChildren().addAll(tip1,button3);
									vb2.setSpacing(20);
									vb2.setAlignment(Pos.CENTER);
									waborder.setCenter(vb2);
									button3.setOnAction(new EventHandler<ActionEvent>(){
										@Override
										public void handle(ActionEvent event) {
											vb2.setVisible(false);
											waborder.setCenter(vb1);
										}
									});
								}
								else{
									//该酒店工作人员未注册过
									String id = "1111";
									Text HotelWorkerid = new Text("账号：");
									Text name = new Text("名称：");
									Text HotelWorkerPassword = new Text("密码：");
									Text HotelWorkerPasswordConfirm = new Text("确认密码：");
									TextField HotelWorkeridtf = new TextField();
									HotelWorkeridtf.setPromptText("4位数字");
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
									gridpane.setAlignment(Pos.CENTER);
									HBox hbbutton = new HBox();
									hbbutton.getChildren().addAll(confirmbutton,cancelbutton);
									hbbutton.setSpacing(20);
									gridpane.add(hbbutton, 1, 4);
									confirmbutton.setOnAction(new EventHandler<ActionEvent>(){
										@Override
										public void handle(ActionEvent event) {
											String hotelworkerpassword = HotelWorkerPasswordtf.getText();
											String hotelworkerpasswordconfirm = HotelWorkerPasswordConfirmtf.getText();
											boolean checkpasswordequal = false;
											if(hotelworkerpassword.equals(hotelworkerpasswordconfirm)){
												checkpasswordequal = true;
											}
											if(checkpasswordequal == false){
												//输入错误
												Text textwrongtwoinput = new Text("两次密码输入不一致");
												gridpane.add(textwrongtwoinput,2, 2);
												
											}								
										}
									});
								}
							}
					});	
						cancelb.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent event) {
								waborder.setCenter(null);
							}
						});	
					}
				});
			}
			
	});		
	    webmarketing.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Text account = new Text("账号：");
				Text wmpassword = new Text("密码：");
				Text wmpasswordconfirm = new Text("确认密码：");
				TextField accounttf = new TextField();
				accounttf.setPromptText("3位数字");
				TextField wmpasswordtf = new TextField();
				TextField wmpasswordconfirmtf = new TextField();
				Button confirmbu = new Button("确定");
				Button cancelbu = new Button("取消");
				GridPane wmpane = new GridPane();
				wmpane.add(account, 0, 0);
				wmpane.add(accounttf, 1, 0);
				wmpane.add(wmpassword, 0, 1);
				wmpane.add(wmpasswordtf, 1, 1);
				wmpane.add(wmpasswordconfirm, 0, 2);
				wmpane.add(wmpasswordconfirmtf, 1, 2);
				Text tipt = new Text("该账号已被注册");
				wmpane.add(tipt, 2, 0);
				Button reacttipt = new Button("确定");
				wmpane.add(reacttipt, 4, 0);
				tipt.setVisible(false);
				reacttipt.setVisible(false);
				wmpane.setVgap(20);
				wmpane.setHgap(20);
				wmpane.setPadding(new Insets(40,0,0,0));
				wmpane.setAlignment(Pos.CENTER);
				HBox buttonhb = new HBox();
				buttonhb.getChildren().addAll(confirmbu,cancelbu);
				buttonhb.setAlignment(Pos.CENTER_RIGHT);
				buttonhb.setSpacing(20);
				wmpane.add(buttonhb, 1, 3);
				waborder.setCenter(wmpane);
				
				confirmbu.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						String smid = accounttf.getText();
						String password1 = wmpasswordtf.getText();
						String password2 = wmpasswordconfirmtf.getText();
						if(smid.equals("151250046")){
							//该网站营销人员账号已被注册
							accounttf.setDisable(true);
							wmpasswordtf.setDisable(true);
							wmpasswordconfirmtf.setDisable(true);										
							tipt.setVisible(true);
							reacttipt.setVisible(true);

							reacttipt.setOnAction(new EventHandler<ActionEvent>(){
								@Override
								public void handle(ActionEvent event) {
									reacttipt.setVisible(false);
									tipt.setVisible(false);
									accounttf.setDisable(false);
									accounttf.setText("");
									wmpasswordtf.setDisable(false);
									wmpasswordconfirmtf.setDisable(false);
								}
							});
						}
						else{
							//未被注册，但是要判断两次密码输入是否一致
							if(!(password1.equals(password2))){
								HandlePasswordNotEqual hpne = new HandlePasswordNotEqual();
								Stage stage = hpne.passwordnotEqual();
								stage.show();
								wmpasswordtf.setText("");
								wmpasswordconfirmtf.setText("");
							}
							else{
								//两次密码一致，此时应该存储新建的账号
							}
						}
					}
				});
			}
		});
		return waborder;
	}

}
