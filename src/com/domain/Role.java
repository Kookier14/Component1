package com.domain;

import java.util.List;



public class Role {
	private String roleId;
	private String roleName;
	private List<Menu> menus;
	private List<String> menuIds;
	
	public List<String> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<String> menuIds) {
		this.menuIds = menuIds;
	}
	public String getRoleId() {
		return roleId;
	}
	public Role() {
		super();
	}
	public Role(String roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	

}
