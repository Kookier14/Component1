package com.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.domain.ComParent;
import com.domain.Component;
import com.domain.Term;

@Repository("componentDao")
public class ComponentDao {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//首次上传调用此方法
	public Integer uploadComPar(ComParent comParent){
		return sqlSessionTemplate.insert("com.mapper.ComponentMapper.uploadComParent", comParent);
	}
	public Integer uploadComponet(Component component){
		return sqlSessionTemplate.insert("com.mapper.ComponentMapper.uploadComponent", component);
	}
	public ComParent selectComParByCdl(String cdlHashValue){
		return sqlSessionTemplate.selectOne("com.mapper.ComponentMapper.selectComParByCdl", cdlHashValue);
	}
	public Integer updateParVersion(Integer lastestVersion){
		return sqlSessionTemplate.update("com.mapper.ComponentMapper.updateParVersion", lastestVersion);
	}
	
	public List<Component> selectComByUserId(String userId){
		List<Component> components=sqlSessionTemplate.selectList("com.mapper.ComponentMapper.selectComByUserId", userId);
		for(int i=0;i<components.size();i++){
			components.get(i).setComParent(selectComParById(components.get(i).getComParent().getParId()));
		}
		return components;
	}
	
	public Component selectComById(String comId){
		Component component=sqlSessionTemplate.selectOne("com.mapper.ComponentMapper.selectComById", comId);
		component.setComParent(selectComParById(component.getComParent().getParId()));
		return component;
	}
	public Integer deleteComById(String comId){
		return sqlSessionTemplate.delete("com.mapper.ComponentMapper.deleteComById", comId);
	}
	
	public List<Component> showUnverifyCom(){
		List<Component> components=sqlSessionTemplate.selectList("com.mapper.ComponentMapper.showUnverifyCom");
		for(int i=0;i<components.size();i++){
			components.get(i).setComParent(selectComParById(components.get(i).getComParent().getParId()));
		}
		return components;
		
	}
	
	public Integer verifyCom(String comId,Integer state,String denyInfo){
		Map<String, Object> maps=new HashMap<String, Object>();
		maps.put("comId", comId);
		maps.put("state",state);
		maps.put("denyInfo", denyInfo);
		return sqlSessionTemplate.update("com.mapper.ComponentMapper.verifyCom", maps);
	}
	public Component downloadCom(String comId){
		return sqlSessionTemplate.selectOne("com.mapper.ComponentMapper.downloadCom", comId);
	}
	
	public ComParent downloadComParent(String parId){
		return sqlSessionTemplate.selectOne("com.mapper.ComponentMapper.downloadComParent", parId);
	}
	
	public List<Component> showVerifiedCom(){
		List<Component> components=sqlSessionTemplate.selectList("com.mapper.ComponentMapper.showVerifiedCom");
		for(int i=0;i<components.size();i++){
			components.get(i).setComParent(selectComParById(components.get(i).getComParent().getParId()));
		}
		return components;
	}
	
	public ComParent selectComParById(String parId){
		return sqlSessionTemplate.selectOne("com.mapper.ComponentMapper.selectComParById", parId);
	}
	
	public Integer updateDonwloadTimes(String comId){
		return sqlSessionTemplate.update("com.mapper.ComponentMapper.updateDonwloadTimes", comId);
	}
	
	public List<Term> showTerms(){
		return sqlSessionTemplate.selectList("com.mapper.ComponentMapper.showTerms");
	}
	
	public List<String> showComIdByTerm(String termId){
		return sqlSessionTemplate.selectList("com.mapper.ComponentMapper.showComIdByTerm", termId);
	}
	
	public Integer deleteTermById(String termId){
		return sqlSessionTemplate.delete("com.mapper.ComponentMapper.deleteTermById", termId);
	}
	
	public Integer deleteMapByTermId(String termId){
		return sqlSessionTemplate.delete("com.mapper.ComponentMapper.deleteMapByTermId",termId);
	}
	public List<String> getTermByParId(String parId){
		return sqlSessionTemplate.selectList("com.mapper.ComponentMapper.getTermByParId", parId);
	}
	
	public Integer addTerm(Term term){
		return sqlSessionTemplate.insert("com.mapper.ComponentMapper.addTerm", term);
	}
	public Integer deleteMapByComId(String comId){
		return sqlSessionTemplate.delete("com.mapper.ComponentMapper.deleteMapByComId",comId);
	}
	public Integer addComTermMap(String[] terms,String comId){
		
		for(int i=0;i<terms.length;i++){
			Map<String, Object> maps=new HashMap<>();
			maps.put("termId", terms[i]);
			maps.put("comId", comId);
			sqlSessionTemplate.insert("com.mapper.ComponentMapper.addComTermMap", maps);
		}
		return 1;
	}
	public Term getTermByName(String termName){
		return sqlSessionTemplate.selectOne("com.mapper.ComponentMapper.getTermByName", termName);
	}

}
