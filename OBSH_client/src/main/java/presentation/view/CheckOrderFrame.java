package presentation.view;

import java.rmi.RemoteException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import vo.OrderVo;

public class CheckOrderFrame {
	//跳至查看订单信息主界面
	private Button tb1 = new Button("未执行正常订单");
	private Button tb2 = new Button("已执行正常订单");
	private Button tb3 = new Button("撤销订单");
	private Button tb4 = new Button("异常订单");
	List<OrderVo> list ;
	UserViewControllerService controller;
	
	public CheckOrderFrame(UserViewControllerService controller) {
		this.controller=controller;
	}

	public BorderPane jumptoCheckOrder(){	
		BorderPane border = new BorderPane();
		VBox vb = new VBox();
		vb.getChildren().addAll(tb1,tb2,tb3,tb4);
		vb.setSpacing(100);
		vb.setPadding(new Insets(100,10,0,0));
        
		try {
			list = controller.getAllHistoryOrdersByUserID(controller.GetPresentUserInfo().getID());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		tb1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tb1.setId("SelectedButton");
	    		tb2.setId("ToggleButton");
		        tb3.setId("ToggleButton");
		        tb4.setId("ToggleButton");
		        //显示未执行正常订单;
		        OrderList orderlist = new OrderList(list,controller);
			    VBox ordervb1 = orderlist.addOrderList(0);
				border.setLeft(vb);
				border.setCenter(ordervb1);
			}	        
        });
        tb2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tb1.setId("ToggleButton");
	    		tb2.setId("SelectedButton");
		        tb3.setId("ToggleButton");
		        tb4.setId("ToggleButton");

		        OrderList orderlist = new OrderList(list,controller);
			    VBox ordervb2 = orderlist.addOrderList(1);
				border.setLeft(vb);
				border.setCenter(ordervb2);
			}
        });
        tb3.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tb1.setId("ToggleButton");
		        tb2.setId("ToggleButton");
	    		tb3.setId("SelectedButton");
		        tb4.setId("ToggleButton");

		        OrderList orderlist = new OrderList(list,controller);
			    VBox ordervb3 = orderlist.addOrderList(2);
				border.setLeft(vb);
				border.setCenter(ordervb3);
			}
        });
        tb4.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tb1.setId("ToggleButton");
		        tb2.setId("ToggleButton");
		        tb3.setId("ToggleButton");
	    		tb4.setId("SelectedButton");

	    		 OrderList orderlist = new OrderList(list,controller);
	    		 VBox ordervb4 = orderlist.addOrderList(3);
	    		 border.setLeft(vb);
	    		 border.setCenter(ordervb4);
			}
        });
        
       
	    OrderList orderlist = new OrderList(list,controller);
	    VBox ordervb = orderlist.addOrderList(0);
		border.setLeft(vb);
		border.setCenter(ordervb);
		
		border.getStyleClass().add("ordervb");
        tb1.setId("ToggleButton");
        tb2.setId("ToggleButton");
        tb3.setId("ToggleButton");
        tb4.setId("ToggleButton");
        
        border.setMaxSize(800, 550);
        border.setPadding(new Insets(0,0,0,0));
        return border;
	}
}
