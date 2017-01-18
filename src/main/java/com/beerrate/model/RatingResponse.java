package com.beerrate.model;

public class RatingResponse {

	private String message;
	private String code;
	private Integer rating;
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}	
	public Integer getRating() {
		return rating;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
}
