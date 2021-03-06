package HotelStaffUI.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.controller.UserViewControllerImpl;
import vo.HotelDeliverVo;
import vo.HotelroomVo;
import vo.UserVo;

public class RoomMessageController {
	
	private UserViewControllerImpl controller = new UserViewControllerImpl();

	@FXML
	private TableView<Room> table1;
	
	@FXML
	private TableView<Room> table2;
	
	@FXML
	private TableView<Room> table3;
	
	@FXML
	private TableView<Room> table4;
	
	@FXML
	private TableColumn tp1,ri1,p1,ud1,et1,lt1;  
	
	@FXML
	private TableColumn tp2,ri2,p2,ud2,et2,lt2;   
	
	@FXML
	private TableColumn tp3,ri3,p3,ud3,et3,lt3;  
	
	@FXML
	private TableColumn tp4,ri4,p4,ud4,et4,lt4;    
	
	private UserVo hotelstaffVo;
	
	private List<Room> roomlist = new ArrayList<Room>();
	/**
	 * 这里正确的方法是只利用这一个list 调用一次controller
	 */
	
	private HotelDeliverVo hoteldeliver;
	
	/*@FXML
	private void initialize() {}*/

	/**
	 * 显示的是所有的房间类型
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void showAll() {
		try {
			int hotelID = hoteldeliver.getHotelStaffID();
			controller.setUserID(hotelID);
			List<HotelroomVo> allrooms;
			allrooms = controller.getHotelRoomByHotelID();
			for (HotelroomVo vo : allrooms) {
				String type = vo.getRoomType();
				int roomid = vo.getRoomID();
				double price = vo.getPrice();
				boolean used = vo.isIfOccupied();
				String occupied;
				if(used)
					occupied = "是";
				else
					occupied = "否";
				String entertime = String.valueOf(vo.getTimeCheckIn());
				String outtime = String.valueOf(vo.getAttemptedLeaveTime());	

				roomlist.add(new Room(type, roomid, price,occupied,entertime, outtime));
			}

				final ObservableList<Room> data = FXCollections.observableArrayList(roomlist);

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
		                new PropertyValueFactory<>("used")
		            );
		        et1.setCellValueFactory(
		                new PropertyValueFactory<>("entertime")
		            );
		        lt1.setCellValueFactory(
		                new PropertyValueFactory<>("outtime")
		            );
				table1.setItems(data);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 显示所有的标准间
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void showStandard() {
		roomlist = new ArrayList<Room>();
		List<HotelroomVo> standrooms;
		try {
			standrooms = controller.getHotelRoomByType("标准间");
			for (HotelroomVo vo : standrooms) {
				String type = vo.getRoomType();
				int roomid = vo.getRoomID();
				double price = vo.getPrice();
				boolean used = vo.isIfOccupied();
				String occupied;
				if(used)
					occupied = "是";
				else
					occupied = "否";
				String entertime = String.valueOf(vo.getTimeCheckIn());
				String outtime = String.valueOf(vo.getAttemptedLeaveTime());	

				roomlist.add(new Room(type, roomid, price,occupied,entertime, outtime));
				
			}
		
			final ObservableList<Room> data = FXCollections.observableArrayList(roomlist);

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
	                new PropertyValueFactory<>("used")
	            );
	        et2.setCellValueFactory(
	                new PropertyValueFactory<>("entertime")
	            );
	        lt2.setCellValueFactory(
	                new PropertyValueFactory<>("outtime")
	            );

			table2.setItems(data);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	private void showBigRoom() {
		roomlist = new ArrayList<Room>();
		List<HotelroomVo> standrooms;
		try {
			standrooms = controller.getHotelRoomByType("大床房");
			for (HotelroomVo vo : standrooms) {
				String type = vo.getRoomType();
				int roomid = vo.getRoomID();
				double price = vo.getPrice();
				boolean used = vo.isIfOccupied();
				String occupied;
				if(used)
					occupied = "是";
				else
					occupied = "否";
				String entertime = String.valueOf(vo.getTimeCheckIn());
				String outtime = String.valueOf(vo.getAttemptedLeaveTime());	

				roomlist.add(new Room(type, roomid, price,occupied,entertime, outtime));
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final ObservableList<Room> data = FXCollections.observableArrayList(roomlist);

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
            new PropertyValueFactory<>("used")
        );
		et3.setCellValueFactory(
            new PropertyValueFactory<>("entertime")
        );
		lt3.setCellValueFactory(
            new PropertyValueFactory<>("outtime")
        );
		table3.setItems(data);
	}
	
	@SuppressWarnings({ "unchecked" })
	@FXML
	private void showTwice() {
		List<HotelroomVo> standrooms;
		try {
			standrooms = controller.getHotelRoomByType("家庭房");
			for (HotelroomVo vo : standrooms) {
				String type = vo.getRoomType();
				int roomid = vo.getRoomID();
				double price = vo.getPrice();
				boolean used = vo.isIfOccupied();
				String occupied;
				if(used)
					occupied = "是";
				else
					occupied = "否";
				String entertime = String.valueOf(vo.getTimeCheckIn());
				String outtime = String.valueOf(vo.getAttemptedLeaveTime());	

				roomlist.add(new Room(type, roomid, price,occupied,entertime, outtime));
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final ObservableList<Room> data = FXCollections.observableArrayList(roomlist);


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
                new PropertyValueFactory<>("used")
            );
        et4.setCellValueFactory(
                new PropertyValueFactory<>("entertime")
            );
        lt4.setCellValueFactory(
                new PropertyValueFactory<>("outtime")
            );
		table4.setItems(data);
	}
	
	/**
	 * 录入房间
	 */
	@FXML
	private void enterRooms() {
		Stage stage = new Stage();
		Text typet = new Text("客房类型：");
		ObservableList<String> options = FXCollections.observableArrayList(
		"标准间",
        "大床房",
        "家庭房",
        "豪华房"
    );
		ComboBox<String> type = new ComboBox<String>(options);
		type.setValue("标准间");
		type.setMaxWidth(120);
		Text numt = new Text("房间号：");
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
				String roomType = options.get(index);
				try {
					int hotelID = controller.getUserID();
					HotelroomVo roomVo = new HotelroomVo(Integer.parseInt(number),hotelID,roomType,Double.parseDouble(price));
					if(controller.addRoom(roomVo)) {
						stage.close();
						/**
						 * 要根据roomType给hotel里的roominfo进行加减变化
						 */
						
								roomlist.add(new Room(roomType, roomVo.getRoomID(), roomVo.getPrice(),"否",String.valueOf(roomVo.getTimeCheckIn()), String.valueOf(roomVo.getAttemptedLeaveTime())));
								System.out.println(roomlist.size());
								final ObservableList<Room> data = FXCollections.observableArrayList(roomlist);

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
						                new PropertyValueFactory<>("used")
						            );
						        et1.setCellValueFactory(
						                new PropertyValueFactory<>("entertime")
						            );
						        lt1.setCellValueFactory(
						                new PropertyValueFactory<>("outtime")
						            );
								table1.setItems(data);
						
						
						
						Stage stage1 = new Stage();
						Text text = new Text("添加成功");
						Button button = new Button("确定");
						VBox vb = new VBox();
						vb.setSpacing(10);
						vb.getChildren().addAll(text, button);
						vb.setMinSize(200, 200);
						vb.setMaxSize(200, 200);
						vb.setAlignment(Pos.CENTER);
						Scene scene = new Scene(vb);
						stage1.setScene(scene);
						stage1.show();
						button.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								/**
								 * 要有一次刷新啊
								 */
								stage1.close();
							}
						});
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
	private void checkIn1() {
		/**
		 * 有一个刷新的问题在这里
		 */
		int index = table1.getSelectionModel().getSelectedIndex();
		String roomType = table1.getItems().get(index).getType();
		int roomid = table1.getItems().get(index).getRoomid();
		double price = table1.getItems().get(index).getPrice();
		try {
			int hotelID = controller.getUserID();
			HotelroomVo vo = new HotelroomVo(roomid,hotelID,roomType,price);
			controller.CheckIn(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void checkIn2() {
		/**
		 * 有一个刷新的问题在这里
		 */
		int index = table2.getSelectionModel().getSelectedIndex();
		String roomType = table2.getItems().get(index).getType();
		int roomid = table2.getItems().get(index).getRoomid();
		double price = table2.getItems().get(index).getPrice();
		try {
			int hotelID = controller.getUserID();
			HotelroomVo vo = new HotelroomVo(roomid,hotelID,roomType,price);
			controller.CheckIn(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void checkIn3() {
		/**
		 * 有一个刷新的问题在这里
		 */
		int index = table3.getSelectionModel().getSelectedIndex();
		String roomType = table3.getItems().get(index).getType();
		int roomid = table3.getItems().get(index).getRoomid();
		double price = table3.getItems().get(index).getPrice();
		try {
			int hotelID = controller.getUserID();
			HotelroomVo vo = new HotelroomVo(roomid,hotelID,roomType,price);
			controller.CheckIn(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void checkIn4() {
		/**
		 * 有一个刷新的问题在这里
		 */
		int index = table4.getSelectionModel().getSelectedIndex();
		String roomType = table4.getItems().get(index).getType();
		int roomid = table4.getItems().get(index).getRoomid();
		double price = table4.getItems().get(index).getPrice();
		try {
			int hotelID = controller.getUserID();
			HotelroomVo vo = new HotelroomVo(roomid,hotelID,roomType,price);
			controller.CheckIn(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void checkOut1() {
		/*
		 * 退房按钮的监听
		 */
		int index = table1.getSelectionModel().getSelectedIndex();
		int roomID = table1.getItems().get(index).getRoomid();
		int hotelID = controller.getUserID();
		try {
			controller.CheckOut(hotelID, roomID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void checkOut2() {
		/*
		 * 退房按钮的监听
		 */
		int index = table2.getSelectionModel().getSelectedIndex();
		int roomID = table2.getItems().get(index).getRoomid();
		int hotelID = controller.getUserID();
		try {
			controller.CheckOut(hotelID, roomID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void checkOut3() {
		/*
		 * 退房按钮的监听
		 */
		int index = table3.getSelectionModel().getSelectedIndex();
		int roomID = table3.getItems().get(index).getRoomid();
		int hotelID = controller.getUserID();
		try {
			controller.CheckOut(hotelID, roomID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void checkOut4() {
		/*
		 * 退房按钮的监听
		 */
		int index = table4.getSelectionModel().getSelectedIndex();
		int roomID = table4.getItems().get(index).getRoomid();
		int hotelID = controller.getUserID();
		try {
			controller.CheckOut(hotelID, roomID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * order列表的构造函数
	 * @author asus1
	 *
	 */
	public static class Room {
		private final SimpleStringProperty type;      //房间类型
		private final SimpleIntegerProperty roomid;   //房间号  
		private final SimpleDoubleProperty price;     //房间价格
		private final SimpleStringProperty used;      //占用情况 
		private final SimpleStringProperty entertime; //入住时间
		private final SimpleStringProperty outtime;   //预计退房时间

		private Room(String type, int roomid, double price,String used, String entertime, String outtime) {
			this.type = new SimpleStringProperty(type);
			this.roomid = new SimpleIntegerProperty(roomid);
			this.price = new SimpleDoubleProperty(price);
			this.used = new SimpleStringProperty(used);
			this.entertime = new SimpleStringProperty(entertime);
			this.outtime = new SimpleStringProperty(outtime);
		}
	 
		public String getType() {
			return type.get();
		}	 
		public void setType(String id) {
			type.set(id);
		} 
		public int getRoomid() {
			return roomid.get();
		}	 
		public void setRoomid(int in) {
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
		
		public double getPrice() {
			return price.get();
		}
	 
		public void setPrice(double p) {
			price.set(p);
		}


	}

}


