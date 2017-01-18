package com.beerrate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beerrate.model.Rating;

public interface RatingRepository  extends JpaRepository<Rating, Long> {
	List<Rating> findByBeerIdAndUserId(Long beerId, Long userId);
	List<Rating> findByBeerId(Long beerId);
}
