package de.rentoudu.mensa.thumbs.v1;

public class ThumbsQuery {

	String menuId;
	String deviceId;
	
	public ThumbsQuery() {
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public String getMenuId() {
		return menuId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
