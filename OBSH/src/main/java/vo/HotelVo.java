package vo;

import java.util.List;


public class HotelVo {
	private int hotelID;
	private String name;
	private int star;
	private String briefInro;  //简介
	private String location;  //地址+商圈
	private List<String> summary;
	private String services;
	private String roomInfo;//房间类型+个数 中间用分号区分
	private static double score=0;


	public HotelVo(int hotelID, String name, int star, String location, List<String> summary, String services,
			String roominfo,double s,String br) {
		this.hotelID = hotelID;
		this.name = name;
		this.star = star;
		this.location = location;
		this.summary = summary;
		this.services = services;
		this.roomInfo = roominfo;
		HotelVo.score=s;
		this.briefInro=br;
	}

	public String getBriefInro() {
		return briefInro;
	}

	public void setBriefInro(String briefInro) {
		this.briefInro = briefInro;
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
		HotelVo.score = score;
	}


}