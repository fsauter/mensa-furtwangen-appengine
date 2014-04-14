package de.rentoudu.mensa;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.ObjectifyService;

import de.rentoudu.mensa.ratings.v1.Rating;

public class ChartsViewController {
	
	static {
		ObjectifyService.register(Rating.class);
	}
	
	public List<Rating> getRatings() {
		return ofy().load().type(Rating.class).list();
	}

	public Map<String, List<Rating>> getMenuRatings() {
		HashMap<String, List<Rating>> map = new HashMap<String, List<Rating>>();
		
		List<Rating> ratings = getRatings();
		
		for(Rating rating : ratings) {
			if(map.containsKey(rating.getMenuId())) {
				map.get(rating.getMenuId()).add(rating);
			} else {
				map.put(rating.getMenuId(), new ArrayList<Rating>());
				map.get(rating.getMenuId()).add(rating);
			}
		}
		
		return map;
	}
	
}
