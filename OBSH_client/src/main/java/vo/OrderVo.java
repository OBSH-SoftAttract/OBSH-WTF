package vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * orderID		                订单编号
 * orderState         订单状态（0：未执行订单；1：已执行订单；2：异常订单；3：已撤销订单）
 * StartTime          订单生成时间
 * EndTime            订单生效时间
 * lastTime           订单最晚时间
 * TimeCheckIn        入住时间 
 * AttemptedLeaveTime 预计退房时间
 * TimeCheckOut       退房时间
 * userID             用户的账号
 * price              订单总价格
 * hotelID            酒店编号
 * roomInfo           房间信息(类型+个数+单个原价)
 * evaluate           是否评价过
 */
public class OrderVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String orderID="";
	
	private int orderState=-1;
	
	private Timestamp StartTime;
	
	private Timestamp EndTime;
	
	private Timestamp lastTime;
	
	private Timestamp AttemptedLeaveTime;
	
	private Timestamp TimeCheckIn;
	
	private Timestamp TimeCheckOut; 
	
	private int userID=0;
	
	private double price=0;
	
	private int hotelID=0;
	
	private String roomInfo="";
	
	private boolean evaluate=false;
	
	
	public OrderVo(String orderid,int orderstate,Timestamp starttime,Timestamp endtime,
			Timestamp lasttime,Timestamp AttemptedLeaveTime,Timestamp TimeCheckIn,
			Timestamp TimeCheckOut,int userid,double pri,int hotelid,String roominfo,boolean evaluate){
		this.orderID=orderid;
		this.orderState=orderstate;
		this.StartTime=starttime;
		this.EndTime=endtime;
		this.lastTime=lasttime;
		this.AttemptedLeaveTime=AttemptedLeaveTime;
		this.TimeCheckIn=TimeCheckIn;
		this.TimeCheckOut=TimeCheckOut;
		this.userID=userid;
		this.price=pri;
		this.hotelID=hotelid;
		this.evaluate=evaluate;
	}
	
	public OrderVo(String orderid,int orderstate,Timestamp starttime,Timestamp endtime,
			Timestamp lasttime,Timestamp AttemptedLeaveTime,int userid,double pri,int hotelid,String roominfo,boolean evaluate){
		this.orderID=orderid;
		this.orderState=orderstate;
		this.StartTime=starttime;
		this.EndTime=endtime;
		this.lastTime=lasttime;
		this.AttemptedLeaveTime=AttemptedLeaveTime;
		this.userID=userid;
		this.price=pri;
		this.hotelID=hotelid;
		this.roomInfo=roominfo;
		this.evaluate=evaluate;
	}


	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public Timestamp getStartTime() {
		return StartTime;
	}

	public void setStartTime(Timestamp startTime) {
		StartTime = startTime;
	}

	public Timestamp getEndTime() {
		return EndTime;
	}

	public void setEndTime(Timestamp endTime) {
		EndTime = endTime;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Timestamp getAttemptedLeaveTime() {
		return AttemptedLeaveTime;
	}

	public void setAttemptedLeaveTime(Timestamp attemptedLeaveTime) {
		AttemptedLeaveTime = attemptedLeaveTime;
	}

	public Timestamp getTimeCheckIn() {
		return TimeCheckIn;
	}

	public void setTimeCheckIn(Timestamp timeCheckIn) {
		TimeCheckIn = timeCheckIn;
	}

	public Timestamp getTimeCheckOut() {
		return TimeCheckOut;
	}

	public void setTimeCheckOut(Timestamp timeCheckOut) {
		TimeCheckOut = timeCheckOut;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public String getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}

	public boolean isEvaluate() {
		return evaluate;
	}

	public void setEvaluate(boolean evaluate) {
		this.evaluate = evaluate;
	}



}
