package com.domain;

import java.util.List;

public class Term {
	private String termId;
	private String termName;
	private String parId;
	private Integer level;
	private Boolean isLeaf;
	private List<Term> children;
	private String key;
	private String title;
	private String value;	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public List<Term> getChildren() {
		return children;
	}
	public void setChildren(List<Term> children) {
		this.children = children;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getParId() {
		return parId;
	}
	public void setParId(String parId) {
		this.parId = parId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}	

}
