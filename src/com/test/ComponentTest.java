package com.test;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.ComponentDao;
import com.domain.Component;
import com.domain.Term;
import com.domain.User;
import com.service.ComponentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ComponentTest {
	
	private static Logger logger = Logger.getLogger(UserTest.class);
	
	@Resource
	private ComponentService componentService=null;
	
	@Resource
	private ComponentDao componentDao=null;
	public void testselectComByUserId(){
		
		List<Component> components=componentService.selectComByUserId("fjsdl");
		System.out.println(components.get(0).getComName());
	}
	
	
	public void testshowComDetail(){
		Component component=componentService.selectComById("07f88891-65b4-47a2-8ade-f126bdfcb4d0");
		System.out.println(component.getKeyWord());
	}
	
	 
	public void testshowUnverifyCom(){
		List<Component> components=componentService.showUnverifyCom();
		System.out.println(components.get(0).getUser());
		//System.out.println(components.get(0).getUser().getUserName());
	}
	
	
	
	//@Test
	public void testDownloadComponent() throws Exception{
		componentService.downloadComponent("fdsgdf","0d8655e2-ad94-46bd-86c9-d721e2156fd7");
	}
	
	
	//@Test
	public void testShowTerms(){
		
		List<Term> lists=componentService.showTerms();
		System.out.println(lists.get(1).getTermName());
	}
	
	
	public void testShowComByTerm(){
		List<Component> lists=componentService.showComByTerm("78c010cb-1ba3-479b-b02e-b2679ee7bc16");
		System.out.println();
	}
	
	//@Test
	public void testDeleteTerm(){
		componentService.deleteItem("78c010cb-1ba3-479b-b02e-b2679ee7bc16");
	}
	
	
	public void testDeleteMapByTermId(){
		//componentDao.deleteMapByComId("4083bb72-8fdc-4ad4-8437-463a86b041ec");
		List<Component> components=componentService.showAllComs();
		System.out.println(components.size());
	}

	@Test
	public void testShowUnverifid(){
		List<Component> list=componentService.showUnverifyCom();
		System.out.println(list.get(0).getDesInfo());
	}
	
	
	
	

}
