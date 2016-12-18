package presentation.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReverseHistory {
	
	private UserViewControllerService controller;
	
	public ReverseHistory(UserViewControllerService controller){
		this.controller=controller;
	}
	
	//跳至查看预定历史主界面
		private ToggleButton tb1 = new ToggleButton("正常订单");
		private ToggleButton tb2 = new ToggleButton("撤销订单");
		private ToggleButton tb3 = new ToggleButton("异常订单");
		
		public BorderPane jumptoReverseHistory(){	
			BorderPane border = new BorderPane();
			HBox hb = new HBox();
			final ToggleGroup tgroup = new ToggleGroup();
			tb1.setToggleGroup(tgroup);
			tb2.setToggleGroup(tgroup);
			tb3.setToggleGroup(tgroup);
			hb.getChildren().addAll(tb1,tb2,tb3);
			hb.setSpacing(100);
			hb.setPadding(new Insets(100,10,20,0));
			hb.setAlignment(Pos.CENTER);
			
		    HistoryList historylist = new HistoryList(controller);
		    VBox historyvb = historylist.addHistoryList(0);
			border.setTop(hb);
			historyvb.setAlignment(Pos.CENTER);
			border.setCenter(historyvb);

			border.getStyleClass().add("ordervb");
	        tb1.setId("ToggleButton");
	        tb2.setId("ToggleButton");
	        tb3.setId("ToggleButton");
	        
	        tb1.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					HistoryList historylist = new HistoryList(controller);
				    VBox historyvb = historylist.addHistoryList(0);
					border.setTop(hb);
					historyvb.setAlignment(Pos.CENTER);
					border.setCenter(historyvb);

					border.getStyleClass().add("ordervb");
					tb1.setId("SelectedButton");
		    		tb2.setId("ToggleButton");
			        tb3.setId("ToggleButton");
				}	        
	        });
	        tb2.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					HistoryList historylist = new HistoryList(controller);
				    VBox historyvb = historylist.addHistoryList(2);
					border.setTop(hb);
					historyvb.setAlignment(Pos.CENTER);
					border.setCenter(historyvb);

					border.getStyleClass().add("ordervb");
					tb1.setId("ToggleButton");
		    		tb2.setId("SelectedButton");
			        tb3.setId("ToggleButton");
				}
	        });
	        tb3.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					HistoryList historylist = new HistoryList(controller);
				    VBox historyvb = historylist.addHistoryList(3);
					border.setTop(hb);
					historyvb.setAlignment(Pos.CENTER);
					border.setCenter(historyvb);

					border.getStyleClass().add("ordervb");
					tb1.setId("ToggleButton");
			        tb2.setId("ToggleButton");
		    		tb3.setId("SelectedButton");
				}
	        });	  
	        border.setMaxSize(800, 550);
	        border.setPadding(new Insets(0,0,0,0));
	        return border;
		}
}
