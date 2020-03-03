package com.refactoring.fcm.service;

import com.refactoring.fcm.DTO.user.Account;
import com.refactoring.fcm.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author seojaeyeon
 * spring-security UserDetailsService 구현
 * passwordEncoder- Default
 */
@Service
public class AccountService implements UserDetailsService{

	private AccountDAO accountDao;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setAccountDao(AccountDAO accountDao){
		this.accountDao=accountDao;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder){
		this.passwordEncoder=passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account=accountDao.findById(username);
		account.setAuthorities(getAuthorities(username));

		UserDetails userDetails=new UserDetails() {

			@Override
			public boolean isEnabled() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public String getUsername() {
				return account.getId();
			}

			@Override
			public String getPassword() {
				return account.getPassword();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return account.getAuthorities();
			}

		};
		return account;
	}

	public Account save(Account account, String role, String type) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setType(type);
		accountDao.save(account);
		accountDao.saveAutority(account, role);
		return account;
	}

	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<String> string_authorities = accountDao.findAuthoritiesByID(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String authority : string_authorities) {
			authorities.add(new SimpleGrantedAuthority(authority));
		}
		return authorities;
	}

	public boolean matchPassword(String id, String password) {
		String ori=loadUserByUsername(id).getPassword();
		return passwordEncoder.matches(password,ori);
	}

	public boolean updatePassword(String id, String password){
		accountDao.updatePassword(id, passwordEncoder.encode(password));
		return true;
	}




}
