package com.beerrate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "breweries")
public class Brewery {

    @Id
    private Long id;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String code;
    private String country;
    private String phone;
    private String website;
    private String descript;
    
    //@OneToMany(cascade=CascadeType.ALL, mappedBy="brewery_id", fetch=FetchType.EAGER)
    //private List<Beer> beer;
    
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getCity() {
		return city;
	}
	public String getCode() {
		return code;
	}
	public String getCountry() {
		return country;
	}
	public String getDescript() {
		return descript;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getState() {
		return state;
	}
	public String getWebsite() {
		return website;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
    
    
}
