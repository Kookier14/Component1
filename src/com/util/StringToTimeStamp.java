package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class StringToTimeStamp {
	public static  java.sql.Timestamp string2Time(String dateString) 
			  throws java.text.ParseException { 
			   DateFormat dateFormat; 
			  dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);//�趨��ʽ 
			  //dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH); 
			  dateFormat.setLenient(false); 
			  java.util.Date timeDate = dateFormat.parse(dateString);//util���� 
			  java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp����,timeDate.getTime()����һ��long�� 
			  return dateTime; 
			} 

}
