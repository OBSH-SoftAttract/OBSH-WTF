package presentation.view;

import java.util.ArrayList;
import java.util.List;

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
import presentation.controller.UserViewControllerImpl;

public class PrePrice {
	private final TableView table = new TableView();
	ObservableList<Comment> data;

	private UserViewControllerImpl controller;
	public PrePrice(UserViewControllerImpl controller){
		this.controller=controller;
	}
		public VBox addPrePrice(BorderPane mainFrame,String hotelname){
			List<Comment> list=new ArrayList<Comment>();
			
			data = FXCollections.observableArrayList(list);
			
			VBox v = new VBox();
			table.setEditable(false);		 
			TableColumn numtc = new TableColumn("名称");
	        TableColumn sspottc = new TableColumn("开始时间");
	        TableColumn tickettypetc = new TableColumn("结束时间");
	        TableColumn pricetc = new TableColumn("详细信息");
	        TableColumn ntc = new TableColumn("折扣");
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
	        table.setMaxSize(800, 300);
	        v.getChildren().add(table);
	        v.setSpacing(20);

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
			return sspot.get();
		}
	 
		public void setSspot(String st) {
			sspot.set(st);
		}
		public String getTickettype() {
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
			return numt.get();
		}
	 
		public void setNumt(String nt) {
			numt.set(nt);
		}
	}
}
