package hot.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Receipt{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;

    private String date;

    private String address;

    private String comment;

    private int total;

    @JsonIgnore
    @Transient
    private List<Dish> dishes;
    
    protected Receipt() {/* for JPA only */}
    
    public Receipt(long id, long userId, String date, String address, String comment, int total) {
    	this.id = id;
    	this.userId = userId;
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
