package com.ericpol.hotmeals.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long supplierId;
    private String name;

    protected Category() {/* for JPA only */}

    public Category(long supplierId, String name) {
    	super();
        this.supplierId = supplierId;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return String.format("Category[id=%d, supplierId=%d, name='%s']",
                             id, supplierId, name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public int hashCode() {

		return Objects.hashCode(supplierId, name);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Category) {

			Category other = (Category) obj;

			return supplierId == other.supplierId
					&& Objects.equal(name, other.name);
		}
		else {

			return false;
		}
	}
}
