package com.beerrate.service;

import org.springframework.data.domain.Page;

import com.beerrate.model.Beer;

public interface BeerService {
	Page<Beer> getBeers(Integer pageNumber);

	Beer getBeerInfos(Long beerId);
}
