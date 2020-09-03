package com.service;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.List;

import com.domain.Log;


public interface LogService {
	public List<Log> showLogs();
	public List<Log> showLogsByTime(Timestamp dateTime1,Timestamp dateTime2);
    public String exportLogs(Timestamp dateTime1,Timestamp dateTime2) throws FileNotFoundException;
}
