package po;

import java.sql.Timestamp;

import vo.HotelroomVo;

/**
 * 
 * roomID 房间编号：301 
 * TimeCheckIn 入住时间 
 * hotelID 酒店的ID 
 * AttemptedLeaveTime 预计退房时间
 * TimeCheckOut 退房时间
 * roomType 房间类型
 * price 房间价格 
 * IfOccupied 占用情况
 */
public class HotelroomPo {
	private int roomID;
	private Timestamp TimeCheckIn;
	private int hotelID;
	private Timestamp AttemptedLeaveTime;
	private Timestamp TimeCheckOut;
	private String roomType;
	private double price;
	private boolean IfOccupied;

	public HotelroomPo(int roomID, Timestamp datein, int hotelID, Timestamp attemptedLeaveTime, Timestamp timecheckout,
			String roomtype, double price, boolean ifoccupied) {
		this.roomID = roomID;
		this.TimeCheckIn = datein;
		this.hotelID = hotelID;
		this.AttemptedLeaveTime = attemptedLeaveTime;
		this.TimeCheckOut = timecheckout;
		this.roomType = roomtype;
		this.price = price;
		this.IfOccupied = ifoccupied;
	}

	public HotelroomPo(HotelroomVo vo) {
		this.roomID=vo.getRoomID();
		this.hotelID=vo.getHotelID();
		this.TimeCheckIn=vo.getTimeCheckIn();
		this.TimeCheckOut=vo.getTimeCheckOut();
		this.AttemptedLeaveTime=vo.getAttemptedLeaveTime();
		this.price=vo.getPrice();
		this.IfOccupied=vo.isIfOccupied();
		this.roomType=vo.getRoomType();
	}

	public boolean isIfOccupied() {
		return IfOccupied;
	}

	public void setIfOccupied(boolean ifOccupied) {
		IfOccupied = ifOccupied;
	}

	public HotelroomPo() {
		super();
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public Timestamp getTimeCheckIn() {
		return TimeCheckIn;
	}

	public void setTimeCheckIn(Timestamp timeCheckIn) {
		TimeCheckIn = timeCheckIn;
	}

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public Timestamp getAttemptedLeaveTime() {
		return AttemptedLeaveTime;
	}

	public void setAttemptedLeaveTime(Timestamp attemptedLeaveTime) {
		this.AttemptedLeaveTime = attemptedLeaveTime;
	}

	public Timestamp getTimeCheckOut() {
		return TimeCheckOut;
	}

	public void setTimeCheckOut(Timestamp timeCheckOut) {
		TimeCheckOut = timeCheckOut;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}