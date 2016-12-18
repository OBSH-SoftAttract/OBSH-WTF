package vo;
import java.sql.Timestamp;

/**
 * userID       客户ID
 * time         信用值变化时间
 * action       动作 int类型（0 订单执行、1 订单异常、2 订单撤销、3 充值）
 * orderID      订单号
 * CreditChange           信用值变化  String表示+-表示增减 之后为具体数值 如+500
 * CreditResultResult     信用值结果 double类型
 * @author bxh
 */

public class CreditVo {
	private int userID;
	
	private Timestamp  time;
	
	private int action;
	
	private String CreditChange;
	
	private double CreditResult;
	
	private String orderID="";
	
	public CreditVo(int id,Timestamp t,int act,String creChange,double credit,String orderID){
		this.userID=id;
		this.time=t;
		this.action=act;
		this.CreditChange=creChange;
		this.CreditResult=credit;
		this.orderID=orderID;
	}
	
	
	public CreditVo() {
		super();
	}


	public String getOrderID() {
		return orderID;
	}


	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}


	public int getUserID() {
		return userID;
	}
	
	public void setUserId(int userID) {
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