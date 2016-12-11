package vo;


public class UserVo {
	private String id;
	private String username;
	private String password;
	private String phone;

	public UserVo() {
		super();
	}

	public UserVo (String i, String pa,String ph,String un){ 
		this.id=i;
		this.password=pa; 
		this.phone=ph;
		this.username=un;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String ph) {
		this.phone = ph;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pa) {
		this.password = pa;
	}
}
