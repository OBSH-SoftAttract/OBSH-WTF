package vo;
import java.sql.Timestamp;

/**
 * credit       信用值
 * userInfo		用户名称
 * time         信用充值时当前时间
 * creditInfo   信用记录详情
 * @author bxh
 */

public class CreditVo {
	private String userID;
	
	private Timestamp  time;
	
	private int action;
	
	private String CreditChange;
	
	private double CreditResult;
	
	public CreditVo(String id,Timestamp t,int act,String creChange,double credit){
		this.userID=id;
		this.time=t;
		this.action=act;
		this.CreditChange=creChange;
		this.CreditResult=credit;
	}
	
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserId(String userID) {
		this.userID = userID;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public int getAction() {
		return action;
	}
	
	public void setAction(int action) {
		this.action = action;
	}
	
	public String getCreditChange() {
		return CreditChange;
	}
	
	public void setCreditChange(String CreditChange) {
		this.CreditChange = CreditChange;
	}
	
	public double getCreditResult() {
		return CreditResult;
	}
	
	public void setCreditResult(double CreditResult) {
		this.CreditResult = CreditResult;
	}

}