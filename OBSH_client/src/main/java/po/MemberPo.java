package po;

import java.sql.Date;

/**
 * userId          用户编号
 * birthday		      普通会员生日
 * corpoateName    企业名称
 * rank	                      会员等级
 * memberInfo      会员信息详情
 * @author bxh
 */

public class MemberPo {
	
	private int userId;
	
	private Date birthday;
	
	private String corpoateName;
	
	private int rank;
	
	private String memberInfo;

	public MemberPo(){
		super();
	}

	public MemberPo(int userId, Date birthday, String corpoateName, int rank, String memberInfo) {
		super();
		this.userId = userId;
		this.birthday = birthday;
		this.corpoateName = corpoateName;
		this.rank = rank;
		this.memberInfo = memberInfo;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getCorpoatename() {
		return corpoateName;
	}
	
	public void setCorpoatename(String corpoatename) {
		this.corpoateName = corpoatename;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String getMemberInfo() {
		return memberInfo;
	}
	
	public void setMemberInfo(String memberInfo) {
		this.memberInfo = memberInfo;
	}

}