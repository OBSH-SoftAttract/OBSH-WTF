package presentation.view;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;
import presentation.view.HotelList;
import presentation.view.UserViewControllerService;
import vo.HotelVo;
import vo.UserVo;

public class UserMainFrame {
	private Button searchIcon;
	private Button button;
	private int priceSelectIndex;
	private int roomTypeSelectIndex;
	private int priceSort;
	private final String pattern = "yyyy-MM-dd";
	private DatePicker checkinDatePicker;
	private DatePicker checkoutDatePicker;
	private LocalDate checkindate;
	private LocalDate checkoutdate;
	/*客户主界面
     * 包括退出的链接，跳转到搜索酒店界面，查看订单界面，维护个人信息界面的按钮
     *  以及要求用户输入关于地址商圈信息的搜索框
     */
	
	private UserViewControllerService controller;
	
	public UserMainFrame(UserViewControllerService controller){
		this.controller=controller;
	}
	
	public BorderPane jumptoUserMainFrame() {
		BorderPane mainFrame = new BorderPane();
		VBox guideline = new VBox();
		//三个用于界面跳转的按钮
		Button searchHotel = new Button();
		searchHotel.setText("搜索酒店信息");
		searchHotel.setMaxSize(130, 40);
		searchHotel.setMinSize(130, 40);
		Button checkOrder = new Button();
		checkOrder.setText("查看订单信息");
		checkOrder.setMaxSize(130, 40);
		checkOrder.setMinSize(130, 40);
		Button maintainPersonalInfo = new Button();
		maintainPersonalInfo.setText("维护个人信息");	
		maintainPersonalInfo.setMaxSize(130, 40);
		maintainPersonalInfo.setMinSize(130, 40);
		Button reversehistory = new Button();
		reversehistory.setText("查看预定历史");	
		reversehistory.setMaxSize(130, 40);
		reversehistory.setMinSize(130, 40);
		guideline.getChildren().addAll(searchHotel,checkOrder,maintainPersonalInfo,reversehistory);
		guideline.setSpacing(30);
		guideline.setAlignment(Pos.CENTER);
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		Button flexiblebt = new Button();
		Button expandbt = new Button();
		HBox label = new HBox();
		label.getChildren().add(guideline);
		label.setMinSize(160, 458);
		label.setMaxSize(160, 458);
		flexiblebt.setMinSize(45, 90);
		flexiblebt.setMaxSize(45, 90);
		expandbt.setMinSize(45, 90);
		expandbt.setMaxSize(45, 90);
		hb1.setPadding(new Insets(305,0,0,0));
		hb1.getChildren().add(flexiblebt);
		hb2.setPadding(new Insets(40,0,0,0));
		hb2.getChildren().addAll(label,expandbt);
		hb2.setAlignment(Pos.CENTER);
		mainFrame.setLeft(hb1);
		//搜索框	
		HBox searchhb = new HBox();		
		TextField locationtf = new TextField();
		TextField commercialtf = new TextField();
		locationtf.setMinSize(150, 30);
		locationtf.setMaxSize(150, 30);
		commercialtf.setMinSize(150, 30);
		commercialtf.setMaxSize(150, 30);
		searchhb.setSpacing(15);
		searchhb.getChildren().addAll(addText("地址"),locationtf,addText("商圈"),commercialtf);
		searchhb.setPadding(new Insets(100,150,100,150));
		searchhb.setSpacing(10);
		searchhb.setAlignment(Pos.CENTER);
		button = AddSearchButton();
		searchhb.getChildren().add(button);
		mainFrame.setCenter(searchhb);			

		HBox top = new HBox();
	    Button close = new Button();
	    close.setMaxSize(30,30);
	    close.setMinSize(30,30);
	    top.getChildren().add(close);
	    top.setMaxHeight(30);
	    top.setPadding(new Insets(0,0,0,0));
	    close.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
	    });
	    mainFrame.setTop(top);
		searchHotel.getStyleClass().add("SearchHotel");
		checkOrder.getStyleClass().add("CheckOrder");
		maintainPersonalInfo.getStyleClass().add("MaintainPersonalInfo");
		reversehistory.getStyleClass().add("ReverseHistory");
		flexiblebt.getStyleClass().add("FlexibleButton");
		label.getStyleClass().add("Label");
		expandbt.getStyleClass().add("FlexibleButton");
		button.getStyleClass().add("SearchButton");
		close.getStyleClass().add("CloseButton");
		flexiblebt.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				mainFrame.setLeft(hb2);
			}
		});
		expandbt.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				mainFrame.setLeft(hb1);
			}
		});
		//搜索按钮的事件
		searchIcon.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//跳至搜索酒店信息主界面
				String address = locationtf.getText();
				String commercial = commercialtf.getText();
				if(!address.equals("")&&!commercial.equals("")){
					mainFrame.setLeft(hb1);
					VBox searchHotelvb = jumptoSearchHotelMainFrame(mainFrame,address,commercial);
					mainFrame.setCenter(searchHotelvb);					
				}	
			}
		});
		
		searchHotel.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				mainFrame.setLeft(hb1);
				HBox searchhb = addSearchhb(mainFrame,hb1);
				mainFrame.setCenter(searchhb);					
				}
			});
		checkOrder.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//跳至查看订单信息主界面
				mainFrame.setLeft(hb1);
				CheckOrderFrame cof = new CheckOrderFrame(controller);
				BorderPane checkOrderborder = cof.jumptoCheckOrder();
				mainFrame.setCenter(checkOrderborder);
			}
		});
		maintainPersonalInfo.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){				
				//跳至维护个人信息信息主界面
				mainFrame.setLeft(hb1);
				MaintainPersonalInfo mpi = new MaintainPersonalInfo(controller);
				UserVo vo = null;
				try {
					vo = controller.GetPresentUserInfo();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				GridPane mpigridpane = mpi.jumptoMaintainPersonalInfo(mainFrame,vo);
				mainFrame.setCenter(mpigridpane);
			}
		});
		reversehistory.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){				
				//跳至查看预定历史主界面
				mainFrame.setLeft(hb1);
				ReverseHistory rh = new  ReverseHistory(controller);
				BorderPane rhborder = rh.jumptoReverseHistory();
				mainFrame.setCenter(rhborder);
			}
		});
		return mainFrame;
		}
	public Button AddSearchButton(){
		searchIcon = new Button();
		searchIcon.setMinSize(25, 25);
		searchIcon.setAlignment(Pos.CENTER);
		return searchIcon;
	}
	//跳转到搜索酒店主界面
	public VBox jumptoSearchHotelMainFrame(BorderPane mainFrame,String address,String commercial){	
		VBox v = new VBox();
		v.setMinSize(800, 600);
		v.setMaxSize(800, 600);
		HBox top = new HBox();
		VBox vb = new VBox();
		vb.setSpacing(10);
		HBox searchLineone = new HBox();
		TextField locationtf = new TextField();
		TextField commercialtf = new TextField();
		locationtf.setMinSize(120, 30);
		locationtf.setMaxSize(120, 30);
		locationtf.setText(address);
		commercialtf.setMinSize(120, 30);
		commercialtf.setMaxSize(120, 30);
		commercialtf.setText(commercial);
		searchLineone.setSpacing(10);
		searchLineone.getChildren().addAll(addText("地址"),locationtf,addText("商圈"),commercialtf);
		HBox searchLinetwo = new HBox();
		Text checkin = new Text("入住");
		Text checkout = new Text("退房");
		checkinDatePicker = new DatePicker();
		checkinDatePicker.setValue(LocalDate.now());
		checkoutDatePicker = new DatePicker();			
	    checkoutDatePicker.setValue(checkinDatePicker.getValue().plusDays(1));
		Button searchIcon = AddSearchButton();
		searchLinetwo.getChildren().addAll(checkin,addDatePicker(checkinDatePicker),
				checkout,addDatePicker(checkoutDatePicker),searchLineone,searchIcon);
		searchLinetwo.setSpacing(10);
		vb.getChildren().add(searchLinetwo);
		//价格
		HBox price = new HBox();
		RadioButton rb1 = new RadioButton();
		rb1.setText("￥150以下");
		RadioButton rb2 = new RadioButton();
		rb2.setText("￥150-300");
		RadioButton rb3 = new RadioButton();
		rb3.setText("￥301-450");
		RadioButton rb4 = new RadioButton();
		rb4.setText("￥451-600");
		RadioButton rb5 = new RadioButton();
		rb5.setText("￥600以上");
		RadioButton rb6 = new RadioButton();
		rb6.setText("不限");
		price.setSpacing(10);
		Text pricet = new Text("价格");
		price.getChildren().addAll(pricet,rb1,rb2,rb3,rb4,rb5,rb6);
		vb.getChildren().add(price);
		final ToggleGroup tgroup = new ToggleGroup();
		rb1.setToggleGroup(tgroup);
		rb2.setToggleGroup(tgroup);
		rb3.setToggleGroup(tgroup);
		rb4.setToggleGroup(tgroup);
		rb5.setToggleGroup(tgroup);
		rb6.setToggleGroup(tgroup);
		
		//房间类型
		HBox roomType = new HBox();
		Text room = new Text();
		room.setText("房间类型");
		RadioButton c1 = new RadioButton("大床房");
		RadioButton c2 = new RadioButton("双床房");
		RadioButton c3 = new RadioButton("家庭房/三人");
		RadioButton c4 = new RadioButton("套间");
		RadioButton c5 = new RadioButton("不限");
		roomType.setSpacing(10);
		roomType.getChildren().addAll(room,c1,c2,c3,c4,c5);
		final ToggleGroup roomTypeGroup = new ToggleGroup();
		c1.setToggleGroup(roomTypeGroup);
		c2.setToggleGroup(roomTypeGroup);
		c3.setToggleGroup(roomTypeGroup);
		c4.setToggleGroup(roomTypeGroup);
		c5.setToggleGroup(roomTypeGroup);
		vb.getChildren().add(roomType);


		//根据酒店和商圈获得相应酒店的controller调用
		List<HotelVo> hotelList=null;
		try {
			hotelList=controller.getHotels(locationtf.toString(), commercialtf.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
		HotelList hl = new HotelList(hotelList,controller);
		VBox hotellist = hl.addHotelList(mainFrame);
		vb.getChildren().add(hotellist);	
		v.getChildren().addAll(top,vb);
		searchIcon.getStyleClass().add("SearchButton");
		
		searchIcon.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				checkindate = checkinDatePicker.getValue();
				checkoutdate = checkoutDatePicker.getValue();
				String address = locationtf.getText();
				String commercial = commercialtf.getText();
				priceSelectIndex = 6;
				tgroup.selectedToggleProperty().addListener(
					    (ObservableValue<? extends Toggle> ov, Toggle old_Toggle,
					    Toggle new_Toggle) -> {
					        if (tgroup.getSelectedToggle() == rb1) {
					        	priceSelectIndex = 1;
					        }
					        else if (tgroup.getSelectedToggle() == rb2) {
					        	priceSelectIndex = 2;
					        }
					        else if (tgroup.getSelectedToggle() == rb3) {
					        	priceSelectIndex = 3;
					        }
					        else if (tgroup.getSelectedToggle() == rb4) {
					        	priceSelectIndex = 4;
					        }
					        else if (tgroup.getSelectedToggle() == rb5) {
					        	priceSelectIndex = 5;
					        }
					        else if (tgroup.getSelectedToggle() == rb6) {
					        	priceSelectIndex = 6;
					        }
					});
				roomTypeSelectIndex = 5;
				roomTypeGroup.selectedToggleProperty().addListener(
					    (ObservableValue<? extends Toggle> ov, Toggle old_Toggle,
					    Toggle new_Toggle) -> {
					        if (roomTypeGroup.getSelectedToggle() == c1) {
					        	 roomTypeSelectIndex = 1;
					        }
					        else if (roomTypeGroup.getSelectedToggle() == c2) {
					        	 roomTypeSelectIndex = 2;
					        }
					        else if (roomTypeGroup.getSelectedToggle() == c3) {
					        	 roomTypeSelectIndex = 3;
					        }
					        else if (roomTypeGroup.getSelectedToggle() == c4) {
					        	 roomTypeSelectIndex = 4;
					        }
					        else if (roomTypeGroup.getSelectedToggle() == c5) {
					        	 roomTypeSelectIndex = 5;
					        }
					});
				
				
			}				
		});
		return v;
	}	
	//对text的字体设定
	public Text addText(String s){
		Text text = new Text(s);
		text.setFont(Font.font("冬青黑体简体中文",15));
		return text;
	}
	public HBox addSearchhb(BorderPane mainFrame, HBox hb1){
		HBox searchhb = new HBox();
		TextField locationtf = new TextField();
		TextField commercialtf = new TextField();
		locationtf.setMinSize(150, 30);
		locationtf.setMaxSize(150, 30);
		commercialtf.setMinSize(150, 30);
		commercialtf.setMaxSize(150, 30);
		searchhb.setSpacing(15);
		searchhb.getChildren().addAll(addText("地址"),locationtf,addText("商圈"),commercialtf);
		searchhb.setPadding(new Insets(100,150,100,150));
		searchhb.setSpacing(10);
		searchhb.setAlignment(Pos.CENTER);
		button = AddSearchButton();
		button.getStyleClass().add("SearchButton");
		searchhb.getChildren().add(button);
		searchIcon.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//跳至搜索酒店信息主界面
				mainFrame.setLeft(hb1);
				String address = locationtf.getText();
				String commercial = commercialtf.getText();
				if(!address.equals("")&&!commercial.equals("")){
					VBox searchHotelvb = jumptoSearchHotelMainFrame(mainFrame,address,commercial);
				    mainFrame.setCenter(searchHotelvb);
				}
						
			}
		});
		return searchhb;
	}

private DatePicker addDatePicker(DatePicker datePicker){
	datePicker.setMaxSize(120, 25);
	datePicker.setMinSize(120, 25);
	StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
    	DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern(pattern);
    	@Override
    	public String toString(LocalDate date) {
    		if (date != null) {
    			return dateFormatter.format(date);
    		} else {
    				return "";
    			}
    	}	    	
    	@Override
    	public LocalDate fromString(String string) {
    		if (string != null && !string.isEmpty()) {
    			return LocalDate.parse(string, dateFormatter);
    			} else {
    				return null;
    				}
    		}
    	};     
    datePicker.setConverter(converter);
    datePicker.setPromptText(pattern.toLowerCase());
    datePicker.requestFocus();
	
    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
    	@Override
    	public DateCell call(final DatePicker datePicker) {
    		return new DateCell() {
    			@Override
    			public void updateItem(LocalDate item, boolean empty) {
    				super.updateItem(item, empty);
    				
    				if(datePicker == checkinDatePicker){
    					if (item.isBefore(
    							checkinDatePicker.getValue().plusDays(0))
    							) {
    						setDisable(true);
    						setStyle("-fx-background-color: #ffc0cb;");
    						}
    					}
    				else if(datePicker == checkoutDatePicker){
    					if (item.isBefore(
    							checkinDatePicker.getValue().plusDays(1))
    							) {
    						setDisable(true);
    						setStyle("-fx-background-color: #ffc0cb;");
    						}   
    					}
    				};
    			};
    		}	
    };
    datePicker.setDayCellFactory(dayCellFactory);
    return datePicker;
    }
}
