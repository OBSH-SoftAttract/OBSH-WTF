package presentation.view;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;
import presentation.controller.UserViewControllerImpl;
import presentation.view.HotelList;
import vo.HotelVo;
import vo.UserVo;

public class UserMainFrame {
	private Button searchIcon = new Button();
	final ObservableList<String> Saddress = FXCollections.observableArrayList("南京","常州","苏州");		
	final ObservableList<String> NJcommercial = FXCollections.observableArrayList("新街口","湖南路","珠江路","夫子庙","中央门");		
	final ObservableList<String> CZcommercial = FXCollections.observableArrayList("南大街","文化宫","花园街");		
	final ObservableList<String> SZcommercial = FXCollections.observableArrayList("观前街","石路","南门","新区");		
	final ComboBox<String>  address = new ComboBox<String> (Saddress);
	final ComboBox<String>  commercial = new ComboBox<String> (NJcommercial);
	private final String pattern = "yyyy-MM-dd";
	private DatePicker checkinDatePicker;
	private DatePicker checkoutDatePicker;

	
	/*客户主界面
     * 包括退出的链接，跳转到搜索酒店界面，查看订单界面，维护个人信息界面的按钮
     *  以及要求用户输入关于地址商圈信息的搜索框
     */
	
	private UserViewControllerImpl controller;
	
	public UserMainFrame(UserViewControllerImpl controller){
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
		HBox hb = new HBox();
		address.setPromptText("南京");	
		commercial.setPromptText("新街口");
		hb.setSpacing(30);
		hb.setAlignment(Pos.CENTER);
		hb.setMaxSize(400, 400);
		hb.setMinSize(400, 400);
		address.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
					@Override
					public void changed(ObservableValue observable, Object oldValue, Object newValue) {
						// TODO Auto-generated method stub
						int Aindex = address.getSelectionModel().getSelectedIndex();
						if(Aindex == 0){
							commercial.setItems(null);
							commercial.setPromptText("新街口");
							commercial.setItems(NJcommercial);
						}
						if(Aindex == 1){
							commercial.setItems(null);
							commercial.setPromptText("南大街");
							commercial.setItems(CZcommercial);
						}
						if(Aindex == 2){
							commercial.setItems(null);
							commercial.setPromptText("观前街");
							commercial.setItems(SZcommercial);
						}
					}			
				});
				commercial.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub                                         
						int Cindex = commercial.getSelectionModel().getSelectedIndex();
					}
				});
				searchIcon.setMinSize(25, 25);
				hb.getChildren().addAll(address,commercial,searchIcon);
				mainFrame.setCenter(hb);					

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
		searchIcon.getStyleClass().add("SearchButton");
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
				int index1=address.getSelectionModel().getSelectedIndex();
				int index2=commercial.getSelectionModel().getSelectedIndex();
				if(index1==-1)index1++;
				if(index2==-1)index2++;
					mainFrame.setLeft(hb1);
					VBox searchHotelvb = jumptoSearchHotelMainFrame(mainFrame,address.getItems().get(index1),commercial.getItems().get(index2),"","不限",
							0,false,false,false,false);
					mainFrame.setCenter(searchHotelvb);					
				
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
	public VBox jumptoSearchHotelMainFrame(BorderPane mainFrame,String Address,String Commercial,String hotelName,String roomType,
			int hotelStar,boolean ifHis,boolean PriceSort,boolean StarSort,boolean ScoreSort){	
		
		//根据酒店和商圈获得相应酒店的controller调用
		List<HotelVo> hotelList=null;
		try {
			hotelList=controller.getHotels(Address, Commercial,hotelName,roomType,
					hotelStar,PriceSort,StarSort,ScoreSort);
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
		HotelList hl = new HotelList(hotelList,controller,ifHis);
		
		VBox v = new VBox();
		v.setMinSize(800, 600);
		v.setMaxSize(800, 600);
		HBox top1=new HBox();
		HBox top2=new HBox();
		HBox top3 = new HBox();
		HBox top4=new HBox();
		VBox vb = new VBox();
		vb.setSpacing(10);
		
		ObservableList<String> Types = FXCollections.observableArrayList("不限","标准房","双人房","家庭房","豪华房");		
		ComboBox<String>  types = new ComboBox<String> (Types);
		ObservableList<String> Stars = FXCollections.observableArrayList("不限","二星及以上","三星及以上","四星及以上","五星");		
		ComboBox<String>  stars = new ComboBox<String> (Stars);	
		ObservableList<String> historys = FXCollections.observableArrayList("否","是");		
		ComboBox<String>  history = new ComboBox<String> (historys);
		String temp[]={"不限","二星及以上","三星及以上","四星及以上","五星"};
		
        types.setPromptText(roomType);	
		stars.setPromptText(temp[hotelStar]);
		String s="否";
		if(ifHis)
			s="是";
		history.setPromptText(s);
		
		Text t=new Text("排序:");
        CheckBox cbs1 = new CheckBox("价格");
        CheckBox cbs2 = new CheckBox("星级");
        CheckBox cbs3 = new CheckBox("评分");
        
        cbs1.setSelected(PriceSort);
        cbs2.setSelected(StarSort);
        cbs3.setSelected(ScoreSort);
        
        top3.getChildren().addAll(t,cbs1,cbs2,cbs3);
        TextField name=new TextField();
        name.setMinWidth(10);
        Text t1=new Text("酒店名称");
        Text t2=new Text("类型");
        Text t3=new Text("星级");
        Text t4=new Text("住过的酒店");
        
        top1.setSpacing(15);
        top2.setSpacing(15);
        top3.setSpacing(15);
        top4.setSpacing(10);
        v.setSpacing(10);
        
        top2.getChildren().addAll(t1,name,t2,types,t3,stars);
        top1.getChildren().addAll(address,commercial,searchIcon);
        top4.getChildren().addAll(t4,history);
		//这里应该有一个根据酒店和商圈获得相应酒店的controller调用
		VBox hotellist = hl.addHotelList(mainFrame);
		hotellist.setMaxWidth(600);
		vb.getChildren().add(hotellist);	
		v.getChildren().addAll(top1,top2,top3,top4,vb);
		searchIcon.getStyleClass().add("SearchButton");
		

		searchIcon.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int typesIndex=types.getSelectionModel().getSelectedIndex();//房间类型
				if(typesIndex<0)typesIndex++;
				int starIndex=stars.getSelectionModel().getSelectedIndex();//星级
				if(starIndex<0)starIndex++;
				int ifHistoryIndex=history.getSelectionModel().getSelectedIndex();//是否住过
				if(ifHistoryIndex<0)ifHistoryIndex++;
				String type=types.getItems().get(typesIndex);
				int star=starIndex;
				
				String hotelname=name.getText();
				int index1=address.getSelectionModel().getSelectedIndex();
				int index2=commercial.getSelectionModel().getSelectedIndex();
				if(index1==-1)index1++;
				if(index2==-1)index2++;
				boolean ifHistorY=false;
				if(ifHistoryIndex==1)ifHistorY=true;
				boolean priceSort=cbs1.isSelected();
				boolean starSort=cbs2.isSelected();
				boolean scoreSort=cbs3.isSelected();
				
				
				jumptoSearchHotelMainFrame(mainFrame,address.getItems().get(index1),commercial.getItems().get(index2),
						hotelname,type,star,ifHistorY,priceSort,starSort,scoreSort);
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
		HBox hb = new HBox();
		address.setPromptText("南京");	
		commercial.setPromptText("新街口");
		hb.setSpacing(30);
		hb.setAlignment(Pos.CENTER);
		hb.setMaxSize(400, 400);
		hb.setMinSize(400, 400);
		address.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
					@Override
					public void changed(ObservableValue observable, Object oldValue, Object newValue) {
						// TODO Auto-generated method stub
						int Aindex = address.getSelectionModel().getSelectedIndex();
						if(Aindex == 0){
							commercial.setItems(null);
							commercial.setPromptText("新街口");
							commercial.setItems(NJcommercial);
						}
						if(Aindex == 1){
							commercial.setItems(null);
							commercial.setPromptText("南大街");
							commercial.setItems(CZcommercial);
						}
						if(Aindex == 2){
							commercial.setItems(null);
							commercial.setPromptText("观前街");
							commercial.setItems(SZcommercial);
						}
					}			
				});
				commercial.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub                                         
						int Cindex = commercial.getSelectionModel().getSelectedIndex();
					}
				});
				searchIcon.setMinSize(25, 25);
				hb.getChildren().addAll(address,commercial,searchIcon);
				mainFrame.setCenter(hb);		
		return hb;
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
