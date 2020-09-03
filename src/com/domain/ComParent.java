package com.domain;

public class ComParent {
	private String parId;
	private String parName;
	private String cdlHashValue;
	private Integer lastestVersion;
	private Integer state;
	private String CDL;
	private String url;
    private String interfaceInfo;
	
	public String getInterfaceInfo() {
		return interfaceInfo;
	}
	public void setInterfaceInfo(String interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCDL() {
		return CDL;
	}
	public void setCDL(String cDL) {
		CDL = cDL;
	}
	public String getParId() {
		return parId;
	}
	public void setParId(String parId) {
		this.parId = parId;
	}
	public String getParName() {
		return parName;
	}
	public void setParName(String parName) {
		this.parName = parName;
	}
	public String getCdlHashValue() {
		return cdlHashValue;
	}
	public void setCdlHashValue(String cdlHashValue) {
		this.cdlHashValue = cdlHashValue;
	}
	public Integer getLastestVersion() {
		return lastestVersion;
	}
	public void setLastestVersion(Integer lastestVersion) {
		this.lastestVersion = lastestVersion;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
