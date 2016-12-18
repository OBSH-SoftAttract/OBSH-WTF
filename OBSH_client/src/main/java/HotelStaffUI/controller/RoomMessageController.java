package HotelStaffUI.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RoomMessageController {
	
	private Button basic;
	
	@FXML
	private TableView table1;
	
	@FXML
	private TableView table2;
	
	@FXML
	private TableView table3;
	
	@FXML
	private TableView table4;
	
	@FXML
	private TableColumn tp1,ri1,p1,ud1,et1,lt1,at1;  
	
	@FXML
	private TableColumn tp2,ri2,p2,ud2,et2,lt2,at2;   
	
	@FXML
	private TableColumn tp3,ri3,p3,ud3,et3,lt3,at3;  
	
	@FXML
	private TableColumn tp4,ri4,p4,ud4,et4,lt4,at4;    
	
	/**
	 * 这里同order那里
	 * 下面4个才是显示的 不过一开始也要有东西吧
	 * 初始化用来解决能否关闭的问题
	 */
	@FXML
	private void initialize() {
		final ObservableList<Room> data = FXCollections.observableArrayList(
				new Room("","",0,"","","",""));

        tp1.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
        ri1.setCellValueFactory(
                new PropertyValueFactory<>("roomid")
            );
        p1.setCellValueFactory(
                new PropertyValueFactory<>("price")
            );
        ud1.setCellValueFactory(
                new PropertyValueFactory<>("userid")
            );
        et1.setCellValueFactory(
                new PropertyValueFactory<>("entertime")
            );
        lt1.setCellValueFactory(
                new PropertyValueFactory<>("outtime")
            );
        at1.setCellValueFactory(
                new PropertyValueFactory<>("actual")
            );
		table1.setItems(data);
		
		
        tp2.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
        ri2.setCellValueFactory(
                new PropertyValueFactory<>("roomid")
            );
        p2.setCellValueFactory(
                new PropertyValueFactory<>("price")
            );
        ud2.setCellValueFactory(
                new PropertyValueFactory<>("userid")
            );
        et2.setCellValueFactory(
                new PropertyValueFactory<>("entertime")
            );
        lt2.setCellValueFactory(
                new PropertyValueFactory<>("outtime")
            );
        at2.setCellValueFactory(
                new PropertyValueFactory<>("actual")
            );
		table2.setItems(data);
		
		
        tp3.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
        ri3.setCellValueFactory(
                new PropertyValueFactory<>("roomid")
            );
        p3.setCellValueFactory(
                new PropertyValueFactory<>("price")
            );
        ud3.setCellValueFactory(
                new PropertyValueFactory<>("userid")
            );
        et3.setCellValueFactory(
                new PropertyValueFactory<>("entertime")
            );
        lt3.setCellValueFactory(
                new PropertyValueFactory<>("outtime")
            );
        at3.setCellValueFactory(
                new PropertyValueFactory<>("actual")
            );
		table3.setItems(data);
		
		
        tp4.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
        ri4.setCellValueFactory(
                new PropertyValueFactory<>("roomid")
            );
        p4.setCellValueFactory(
                new PropertyValueFactory<>("price")
            );
        ud4.setCellValueFactory(
                new PropertyValueFactory<>("userid")
            );
        et4.setCellValueFactory(
                new PropertyValueFactory<>("entertime")
            );
        lt4.setCellValueFactory(
                new PropertyValueFactory<>("outtime")
            );
        at4.setCellValueFactory(
                new PropertyValueFactory<>("actual")
            );
		table4.setItems(data);
		
	}
	
	@FXML
	private void showAll() {
		final ObservableList<Room> data = FXCollections.observableArrayList(
				new Room("","",1.0,"","","",""));

        tp1.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
        ri1.setCellValueFactory(
                new PropertyValueFactory<>("roomid")
            );
        p1.setCellValueFactory(
                new PropertyValueFactory<>("price")
            );
        ud1.setCellValueFactory(
                new PropertyValueFactory<>("userid")
            );
        et1.setCellValueFactory(
                new PropertyValueFactory<>("entertime")
            );
        lt1.setCellValueFactory(
                new PropertyValueFactory<>("outtime")
            );
        at1.setCellValueFactory(
                new PropertyValueFactory<>("actual")
            );
		table1.setItems(data);
	}
	
	@FXML
	private void showStandard() {
		final ObservableList<Room> data = FXCollections.observableArrayList(
				new Room("","",2.0,"","","",""));

        tp2.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
        ri2.setCellValueFactory(
                new PropertyValueFactory<>("roomid")
            );
        p2.setCellValueFactory(
                new PropertyValueFactory<>("price")
            );
        ud2.setCellValueFactory(
                new PropertyValueFactory<>("userid")
            );
        et2.setCellValueFactory(
                new PropertyValueFactory<>("entertime")
            );
        lt2.setCellValueFactory(
                new PropertyValueFactory<>("outtime")
            );
        at2.setCellValueFactory(
                new PropertyValueFactory<>("actual")
            );
		table2.setItems(data);
	}
	
	@FXML
	private void showBigRoom() {
		final ObservableList<Room> data = FXCollections.observableArrayList(
			new Room("","",3.0,"","","",""));

		tp3.setCellValueFactory(
            new PropertyValueFactory<>("type")
        );
		ri3.setCellValueFactory(
            new PropertyValueFactory<>("roomid")
        );
		p3.setCellValueFactory(
            new PropertyValueFactory<>("price")
        );
    ud3.setCellValueFactory(
            new PropertyValueFactory<>("userid")
        );
    et3.setCellValueFactory(
            new PropertyValueFactory<>("entertime")
        );
    lt3.setCellValueFactory(
            new PropertyValueFactory<>("outtime")
        );
    at1.setCellValueFactory(
            new PropertyValueFactory<>("actual")
        );
	table3.setItems(data);
	}
	
	@FXML
	private void showTwice() {
		final ObservableList<Room> data = FXCollections.observableArrayList(
				new Room("","",4.0,"","","",""));

        tp4.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
        ri4.setCellValueFactory(
                new PropertyValueFactory<>("roomid")
            );
        p4.setCellValueFactory(
                new PropertyValueFactory<>("price")
            );
        ud4.setCellValueFactory(
                new PropertyValueFactory<>("userid")
            );
        et4.setCellValueFactory(
                new PropertyValueFactory<>("entertime")
            );
        lt4.setCellValueFactory(
                new PropertyValueFactory<>("outtime")
            );
        at4.setCellValueFactory(
                new PropertyValueFactory<>("actual")
            );
		table4.setItems(data);
	}
	
	@FXML
	private void enterRooms() {
		Stage stage = new Stage();
		Text typet = new Text("客房类型：");
		ObservableList<String> options = FXCollections.observableArrayList(
		"单人间",
        "大床房",
        "双床房",
        "家庭房",
        "套间"
    );
		ComboBox type = new ComboBox(options);
		type.setValue("单人间");
		type.setMaxWidth(120);
		Text numt = new Text("数量：");
		TextField numtf = new TextField();
		numtf.setMaxWidth(120);
		Text pricet = new Text("价格：");
		TextField pricetf = new TextField();
		pricetf.setMaxWidth(120);
		Button confirm = new Button("确定");		
		Button cancel = new Button("取消");		
		HBox hb = new HBox();
		hb.getChildren().addAll(confirm,cancel);
		hb.setSpacing(20);
		GridPane KeyInRoomgrid = new GridPane();
		KeyInRoomgrid.add(typet, 0, 0);
		KeyInRoomgrid.add(type, 1, 0);
		KeyInRoomgrid.add(numt, 0, 1);
		KeyInRoomgrid.add(numtf, 1, 1);
		KeyInRoomgrid.add(pricet, 0, 2);
		KeyInRoomgrid.add(pricetf, 1, 2);
		KeyInRoomgrid.add(hb, 1, 3);
		hb.setAlignment(Pos.CENTER_RIGHT);
		KeyInRoomgrid.setVgap(20);
		KeyInRoomgrid.setHgap(20);
		KeyInRoomgrid.setMaxSize(400, 400);
		KeyInRoomgrid.setMinSize(400, 400);
		KeyInRoomgrid.setAlignment(Pos.CENTER);
		Scene keyinroomscene = new Scene(KeyInRoomgrid);
		stage.setScene(keyinroomscene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
		keyinroomscene.getStylesheets().add("HotelStaffUI/view/keyinroom.css");

		confirm.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//controller录入数据
				int index = type.getSelectionModel().getSelectedIndex();
				String number = numtf.getText();
				String price = pricetf.getText();
			}
			});
		cancel.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
			});


	}
	
	@FXML
	private void checkIn() {
		/*
		 * 入住按钮的监听 讨论一下需要做什么
		 */
	}
	
	@FXML
	private void checkOut() {
		/*
		 * 退房按钮的监听
		 */
	}
	
	/**
	 * order列表的构造函数
	 * @author asus1
	 *
	 */
	public static class Room {
		private final SimpleStringProperty type;      //房间类型
		private final SimpleStringProperty roomid;    //房间号  不要被起名疑惑后三位id是房间号 友情提醒
		private final SimpleDoubleProperty price;     //房间价格
		private final SimpleStringProperty used;      //占用情况 还不知道这里需要什么类型 暂时string吧
		private final SimpleStringProperty entertime; //入住时间
		private final SimpleStringProperty outtime;   //预计退房时间
		private final SimpleStringProperty actual;     //实际离开时间

	 
		private Room(String type, String roomid, double price,String used, String entertime, String outtime, String actual) {
			this.type = new SimpleStringProperty(type);
			this.roomid = new SimpleStringProperty(roomid);
			this.price = new SimpleDoubleProperty(price);
			this.used = new SimpleStringProperty(used);
			this.entertime = new SimpleStringProperty(entertime);
			this.outtime = new SimpleStringProperty(outtime);
			this.actual = new SimpleStringProperty(actual);	
		}
	 
		public String getType() {
			return type.get();
		}	 
		public void setType(String id) {
			type.set(id);
		} 
		public String getRoomid() {
			return roomid.get();
		}	 
		public void setRoomid(String in) {
			roomid.set(in);
		}
		public String getUsed() {
			return used.get();
		}	 
		public void setUsed(String ud) {
			used.set(ud);
		}
		public String getEntertime() {
			return entertime.get();
		}
	 
		public void setEntertime(String et) {
			entertime.set(et);
		}
		
		public String getOuttime() {
			return outtime.get();
		}
	 
		public void setOuttime(String ot) {
			outtime.set(ot);
		}
		
		public String getActual() {
			return actual.get();
		}
	 
		public void setActual(String at) {
			actual.set(at);
		}
		
		public double getPrice() {
			return price.get();
		}
	 
		public void setPrice(double p) {
			price.set(p);
		}


	}

}


