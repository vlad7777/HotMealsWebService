package com.ericpol.hotmeals.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long supplierId;
    private String name;

    protected Category() {/* for JPA only */}

    public Category( long supplierId, String name) {
        this.supplierId = supplierId;
        this.name = name;
    }
    
    public Category( long supplierId, long id, String name) {
    	this.supplierId = supplierId;
    	this.id = id;
    	this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Category[id=%d, supplierId=%d, name='%s']",
                             id, supplierId, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
