package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.domain.Log;
import com.service.LogService;
import com.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class LogTest {
	private static Logger logger = Logger.getLogger(UserTest.class);
	@Resource
	private LogService logService = null;
	
	
	public void testShowLogs(){
		List<Log> lists=logService.showLogs();
		System.out.println(lists.get(1).getCreateTime());
	}
	
	
	public void testShowLogsByTime() throws ParseException{
		String sToTimestamp1 = "2019-8-28 10:01:36.01"; 
		String sToTimestamp2 = "2019-8-28 01:00:00.0";
		String tt="2019-8-28 01:00:00.0";
		System.out.println(string2Time(tt).toString());
//	    Timestamp date1 = string2Time(sToTimestamp1);
//	    Timestamp date2 = string2Time(sToTimestamp2);
//	    System.out.println(date1.toString());
//	    List<Log> lists=logService.showLogsByTime(date1, date2);
//	    System.out.println(lists.get(1).getMessage());
//	    System.out.println(lists.size());
	}
	public  java.sql.Timestamp string2Time(String dateString) 
			  throws java.text.ParseException { 
			   DateFormat dateFormat; 
			  dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);//设定格式 
			  //dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH); 
			  dateFormat.setLenient(false); 
			  java.util.Date timeDate = dateFormat.parse(dateString);//util类型 
			  java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp类型,timeDate.getTime()返回一个long型 
			  return dateTime; 
			} 
	@Test
	public void testExportLogs() throws FileNotFoundException, ParseException{
		
		File logFile=new File("log"+".txt");
		PrintStream ps = new PrintStream(new FileOutputStream(logFile));
		ps.append("sfjlsdjflksd;");
		ps.close();
		String sToTimestamp1 = "2019-8-28 10:01:36.01"; 
		String sToTimestamp2 = "2019-10-27 01:00:00.0";
		//logService.exportLogs(string2Time(sToTimestamp1), string2Time(sToTimestamp2));
	}

}
