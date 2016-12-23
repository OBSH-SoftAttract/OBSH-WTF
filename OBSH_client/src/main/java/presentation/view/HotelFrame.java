package presentation.view;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import presentation.controller.UserViewControllerImpl;
import vo.HotelVo;

public class HotelFrame {

	private UserViewControllerImpl controller;
	private HotelVo hotel;

	public HotelFrame(UserViewControllerImpl controller, HotelVo hotelvo) {
		this.controller = controller;
		this.hotel = hotelvo;
	}

	public GridPane hotelFrame(BorderPane mainFrame) {
		GridPane gp = new GridPane();
		// 获取选中的酒店名称，星级，价格，评分，简介，设施服务
		String hotelname = hotel.getName();
		String price = String.valueOf(hotel.getMinPrice());
		Text pricet = new Text("最低价格：");
		Text markt = new Text("评分：");
		String mark = String.valueOf(hotel.getScore());
		Text start = new Text("星级：");
		String starlevel = String.valueOf(hotel.getStar());
		String Location = "地址："+hotel.getLocation();
		String services = "服务："+hotel.getServices();
		String Intro = "简介："+hotel.getBriefInfo();

		HBox hb1 = new HBox();
		hb1.getChildren().addAll(addText(hotelname), pricet, addText(price));
		hb1.setSpacing(15);
		hb1.setAlignment(Pos.CENTER);
		gp.add(hb1, 0, 0);
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(markt, addText(mark), start, addText(starlevel));
		hb2.setSpacing(15);
		hb2.setAlignment(Pos.CENTER);
		gp.add(hb2, 0, 1);
		gp.add(addText(Location), 0, 2);
		gp.add(addText(services), 0, 3);
		gp.add(addText(Intro), 0, 4);
		gp.setVgap(20);
		gp.setAlignment(Pos.TOP_CENTER);

		Button tb1 = new Button("房型列表");
		Button tb2 = new Button("优惠策略");
		Button tb3 = new Button("用户评价");
		Button tb4 = new Button("历史预定");
		HBox hb = new HBox();
		hb.getChildren().addAll(tb1, tb2, tb3, tb4);
		gp.add(hb, 0, 5);
		BorderPane b = new BorderPane();
		tb1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String HotelRooms[] = hotel.getRoomInfo().split(";");
				AddRoomList rl = new AddRoomList(controller,HotelRooms);
				VBox vb1 = rl.addRoomList(hotelname);
				b.setCenter(vb1);
			}
		});
		tb2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				PrePrice pp = new PrePrice(controller);
				VBox vb2 = pp.addPrePrice(mainFrame, hotelname);
				b.setCenter(vb2);
			}
		});
		tb3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				UserComment uc = new UserComment();
				
				VBox vb3 = null;
				try {
					vb3 = uc.addUserComment(controller.getHotelInfoByName(hotelname).getSummary());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				b.setCenter(vb3);
			}
		});
		
		tb4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				HistoryList rl = new HistoryList(controller);
				VBox vb4 = rl.addHistoryList(-1);
				b.setCenter(vb4);
			}
		});
		
		gp.add(b, 0, 6);
		return gp;
	}
	

	// 对text的字体设定
	public Text addText(String s) {
		Text text = new Text(s);
		text.setFont(Font.font("冬青黑体简体中文", 15));
		return text;
	}
}
