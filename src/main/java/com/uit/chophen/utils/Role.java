package com.uit.chophen.utils;
import static com.uit.chophen.utils.AuthorityConstant.*;
public enum Role {

	ROLE_USER(USER_AUTHORITIES),
	ROLE_ADMIN(ADMIN_AUTHORITIES);

	private String[] authorities;
	
	

	Role(String...authorities) {
		this.authorities = authorities;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}
}
