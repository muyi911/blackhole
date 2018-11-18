package pers.miaku.blackhole.rest.form;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 登录用户
 * 
 * @author caozn
 *
 */
public class UserForm {
	@NotEmpty(message = "账号不能为空")
	@JsonProperty("loginid")
	private String loginId;

	@NotEmpty(message = "密码不能为空")
	@JsonProperty("password")
	private String password;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
