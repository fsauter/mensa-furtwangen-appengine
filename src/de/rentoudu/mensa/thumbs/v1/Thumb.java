package de.rentoudu.mensa.thumbs.v1;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Thumb {

	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	@Id Long id;
	
	@Index String deviceId;
	@Index String menuId;
	@Index ThumbState state;
	
	public Thumb() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getMenuId() {
		return menuId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	public ThumbState getState() {
		return state;
	}
	
	public void setState(ThumbState state) {
		this.state = state;
	}
	
}
