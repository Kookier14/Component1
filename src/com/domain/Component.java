package com.domain;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class Component {
	private String comId;
	//private String comName;
	private Integer version;
	private String keyWord;
	private String desInfo;
	private Integer downTimes;
	private Timestamp createTime;
	private ComParent comParent;
	private User user;
	private Integer state;
	private String failInfo;
	private String url;
	private String comName;
	
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	//private MultipartFile content;
	//private byte[] comEntity;
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDesInfo() {
		return desInfo;
	}
	public void setDesInfo(String desInfo) {
		this.desInfo = desInfo;
	}
	public Integer getDownTimes() {
		return downTimes;
	}
	public void setDownTimes(Integer downTimes) {
		this.downTimes = downTimes;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public ComParent getComParent() {
		return comParent;
	}
	public void setComParent(ComParent comParent) {
		this.comParent = comParent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getFailInfo() {
		return failInfo;
	}
	public void setFailInfo(String failInfo) {
		this.failInfo = failInfo;
	}
	

}
