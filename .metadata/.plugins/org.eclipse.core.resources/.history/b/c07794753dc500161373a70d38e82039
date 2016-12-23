package po;

import java.sql.Timestamp;

/**
 * userID		                    用户编号 int类型
 * orderID                订单编号
 * time                   每一次信用值变化的时间 类型为DateTIme
 * action                 动作 int类型（0 订单执行、1 订单异常、2 订单撤销、3 充值）
 * CreditChange           信用值变化  String表示+-表示增减 之后为具体数值 如+500
 * CreditResultResult     信用值结果 double类型
 * @author 毕潇晗
 */

public class CreditPo {
	
	private int userID;
	
	private String orderID;
	
	private Timestamp  time;
	
	private int action;
	
	private String CreditChange;
	
	private double CreditResult;
	
	public CreditPo(){
		super();
	}

	/**
	 * 构造只含userID和CreditResult的CreditResultPo用于客户注册后生成初始信用记录 时间是否生成？？？
	 * @param userId
	 * @param CreditResult
	 */
	public CreditPo(int userID, Timestamp time, double CreditResult){
		this.userID = userID;
		this.time = time;
		this.CreditResult = CreditResult;	
	}
	
	/**
	 * 记录有信用变化后每次信用值的变更
	 * @param userID
	 * @param time
	 * @param action
	 * @param CreditResultChange
	 * @param CreditResultResult
	 */
	public CreditPo(int userID,String orderID, Timestamp  time, int action, String CreditChange, double CreditResult) {
		this.userID = userID;
		this.orderID = orderID;
		this.time = time;
		this.action = action;
		this.CreditChange = CreditChange;
		this.CreditResult = CreditResult;
	}

	public int getUserID() {
		return userID;
	}
	
	public void setUserId(int userID) {
		this.userID = userID;
	}
	
	public String getOrderID() {
		return orderID;
	}
	
	public void setOrderId(String orderID) {
		this.orderID = orderID;
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
