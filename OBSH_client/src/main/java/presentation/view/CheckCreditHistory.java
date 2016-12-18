package presentation.view;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import vo.CreditVo;

public class CheckCreditHistory {
	
	UserViewControllerService controller;
	
	public  CheckCreditHistory(UserViewControllerService controller){
		this.controller=controller;
	}
	
	//查看信用记录
	private final TableView<CreditHistory> table = new TableView<>();
	
	List<CreditHistory> list=new ArrayList<CreditHistory>();
	public CheckCreditHistory(List<CreditVo> creditlist){
		for(CreditVo creditvo: creditlist){
			String date=creditvo.getTime().toString().substring(0, 10);
			String orderID="/";
			if(!creditvo.getOrderID().equals(""))orderID=creditvo.getOrderID();
			String change=creditvo.getCreditChange();
			String result=String.valueOf(creditvo.getCreditResult());
			list.add(new CreditHistory(date,orderID,change,result));
		}

	}
	

	public BorderPane addCreditHistory(){
		ObservableList<CreditHistory> data =FXCollections.observableArrayList(list);
		
		final BorderPane border = new BorderPane();
		table.setEditable(false);	 
        TableColumn timetc = new TableColumn("日期");
        timetc.setMinWidth(100);
        timetc.setCellValueFactory(
                new PropertyValueFactory<>("time"));     
        TableColumn orderidtc = new TableColumn("订单号");
        orderidtc.setMinWidth(100);
        orderidtc.setCellValueFactory(
                new PropertyValueFactory<>("orderid"));     
        TableColumn creditchangetc = new TableColumn("信用值变化");
        creditchangetc.setMinWidth(100);
        creditchangetc.setCellValueFactory(
                new PropertyValueFactory<>("creditchange")); 
        TableColumn creditresulttc = new TableColumn("信用值结果");
        creditresulttc.setMinWidth(100);
        creditresulttc.setCellValueFactory(
                new PropertyValueFactory<>("creditresult")); 
        table.setItems(data);
        table.getColumns().addAll(timetc, orderidtc, creditchangetc, creditresulttc);
        border.setCenter(table);
        border.setMaxSize(400, 400);
		return border;
	}
	public static class CreditHistory {
		private final SimpleStringProperty time;
		private final SimpleStringProperty orderid;
		private final SimpleStringProperty creditchange;
		private final SimpleStringProperty creditresult;
	 
		private CreditHistory(String t, String oi, String cc, String cr) {
			this.time = new SimpleStringProperty(t);
			this.orderid = new SimpleStringProperty(oi);
			this.creditchange = new SimpleStringProperty(cc);
			this.creditresult = new SimpleStringProperty(cr);
		} 
		public String getTime() {
			return time.get();
		} 
		public void setTime(String t) {
			time.set(t);
		}	 
		public String getOrderid() {
			return orderid.get();
		}	 
		public void setOrderid(String oi) {
			orderid.set(oi);
		}
		public String getCreditchange() {
			return creditchange.get();
		} 
		public void setCreditchange(String cc) {
			creditchange.set(cc);
		}
		public String getCreditresult() {
			return creditresult.get();
		}	 
		public void setCreditresult(String cr) {
			creditresult.set(cr);
		}
	}
}
