package com.beerrate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.beerrate.model.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long>{
	Page<Beer> findAll(Pageable pageRequest);
	Beer findBeerById(Long id);
}
