package com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Log;
import com.domain.User;
import com.service.LogService;
import com.service.UserService;
import com.util.StringToTimeStamp;

@CrossOrigin(origins = "*",maxAge = 3000)
@RestController
@RequestMapping("/log")
public class LogController {
	@Resource
	private LogService logService;
	
	@RequestMapping("/showLogs")
    public List<Log> showLogs(){
    	return logService.showLogs();
    }
	
	@RequestMapping("/showLogsByTime")
	//public List<Log> showLogsByTime(String dateTime1,String dateTime2) throws ParseException{
	public List<Log> showLogsByTime(@RequestBody Dates dates) throws ParseException{
//		dates.getDateTime1()+=" 01:00:00.0";
//		dates.getDateTime2()+=" 01:00:00.0";
		return logService.showLogsByTime(StringToTimeStamp.string2Time(dates.getDateTime1()+" 01:00:00.0"), StringToTimeStamp.string2Time(dates.getDateTime2()+" 01:00:00.0"));
	}
	
	//@RequestMapping("/exportLogs",method=RequestMethod.GET)
//	public String exportLog(String dateTime1,String dateTime2) throws FileNotFoundException, ParseException{
//		dateTime1+=" 01:00:00.0";
//		dateTime2+=" 01:00:00.0";
//		return logService.exportLogs(StringToTimeStamp.string2Time(dateTime1), StringToTimeStamp.string2Time(dateTime2));
//	}
	@RequestMapping("/exportLogs")
//    public void download(HttpServletRequest request,
//            HttpServletResponse response,String dateTime1,String dateTime2) throws IOException, ParseException {
//	public void download(HttpServletRequest request,
//        HttpServletResponse response,@RequestBody Dates dates) throws IOException, ParseException {
//		System.out.println(dates.getDateTime1()+"  "+dates.getDateTime2());
//		String path=logService.exportLogs(StringToTimeStamp.string2Time(dates.getDateTime1()+" 01:00:00.0"), StringToTimeStamp.string2Time(dates.getDateTime2()+" 01:00:00.0"));
//        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path))); 
//        response.setContentType("multipart/form-data"); 
//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
//        int len = 0;  
//        while((len = bis.read()) != -1){  
//            out.write(len);  
//            out.flush();  
//        }  
//        out.close();  
//    }
	public void download(HttpServletRequest request,
	        HttpServletResponse response,@RequestBody Dates dates) throws IOException, ParseException {
		String path=logService.exportLogs(StringToTimeStamp.string2Time(dates.getDateTime1()+" 01:00:00.0"), StringToTimeStamp.string2Time(dates.getDateTime2()+" 01:00:00.0"));
		 response.reset();  
		   File file = new File(path);
	        String fileName=file.getName();
	        response.setContentType("application/force-download");
	        String downloadFileName = URLEncoder.encode(fileName,"UTF-8");        
	        response.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
	        FileInputStream inputStream = null;
	        OutputStream out = null;
	        inputStream = new FileInputStream(file);     
	        out = response.getOutputStream();   
	        int length = 0;   
	        byte[] buffer = new byte[1024];   
	        while ((length = inputStream.read(buffer)) != -1){   
	                //4.写到输出流(out)中   
	                out.write(buffer,0,length);   
	            } 
	        inputStream.close();   
	        out.flush(); 
	        out.close();
	
	    }

}
