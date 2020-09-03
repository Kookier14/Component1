package com.service;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;

import com.controller.LoginResult;
import com.domain.Api;
import com.domain.Menu;
import com.domain.Role;
import com.domain.Token;
import com.domain.User;

public interface UserService {
	public User getUserById(String userId);
	public Integer addUser(User user,String userId);
	public List<User> showAllUsers();
	public List<Role> showAllRoles();
	public Integer deleteUser(String userId,String curUserId);
	public String login(User user);
	public Integer isAvailavle(String userId,String url);
	public Integer updateUser(User user,String userId);
	public List<Role> showRolesByUserId(String userId);
	public List<User> showUserByName(String userName);
	public Integer updatePassword(User user);
	public Integer addRole(Role role);
	public Integer updateRole(Role role);
	public Integer deleteRole(Role role);
	public List<Menu> showAllmenus();
	public LoginResult genResult(String userId);
	public List<Menu> getMenuByToken(String token);
	public String getUserByToken(String token);
	public Integer deleteToken(String token);
	public List<Api> getAllApi();
	public List<String> getApiByMenu(String menuId);
	public Token selectToken(String token);

}
