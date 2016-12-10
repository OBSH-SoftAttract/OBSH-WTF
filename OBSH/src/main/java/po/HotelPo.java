package po;

import java.util.List;

import vo.HotelVo;

public class HotelPo {
	private int hotelID;
	private String name;
	private int star;
	private String briefInfo;  //简介
	private String location;  //地址+商圈
	private List<String> summary; //评价
	private String services;  //服务设施
	private String roomInfo;//房间类型+个数 中间用分号区分
	private static double score=0;
	
	public HotelPo(HotelVo hotelvo) {
		this.hotelID = hotelvo.getHotelID();
		this.name = hotelvo.getName();
		this.star = hotelvo.getStar();
		this.location = hotelvo.getLocation();
		this.summary = hotelvo.getSummary();
		this.services = hotelvo.getServices();
		this.roomInfo =hotelvo.getRoomInfo();
		HotelPo.score=hotelvo.getScore();
	}

	public HotelPo(int hotelID, String name, int star, String location, List<String> summary, String services,
			String roominfo,double s,String br) {
		this.hotelID = hotelID;
		this.name = name;
		this.star = star;
		this.location = location;
		this.summary = summary;
		this.services = services;
		this.roomInfo = roominfo;
		HotelPo.score=s;
		this.briefInfo=br;
	}

	public String getBriefInfo() {
		return briefInfo;
	}

	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getSummary() {
		return summary;
	}

	public void setSummary(List<String> summary) {
		this.summary = summary;
	}
	
	public void addSummary(String summary){
		this.summary.add(summary);
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		HotelPo.score = score;
	}


}

