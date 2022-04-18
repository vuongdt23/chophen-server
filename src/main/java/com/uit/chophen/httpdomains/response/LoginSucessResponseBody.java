package com.uit.chophen.httpdomains.response;

public class LoginSucessResponseBody {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginSucessResponseBody(String token) {
		super();
		this.token = token;
	}
	

}
