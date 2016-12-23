package presentation.view;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import presentation.controller.UserViewControllerImpl;

public class AddRoomList {
	
	private UserViewControllerImpl controller;
	String HotelRooms[];
	int index=0;
	public AddRoomList(UserViewControllerImpl controller,String HotelRooms[]){
		this.controller=controller;
		this.HotelRooms=HotelRooms;
	}
	
	private final TableView<RoomList> table = new TableView<>();
	ObservableList<RoomList> data;
	//房型列表
	public VBox addRoomList(String hotelName){
		
		VBox v = new VBox();
		table.setEditable(false);	
		List<RoomList> list=new ArrayList<RoomList>();
		for(int i=0;i<HotelRooms.length;i++){
			String ssr[]=HotelRooms[i].split("+");
			list.add(new RoomList(ssr[0],ssr[1],ssr[2]));
		}
		
		data = FXCollections.observableArrayList(list);
		
		TableColumn roomtypetc = new TableColumn("房型");
        TableColumn bedtypetc = new TableColumn("数量");
        TableColumn pricetc = new TableColumn("房价");
        roomtypetc.setCellValueFactory(
            new PropertyValueFactory<>("roomtype")
        );
        bedtypetc.setCellValueFactory(
            new PropertyValueFactory<>("bedtype")
        );
        pricetc.setCellValueFactory(
            new PropertyValueFactory<>("price")
        );
        table.getColumns().addAll(roomtypetc, bedtypetc, pricetc);
        table.setItems(data);
        v.getChildren().add(table);
        Button button = new Button("预定");
        v.getChildren().add(button);
        button.setAlignment(Pos.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//预定
				index = table.getSelectionModel().getSelectedIndex();
				ProduceOrder po = new ProduceOrder(controller);
				po.produce(hotelName,table.getItems().get(index).getRoomtype());
			}
        });
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
		return v;
	}
	public static class RoomList{
		private final SimpleStringProperty roomtype;
		private final SimpleStringProperty bedtype;
		private final SimpleStringProperty price;
	 
		private RoomList(String rt, String bt, String p) {
			this.roomtype = new SimpleStringProperty(rt);
			this.bedtype = new SimpleStringProperty(bt);
			this.price = new SimpleStringProperty(p);
		}
	 
		public String getRoomtype() {
			return roomtype.get();
		}
	 
		public void setRoomtype(String rt) {
			roomtype.set(rt);
		}
	 
		public String getBedtype() {
			return bedtype.get();
		}
	 
		public void setBedtype(String bt) {
			bedtype.set(bt);
		}
	 
		public String getPrice() {
			return price.get();
		}
	 
		public void setPrice(String p) {
			price.set(p);
		}
	}
}
