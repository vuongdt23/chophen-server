package com.uit.chophen.security;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uit.chophen.entities.UserProfile;
public class UserPrincipal implements UserDetails {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3233819344014465573L;
	private UserProfile userProfile;

	public UserPrincipal() {
	}

	public UserPrincipal(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return stream(this.userProfile.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.userProfile.getPassword();
	}

	@Override
	public String getUsername() {
		return this.userProfile.getAccountName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return this.userProfile.getIsNotLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.userProfile.getIsActive();
	}

}
