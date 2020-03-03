package com.refactoring.fcm.DTO.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
public class Account implements UserDetails {

	@Size(min = 5, max = 15)
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", message = "아이디는 반드시 영문자와 숫자로만 구성해야합니다!!")
	@NotNull(message = "이 필드는 비어있을 수 없습니다!!!")
	private String id;
	private String password;
	private String type;
	private boolean isAccountNonExpired = true;
	private boolean isAccountNonLocked = true;
	private boolean isCredentialsNonExpired = true;
	private boolean isEnabled = true;
	private String center_id;
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.isAccountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isAccountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.isCredentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}

}
