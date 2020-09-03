package com.mapper;

import java.util.List;
import java.util.Map;

import com.domain.ComParent;
import com.domain.Component;
import com.domain.Term;

public interface ComponentMapper {
	public Integer uploadComponent(Component component);
	public Integer uploadComParent(ComParent comParent);
	public ComParent selectComParByCdl(String cdlHashValue);
	public Integer updateParVersion(Integer lastestVersion);
	public List<Component> selectComByUserId(String userId);
	public Component selectComById(String comId);
	public Integer deleteComById(String comId);
	public List<Component> showUnverifyCom();
	public Integer verifyCom(Map<String,Object> maps);
	public Component downloadCom(String comId);
	public ComParent downloadComParent(String parId);
	public List<Component> showVerifiedCom();
	public ComParent selectComParById(String parId);
	public Integer updateDonwloadTimes(String comId);
	public List<Term> showTerms();
	public List<String> showComIdByTerm(String termId);
	public Integer deleteTermById(String termId);
	public List<String> getTermByParId(String parId);
	public Integer deleteMapByTermId(String termId);
	public Integer addTerm(Term term);
	public Integer deleteMapByComId(String comId);
	public Integer addComTermMap(Map<String, Object> maps);
	public Term getTermByName(String termName);

}
