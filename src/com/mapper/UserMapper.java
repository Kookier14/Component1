package com.mapper;

import java.util.List;

import com.domain.Api;
import com.domain.Menu;
import com.domain.Role;
import com.domain.RoleMenuMap;
import com.domain.Token;
import com.domain.User;

public interface UserMapper {
	public User getUserById(Integer id);
	public Integer addUser(User user);
	public Integer addRoleForUser(User user);
	public User getUserByName(String name);
	public List<User> showAllUsers();
	public List<String> getRolesByUserId(String userId);
	public Role getRoleById(String roleId);
	public List<Role> showAllRoles();
	public Integer deleteUserById(String userId);
	public Integer deleteRolesOfUser(String userId);
	public List<String> getMenuByRole(String roleId);
	public String getUrlByMenuId(String menuId);
	public Integer updateUser(User user);
	public Integer updatePassword(User user);
	public Integer addRole(Role role);
	public Integer addRoleMenuMap(RoleMenuMap roleMenuMap);
	public Role selectRoleByName(String roleName);
	public Integer deleteRole(String roleId);
	public Integer deleteRoleMenuMap(String roleId);
	public Integer updateRole(Role role);
	public List<Menu> showAllMenus();
	public Role getMenuById(String menuId);
	public Integer addToken(Token token);
	public Integer deleteToken(String token);
	public Token selectToken(String token);
	public Token selectTokenByUserId(String userId);
	public List<Api> getAllApi();
	public List<String> getApiByMenu(String menuId);
	
}
