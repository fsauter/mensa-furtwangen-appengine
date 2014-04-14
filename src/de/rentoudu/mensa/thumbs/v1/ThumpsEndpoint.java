package de.rentoudu.mensa.thumbs.v1;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.googlecode.objectify.ObjectifyService;

@Api(
		name = "thumbs",
		description = "Thumbs API for Mensa Furtwangen.",
		version = "v1",
		defaultVersion = AnnotationBoolean.TRUE
)
public class ThumpsEndpoint {

	static {
		ObjectifyService.register(Thumb.class);
	}
	
	/**
	 * Returns a specific menu rating for the given device id.
	 */
	@ApiMethod(name = "fetchThumbs")
	public ThumbsResult fetchThumbs(ThumbsQuery ratingQuery) {
		String menuId = ratingQuery.getMenuId();
		String deviceId = ratingQuery.getDeviceId();
		
		if(deviceId == null || deviceId.isEmpty()) {
			throw new IllegalArgumentException("No device id given.");
		} else if(menuId == null || menuId.isEmpty()) {
			throw new IllegalArgumentException("No menu id given.");
		} else {
			
			List<Thumb> thumbUps = ofy().load().type(Thumb.class).filter("menuId", menuId).filter("state", ThumbState.UP).list();
			List<Thumb> thumbDowns = ofy().load().type(Thumb.class).filter("menuId", menuId).filter("state", ThumbState.DOWN).list();
			ThumbsResult result = new ThumbsResult();
			result.setThumbsUp(thumbUps.size());
			result.setThumbsDown(thumbDowns.size());
			Thumb devicethumb = ofy().load().type(Thumb.class).filter("menuId", menuId).filter("deviceId", deviceId).first().now();
			if(devicethumb == null) {
				result.setDeviceState(null);
			} else {
				result.setDeviceState(devicethumb.getState());
			}
			return result;
		}
	}

	/**
	 * Inserts a thumb down rating. It uses HTTP
	 * POST method.
	 * 
	 * @param thumb
	 *            the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertThumb", httpMethod = HttpMethod.POST)
	public void insertThumb(Thumb thumb) {
		if(thumb.getDeviceId() == null || thumb.getDeviceId().isEmpty()) {
			throw new IllegalArgumentException("No device id given.");
		} else if(thumb.getMenuId() == null || thumb.getMenuId().isEmpty()) {
			throw new IllegalArgumentException("No menu id given.");
		} else if(thumb.getState() == null) {
			throw new IllegalArgumentException("No thumb state given.");
		} else {
			ThumbsQuery query = new ThumbsQuery();
			query.setDeviceId(thumb.getDeviceId());
			query.setMenuId(thumb.getMenuId());
			removeThumbs(query);
			ofy().save().entity(thumb).now();
		}
	}

	/**
	 * This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 * 
	 * @param id
	 *            the primary key of the entity to be deleted.
	 * @return The deleted entity.
	 */
	@ApiMethod(name = "removeThumbs", httpMethod = HttpMethod.DELETE)
	public void removeThumbs(ThumbsQuery query) {
		List<Thumb> thumbs = ofy().load().type(Thumb.class).filter("menuId", query.getMenuId()).filter("deviceId", query.getDeviceId()).list();
		ofy().delete().entities(thumbs).now();
	}

}
