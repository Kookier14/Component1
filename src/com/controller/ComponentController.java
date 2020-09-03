package com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.domain.Component;
import com.domain.Term;
import com.service.ComponentService;
import com.service.UserService;
import com.util.StringToTimeStamp;



@CrossOrigin(origins = "*",maxAge = 3000)
@RestController
@RequestMapping("/component")
public class ComponentController {

	@Resource
	private ComponentService componentService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/uploadComponent")
	//public Integer uploadComponent(HttpSession httpSession,@RequestBody UploadMap uploadMap) throws Exception{
	public ReturnMsg uploadComponent(HttpSession httpSession,String md5Value,String comName,String[] terms,Component component,MultipartFile upfile,@RequestHeader("Access-Token") String token) throws Exception{
	//public Integer uploadComponent(HttpSession httpSession,String md5Value,String comName,String[] terms,Component component,String  upfile) throws Exception{
		System.out.println("aa"+token);
		//String userId=(String) httpSession.getAttribute("userId");
		String userId=userService.getUserByToken(token);
		System.out.println("usId  "+userId);
		 return componentService.uploadComponent(comName,upfile, component,md5Value,userId,terms);
		
		
	}
	@RequestMapping("/showComByUser")
	public List<Component> showComByUser(@RequestHeader("Access-Token") String token){
		String userId=userService.getUserByToken(token);
		System.out.println(userId);
		//return componentService.selectComByUserId(userId);
		return componentService.selectComByUserId(userId);
		
	}
	
//	@RequestMapping("/addComIdToSession")
//	public Integer addComIdToSession(String comId,HttpSession httpSession){
//		String userId=(String) httpSession.getAttribute("userId");
//		httpSession.setAttribute(userId+"comId", comId);
//		return 1;
//	}
	
	@RequestMapping("/showComDetail")
	public Component showComDetail(@RequestBody Component component ){
		
		return componentService.selectComById(component.getComId());		
	}
	
	@RequestMapping("/deleteCom")
	public Integer deleteComById(@RequestBody Component component,@RequestHeader("Access-Token") String token){
		String userId=userService.getUserByToken(token);
		System.out.println(component.getComId());
		return componentService.deleteComById(userId,component.getComId());
	}
	
	@RequestMapping("/showUnverifyCom")
	public List<Component> showUnverifyCom(){
		return componentService.showUnverifyCom();
	}
	
	@RequestMapping("/verifyCom")
	//public Integer verifyCom(String comId,Integer state,String denyInfo,HttpSession httpSession){
	public Integer verifyCom(@RequestBody Component component,@RequestHeader("Access-Token") String token){
		String userId=userService.getUserByToken(token);
		return componentService.verifyCom(userId,component.getComId(), component.getState(),component.getFailInfo());
	}
	
	@RequestMapping("/comSearch")
	public List<Component> comSearch(@RequestBody Component component) throws UnsupportedEncodingException{
		String keyWord=URLDecoder.decode(component.getKeyWord(),"UTF-8");
		System.out.println("KK"+keyWord+"KK");
		return componentService.comSearch(keyWord);
	}
	
	@RequestMapping("/downCom")//要把comId放到最后，否则会报错
	public void downCom(HttpServletRequest request,
            HttpServletResponse response,@RequestHeader("Access-Token") String token,@RequestBody Component component) throws Exception{
		System.out.println(component.getComId());
		String userId=userService.getUserByToken(token);
		String path=componentService.downloadComponent(userId, component.getComId());
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        response.setContentType("multipart/form-data"); 
        //response.setCharacterEncoding("utf-8");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();
	}
	
	@RequestMapping("/showTerms")
	public List<Term> showTerms(){
		return componentService.showTerms();
	}
	
	@RequestMapping("/showTerms1")
	public List<Term> showTerms1(){
		return componentService.showTerms1();
	}
	
	
	@RequestMapping("/showComsByTerm")
	public List<Component> showComsByTerm(@RequestBody Term term){
		return componentService.showComByTerm(term.getTermId());
	}
	
	@RequestMapping("/deleteTermById")
	public boolean deleteItem(@RequestBody Term term){
		componentService.deleteItem(term.getTermId());
		return true;
	}
	
	@RequestMapping("/addTerm")
	public Integer addTerm(@RequestBody Term term){
		return componentService.addTerm(term);
	}
	
	@RequestMapping("/showAllComs")
	public List<Component> showAllComs(){
		return componentService.showAllComs();
	}
	
	
}
