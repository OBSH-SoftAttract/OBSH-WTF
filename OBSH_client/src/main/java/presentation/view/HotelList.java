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
import presentation.controller.UserViewControllerImpl;
import vo.HotelVo;
import vo.OrderVo;

public class HotelList extends VBox {

	List<Hotel> hotelList=new ArrayList<Hotel>();;

	private UserViewControllerImpl controller;
	
	final List<String> hotelName=new ArrayList<String>();
	String Types[]={"标准房","双人房","家庭房","豪华房"};
	public HotelList(List<HotelVo> list,UserViewControllerImpl controller,boolean ifHis) {
		this.controller=controller;
		for (HotelVo hotel : list) {
			String name=hotel.getName();			
			String star=String.valueOf(hotel.getStar());
			String price=String.valueOf(hotel.getMinPrice());
			String score=String.valueOf(hotel.getScore());
			String[] roomInfo=hotel.getRoomInfo().split(";");
			List<String> T=new ArrayList<String>();
			for(int i=0;i<roomInfo.length;i++){
				String []temp=roomInfo[i].split("[+]");
				T.add(temp[0]);
			}
			
			String types=getTypes(T);
			hotelName.add(name);
			int userid;
			List<OrderVo> orderlist = null;
			try {
				userid = controller.GetPresentUserInfo().getID();
				orderlist=controller.getAllHistoryOrdersByUserID(userid);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			boolean orderstate[]={false,false,false,false};
			String state[]={"正常订单","正常订单","撤销订单","异常订单"};
			for(OrderVo order:orderlist){
				orderstate[order.getOrderState()]=true;
			}
			String history="";
			boolean fir=true;
			for(int i=0;i<4;i++){
				if(orderstate[i]){
					if(fir){
						history=history+"记录:"+state[i];
						fir=false;
					}				   
					else
						history=history+"/"+state[i];
				}
					
			}
			if(!ifHis||!history.equals("")){
				hotelList.add(new Hotel(name,star,price,score,types,history));
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
        TableColumn types = new TableColumn("房间类型");
        types.setMaxWidth(200);
        TableColumn history = new TableColumn("历史预定情况");
        history.setMaxWidth(200);
        
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
        types.setCellValueFactory(
                new PropertyValueFactory<>("types")
            );
        history.setCellValueFactory(
                new PropertyValueFactory<>("history")
            );

        table.setItems(data);
        table.setMaxWidth(700);
        table.getColumns().addAll(hotelnametc, starleveltc,  lowpricetc, marktc,types,history);
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
				if(selecterhotel<0)selecterhotel=0;
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
				if(index<0)index=0;
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
	
	public String getTypes(List<String> a){
		String res="";
		boolean jun[]={false,false,false,false};
		for(int i=0;i<a.size();i++){
			if(a.get(i).equals(Types[0]))
				jun[0]=true;
			if(a.get(i).equals(Types[1]))
				jun[1]=true;
			if(a.get(i).equals(Types[2]))
				jun[2]=true;
			if(a.get(i).equals(Types[3]))
				jun[3]=true;
		}
		boolean fir=true;
		for(int i=0;i<4;i++){
			if(jun[i]){
				if(fir){
					res+=Types[i];
				}
				else
					res=res+"/"+Types[i];
			}
		}
		
		return res;
	}
	public static class Hotel {
		private final SimpleStringProperty hotelname;
		private final SimpleStringProperty starlevel;
		private final SimpleStringProperty lowprice;
		private final SimpleStringProperty mark;
		private final SimpleStringProperty types;
		private final SimpleStringProperty history;
	 
		private Hotel(String hn, String star, String price, String m, String types,String r) {
			this.hotelname = new SimpleStringProperty(hn);
			this.starlevel = new SimpleStringProperty(star);
			this.lowprice = new SimpleStringProperty(price);
			this.mark = new SimpleStringProperty(m);
			this.types=new SimpleStringProperty(types);
			this.history = new SimpleStringProperty(r);
		}
		public String getTypes() {
			return types.get();
		}	 
		public void setTypes(String hn) {
			types.set(hn);
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
		
		public String getHistory() {
			return history.get();
		}
	 
		public void setHistory(String m) {
			history.set(m);
		}
	}
}