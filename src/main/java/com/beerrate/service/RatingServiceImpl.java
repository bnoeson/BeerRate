package com.beerrate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerrate.model.Rating;
import com.beerrate.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepository;
	
	List<Rating> overallRatings;
	
	@Override
	public void save(Rating rating) {
		ratingRepository.save(rating);
	}

	@Override
	public List<Rating> findByBeerIdAndUserId(Long beerid, Long userid) {
		return ratingRepository.findByBeerIdAndUserId(beerid, userid);		
	}

	// Look for the average rating of a beer by its id
	@Override
	public double getAverageRating(Long beerId) {
		overallRatings=ratingRepository.findByBeerId(beerId);
		if(overallRatings.size()==0) return 0; // No rating yet
		else{
			double sum=0;
	    	for(int i = 0; i < overallRatings.size(); i++){
	    		sum += overallRatings.get(i).getRating();
	        }
	    	return sum/overallRatings.size();
		}		
	}

}
