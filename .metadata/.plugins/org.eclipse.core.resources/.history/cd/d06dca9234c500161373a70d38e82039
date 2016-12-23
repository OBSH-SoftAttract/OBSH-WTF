package po;

import java.io.Serializable;

import vo.UserVo;

public class UserPo implements Serializable{
	private int id;
	private String username;
	private String password;
	private String phone;
	private boolean login;

	public UserPo(int i, String pa, String ph, String un, boolean log) {
		this.id = i;
		this.password = pa;
		this.phone = ph;
		this.username = un;
		this.login = log;
	}

	public UserPo(UserVo vo) {
		this.id = vo.getID();
		this.password = vo.getPassword();
		this.phone = vo.getPhone();
		this.username = vo.getUsername();
		this.login = vo.isLogin();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

}