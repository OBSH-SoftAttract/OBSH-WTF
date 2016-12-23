package HotelStaffUI.controller;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.controller.UserViewControllerImpl;
import vo.HotelDeliverVo;
import vo.HotelVo;
import vo.HotelroomVo;
import vo.PromotionVo;

public class PromotionItemController {
	
	private UserViewControllerImpl controller = new UserViewControllerImpl();
	
	private HotelDeliverVo hoteldeliver;
	
	@FXML
	private Button roommessage;
	
	@FXML
	private Button orderdeal;
	
	@FXML
	private Button addpromotion;
	
	@FXML
	private Button basic;
	
	@FXML
	private Button add;
	
	@FXML
	private TableColumn name;
	
	@FXML
	private TableColumn start;
	
	@FXML
	private TableColumn end;
	
	@FXML
	private TableColumn info;
	
	@FXML
	private TableColumn count;
	
	@FXML
	private TableView<Promotion> table;
	
	private List<Promotion> promotionlist;
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		int hotelID = hoteldeliver.getHotelStaffID();
		promotionlist = new ArrayList<Promotion>();

		try {
			controller.setUserID(hotelID);
			List<PromotionVo> promotions = controller.getPromotions(hotelID);
			System.out.println(promotions.size());
			//List<PromotionVo> promotions = hoteldeliver.getPromotion();
			for(PromotionVo vo:promotions) {
				String promotionname = vo.getItemName();
				String startdate = vo.getStartTime().toString();
				String enddate = vo.getEndTime().toString();
				String information = vo.getPromotionInfo();
				double discount = vo.getDiscount();
				promotionlist.add(new Promotion(promotionname, startdate, enddate, information, discount));
			}
			
			final ObservableList<Promotion> data = FXCollections.observableArrayList(promotionlist);

	        name.setCellValueFactory(
	                new PropertyValueFactory<>("promotionname")
	            );
	        start.setCellValueFactory(
	                new PropertyValueFactory<>("startdate")
	            );
	        end.setCellValueFactory(
	                new PropertyValueFactory<>("enddate")
	            );
	        info.setCellValueFactory(
	                new PropertyValueFactory<>("information")
	            );
	        count.setCellValueFactory(
	                new PropertyValueFactory<>("discount")
	            );
			table.setItems(data);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*promotionlist.add(new Promotion("生日特惠折扣","2016-11-25","2018-12-25","全体用户",0.7));
		promotionlist.add(new Promotion("三间及以上预订优惠","2016-11-25","2017-02-03","全体用户",0.9));
		promotionlist.add(new Promotion("合作企业用户折扣","2016-11-25","2018-12-25","合作企业用户",0.88));
		promotionlist.add(new Promotion("双十一活动折扣","2016-11-10","2016-11-12","全体用户",0.88));*/
		

	}
	
	
	@FXML
	private void deletepromotion() {
		/**
		 * 删除策略
		 */
		PromotionVo vo;
		int hotelID = hoteldeliver.getHotelStaffID();

		int index = table.getSelectionModel().getSelectedIndex();
		String promotionname =  table.getItems().get(index).getPromotionname();
		Timestamp startdate = Timestamp.valueOf(table.getItems().get(index).getStartdate());System.out.println(startdate);
		Timestamp enddate =  Timestamp.valueOf(table.getItems().get(index).getEnddate());System.out.println(enddate);
		String information =  table.getItems().get(index).getInformation();
		double discount =  table.getItems().get(index).getDiscount();
		String id = String.valueOf(hotelID) + String.valueOf(index);//这里关于promotionid有严重的问题
		int promotionID = Integer.parseInt(id);
		vo = new PromotionVo(promotionID ,discount, promotionname,  startdate, enddate,information);
		try {
			controller.setUserID(hotelID);
			if(controller.DelPromotion(vo)){  //下面返回两个弹窗
				Stage stage = new Stage();
				Text text = new Text("删除成功");
				Button button = new Button("确定");
				VBox vb = new VBox();
				vb.setSpacing(10);
				vb.getChildren().addAll(text, button);
				vb.setMinSize(200, 200);
				vb.setMaxSize(200, 200);
				vb.setAlignment(Pos.CENTER);
				Scene scene = new Scene(vb);
				stage.setScene(scene);
				stage.show();
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						stage.close();
					}
				});
			}
			else
				;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void modifypromotion() {
		/**
		 * 修改策略
		 */
		int hotelID = controller.getUserID();
		
		int index = table.getSelectionModel().getSelectedIndex();
		String promotionname =  table.getItems().get(index).getPromotionname();
		Timestamp startdate = Timestamp.valueOf(table.getItems().get(index).getStartdate());System.out.println(startdate);
		Timestamp enddate =  Timestamp.valueOf(table.getItems().get(index).getEnddate());System.out.println(enddate);
		String information =  table.getItems().get(index).getInformation();
		double discount =  table.getItems().get(index).getDiscount();
		PromotionVo vo = new PromotionVo(hotelID ,discount, promotionname,  startdate, enddate,information);
	}
	
	
	/**
	 * 打开添加策略的界面
	 */
	@FXML
	private void jumpToAddpromotion() {
		//AddPromotion a = new AddPromotion();
		//a.start(new Stage());
		GridPane grid = new GridPane();
		grid.setVgap(20);
		grid.setHgap(20);
		grid.setAlignment(Pos.CENTER);
		grid.add(addText("策略名称："), 0, 0);
		grid.add(addText("策略开始时间："), 0, 1);
		grid.add(addText("策略结束时间："), 0, 2);
		grid.add(addText("策略详细信息："), 0, 3);
		grid.add(addText("折扣："), 0, 4);
		TextField nametf = new TextField();
		nametf.setMaxWidth(140);
		TextField starttimetf = new TextField();
		starttimetf.setMaxWidth(140);
		TextField endtimetf = new TextField();
		endtimetf.setMaxWidth(140);
		TextField detailtf = new TextField();
		detailtf.setMaxWidth(140);
		TextField discounttf = new TextField();
		discounttf.setTooltip(new Tooltip("请输入两位小数"));
		discounttf.setMaxWidth(140);
		grid.add(nametf, 1, 0);
		grid.add(starttimetf, 1, 1);
		grid.add(endtimetf, 1, 2);
		grid.add(detailtf, 1, 3);
		grid.add(discounttf, 1, 4);
		HBox button = new HBox();
		Button modify = new Button("添加");
		Button cancel = new Button("取消");
		button.getChildren().addAll(modify,cancel);
		button.setSpacing(20);
		button.setAlignment(Pos.CENTER);
		VBox vb = new VBox();
		vb.getChildren().addAll(grid,button);
		vb.setSpacing(20);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setWidth(500);
		stage.setHeight(450);
		stage.show();
		modify.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				
				int hotelID = controller.getUserID();
				//int hotelID = hoteldeliver.getHotelStaffID();
				String itemName = nametf.getText();
				String start = starttimetf.getText();
				String end = endtimetf.getText();
				String info = detailtf.getText();
				String dis = discounttf.getText();

				if(itemName.equals("")||start.equals("")||end.equals("")||info.equals("")||dis.equals("")) {
					/**
					 * 弹输入不能为空
					 */
					Stage stage = new Stage();
					Text text = new Text("请认真填写信息");
					Button button = new Button("确定");
					VBox vb = new VBox();
					vb.setSpacing(10);
					vb.getChildren().addAll(text, button);
					vb.setMinSize(200, 200);
					vb.setMaxSize(200, 200);
					vb.setAlignment(Pos.CENTER);
					Scene scene = new Scene(vb);
					stage.setScene(scene);
					stage.show();
					button.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							stage.close();
						}
					});
				} else{
					try {
						if(controller.AddPromotion(dis,itemName,start,end,info)){
							/**
							 * 弹添加成功
							 */
							Stage stage = new Stage();
							Text text = new Text("添加成功");
							Button button = new Button("确定");
							VBox vb = new VBox();
							vb.setSpacing(10);
							vb.getChildren().addAll(text, button);
							vb.setMinSize(200, 200);
							vb.setMaxSize(200, 200);
							vb.setAlignment(Pos.CENTER);
							Scene scene = new Scene(vb);
							stage.setScene(scene);
							stage.show();
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									stage.close();
								}
							});
						} else {
							/**
							 * 弹添加失败
							 */
							Stage stage = new Stage();
							Text text = new Text("添加失败");
							Button button = new Button("确定");
							VBox vb = new VBox();
							vb.setSpacing(10);
							vb.getChildren().addAll(text, button);
							vb.setMinSize(200, 200);
							vb.setMaxSize(200, 200);
							vb.setAlignment(Pos.CENTER);
							Scene scene = new Scene(vb);
							stage.setScene(scene);
							stage.show();
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									stage.close();
								}
							});
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
	
	public Text addText(String s){
		Text text = new Text(s);
		text.setFont(Font.font("冬青黑体简体中文",15));
		return text;
		}

	/**
	 * 构造了PromotionList中的内容
	 * @author asus1
	 *
	 */
	public static class Promotion {
		private final SimpleStringProperty promotionname;
		private final SimpleStringProperty startdate;
		private final SimpleStringProperty enddate;
		private final SimpleStringProperty information;
		private final SimpleDoubleProperty discount;
	 
		private Promotion(String pn, String sd, String ed, String in, double dc) {
			this.promotionname = new SimpleStringProperty(pn);
			this.startdate = new SimpleStringProperty(sd);
			this.enddate = new SimpleStringProperty(ed);
			this.information = new SimpleStringProperty(in);
			this.discount = new SimpleDoubleProperty(dc);
		}
	 
		public String getPromotionname() {
			return promotionname.get();
		}	 
		public void setPromotionname(String on) {
			promotionname.set(on);
		} 
		public String getStartdate() {
			return startdate.get();
		}	 
		public void setStartdate(String sd) {
			startdate.set(sd);
		}
		public String getEnddate() {
			return enddate.get();
		}	 
		public void setEnddate(String ed) {
			enddate.set(ed);
		}
		public String getInformation() {
			return information.get();
		}
	 
		public void setInformation(String in) {
			information.set(in);
		}
		
		public double getDiscount() {
			return discount.get();
		}
	 
		public void setDiscount(double ds) {
			discount.set(ds);
		}

	}
	


}
