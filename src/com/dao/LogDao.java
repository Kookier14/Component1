package com.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.domain.Log;

@Repository("logDao")
public class LogDao {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Log> showLog(){
		return sqlSessionTemplate.selectList("com.mapper.LogMapper.showLogs");
	}
	
	public List<Log> showLogsByTime(Timestamp dateTime1,Timestamp dateTime2){
		Map<String, Object> maps=new HashMap<String, Object>();
		maps.put("dateTime1", dateTime1);
		maps.put("dateTime2", dateTime2);
		return sqlSessionTemplate.selectList("com.mapper.LogMapper.showLogsByTime",maps);
	}
	
	public Integer insertLog(Log log){
		return sqlSessionTemplate.insert("com.mapper.LogMapper.insertLog", log);
	}
	

}
