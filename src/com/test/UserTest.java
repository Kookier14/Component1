package com.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.validator.PublicClassValidator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.UserDao;
import com.domain.Api;
import com.domain.Menu;
import com.domain.Role;
import com.domain.Token;
import com.domain.User;
import com.mysql.fabric.xmlrpc.base.Data;
import com.service.UserService;
import com.util.Encryption;
import com.util.FileUtil;
import com.util.FuzzySearch;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserTest {
	private static Logger logger = Logger.getLogger(UserTest.class);
	@Resource
	private UserService userService = null;
	
	
	@Resource
	private UserDao userDao=null;
	
	public void testAddUser(){
		User user=new User();
		user.setUserName("bbb");
		user.setPassword("123456");
		List<Role> lists=new ArrayList<>();
		Role role1=new Role("340cf31d-76a6-4f63-87dc-fed5fac9b59c","系统管理员");
		Role role2=new Role("5d080c79-286a-463b-977b-3c1b670f12f6","组件开发人员");
		lists.add(role1);
		lists.add(role2);
		user.setRoles(lists);
		Date date=new Date();
		System.out.println(new Timestamp(date.getTime()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//user.setCreateTime(format.format(date));
		user.setCreateTime(new Timestamp(date.getTime()));
		
	}
	
	//@Test
	public void testShowAllUsers(){
		List<User> users=userService.showAllUsers();
		System.out.println(users.get(0).getUserName());
		//System.out.println(users.get(1).getRoles().get(0).getRoleNmae());
	}
	
	@Test
	public void testShowAllRoles(){	
		
		Token token=userDao.selectToken("a3afccb2-5d69-4f33-9634-e61e51f479ef"
);		
		System.out.println(token.getExpireTime());
		
		Date date=new Date();
		System.out.println(date);
		Timestamp timestamp=new Timestamp(date.getTime());
		System.out.println(timestamp.after(date));
		//List<Api> lists=userService.getAllApi();
		List<String> list=userService.getApiByMenu("2");
		//System.out.println(lists.toString());
		System.out.println(list.toString());
//		Date date=new Date();
//		System.out.println(date);
//		Timestamp timestamp=new Timestamp(date.getTime());
//		System.out.println(timestamp);
//		Calendar calendar = Calendar.getInstance(); 
//		calendar.add(Calendar.HOUR,3); //加小时
//		System.out.println(calendar.getTime());
//		//Timestamp timestamp1=new Timestamp(calendar.getTime());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		String dateStr = sdf.format(calendar.getTime());
//		Timestamp ts = Timestamp.valueOf(dateStr);
//		
//		System.out.println(ts);
//		
//		
//		System.out.println(Encryption.md5Password("md5Password"));
//		String aString="aa";	
//		String keyWord="爽肤水 广泛的";	
//		String[] keyWords=keyWord.split(" ");
//		System.out.println(keyWords[0]);
//		System.out.println(keyWords[1]);
//		//System.out.println(keyWords[2]);
//		System.out.println(keyWords.length);
//		System.out.println(aString=="aa");			
//		List<Role> roles=userService.showAllRoles();
//		System.out.println(roles.get(0).getRoleName());		
//		System.out.println(UUID.randomUUID());
	}	
	//@Test
	public void testDeleteUser(){
		userService.deleteUser("2eb9b248-4852-40b9-a7d4-facc4af302e6");
	}
	
	
	public void generateUUID(){
		System.out.println(UUID.randomUUID());
	}
	
	
	
	public void testDeleteFile(){
		String path="D:\\54\\eclipse\\2b3a63f7-7560-4472-9d71-6ef82a18f8d0\\comFile.zip";
		//FileUtil.delFolder(path);
		FileUtil.deleteFile(path);
	}
	
	
	
	public void testFuzzySearch(){
		String string="abc";
		List<String> lists=new ArrayList<>();
		lists.add("fdafffc");
		lists.add("abcc");
		lists.add("abc");
		lists.add("wabct");
		lists.add("afffg");
		List<String> reStrings=FuzzySearch.search(string, lists);
		for(int i=0;i<reStrings.size();i++){
			System.out.println(lists.get(i));
		}
		
	}
	
	//@Test
	public void testAddToken(){
		Token token=new Token();
		token.setToken(UUID.randomUUID().toString());
		token.setUserId("fdsfs");
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.HOUR,3);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(calendar.getTime());
		Timestamp ts = Timestamp.valueOf(dateStr);
		token.setExpireTime(ts);
		userDao.addToken(token);
	}
	
	//@Test
	public void testSelectToken(){
		Token token1=userDao.selectToken("f3dc17ae-5ed5-457c-8cc0-138d9a91f12a");
		System.out.println(token1.getToken());
		System.out.println(token1.getUserId());
		System.out.println(token1.getExpireTime());
	}
	
	//@Test
	public void deleteToken(){
		userDao.deleteToken("f3dc17ae-5ed5-457c-8cc0-138d9a91f12a");
	}
	
}
