package presentation.view;

import java.rmi.RemoteException;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vo.HotelVo;

public class HotelList extends VBox {

	List<Hotel> hotelList;

	UserViewControllerService controller;
	
	final List<String> hotelName=new ArrayList<String>();
	
	public HotelList(List<HotelVo> list,UserViewControllerService controller) {
		this.controller=controller;
		for (HotelVo hotel : list) {
			String name=hotel.getName();			
			String star=String.valueOf(hotel.getStar());
			String price=String.valueOf(hotel.getMinPrice());
			String score=String.valueOf(hotel.getScore());
			String[] roomInfo=hotel.getRoomInfo().split(";");
			String[] singleHotel;
			for(int i=0;i<roomInfo.length;i++){
				singleHotel=roomInfo[i].split("+");
				hotelName.add(name);
				hotelList.add(new Hotel(name,star,price,score,"",singleHotel[0],singleHotel[2],singleHotel[1]));
			}
			
			
		}
	}	

	private final TableView<Hotel> table = new TableView<>();

	public VBox addHotelList(BorderPane mainFrame) {
		ObservableList<Hotel> data = FXCollections.observableArrayList(hotelList);
		
		VBox vb = new VBox();
		table.setEditable(false);

		TableColumn hotelnametc = new TableColumn("酒店名称");
        hotelnametc.setMaxWidth(100);
        TableColumn starleveltc = new TableColumn("星级");
        starleveltc.setMaxWidth(100);
        TableColumn lowpricetc = new TableColumn("价格");
        lowpricetc.setMaxWidth(100);
        TableColumn marktc = new TableColumn("评分");
        marktc.setMaxWidth(100);
        TableColumn roomtc = new TableColumn("房间");
        roomtc.setMaxWidth(100);
        TableColumn roomtypetc = new TableColumn("房间类型");
        roomtypetc.setMaxWidth(100);
        TableColumn roompricetc = new TableColumn("房间价格");
        roompricetc.setMaxWidth(100);
        TableColumn roomnumtc = new TableColumn("数量");
        roomnumtc.setMaxWidth(100);
        hotelnametc.setCellValueFactory(
            new PropertyValueFactory<>("hotelname")
        );
        starleveltc.setCellValueFactory(
            new PropertyValueFactory<>("starlevel")
        );
        lowpricetc.setCellValueFactory(
            new PropertyValueFactory<>("lowprice")
        );
        marktc.setCellValueFactory(
                new PropertyValueFactory<>("mark")
            );
        roomtc.setCellValueFactory(
                new PropertyValueFactory<>("room")
            );
        roomtypetc.setCellValueFactory(
                new PropertyValueFactory<>("roomtype")
            );
        roompricetc.setCellValueFactory(
                new PropertyValueFactory<>("roomprice")
            );
        roomnumtc.setCellValueFactory(
                new PropertyValueFactory<>("roomnum")
            );
        roomtc.getColumns().addAll(roomtypetc, roompricetc,roomnumtc);
        table.setItems(data);
        table.setMaxWidth(800);
        table.getColumns().addAll(hotelnametc, starleveltc,  lowpricetc, marktc, roomtc);
        vb.getChildren().add(table);
        HBox buttonhb = new HBox();
        buttonhb.setSpacing(20);
        buttonhb.setAlignment(Pos.CENTER);
        Button produceOrder = new Button("生成订单");
        Button button = new Button("查看详细信息");
        buttonhb.getChildren().addAll(produceOrder,button);
        button.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int selecterhotel = table.getSelectionModel().getSelectedIndex();
				String hotelname=table.getItems().get(selecterhotel).getHotelname();
				HotelVo vo = null;
				try {
					vo = controller.getHotelInfoByName(hotelname);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				HotelFrame hf = new HotelFrame(controller,vo);
				GridPane hotelgp = hf.hotelFrame(mainFrame);
				mainFrame.setCenter(hotelgp);
				
			}
        });
      
		produceOrder.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int index = table.getSelectionModel().getSelectedIndex();
				//根据index获得酒店列表中的酒店
				String hotelname = hotelName.get(index);
				ProduceOrder po = new ProduceOrder(controller);
				po.produce(hotelname,"");
				}
			});
        vb.getChildren().add(buttonhb);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
		return vb;
	}
	public static class Hotel {
		private final SimpleStringProperty hotelname;
		private final SimpleStringProperty starlevel;
		private final SimpleStringProperty lowprice;
		private final SimpleStringProperty mark;
		private final SimpleStringProperty room;
		private final SimpleStringProperty roomtype;
		private final SimpleStringProperty roomprice;
		private final SimpleStringProperty roomnum;
	 
		private Hotel(String hn, String star, String price, String m, String r, String rt, String rp,String num) {
			this.hotelname = new SimpleStringProperty(hn);
			this.starlevel = new SimpleStringProperty(star);
			this.lowprice = new SimpleStringProperty(price);
			this.mark = new SimpleStringProperty(m);
			this.room = new SimpleStringProperty(r);
			this.roomtype = new SimpleStringProperty(rt);
			this.roomprice = new SimpleStringProperty(rp);
			this.roomnum = new SimpleStringProperty(num);
		}
	 
		public String getHotelname() {
			return hotelname.get();
		}	 
		public void setHotelname(String hn) {
			hotelname.set(hn);
		} 
		public String getStarlevel() {
			return starlevel.get();
		}	 
		public void setStarlevel(String s) {
			starlevel.set(s);
		}
		public String getLowprice() {
			return lowprice.get();
		}	 
		public void setLowprice(String price) {
			lowprice.set(price);
		}
		public String getMark() {
			return mark.get();
		}
	 
		public void setMark(String m) {
			mark.set(m);
		}
		public String getRoom() {
			return room.get();
		}
	 
		public void setRoom(String r) {
			room.set(r);
		}
		public String getRoomtype() {
			return roomtype.get();
		}
	 
		public void setRoomtype(String rt) {
			roomtype.set(rt);
		}
		public String getRoomprice() {
			return roomprice.get();
		}	 
		public void setRoomprice(String rp) {
			roomprice.set(rp);
		}
		public String getRoomnum() {
			return roomnum.get();
		}	 
		public void setRoomnum(String num) {
			roomnum.set(num);
		}
	}
}