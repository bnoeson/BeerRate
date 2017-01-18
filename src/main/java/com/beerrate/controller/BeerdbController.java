package com.beerrate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beerrate.model.Beer;
import com.beerrate.model.Rating;
import com.beerrate.model.User;
import com.beerrate.service.BeerService;
import com.beerrate.service.RatingService;
import com.beerrate.service.UserService;

@Controller
public class BeerdbController {

	@Autowired
	private BeerService beerService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private RatingService ratingService;
	
	private boolean authenticated;
	private List<Rating> currentRatings;
	private List<Double> beersRatings;
	private double averageRating;
	private User user;
	private UserDetails userDetails;
	
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
    public String browse(
    		@RequestParam(value="sort", defaultValue="asc") String sort, 
    		@RequestParam(value="page", defaultValue="1") Integer pageNumber,
    		/*@PathVariable Integer pageNumber, */Model model) {
		Page<Beer> beersPage = beerService.getBeers(pageNumber);
		List<Beer> beers = beersPage.getContent();

	    int current = beersPage.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, beersPage.getTotalPages());

    	// Calcul de overall rating
	    beersRatings= new ArrayList<Double>();
	    for(int i=0;i<beers.size();i++){		    
	    	beersRatings.add(ratingService.getAverageRating(beers.get(i).getId()));
	    }
    	model.addAttribute("beersRatings", beersRatings);

    	model.addAttribute("overallRating", averageRating);	    
	    model.addAttribute("beersPage", beersPage);
	    model.addAttribute("beers", beers);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    
	    // Search options TODO ajouter SORT, FILTERS by category etc.
	    model.addAttribute("sort", sort);
		
        return "browse";
    }
	
	@RequestMapping(value = "/beer/{beerId}", method = RequestMethod.GET)
    public String beer(@PathVariable Long beerId, Model model) {
	
		// Vérifie si on est authentifié. Si oui, on ajoute les données
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // http://www.mkyong.com/spring-security/get-current-logged-in-username-in-spring-security/
		authenticated = auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken); //when Anonymous Authentication is enabled
				
		if(authenticated){
			userDetails = (UserDetails)auth.getPrincipal();
	        user = userService.findByUsername(userDetails.getUsername());
	    	model.addAttribute("user", user);
	    	
	    	// On cherche le rating actuel de l'utilisateur. Si il existe on ajoute la valeur ds page jsp, sinon on met O.
			currentRatings=ratingService.findByBeerIdAndUserId(beerId, user.getId());
	    	if(currentRatings==null || currentRatings.isEmpty()){
	    		model.addAttribute("userRating", 0);
	    	}
	    	else{
	    		model.addAttribute("userRating", currentRatings.get(0).getRating());
	    	}
	    		    	
		}
		
		Beer beer= beerService.getBeerInfos(beerId);
	    model.addAttribute("beer", beer);
		
		// Calcul de overall rating
    	averageRating=ratingService.getAverageRating(beerId);
    	model.addAttribute("overallRating", averageRating);
	    
        return "beer";        
    }
	
	
	
}
