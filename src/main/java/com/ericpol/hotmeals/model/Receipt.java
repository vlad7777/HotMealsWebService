package com.ericpol.hotmeals.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;
    
    private long supplierId;

    private String date;

    private String address;

    private String comment;

    private double total;

	@Transient
	Dish[] dishes;
	
    protected Receipt() {/* for JPA only */}
    
    public Receipt(long userId, long supplierId, String date, String address, String comment, double total) {
    	super();
    	this.userId = userId;
    	this.supplierId = supplierId;
    	this.date = date;
    	this.address = address;
    	this.comment = comment;
    	this.total = total;
    }
    
    @Override
	public String toString() {
		return String.format("Receipt[ id = %d, userId = %d]", id, userId);
	}
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public Dish[] getDishes() {
    	return dishes;
    }

    public void setDishes(Dish[] dishes) {
    	this.dishes = dishes;
    }

    public double getTotal() {
    	return total;
    }
    
    public void setTotal(double total) {
    	this.total = total;
    }
    
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    public String getComment() {
    	return comment;
    }
    
    public void setComment(String comment) {
    	this.comment = comment;
    }
}
