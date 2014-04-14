package de.rentoudu.mensa.ratings.v1;


import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.googlecode.objectify.ObjectifyService;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

@Api(
		name = "ratings",
		description = "Rating API for Mensa Furtwangen.",
		version = "v1",
		defaultVersion = AnnotationBoolean.TRUE
)
public class RatingEndpoint {

	static {
		ObjectifyService.register(Rating.class);
	}

	/**
	 * Returns the total menu rating.
	 * 
	 * @param menuId the menu id.
	 * @return The total rating for the given menu.
	 * 
	 */
	@ApiMethod(name = "fetchTotalMenuRating")
	public Rating fetchTotalMenuRating(TotalMenuRatingQuery totalMenuRatingQuery) {
		String menuId = totalMenuRatingQuery.getMenuId();
		if(menuId == null || menuId.isEmpty()) {
			throw new IllegalArgumentException("No menu id given.");
		}
		
		Double ratingSum = 0.0;
		List<Rating> ratings = ofy().load().type(Rating.class).filter("menuId", menuId).list();
		
		if(ratings.isEmpty()) {
			throw new IllegalArgumentException("No ratings found for menu: " + menuId);
		} else {
			for(Rating rating: ratings) {
				ratingSum = ratingSum + rating.getRatingScore();
			}
			
			Double ratingScore = ratingSum / ratings.size();
			
			Rating totalRating = new Rating();
			totalRating.setMenuId(menuId);
			totalRating.setRatingScore(ratingScore);
			return totalRating;
		}
	}
	
	/**
	 * Returns a specific menu rating for the given device id.
	 */
	@ApiMethod(name = "fetchDeviceMenuRating")
	public Rating fetchDeviceMenuRating(DeviceMenuRatingQuery deviceMenuRatingQuery) {
		String menuId = deviceMenuRatingQuery.getMenuId();
		String deviceId = deviceMenuRatingQuery.getDeviceId();
		
		if(deviceId == null || deviceId.isEmpty()) {
			throw new IllegalArgumentException("No device id given.");
		} else if(menuId == null || menuId.isEmpty()) {
			throw new IllegalArgumentException("No menu id given.");
		} else {
			
			Rating rating = ofy().load().type(Rating.class).filter("menuId", menuId).filter("deviceId", deviceId).first().now();
		
			if(rating == null) {
				throw new EntityNotFoundException("No rating found for device " + deviceId + " and menu " + menuId);
			} else {
				return rating;
			}
		
		}
	}

	/**
	 * Updates or inserts a given rating. It uses HTTP
	 * POST method.
	 * 
	 * @param rating
	 *            the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertRating", httpMethod = HttpMethod.POST)
	public Rating insertRating(Rating rating) {
		if(rating.getRatingScore() < 1 || rating.getRatingScore() > 5) {
			throw new IllegalArgumentException("Rating score must between 1 and 5");
		} else if(rating.getDeviceId() == null || rating.getDeviceId().isEmpty()) {
			throw new IllegalArgumentException("No device id given.");
		} else if(rating.getMenuId() == null || rating.getMenuId().isEmpty()) {
			throw new IllegalArgumentException("No menu id given.");
		} else {
			
			Rating existingRating = ofy().load().type(Rating.class).filter("menuId", rating.getMenuId()).filter("deviceId", rating.getDeviceId()).first().now();
			
			if(existingRating == null) {
				ofy().save().entity(rating).now();
			} else {
				existingRating.setRatingScore(rating.getRatingScore());
				ofy().save().entity(existingRating).now();
				rating = existingRating;
			}
		}
		return rating;
	}

	/**
	 * This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 * 
	 * @param id
	 *            the primary key of the entity to be deleted.
	 * @return The deleted entity.
	 */
	@ApiMethod(name = "removeRating", httpMethod = HttpMethod.DELETE)
	public Rating removeRating(@Named("id") Long id) {
		Rating storedRating = ofy().load().type(Rating.class).id(id).now();
		ofy().delete().entity(storedRating);
		return storedRating;
	}

}
