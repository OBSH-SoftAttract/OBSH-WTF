package presentation.view;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import vo.OrderVo;

public class HistoryList {	
	private UserViewControllerService controller;
	
	public HistoryList(UserViewControllerService controller){
		this.controller=controller;
	}
	
	//预定酒店历史
	private final String State[]={"正常订单","正常订单","异常订单","已撤销订单"};
	
	private final TableView<History> hoteltable = new TableView<>();  
	
	private ObservableList<History> data; 

	public VBox addHistoryList(int state){		
		final VBox vb = new VBox();
		
		List<History> list=new ArrayList<History>();
		try {
			int userID=controller.GetPresentUserInfo().getID();
			List<OrderVo> orderList=controller.getAllHistoryOrdersByUserID(userID);
		     for(OrderVo vo:orderList){
		    	 if(vo.getOrderState()==state||(state==0&&vo.getOrderState()==1)){
		    		 int hotelID=vo.getHotelID();
		    		 String name=controller.getHotelNameByHotelID(hotelID);
		    		 String date=vo.getStartTime().toString().substring(0, 10);
		    		 String OrderState=State[vo.getOrderState()];
		    		 list.add(new History(name,date,OrderState));
		    	 }
		     }
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		data=FXCollections.observableArrayList(list);
		
		//酒店列表
		//所有命名后面添加tc表示类型为tablecolumn
		TableColumn hotelnametc = new TableColumn("酒店名称");
		hotelnametc.setMinWidth(150);
		hotelnametc.setCellValueFactory(
	            new PropertyValueFactory<>("hotelname"));
		TableColumn timetc = new TableColumn("预订时间");
		timetc.setMinWidth(150);
		timetc.setCellValueFactory(
	            new PropertyValueFactory<>("time"));
		TableColumn orderStatetc = new TableColumn("订单状态");
		orderStatetc.setMinWidth(150);
		orderStatetc.setCellValueFactory(
	            new PropertyValueFactory<>("orderState"));
		hoteltable.setItems(data);
		hoteltable.getColumns().addAll(hotelnametc,timetc,orderStatetc);
		hoteltable.setEditable(false);	
		hoteltable.setMaxWidth(450);
		vb.getChildren().add(hoteltable);
		return vb;		
	}
	public static class History{		
		private final SimpleStringProperty hotelname;
		private final SimpleStringProperty time;
		private final SimpleStringProperty orderState;
	 
		private History(String hName, String t, String orderstate) {
			this.hotelname = new SimpleStringProperty(hName);
			this.time = new SimpleStringProperty(t);
			this.orderState = new SimpleStringProperty(orderstate);
		}
		//最关键的地方是get、set后面首字母大写
		 public String getHotelname() {
	            return hotelname.get();
	        }
	 
	        public void setHotelname(String hName) {
	        	hotelname.set(hName);
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
	}
}
