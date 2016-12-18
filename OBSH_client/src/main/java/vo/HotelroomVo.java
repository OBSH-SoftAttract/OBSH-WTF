package vo;

import java.sql.Timestamp;

public class HotelroomVo {
	private int roomID;
	private Timestamp TimeCheckIn;
	private int hotelID;
	private Timestamp AttemptedLeaveTime;
	private Timestamp TimeCheckOut;
	private String roomType;
	private double price;
	private boolean IfOccupied;

    public HotelroomVo(int roomID,Timestamp datein,int hotelID,Timestamp attemptedLeaveTime,
    		Timestamp timecheckout,String roomtype,double price,boolean ifoccupied){
    	this.roomID=roomID;
    	this.TimeCheckIn=datein;
    	this.hotelID=hotelID;
    	this.AttemptedLeaveTime=attemptedLeaveTime;
    	this.TimeCheckOut=timecheckout;
    	this.roomType = roomtype;
    	this.price = price;
    	this.IfOccupied=ifoccupied;
    }

	public boolean isIfOccupied() {
		return IfOccupied;
	}

	public void setIfOccupied(boolean ifOccupied) {
		IfOccupied = ifOccupied;
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
		AttemptedLeaveTime = attemptedLeaveTime;
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
