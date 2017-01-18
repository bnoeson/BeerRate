package com.beerrate.model;

public class RatingRequest {
	
	private Long userid;
	private Long beerid;
	private Integer rating;
	
	public Long getBeerid() {
		return beerid;
	}
	public Integer getRating() {
		return rating;
	}	
	public Long getUserid() {
		return userid;
	}
	
	public void setBeerid(Long beerid) {
		this.beerid = beerid;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
}
