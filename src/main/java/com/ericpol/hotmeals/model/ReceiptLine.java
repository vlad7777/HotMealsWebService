package com.ericpol.hotmeals.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ReceiptLine {
	
	@JsonIgnore
	@ManyToOne
	private Receipt receipt;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	//private long receiptId;
	private long dishId;
	
	protected ReceiptLine() { /* For JPA only */ }
	
	public ReceiptLine(long dishId) {
		//this.receiptId = receiptId;
		this.dishId = dishId;
	}
	
	@Override
	public String toString() {
		return String.format("ReceiptLine[ receiptLineId = %d, dishId = %d]", id, dishId);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
/*	
	public long getReceiptId() {
		return receiptId;
	}
	
	public void setReceiptId(long orderId) {
		this.receiptId = orderId;
	}
	*/
	public long getDishId() {
		return dishId;
	}
	
	public void setDishId(long dishId) {
		this.dishId = dishId;
	}
}
