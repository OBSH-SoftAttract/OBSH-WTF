package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HotelVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int hotelID=0;
	private String name="";
	private int star=0;
	private String briefInfo="";  //简介
	private String location="";  //地址+商圈
	private List<String> summary=new ArrayList<String>();//评价
	private String services="";//服务设施
	private String roomInfo="";//房间类型+个数+价格  中间用分号区分
	private double score=0;
    private int scoreCount=0;
    private double minPrice=0;
    private String Companies="";
	
	public HotelVo(int hotelID, String name, int star, String location, List<String> summary, String services,
			String roomInfo,double score,String briefInfo,int scoreCount,String companies) {
		this.hotelID = hotelID;
		this.name = name;
		this.star = star;
		this.location = location;
		this.summary = summary;
		this.services = services;
		this.roomInfo = roomInfo;
		this.score=score;
		this.briefInfo=briefInfo;
		this.scoreCount = scoreCount;
		this.Companies=companies;
		
		this.minPrice=10000000;
		String rooms[]=roomInfo.split(";");
		for(int i=0;i<rooms.length;i++){
			String room[]=rooms[i].split("[+]");
			if( Double.parseDouble(room[2])<this.minPrice){
				this.minPrice=Double.parseDouble(room[2]);
			}
		}
	}
	
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public String getCompanies() {
		return Companies;
	}

	public void setCompanies(String companies) {
		Companies = companies;
	}
	public double getMinPrice() {
		return minPrice;
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

	public String getBriefInfo() {
		return briefInfo;
	}

	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
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
		this.score = score;
	}

	public int getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(int scoreCount) {
		this.scoreCount = scoreCount;
	}



}