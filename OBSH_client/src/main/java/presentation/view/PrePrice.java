package presentation.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrePrice {
	
	private UserViewControllerService controller;
	
	public PrePrice(UserViewControllerService controller){
		this.controller=controller;
	}
	
	private final TableView table = new TableView();
	final ObservableList<Comment> data = FXCollections.observableArrayList(
	    new Comment("1", "总统府", "成人票","10","10"));
	public VBox addPrePrice(BorderPane mainFrame,String hotelname){
		VBox v = new VBox();
		table.setEditable(false);		 
		TableColumn numtc = new TableColumn("序号");
        TableColumn sspottc = new TableColumn("景点");
        TableColumn tickettypetc = new TableColumn("票型");
        TableColumn pricetc = new TableColumn("票价");
        TableColumn ntc = new TableColumn("票数");
        numtc.setCellValueFactory(
                new PropertyValueFactory<>("num")
            );
        sspottc.setCellValueFactory(
                new PropertyValueFactory<>("sspot")
            );
        tickettypetc.setCellValueFactory(
                new PropertyValueFactory<>("tickettype")
            );
        pricetc.setCellValueFactory(
                new PropertyValueFactory<>("price")
            );
        ntc.setCellValueFactory(
                new PropertyValueFactory<>("numt")
            );
        table.getColumns().addAll(numtc, sspottc, tickettypetc,pricetc,ntc);
        table.setItems(data);
        table.setMaxSize(400, 300);
        v.getChildren().add(table);
        Text numt = new Text("数量：");
        TextField numtf = new TextField();
        numtf.setPromptText("请输入数量");
        Button confirm = new Button("预定");
        HBox hb2 = new HBox();
        hb2.getChildren().addAll(numt,numtf);
        hb2.setSpacing(20);
        v.getChildren().addAll(hb2,confirm);
        v.setSpacing(20);
        confirm.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//controller生成订单，并改变ntc
				ProduceOrder po = new ProduceOrder(controller);
				po.produce(hotelname,"");
				mainFrame.setDisable(true);
			}
        });
		return v;
	}
	public static class Comment{
		private final SimpleStringProperty num;
		private final SimpleStringProperty sspot;
		private final SimpleStringProperty tickettype;
		private final SimpleStringProperty price;
		private final SimpleStringProperty numt;
	 
		private Comment(String n ,String st, String tt, String p, String nt) {
			this.num = new SimpleStringProperty(n);
			this.sspot = new SimpleStringProperty(st);
			this.tickettype = new SimpleStringProperty(tt);
			this.price = new SimpleStringProperty(p);
			this.numt = new SimpleStringProperty(nt);
		}
	 
		public String getNum() {
			return num.get();
		}
	 
		public void setNum(String n) {
			num.set(n);
		}
	 
		public String getSspot() {
			return num.get();
		}
	 
		public void setSspot(String st) {
			num.set(st);
		}
		public String getTckettype() {
			return tickettype.get();
		}
	 
		public void setTickettype(String tt) {
			tickettype.set(tt);
		}
	 
		public String getPrice() {
			return price.get();
		}
	 
		public void setPrice(String p) {
			price.set(p);
		}
		public String getNumt() {
			return num.get();
		}
	 
		public void setNumt(String nt) {
			num.set(nt);
		}
	}
}
