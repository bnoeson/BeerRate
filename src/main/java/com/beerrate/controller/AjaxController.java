package com.beerrate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beerrate.model.Beer;
import com.beerrate.model.Rating;
import com.beerrate.model.RatingRequest;
import com.beerrate.model.RatingResponse;
import com.beerrate.model.User;
import com.beerrate.service.BeerService;
import com.beerrate.service.RatingService;
import com.beerrate.service.UserService;

@RestController
public class AjaxController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BeerService beerService;
	@Autowired
	private RatingService ratingService;
	private Rating rating;
	
	private List<Rating> ratingPresent;

	// ** Web Service which save the user rating of a beer ** //
    @RequestMapping(value="/api/submitRating", method = RequestMethod.POST)
    public RatingResponse submitRating(@RequestBody RatingRequest request) {
    	//System.out.println(request.getUserid()+" "+request.getRating()+" "+request.getBeerid());
    	
    	RatingResponse response = new RatingResponse();
    	
    	ratingPresent=ratingService.findByBeerIdAndUserId(request.getBeerid(), request.getUserid());
    	
    	// On voit si un rating existe déjà    	
    	if(ratingPresent==null || ratingPresent.isEmpty()){ // N'existe pas : on créé un objet rating
    		rating=new Rating();
        	
        	rating.setUser(userService.findById(request.getUserid()));
        	rating.setBeer(beerService.getBeerInfos(request.getBeerid()));
        	rating.setRating(request.getRating());
    		
        	ratingService.save(rating);
    	}
    	else{ // Existe : on change le rating
    		System.out.println(ratingPresent.toString());
    		ratingPresent.get(0).setRating(request.getRating());
        	ratingService.save(ratingPresent.get(0));
    	}
    	

    	response.setCode("200");
    	response.setMessage("Rating saved!");
    	response.setRating(request.getRating());
    	
        return response;
    }
    
}