package presentation.view;

import java.rmi.RemoteException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ProduceOrder {
	private final String pattern = "yyyy-MM-dd";
	private DatePicker checkinDatePicker = new DatePicker();
	private DatePicker checkoutDatePicker = new DatePicker();
	private LocalDate checkindate;
	private LocalDate checkoutdate;
	ObservableList<String> mins = FXCollections.observableArrayList("00","10","20","30","40","50");
	ObservableList<String> hours = FXCollections.observableArrayList("00","01","02","03","04","05",
			"06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
	
	UserViewControllerService controller;
	
	public ProduceOrder(UserViewControllerService controller){
		this.controller=controller;
	}
	
	public void produce(String hotelname,String Roomtype){
		Stage stage = new Stage();
		HBox hotelhb = new HBox();
		TextField hotelnametf = new TextField();
		hotelnametf.setText(hotelname);
		hotelnametf.setDisable(true);
		hotelhb.getChildren().addAll(addText("酒店名称："),hotelnametf);
		hotelhb.setSpacing(10);
		hotelhb.setAlignment(Pos.CENTER);
		HBox starttime = new HBox();
		starttime.setSpacing(10);
		starttime.setAlignment(Pos.CENTER);
		HBox endtime = new HBox();
		endtime.setSpacing(10);
		endtime.setAlignment(Pos.CENTER);
		final ComboBox<String> checkinhourbox = new ComboBox<String>(hours);
		checkinhourbox.setMaxWidth(70);
		checkinhourbox.setMinWidth(70);
		checkinhourbox.setValue("12");
		final ComboBox<String> checkinminbox = new ComboBox<String>(mins);
		checkinminbox.setMaxWidth(70);
		checkinminbox.setMinWidth(70);
		checkinminbox.setValue("00");
		final ComboBox<String> checkouthourbox = new ComboBox<String>(hours);
		checkouthourbox.setMaxWidth(70);
		checkouthourbox.setMinWidth(70);
		checkouthourbox.setValue("12");
		final ComboBox<String> checkoutminbox = new ComboBox<String>(mins);
		checkoutminbox.setMaxWidth(70);
		checkoutminbox.setMinWidth(70);
		checkoutminbox.setValue("00");
		checkinDatePicker.setValue(LocalDate.now());
		checkoutDatePicker.setValue(checkinDatePicker.getValue().plusDays(1));
		starttime.getChildren().addAll(addText("开始时间："), addDatePicker(checkinDatePicker),
				checkinhourbox,addText(":"),checkinminbox);
		endtime.getChildren().addAll(addText("退房时间："),addDatePicker(checkoutDatePicker),
				checkouthourbox,addText(":"),checkoutminbox);
		HBox lasttime = new HBox();	
		lasttime.setPadding(new Insets(0,0,0,100));
		lasttime.setSpacing(10);
		
		Text dotime = new Text();
		dotime.setDisable(true);
		dotime.setText("最迟不超过开始时间后两小时");
		lasttime.getChildren().addAll(addText("最晚订单执行时间："),dotime);
		HBox roombox = new HBox();
		roombox.setPadding(new Insets(0,0,0,100));
		roombox.setSpacing(10);
		ObservableList<String> options;
		if(Roomtype.equals("")){
			options=FXCollections.observableArrayList(
			        "大床房",
			        "双床房",
			        "家庭房",
			        "套间");
		}
		else{
			options=FXCollections.observableArrayList(Roomtype);
		}

		
		
		ComboBox roomtype = new ComboBox(options);
		roomtype.setValue("大床房");
		roomtype.setMaxWidth(100);
		roomtype.setMinWidth(100);
		TextField roomnum = new TextField();
		roomnum.setMaxWidth(120);
		roomnum.setMinWidth(120);
		roomnum.setText("1");
		TextField peoplenum = new TextField();
		peoplenum.setMaxWidth(120);
		peoplenum.setText("1");
		ObservableList<String> childoption = 
			    FXCollections.observableArrayList(
			        "有",
			        "无"
			    );
		ComboBox child = new ComboBox(childoption);
		child.setValue("有");
		child.setMaxWidth(70);
		child.setMinWidth(70);
		Button button = new Button("确定");
		roombox.getChildren().addAll(addText("房间类型："),roomtype,addText("房间数量："),roomnum);
		HBox peoplebox = new HBox();
		peoplebox.setSpacing(10);
		peoplebox.setPadding(new Insets(0,0,0,100));
		peoplebox.getChildren().addAll(addText("预计入住人数："),peoplenum,addText("有无儿童："),child);
		HBox buttonbox = new HBox();
		buttonbox.setSpacing(10);
		buttonbox.setPadding(new Insets(20,0,0,0));
		buttonbox.setAlignment(Pos.CENTER);
		buttonbox.getChildren().add(button);
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(starttime,endtime,lasttime,roombox,peoplebox,buttonbox);
		Scene scene = new Scene(vb);
		vb.setSpacing(10);
		vb.setMinSize(600, 500);
		vb.setMaxSize(600, 500);
		stage.setScene(scene);
		stage.show();
		
		button.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//controller保存订单信息
				//这个是 LocalDate数据类型的date，系统自带的
				checkindate = checkinDatePicker.getValue();
				checkoutdate = checkoutDatePicker.getValue();
				//获得checkin时间索引
				
				String start=checkindate.toString();
				String end=checkoutdate.toString();
				String deadline=start;
				
				int startHourIndex = checkinhourbox.getSelectionModel().getSelectedIndex();
				int startMinuteIndex = checkinminbox.getSelectionModel().getSelectedIndex();
				start=start+" "+startHourIndex+":"+startMinuteIndex+":"+"00";
				
				
				int deadHour=startHourIndex+2;
				int deadMinute=startMinuteIndex;
				
				if(startHourIndex>23){
					deadHour-=24;
					Format f = new SimpleDateFormat("yyyy-MM-dd"); 
					java.util.Date datea=Date.valueOf(deadline);
					Calendar   calendar   = Calendar.getInstance();
				    calendar.setTime(datea); 
				    calendar.add(Calendar.DAY_OF_MONTH, 1);
				    datea=calendar.getTime();
				    deadline=datea.toString();
				}
				deadline=deadline+" "+deadHour+":"+deadMinute+":"+"00";
				
				//获得checkout时间索引
				int endHourIndex = checkouthourbox.getSelectionModel().getSelectedIndex();
				int endMinuteIndex = checkoutminbox.getSelectionModel().getSelectedIndex();
				end=end+" "+endHourIndex+":"+endMinuteIndex+":"+"00";
				//checkin，checkout对应小时、分钟
				int roomTypeSelected = roomtype.getSelectionModel().getSelectedIndex();//获得房间类型索引
				int roomnumber = Integer.parseInt(roomnum.getText());//获得房间数量
								
				try {
					int userID=controller.GetPresentUserInfo().getID();
					controller.ProduceOrder(hotelname,start, end, deadline, options.get(roomTypeSelected), roomnumber,userID);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				stage.close();
			}
			});
	}
	public DatePicker addDatePicker(DatePicker datePicker){
		datePicker.setMaxSize(140, 25);
		datePicker.setMinSize(140, 25);
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
	//对text的字体设定
	public Text addText(String s){
		Text text = new Text(s);
		text.setFont(Font.font("冬青黑体简体中文",15));
		return text;
	}
	}
