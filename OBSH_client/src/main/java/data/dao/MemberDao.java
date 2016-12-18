package data.dao;
/**
 * @author bxh
 */
import po.MemberPo;

public interface MemberDao {

	/**
	 * @param memberId
	 * @return	获取会员信息
	 */
	public MemberPo getMember(int memberId);
	
	/**
	 * @param MemberPo
	 * @return	更新会员信息
	 */
	public boolean updateMember(MemberPo memberPo);
	
	/**
	 * @param MemberPo
	 * @return	添加会员
	 */
	public boolean addMemberPo(MemberPo memberPo);

	/**
	 * 
	 * @param ID
	 * @return  判断是否是会员
	 */
	public boolean isMember(int ID);	
	
}
