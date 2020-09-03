package com.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.controller.ReturnMsg;
import com.domain.Component;
import com.domain.Term;

public interface ComponentService {
	public ReturnMsg uploadComponent(String comName,MultipartFile multipartFile,Component component,String md5Value,String userId,String[] terms)throws  Exception;
	public List<Component> selectComByUserId(String userId);
	public Component selectComById(String comId);
	public Integer deleteComById(String userId,String comId);
	public List<Component> showUnverifyCom();
	public Integer verifyCom(String userId,String comId,Integer state,String denyInfo);
	public String downloadComponent(String userId,String comId) throws Exception;
	public List<Component> comSearch(String keyWords);
	public List<Term> showTerms();
	public List<Component> showComByTerm(String termId);
	public void deleteItem(String termId);
	public Integer addTerm(Term term);
	public List<Term> showTerms1();
	public List<Component> showAllComs();
}
