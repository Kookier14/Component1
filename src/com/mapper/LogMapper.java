package com.mapper;


import java.util.List;
import java.util.Map;

import com.domain.Log;


public interface LogMapper {
	public List<Log> showLogs();
	public List<Log> showLogsByTime(Map<String,Object> maps);
	public Integer insertLog(Log log);
}
