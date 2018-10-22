package org.planning.assignment.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{

    private static final long serialVersionUID = 0x62A6DA99AABDA8B8L;
	private Integer orderId;
    private String orderType;
    //private List<Unit> units= new ArrayList<Unit>();
    private Integer price;
    private Integer volume;
   
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}	
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
	public Order() {
	}

	public Order(Integer orderId, String orderType, Integer price, Integer volume) {
		this.orderId = orderId;
		this.orderType = orderType;
		this.price = price;
		this.volume =volume;
	}
	 public Order(OrderForm orderForm) {
	        this.orderId = orderForm.getOrderId();
	        this.orderType = orderForm.getOrderType();
	        this.price = orderForm.getPrice();
	        this.volume = orderForm.getVolume();
	}
	public Order(Integer orderId) {
		this.orderId = orderId;
	}
	
}
