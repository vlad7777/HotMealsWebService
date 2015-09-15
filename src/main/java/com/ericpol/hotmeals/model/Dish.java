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

    @JsonIgnore
    @Transient
    private String categoryName;

    private long supplierId;

    private String name;

    private double price;

    private String dateBegin;

    private String dateEnd;
    
    protected Dish() {/* for JPA only */}

    public Dish(String categoryName, String name, int price, String dateBegin, String dateEnd)
    {
        this.categoryName = categoryName;
        this.name = name;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }
    
    public Dish(long supplierId, long categoryId , String name, double price)
    {
    	this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        // Today
        Date dt = new Date();
        this.dateBegin = df.format(dt);

        // After tomorrow
        dt = new Date(dt.getTime() + (1000 * 60 * 60 * 24 * 2));
        this.dateEnd = df.format(dt);
    }

    @Override
    public int compareTo(Dish dish)
    {
        int c1 = this.categoryName.compareTo(dish.getCategoryName());
        return c1 == 0 ? this.name.compareTo(dish.getName()) : c1;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
