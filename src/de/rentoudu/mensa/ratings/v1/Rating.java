package de.rentoudu.mensa.ratings.v1;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Rating {

	@Id Long id;
	
	@Index String deviceId;
	@Index String menuId;
	Double ratingScore;
	
	public Rating() {
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
	
	public Double getRatingScore() {
		return ratingScore;
	}
	
	public void setRatingScore(Double ratingScore) {
		this.ratingScore = ratingScore;
	}
	
}
