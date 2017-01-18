package com.beerrate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ratings")
public class Rating{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer rating;

	@ManyToOne()
    @JoinColumn(name="user_id", nullable=true)
    private User user;

	@ManyToOne()
    @JoinColumn(name="beer_id", nullable=true)
    private Beer beer;
	
	public Beer getBeer() {
		return beer;
	}

	public Long getId() {
		return id;
	}

	public Integer getRating() {
		return rating;
	}

	public User getUser() {
		return user;
	}

	public void setBeer(Beer beer) {
		this.beer = beer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
    
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
