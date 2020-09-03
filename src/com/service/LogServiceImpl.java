package com.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;


import com.dao.LogDao;
import com.domain.Log;
import com.mapper.LogMapper;

@Service("logService")
public class LogServiceImpl implements LogService {
	
	@Resource
	private LogMapper logMapper;
	
	@Resource
	private LogDao logDao;
	

	@Override
	public List<Log> showLogs() {
		return logDao.showLog();
	}
	@Override
	public List<Log> showLogsByTime(Timestamp dateTime1, Timestamp dateTime2) {
		return logDao.showLogsByTime(dateTime1, dateTime2);
	}
	@Override
	public String exportLogs(Timestamp dateTime1, Timestamp dateTime2) throws FileNotFoundException {
		List<Log> logs=logDao.showLogsByTime(dateTime1, dateTime2);
		final String currentPath = System.getProperty("user.dir");
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dt=df.format(new java.util.Date());
		System.out.println(currentPath);
		//String str=currentPath+"\\"+UUID.randomUUID()+"\\"+"log"+".txt";
		//String str=currentPath+"\\"+"log"+".txt";
		//String str=currentPath+"\\"+"log"+".txt";
		String str=currentPath+"\\"+"log"+".blob";
		File logFile=new File(str);
		//logFile.mkdir();
		//File logFile=new File(currentPath+"\\"+"log"+".txt");
		//File logFile=new File("log"+".txt");
		//File logFile = new File(this.getClass().getResource("").getPath()); 
		PrintStream ps = new PrintStream(new FileOutputStream(logFile));
		System.out.println(logs.size());
		for(int i=0;i<logs.size();i++){
			ps.append(logs.get(i).getCreateTime().toString());
			ps.append("   ");
			ps.append(logs.get(i).getMessage()+"\r\n");
			//ps.append("\n");
		}
		ps.close();
		System.out.println(logFile);
	
		return str;
	}
	
	

}
