package com.controller;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.domain.Component;
import com.domain.Term;

public class UploadMap {
	private String comName;
	private String md5Value;
	private MultipartFile upfile;
	private List<Term> terms;
	private Component component;
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getMd5Value() {
		return md5Value;
	}
	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}
	public MultipartFile getUpfile() {
		return upfile;
	}
	public void setUpfile(MultipartFile upfile) {
		this.upfile = upfile;
	}
	public List<Term> getTerms() {
		return terms;
	}
	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	
	

}
