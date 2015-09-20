package com.ericpol.hotmeals.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by vlad on 10.8.15.
 */

@Entity
public class Dish implements Comparable<Dish> {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private long categoryId;

    private long supplierId;

    private String name;

    private double price;

    private String dateBegin;

    private String dateEnd;
    
    protected Dish() {/* for JPA only */}

    public Dish(long supplierId, long categoryId , String name, double price, String dateBegin, String dateEnd)
    { 
    	this.supplierId = supplierId;
    	this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }
    
    @Override
    public int compareTo(Dish dish)
    {
        return Long.compare(id, dish.getId());
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
