package vo;

import java.io.Serializable;
import java.sql.Date;

/**
 * userId          用户编号
 * birthday		      普通会员生日
 * corpoateName    企业名称
 * rank	                      会员等级
 * memberInfo      会员信息详情
 * @author bxh
 */


public class MemberVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private int userId=0;
	
	private Date birthday=null;
	
	private String corpoateName="";
	
	private int rank=0;
	
	private String memberInfo="";
	
	public MemberVo(int userId, Date birthday, String corpoateName, int rank, String memberInfo) {

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

	public String getCorpoateName() {
		return corpoateName;
	}

	public void setCorpoateName(String corpoateName) {
		this.corpoateName = corpoateName;
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