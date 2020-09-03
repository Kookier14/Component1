package com.dao;

import java.util.List;
import javax.annotation.Resource;

import org.junit.validator.PublicClassValidator;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.domain.Api;
import com.domain.Menu;
import com.domain.Role;
import com.domain.RoleMenuMap;
import com.domain.Token;
import com.domain.User;



@Repository("userDao")
public class UserDao {
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public User getUserById(String userId){
		return (User) sqlSessionTemplate.selectOne("com.mapper.UserMapper.getUserById", userId);
	}
	
	public Integer addUser(User user){
		int s1=sqlSessionTemplate.insert("com.mapper.UserMapper.addUser",user);
		 int s2=sqlSessionTemplate.insert("com.mapper.UserMapper.addRoleForUser",user);
		 if(s1>0&&s2>0)
			 return 1;
		 else return -1;
		 
	}
	public User getUserByName(String name){	
		return sqlSessionTemplate.selectOne("com.mapper.UserMapper.getUserByName", name);
	}
	public List<User> showAllUsers(){
		return sqlSessionTemplate.selectList("com.mapper.UserMapper.showAllUsers");
	}
	public List<String> getRolesByUserId(String userId){
		return sqlSessionTemplate.selectList("com.mapper.UserMapper.getRolesByUserId",userId);
	}
	public Role getRoleById(String roleId){
		return sqlSessionTemplate.selectOne("com.mapper.UserMapper.getRoleById",roleId);
	}
	
	public List<Role> showAllRoles(){
		return sqlSessionTemplate.selectList("com.mapper.UserMapper.showAllRoles");
	}
	public Integer deleteUser(String userId){
		sqlSessionTemplate.delete("com.mapper.UserMapper.deleteUserById", userId);
		return sqlSessionTemplate.delete("com.mapper.UserMapper.deleteRolesOfUser", userId);
	}
	
	public List<String> getMenuByRole(String roleId){
		return sqlSessionTemplate.selectList("com.mapper.UserMapper.getMenuByRole", roleId);
	}
	
	public String getUrlByMenuId(String menuId){
		return sqlSessionTemplate.selectOne("com.mapper.UserMapper.getUrlByMenuId", menuId);
	}
	public Integer updateUser(User user){
		 sqlSessionTemplate.update("com.mapper.UserMapper.updateUser", user);
		 sqlSessionTemplate.delete("com.mapper.UserMapper.deleteRolesOfUser", user.getUserId());
		 return sqlSessionTemplate.insert("com.mapper.UserMapper.addRoleForUser",user);
	}
	public Integer updatePassword(User user){
		return sqlSessionTemplate.update("com.mapper.UserMapper.updatePassword",user);
	}
	
	public Integer addRole(Role role){
		return sqlSessionTemplate.insert("com.mapper.UserMapper.addRole", role);
		
	}
	public Integer addRoleMenuMap(RoleMenuMap roleMenuMap){
		return sqlSessionTemplate.insert("com.mapper.UserMapper.addRoleMenuMap", roleMenuMap);
	}
	public Role selectRoleByName(String roleName){
		return sqlSessionTemplate.selectOne("com.mapper.UserMapper.selectRoleByName", roleName);
	}
	public Integer deleteRole(String roleId){
		return sqlSessionTemplate.delete("com.mapper.UserMapper.deleteRole", roleId);
	}
	public Integer deleteRoleMenuMap(String roleId){
		return sqlSessionTemplate.delete("com.mapper.UserMapper.deleteRoleMenuMap",roleId);
	}
	public Integer updateRole(Role role){
		return sqlSessionTemplate.update("com.mapper.UserMapper.updateRole", role);
	}
	public List<Menu> showAllMenus(){
		return sqlSessionTemplate.selectList("com.mapper.UserMapper.showAllMenus");
	}
	
	public Menu getMenuById(String menuId){
		return sqlSessionTemplate.selectOne("com.mapper.UserMapper.getMenuById", menuId);
	}
	
	public Integer addToken(Token token){
		return sqlSessionTemplate.insert("com.mapper.UserMapper.addToken", token);
	}
	public Integer deleteToken(String token){
		return sqlSessionTemplate.insert("com.mapper.UserMapper.deleteToken",token);
	}
	public Token selectToken(String token){
		return sqlSessionTemplate.selectOne("com.mapper.UserMapper.selectToken", token);
	}
	public Token selectTokenByUserId(String userId){
		return sqlSessionTemplate.selectOne("com.mapper.UserMapper.selectTokenByUserId", userId);
	}
	public List<Api> getAllApi(){
		return sqlSessionTemplate.selectList("com.mapper.UserMapper.getAllApi");
	}
	
	public List<String> getApiByMenu(String menuId){
		return sqlSessionTemplate.selectList("com.mapper.UserMapper.getApiByMenu", menuId);
	}
}
