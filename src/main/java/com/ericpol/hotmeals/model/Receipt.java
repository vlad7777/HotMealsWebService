package com.ericpol.hotmeals.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Receipt{

	@OneToMany(cascade = {CascadeType.ALL})
	private Set<ReceiptLine> receiptLines = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;
    
    private long supplierId;

    private String date;

    private String address;

    private String comment;

    private int total;

//    @JsonIgnore
//    @Transient
//    private List<Dish> dishes;
    
    protected Receipt() {/* for JPA only */}
    
    public Receipt(long id, long userId, long supplierId, String date, String address, String comment, int total) {
    	this.id = id;
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
    
    public Set<ReceiptLine> getReceiptLine() {
    	return receiptLines;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
    
    public int getTotal() {
    	return total;
    }
    
    public void setTotal(int total) {
    	this.total = total;
    }
}
