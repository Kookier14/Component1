package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserDao;
import com.domain.Menu;
import com.domain.Role;
import com.domain.Token;
import com.domain.User;
import com.service.UserService;
import com.util.Encryption;

@CrossOrigin(origins = "*",maxAge = 3000)
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
    @RequestMapping(value="/addUser", method={RequestMethod.POST,RequestMethod.GET})
	//public ReturnMsg addUser(@RequestBody RoleIds roleIds,HttpSession httpSession){
    public ReturnMsg addUser(@RequestBody RoleIds roleIds,@RequestHeader("Access-Token") String token){
    	System.out.println(roleIds.getUserName());
    	List<Role> roles=new ArrayList<>();
    	for(int i=0;i<roleIds.getLists().size();i++){
    		Role tmp=new Role();
    		tmp.setRoleId(roleIds.getLists().get(i));
    		roles.add(tmp);
    		System.out.println(roleIds.getLists().get(i));
    	}
    	User user=new User();
    	user.setRoles(roles);
    	user.setUserName(roleIds.getUserName());
    	user.setPassword(roleIds.getPassword());
    	System.out.println("token"+token);
    	String userId=userService.getUserByToken(token);
    	System.out.println("userId"+userId);
//    	if(userId==null)
//    		return -5;
		//return userService.addUser(user,userId);
    	int s=userService.addUser(user,userId);
    	ReturnMsg returnMsg=new ReturnMsg();
    	returnMsg.setState(s);
    	if(s>0){
    		returnMsg.setMsg("success");
    	}
    	else returnMsg.setMsg("failure");
    	return returnMsg;
		
	}
    
    @RequestMapping("/showAllUsers")
    public List<User> showAllUsers(){
    	return userService.showAllUsers();
    }
    
    @RequestMapping("/showAllRoles")
    public List<Role> showAllRoles(){
    	List<Role> roles= userService.showAllRoles();
    	int j=-1;
    	for(int i=0;i<roles.size();i++){
    		if(roles.get(i).getRoleName().equals("系统管理员")){
    			j=i;
    			break;
    		}
    	}
    	if(j>=0){
    		roles.remove(j);
    	}
    	return roles;
    }
    @RequestMapping(value="/deleteUser", method = {RequestMethod.POST,RequestMethod.GET})
    public ReturnMsg deleteUser(@RequestBody User user,@RequestHeader("Access-Token") String token){
    	System.out.println(user.getUserId());  	
    	String curUserId=userService.getUserByToken(token);
//    	if(curUserId==null)
//    		return -5;
    	System.out.println(curUserId);
    	//return userService.deleteUser(user.getUserId(),curUserId);
    	int s=userService.deleteUser(user.getUserId(),curUserId);
    	ReturnMsg returnMsg=new ReturnMsg();
    	returnMsg.setState(s);
    	if(s>0){
    		returnMsg.setMsg("success");
    	}
    	else returnMsg.setMsg("failure");
    	return returnMsg;
    }
    @RequestMapping("/login")
    public LoginResult login(@RequestBody User user,HttpSession httpSession){
    	LoginResult result=new LoginResult();
    	String s=userService.login(user);
    	if(s.equals("-1")||s.equals("-2")){
    		result.setState(s);
    		return result;
    	}
    	else{
    		httpSession.setAttribute("userName", user.getUserName());
    		httpSession.setAttribute("userId", s);
    		System.out.println(httpSession.getAttribute("userId"));
    		return userService.genResult(s);
    	}
    
    	
    }
    //用于判断该用户是否有访问该资源的权限,-1表示未登陆，1表示有权访问，0表示无权访问
//    @RequestMapping("/isAvailable")
//    public Integer isAvailable(HttpSession httpSession,String url){
//    	
//    	return userService.isAvailavle(userId, url);
//    }
    
    @RequestMapping("/logOut")
    public Integer logOut(@RequestHeader("Access-Token") String token){
    	System.out.println("logout"+token);
    	return userService.deleteToken(token);	
    }
    
    @RequestMapping("/getCurrentUser")
    public String getCurrentUser(HttpSession httpSession){
    	String userName=(String) httpSession.getAttribute("userName");
    	return userName;
    }
    
    @RequestMapping("/updateUser")
    public ReturnMsg updateUser(@RequestBody RoleIds roleIds,@RequestHeader("Access-Token") String token){
    	System.out.println(roleIds.getUserName());
    	List<Role> roles=new ArrayList<>();
    	for(int i=0;i<roleIds.getLists().size();i++){
    		Role tmp=new Role();
    		tmp.setRoleId(roleIds.getLists().get(i));
    		roles.add(tmp);
    		System.out.println(roleIds.getLists().get(i));
    	}
    	User user=new User();
    	user.setRoles(roles);
    	user.setUserId(roleIds.getUserId());
    	user.setUserName(roleIds.getUserName());
    	//user.setPassword(roleIds.getPassword());
    	String userId=userService.getUserByToken(token);
//    	if(userId==null)
//    		return -5;
    	int s=userService.updateUser(user, userId);
    	ReturnMsg returnMsg=new ReturnMsg();
    	returnMsg.setState(s);
    	if(s>0){
    		returnMsg.setMsg("success");
    	}
    	else returnMsg.setMsg("failure");
    	return returnMsg;
    }
    @RequestMapping("/updatePassword")
    public ReturnMsg updatePassword(@RequestBody User user){
    	user.setPassword(Encryption.md5Password(user.getPassword()));
    	int s=userService.updatePassword(user);
    	ReturnMsg returnMsg=new ReturnMsg();
    	returnMsg.setState(s);
    	if(s>0){
    		returnMsg.setMsg("success");
    	}
    	else returnMsg.setMsg("failure");
    	return returnMsg;
    }
    
    
    @RequestMapping("/showRolesByUserId")
    public List<Role> showRolesByUserId(@RequestBody User user){
    	
    	return userService.showRolesByUserId(user.getUserId());
    }
    
    @RequestMapping("/showUserByUserName")
    public List<User> showUserByName(@RequestBody User user){
    	System.out.println(user.getUserName());
    	return userService.showUserByName(user.getUserName());
    }
    
    @RequestMapping("/addRole")
    public Integer addRole(@RequestBody Role role){
    	return userService.addRole(role);
    }
    @RequestMapping("/deleteRole")
    public Integer deleteRole(@RequestBody Role role){
    	return userService.deleteRole(role);
    }
    @RequestMapping("/updateRole")
    public Integer updateRole(@RequestBody Role role){
    	return userService.updateRole(role);
    }
    
    @RequestMapping("/showAllMenus")
    public List<Menu> showAllMenus(){
    	return userService.showAllmenus();
    }
    
    @RequestMapping("/getMenuByToken")
    public List<Menu> getMenuByToken(@RequestBody Token token){
    	return userService.getMenuByToken(token.getToken());
    }
   
    
      
}
