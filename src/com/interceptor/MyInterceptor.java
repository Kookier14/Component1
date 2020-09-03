package com.interceptor;

import java.io.BufferedReader;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.Request;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.domain.Api;
import com.domain.Menu;
import com.domain.Role;
import com.domain.User;
import com.service.UserService;

//import net.sf.json.JSONObject;

public class MyInterceptor implements HandlerInterceptor {
	
	@Resource
	private UserService userService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
//		StringBuffer json = new StringBuffer();
//		   String line = null;
//		   try {
//		   BufferedReader reader = arg0.getReader();
//		   while((line = reader.readLine()) != null) {
//		   json.append(line);
//		   }
//		   }
//		   catch(Exception e) {
//		   System.out.println(e.toString());
//		   }
//		  System.out.println(json.toString());
//		  JSONObject object=JSONObject.parseObject(json.toString());
		  //System.out.println(object.getString("token"));
		  //System.out.println(object.getString("userId"));
		
		String url=arg0.getServletPath();
		if(url.equals("/user/login")){
			return true;
		}
		System.out.println(url);
		String token=arg0.getHeader("Access-Token");
		System.out.println(token);
		String  userId=userService.getUserByToken(token);
		if(userId==null){
			return false;
		}
		if(new Date().after(userService.selectToken(token).getExpireTime())){
			return false;
		}
		List<Menu> menus=userService.getMenuByToken(token);
		String urlId="-1";
		List<Api> apis=userService.getAllApi();
		for(int i=0;i<apis.size();i++){
			if(url.equals(apis.get(i).getApiValue())){
				urlId=apis.get(i).getApiId();
				break;
			}
		}
		if(urlId.equals("-1")){
			return true;
		}
		for(int i=0;i<menus.size();i++){
			List<String> lists=userService.getApiByMenu(menus.get(i).getMenuId());
			for(int j=0;j<lists.size();j++){
				if(urlId.equals(lists.get(j))){
					return true;
				}
			}
		}
			
		return false;
	}

}
