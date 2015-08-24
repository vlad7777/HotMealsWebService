package hot.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import java.util.Date;

/**
 * Created by vlad on 10.8.15.
 */

@Entity
public class Dish implements Comparable<Dish> {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private long category_id;

    private String categoryName;

    private long supplier_id;

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

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public long getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
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

    @JsonIgnore
    public String getDateBegin() {
        return dateBegin;
    }

    @JsonIgnore
    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    @JsonIgnore
    public String getDateEnd() {
        return dateEnd;
    }

    @JsonIgnore
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
