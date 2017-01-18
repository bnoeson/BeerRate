package com.beerrate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beers")
public class Beer {
    @Id
    private Long id;
    private String name;
    private double abv;
    private String descript;
        
    @ManyToOne()
    @JoinColumn(name="brewery_id", nullable=true)
    private Brewery brewery;
    
	@ManyToOne()
    @JoinColumn(name="style_id", nullable=true)
    private Style style;
	
	@ManyToOne()
    @JoinColumn(name="cat_id", nullable=true)
    private Category category;
	
	public double getAbv() {
		return abv;
	}
	public Brewery getBrewery() {
		return brewery;
	}
	public Category getCategory() {
		return category;
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
    
	public Style getStyle() {
		return style;
	}
	public void setAbv(double abv) {
		this.abv = abv;
	}
	
	public void setBrewery(Brewery brewery) {
		this.brewery = brewery;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	public void setStyle(Style style) {
		this.style = style;
	}

}
