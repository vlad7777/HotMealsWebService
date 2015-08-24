package hot.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Created by vlad on 10.8.15.
 */

public class Dish implements Comparable<Dish> {

    private int id;

    private int category_id;

    private String categoryName;

    private int supplier_id;

    private String name;

    private int price;

    private String dateBegin;

    private String dateEnd;

    public Dish(int id, String categoryName, String name, int price, String dateBegin, String dateEnd)
    {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSupplier_id() {
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
