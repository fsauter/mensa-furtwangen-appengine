package de.rentoudu.mensa.ratings.v1;

public class DeviceMenuRatingQuery {

	String menuId;
	String deviceId;
	
	public DeviceMenuRatingQuery() {
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
