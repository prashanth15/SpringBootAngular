package org.planning.assignment.model;


public class Unit {

    private Integer price;
    private Integer volume;
    
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public Unit() {
	}
	
	public Unit(Integer price, Integer volume) {
		this.price = price;
		this.volume = volume;
	}
}
