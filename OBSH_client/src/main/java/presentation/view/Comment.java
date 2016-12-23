package presentation.view;

import java.rmi.RemoteException;

import ResultMessage.ResultMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.controller.UserViewControllerImpl;

public class Comment {
	
	private UserViewControllerImpl controller;
	int HotelId;
	String orderId;

	public Comment(UserViewControllerImpl controller,int hotel,String orderID) {
		this.controller = controller;
		this.HotelId=hotel;
		this.orderId=orderID;
	}
	
	int selectedStarLevel=0;
	public void showstage(){
		Text text = new Text("请输入您的评价：");
		Text markt = new Text("请评分：");
		HBox starhb = new HBox();
		Button star1 = new Button();
		Button star2 = new Button();
		Button star3 = new Button();
		Button star4 = new Button();
		Button star5 = new Button();
		star1.setMaxSize(20, 20);
		star1.setMinSize(20, 20);
		star2.setMaxSize(20, 20);
		star2.setMinSize(20, 20);
		star3.setMaxSize(20, 20);
		star3.setMinSize(20, 20);
		star4.setMaxSize(20, 20);
		star4.setMinSize(20, 20);
		star5.setMaxSize(20, 20);
		star5.setMinSize(20, 20);
		starhb.getChildren().addAll(star1,star2,star3,star4,star5);
		HBox hb = new HBox();
		hb.getChildren().addAll(markt,starhb);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(20);
		TextArea t = new TextArea();
		t.setMaxSize(300, 200);
		Button button = new Button("确定");
		Button button2=new Button("取消");
		HBox hbBtn = new HBox(20);
	    hbBtn.getChildren().add(button);
	    hbBtn.getChildren().add(button2);
	    hbBtn.setSpacing(20);
	    hbBtn.setMaxSize(300, 200);
	    hbBtn.setAlignment(Pos.CENTER);
		VBox vb = new VBox();
		vb.setSpacing(20);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(hb,text,t,hbBtn);
		vb.setMaxSize(400, 350);
		vb.setMinSize(400, 350);
		Scene scene = new Scene(vb);
		scene.getStylesheets().add("mainframe/Star.css");
		star1.setId("Whitestar");
		star2.setId("Whitestar");
		star3.setId("Whitestar");
		star4.setId("Whitestar");
		star5.setId("Whitestar");
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		star1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				star1.setId("Yellowstar");
				star2.setId("Whitestar");
				star3.setId("Whitestar");
				star4.setId("Whitestar");
				star5.setId("Whitestar");
				selectedStarLevel = 1;
			}		
		});
		star2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				star1.setId("Yellowstar");
				star2.setId("Yellowstar");
				star3.setId("Whitestar");
				star4.setId("Whitestar");
				star5.setId("Whitestar");
				selectedStarLevel = 2;
			}		
		});
		star3.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				star1.setId("Yellowstar");
				star2.setId("Yellowstar");
				star3.setId("Yellowstar");
				star4.setId("Whitestar");
				star5.setId("Whitestar");
				selectedStarLevel = 3;
			}		
		});
		star4.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				star1.setId("Yellowstar");
				star2.setId("Yellowstar");
				star3.setId("Yellowstar");
				star4.setId("Yellowstar");
				star5.setId("Whitestar");
				selectedStarLevel = 4;
			}		
		});
		star5.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				star1.setId("Yellowstar");
				star2.setId("Yellowstar");
				star3.setId("Yellowstar");
				star4.setId("Yellowstar");
				star5.setId("Yellowstar");
				selectedStarLevel = 5;
			}		
		});
		button.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				
				//controller存储该用户的评分及评价
				//评分共5分，为selectedStarLevel
				String comment = t.getText();
				
				if(comment.equals("")&&selectedStarLevel==0){
					text.setText("亲，您还没评价呢");
				}
				else{
					ResultMessage r = null;
					try {
						r=controller.Assess(orderId,comment, selectedStarLevel, HotelId);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					switch(r){
					case NULL:text.setText("陛下先前已经评价过了哦");break;
					case UpdateSuccess:stage.close();break;
					default:text.setText("抱歉，我们似乎遇到了意外");
					}
					
					
				}
				
			}
		});
		button2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {		
					stage.close();					
			}
		});
		
	}
}
