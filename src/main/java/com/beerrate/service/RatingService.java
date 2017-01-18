package com.beerrate.service;

import java.util.List;

import com.beerrate.model.Rating;

public interface RatingService {
	void save(Rating rating);
	List<Rating> findByBeerIdAndUserId(Long beerid, Long userid);
	double getAverageRating(Long beerId);
}
