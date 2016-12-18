package presentation.view;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import vo.OrderVo;

public class OrderList extends HBox {

	private final String State[] = { "未执行订单", "已执行订单", "异常订单", "已撤销订单" };

	List<Order> orderList;
	UserViewControllerService controller;
	List<OrderVo> orderVoList;

	public OrderList(List<OrderVo> list, UserViewControllerService controller) {
		this.controller = controller;
		this.orderVoList = list;
	}

	private final TableView<Order> ordertable = new TableView<>();

	public VBox addOrderList(int state) {
		orderList = new ArrayList<Order>();
		try {
			for (OrderVo vo : orderVoList) {
				if (state == vo.getOrderState()) {
					String orderId = vo.getOrderID();
					int hotelId = vo.getHotelID();
					String name = controller.getHotelNameByHotelID(hotelId);
					String date = vo.getStartTime().toString().substring(0, 10);
					String orderstate = State[vo.getOrderState()];
					String s = "";
					if (vo.isEvaluate())
						s = "已评价";

					orderList.add(new Order(orderId, name, date, orderstate, s));
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ObservableList<Order> data = FXCollections.observableArrayList(orderList);

		final VBox vb = new VBox();
		// 酒店列表
		// 所有命名后面添加tc表示类型为tablecolumn
		TableColumn orderIdtc = new TableColumn("订单号");
		orderIdtc.setMinWidth(120);
		orderIdtc.setCellValueFactory(
	            new PropertyValueFactory<>("orderId"));
		TableColumn hotelnametc = new TableColumn("预定酒店");
		hotelnametc.setMinWidth(110);
		hotelnametc.setCellValueFactory(
	            new PropertyValueFactory<>("hotelname"));
		TableColumn timetc= new TableColumn("预定时间");
		timetc.setMinWidth(110);
		timetc.setCellValueFactory(
	            new PropertyValueFactory<>("time"));
		TableColumn orderStatetc = new TableColumn("订单状态");
		orderStatetc.setMinWidth(110);
		orderStatetc.setCellValueFactory(
	            new PropertyValueFactory<>("orderState"));
		TableColumn commentStatetc = new TableColumn("评价情况");
		commentStatetc.setMinWidth(110);
		commentStatetc.setCellValueFactory(
	            new PropertyValueFactory<>("commentState"));
		ordertable.setItems(data);
		ordertable.getColumns().addAll(orderIdtc,hotelnametc,timetc,orderStatetc,commentStatetc);
		ordertable.setEditable(false);	
		ordertable.setMaxWidth(600);
		vb.getChildren().add(ordertable);
        HBox buttonhb = new HBox();
        
        if(state==1){
			Button comment = new Button("评价");
			comment.setMaxHeight(3);
			comment.setVisible(true);
			buttonhb.getChildren().addAll(comment);
			comment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// 判断是否评价

					int index = ordertable.getSelectionModel().getSelectedIndex();
					String comment = ordertable.getItems().get(index).getCommentState();
					String hotel=ordertable.getItems().get(index).getOrderId().substring(14, 18);
					if (!comment.equals("已评价")) {
						// index是被评价的酒店的订单的索引
						Comment c = new Comment(controller,Integer.parseInt(hotel),ordertable.getItems().get(index).getOrderId());
						c.showstage();
						
					} else {
						HaveCommented hc = new HaveCommented();
						
						hc.showstage();
					}

				}
			});
		}
		
		if(state ==0){
	        Button revoke = new Button("撤销");
			revoke.setMaxHeight(3);
			revoke.setVisible(false);
			revoke.setVisible(true);
			buttonhb.getChildren().addAll(revoke);
			revoke.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					//撤销
					//controller重新获取订单信息，更新订单列表
					int revokeindex = ordertable.getSelectionModel().getSelectedIndex();
					String orderID=ordertable.getItems().get(revokeindex).getOrderId();
					try {
						controller.Cancellation(orderID);
					} catch (RemoteException e) {
						e.printStackTrace();
					}					
					data.remove(revokeindex);
				}
			});
			
		}
		
		buttonhb.setSpacing(20);
		buttonhb.setPadding(new Insets(20,0,20,0));
		buttonhb.setAlignment(Pos.CENTER);
		vb.getChildren().add(buttonhb);
		return vb;	
	}

	public static class Order{
		
		private final SimpleStringProperty orderId;
		private final SimpleStringProperty hotelname;
		private final SimpleStringProperty time;
		private final SimpleStringProperty orderState;
		private final SimpleStringProperty commentState;
	 
		private Order(String orderid, String hotelName, String t,String orderstate,String comment) {
			this.orderId = new SimpleStringProperty(orderid);
			this.hotelname = new SimpleStringProperty(hotelName);
			this.time = new SimpleStringProperty(t);
			this.orderState = new SimpleStringProperty(orderstate);
			this.commentState = new SimpleStringProperty(comment);
		}
		//最关键的地方是get、set后面首字母大写
		 public String getOrderId() {
	            return orderId.get();
	        }
	 
		 public void setOrderId(String orderid) {
	        	orderId.set(orderid);
	        }
	 
		 public String getHotelname() {
	            return hotelname.get();
	        }
	 
		 public void setHotelname(String hotelName) {
	        	hotelname.set(hotelName);
	        }
	 
		 public String getTime() {
	            return time.get();
	        }
	 
		 public void setTime(String t) {
	        	time.set(t);
	        }
		 public String getOrderState() {
	            return orderState.get();
	        }
	 
		 public void setOrderState(String orderstate) {
	        	orderState.set(orderstate);
	        }
		 public String getCommentState() {
	            return commentState.get();
	        }
	 
		 public void setCommentState(String comment) {
	        	commentState.set(comment);
	        }
	}
}