package com.ericpol.hotmeals.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by vlad on 10.8.15.
 */

@Entity
public class Dish implements Comparable<Dish> {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private long categoryId;

    private String categoryName;

    private long supplierId;

    private String name;

    private int price;

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
    
    public Dish(long supplierId, long categoryId , String name, int price)
    {
    	this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.dateBegin = null;
        this.dateEnd = null;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
