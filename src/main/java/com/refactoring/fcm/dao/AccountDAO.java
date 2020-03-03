package com.refactoring.fcm.dao;

import com.refactoring.fcm.DTO.user.Account;
import com.refactoring.fcm.mapper.AccountMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Create Account and Account's Authority - save, saveAutority
 * Read Account and Accounts' Authorities - findById, findAuthoritiesById
 * Revise password
 */
@Repository
@Getter
public class AccountDAO {

	private AccountMapper accountMapper;

	@Autowired
	public void setAccountManager(AccountMapper accountMapper){
		this.accountMapper=accountMapper;
	}

	public Account save(Account account){
		accountMapper.insertUser(account);
		return account;
	}

	public Account saveAutority(Account account, String role){
		accountMapper.insertUserAutority(account.getId(), role);
		return account;
	}

	public Account findById(String username) {
		return accountMapper.readAccount(username);
	}

	public List<String> findAuthoritiesByID(String username){
		return accountMapper.readAutorities(username);
	}

	public void updatePassword(String id, String password){
		accountMapper.updatePassword(id,password);
	}

	public boolean deleteUser(String id){
		accountMapper.deleteAccount(id);
		return true;
	}

	public boolean deleteAutorities(String id){
		accountMapper.deleteAuthorities(id);
		return true;
	}

}
