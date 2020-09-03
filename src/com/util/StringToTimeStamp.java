package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class StringToTimeStamp {
	public static  java.sql.Timestamp string2Time(String dateString) 
			  throws java.text.ParseException { 
			   DateFormat dateFormat; 
			  dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);//设定格式 
			  //dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH); 
			  dateFormat.setLenient(false); 
			  java.util.Date timeDate = dateFormat.parse(dateString);//util类型 
			  java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp类型,timeDate.getTime()返回一个long型 
			  return dateTime; 
			} 

}
