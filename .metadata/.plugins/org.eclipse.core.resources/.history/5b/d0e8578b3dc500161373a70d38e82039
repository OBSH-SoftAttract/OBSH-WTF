package po;

import java.sql.Timestamp;

import vo.OrderVo;

/**
 * orderID		订单编号
 * orderState   订单状态（0：未执行订单；1：已执行订单；2：异常订单；3：已撤销订单）
 * StartTime    订单开始时间
 * EndTime      订单生效时间
 * lastTime     订单最晚时间
 * userID       用户的账号
 * price        订单价格
 * hotelID      酒店编号
 * roomInfo     房间信息
 */
public class OrderPo {
	private String orderID;
	
	private int orderState;
	
	private Timestamp StartTime;
	
	private Timestamp EndTime;
	
	private Timestamp lastTime;
	
	private int userID;
	
	private double price;
	
	private int hotelID;
	
	private String roomInfo;

	private boolean evaluate;
	
	public OrderPo(){
		super();
	}
	
	public OrderPo(String orderid,int orderstate,Timestamp starttime,Timestamp endtime,
			Timestamp lasttime,int userid,double pri,int hotelid,String roominfo,boolean evaluate){
		this.orderID=orderid;
		this.orderState=orderstate;
		this.StartTime=starttime;
		this.EndTime=endtime;
		this.lastTime=lasttime;
		this.userID=userid;
		this.price=pri;
		this.hotelID=hotelid;
		this.roomInfo=roominfo;
		this.evaluate=evaluate;
	}
	
	public boolean isEvaluate() {
		return evaluate;
	}

	public void setEvaluate(boolean evaluate) {
		this.evaluate = evaluate;
	}

	public OrderPo(OrderVo ordervo) {
		this.orderID=ordervo.getOrderID();
		this.orderState=ordervo.getOrderState();
		this.StartTime=ordervo.getStartTime();
		this.EndTime=ordervo.getEndTime();
		this.lastTime=ordervo.getLastTime();
		this.userID=ordervo.getUserID();
		this.price=ordervo.getPrice();
		this.hotelID=ordervo.getHotelID();
		this.roomInfo=ordervo.getRoomInfo();
		this.evaluate=ordervo.isEvaluate();
	}

	public String getOrderID(){
		return this.orderID;
	}
	
	public void setOrderID(String id){
		this.orderID=id;
	}
	
	public int getOrderState(){
		return this.orderState;
	}
	
	public void setOrderState(int orderstate){
		this.orderState=orderstate;
	}
	
	public Timestamp getStartTime(){
		return this.StartTime;
	}
	
	public void setStartTime(Timestamp starttime){
		this.StartTime=starttime;
	}
	
	public Timestamp getEndTime(){
		return this.EndTime;
	}
	
	public void setEndTime(Timestamp endtime){
		this.EndTime=endtime;
	}
	
	public Timestamp getlastTime(){
		return this.lastTime;
	}
	
	public void setlastTime(Timestamp lastime){
		this.lastTime=lastime;
	}
	
	public int getUserID(){
		return this.userID;
	}
	
	public void setUserID(int id){
		this.userID=id;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(double pri){
		this.price=pri;
	}
	
	public int getHotelID(){
		return this.hotelID;
	}
	
	public void setHotelID(int id){
		this.hotelID=id;
	}
	
	public String getroomInfo(){
		return this.roomInfo;
	}
	
	public void setroomInfo(String roominfo){
		this.roomInfo=roominfo;
	}
}
