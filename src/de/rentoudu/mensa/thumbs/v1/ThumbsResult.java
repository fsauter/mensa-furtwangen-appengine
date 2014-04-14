package de.rentoudu.mensa.thumbs.v1;

public class ThumbsResult {

	ThumbState deviceState;
	int thumbsUp;
	int thumbsDown;
	
	
	public ThumbsResult() {
	}
	
	public void setDeviceState(ThumbState deviceThumbState) {
		this.deviceState = deviceThumbState;
	}
	
	public ThumbState getDeviceState() {
		return deviceState;
	}
	
	public int getThumbsDown() {
		return thumbsDown;
	}
	
	public void setThumbsDown(int thumbsDown) {
		this.thumbsDown = thumbsDown;
	}
	
	public int getThumbsUp() {
		return thumbsUp;
	}
	
	public void setThumbsUp(int thumbsUp) {
		this.thumbsUp = thumbsUp;
	}
	
}
