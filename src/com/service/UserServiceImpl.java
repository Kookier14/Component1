package com.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.controller.LoginResult;
import com.dao.LogDao;
import com.dao.UserDao;
import com.domain.Api;
import com.domain.Log;
import com.domain.Menu;
import com.domain.Role;
import com.domain.RoleMenuMap;
import com.domain.Token;
import com.domain.User;
import com.mapper.UserMapper;
import com.util.Encryption;
import com.util.FuzzySearch;


@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private LogDao LogDao;
	

	@Override
	public Integer addUser(User user,String userId) {
		User user1=userDao.getUserByName(user.getUserName());
		if(user1!=null)
			return -1;
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(Encryption.md5Password(user.getPassword()));
        Date date=new Date();
        user.setCreateTime(new Timestamp(date.getTime()));
        insertLog(userId, "添加了新用户"+user.getUserName(),"添加");
		return userDao.addUser(user);
	}

	@Override
	public List<User> showAllUsers() {
		List<User> users= userDao.showAllUsers();
		for(int i=0;i<users.size();i++){
			List<String> roleIds=userDao.getRolesByUserId(users.get(i).getUserId());
			List<Role> roles=new ArrayList<>();
			for(int j=0;j<roleIds.size();j++){
				roles.add(userDao.getRoleById(roleIds.get(j)));
			}
			users.get(i).setRoles(roles);
		}
		return users;
	}

	@Override
	public List<Role> showAllRoles() {
		List<Role> roles=userDao.showAllRoles();
		for(int i=0;i<roles.size();i++){
			List<String> menuIds=userDao.getMenuByRole(roles.get(i).getRoleId());
			List<Menu> menus=new ArrayList<>();
			for(int j=0;j<menuIds.size();j++){
				menus.add(userDao.getMenuById(menuIds.get(j)));
			}
			roles.get(i).setMenus(menus);
		}
		return roles;
	}

	//userId为待删除用户的id，curUserId为当前登录系统用户的id
	@Override
	public Integer deleteUser(String userId,String curUserId) {
		String userName=userDao.getUserById(userId).getUserName();
		insertLog(curUserId, "删除了用户"+userName,"删除");
		return userDao.deleteUser(userId);
	}
    
	
	//如果登录成功返回用户id
	@Override
	public String login(User user) {
		User user1=userDao.getUserByName(user.getUserName());
		if(user1==null)
			return "-1";
		if(user1.getPassword().equals(Encryption.md5Password(user.getPassword()))){
			insertLog(user1.getUserId(), "登录了系统","登录");
			return user1.getUserId();
		}
		return "-2";
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

	@Override
	public Integer isAvailavle(String userId, String url) {
		List<String> roles=userDao.getRolesByUserId(userId);
		for(int i=0;i<roles.size();i++){
			List<String> menuIds=userDao.getMenuByRole(roles.get(i));
			for(int j=0;j<menuIds.size();j++){
				String url1=userDao.getUrlByMenuId(menuIds.get(j));
				if(url1.equals(url))
					return 1;
			}
		}
		return 0;
	}
	
	private Integer insertLog(String userId,String mes,String type){
		String userName=userDao.getUserById(userId).getUserName();
		Log log=new Log();
		log.setLogId(UUID.randomUUID().toString());
		mes=userName+mes;
		log.setMessage(mes);
		Date date=new Date();
		log.setCreateTime(new Timestamp(date.getTime()));
		log.setType(type);
		log.setOperator(userName);
		return LogDao.insertLog(log);
		
	}

	@Override
	public Integer updateUser(User user, String userId) {
		User user1=userDao.getUserByName(user.getUserName());
		if(user1!=null&&!user1.getUserId().equals(user.getUserId()))
			return -1;
		
		insertLog(userId, "更改了用户"+user.getUserName()+"的信息","更新");
		return userDao.updateUser(user);
		
	}

	@Override
	public List<Role> showRolesByUserId(String userId) {
		
		List<String> lists=userDao.getRolesByUserId(userId);//返回的是id
		List<Role> roles=new ArrayList<>();
		for(int i=0;i<lists.size();i++){
			roles.add(userDao.getRoleById(lists.get(i)));
		}
		return roles;
	}

	@Override
	public List<User> showUserByName(String userName) {
		List<User> res=new ArrayList<>();
		List<User> allUsers=userDao.showAllUsers();
		for(int i=0;i<allUsers.size();i++){
			if(FuzzySearch.search(userName, allUsers.get(i).getUserName())){
			User user1=allUsers.get(i);
			List<Role> roles=new ArrayList<>();
			roles=showRolesByUserId(user1.getUserId());
			user1.setRoles(roles);
			res.add(user1);
			}
			
		}
		return res;
	}

	@Override
	public Integer updatePassword(User user) {
		return userDao.updatePassword(user);
	}

	@Override
	public Integer addRole(Role role) {
		Role role1=userDao.selectRoleByName(role.getRoleName());
		if(role1!=null)
			return -1;
		role.setRoleId(UUID.randomUUID().toString());
		userDao.addRole(role);
		for(int i=0;i<role.getMenuIds().size();i++){
			RoleMenuMap roleMenuMap=new RoleMenuMap();
			roleMenuMap.setRoleId(role.getRoleId());
			roleMenuMap.setMenuId(role.getMenuIds().get(i));
			userDao.addRoleMenuMap(roleMenuMap);
		}
		return 1;
	}

	@Override
	public Integer updateRole(Role role) {
		userDao.deleteRoleMenuMap(role.getRoleId());
		userDao.updateRole(role);
		for(int i=0;i<role.getMenuIds().size();i++){
			RoleMenuMap roleMenuMap=new RoleMenuMap();
			roleMenuMap.setRoleId(role.getRoleId());
			roleMenuMap.setMenuId(role.getMenuIds().get(i));
			userDao.addRoleMenuMap(roleMenuMap);
		}
		return 1;
		
	}

	

	@Override
	public Integer deleteRole(Role role) {
		userDao.deleteRoleMenuMap(role.getRoleId());
		userDao.deleteRole(role.getRoleId());
		return 1;
	}

	@Override
	public List<Menu> showAllmenus() {
		return userDao.showAllMenus();
	}

	@Override
	public LoginResult genResult(String userId) {
		User user=userDao.getUserById(userId);
		LoginResult result=new LoginResult();
		List<Role> roles =new ArrayList<>();
		List<String> roleids=userDao.getRolesByUserId(userId);
		for(int i=0;i<roleids.size();i++){
			roles.add(userDao.getRoleById(roleids.get(i)));
		}
		result.setUser(user);
		result.setCurrentUserRole(roles);
		result.setState("1");
		String token=UUID.randomUUID().toString();
		result.setToken(token);
		Token token2=new Token();
		token2.setToken(token);
		token2.setUserId(userId);
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.HOUR,3);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(calendar.getTime());
		Timestamp ts = Timestamp.valueOf(dateStr);
		token2.setExpireTime(ts);
		Token token3=userDao.selectTokenByUserId(userId);
		if(token3!=null){
			userDao.deleteToken(token3.getToken());
		}
		userDao.addToken(token2);
		return result;
	
	}

	@Override
	public List<Menu> getMenuByToken(String token) {
		String userId=userDao.selectToken(token).getUserId();
		List<String> roleIds=userDao.getRolesByUserId(userId);
		Set<String> sets=new HashSet<>();
		for(int i=0;i<roleIds.size();i++){
			List<String> lists=userDao.getMenuByRole(roleIds.get(i));
			for(int j=0;j<lists.size();j++){
				sets.add(lists.get(j));
			}
		}
		List<Menu> res=new ArrayList<>();
		Iterator<String> iterator=sets.iterator();
		while(iterator.hasNext()){
			res.add(userDao.getMenuById(iterator.next()));
		}
		return res;
	}

	@Override
	public String getUserByToken(String token) {
		Token token2=userDao.selectToken(token);
		if(token2==null)
			return null;
		
		return token2.getUserId();
	}

	@Override
	public Integer deleteToken(String token) {
		return userDao.deleteToken(token);
	}

	@Override
	public List<Api> getAllApi() {
		return userDao.getAllApi();
	}

	@Override
	public List<String> getApiByMenu(String menuId) {
		return userDao.getApiByMenu(menuId);
	}

	@Override
	public Token selectToken(String token) {
		return userDao.selectToken(token);
	}
}



