package com.beerrate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beerrate.model.Beer;
import com.beerrate.repository.BeerRepository;

@Service
@Transactional
public class BeerServiceImpl implements BeerService{
	private static final int PAGE_SIZE = 20;	

	@Autowired
	private BeerRepository beerRepository;
	
	private Sort sort;
	
	@Override
	public Page<Beer> getBeers(Integer pageNumber) {
		// http://stackoverflow.com/questions/10527124/how-to-query-data-via-spring-data-jpa-by-sort-and-pageable-both-out-of-box
		// TODO : create filters and posibility to change SORT
		sort= new Sort( new Sort.Order(Sort.Direction.ASC, "brewery.country"), new Sort.Order(Sort.Direction.ASC, "name") );
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, sort);
        return beerRepository.findAll(pageRequest);
    }

	@Override
	public Beer getBeerInfos(Long id) {
		return beerRepository.findBeerById(id);
	}
	
}
