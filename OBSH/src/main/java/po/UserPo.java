package po;

import vo.UserVo;

public class UserPo {
	private int id;
	private String username;
	private String password; 
	private String phone; 
	
	public UserPo (int i, String pa,String ph,String un){ 
		this.id=i;
		this.password=pa; 
		this.phone=ph;
		this.username=un;
	}
	public UserPo(UserVo vo) {
		this.id=vo.getID();
		this.password=vo.getPassword(); 
		this.phone=vo.getPhone();
		this.username=vo.getUsername();
	}
	public int getID(){
		return id;
	}
	public void setID(int i){
		this.id=i; 
	}
	public String getPassword(){ 
		return password;
	}
	public void setPassword(String pa){
		this.password=pa; 
	}
	public String getPhone(){ 
		return phone;
	}
	public void setPhone(String ph){
		this.phone=ph;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}