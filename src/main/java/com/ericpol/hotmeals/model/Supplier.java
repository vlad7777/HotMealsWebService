package com.ericpol.hotmeals.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Supplier{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;

    private String address;

    private double lat;

    private double lng;

    @JsonIgnore
    @Transient
    private List<String> dates;

    @JsonIgnore
    @Transient
    private List<Dish> dishes;
    
    protected Supplier() {/* for JPA only */}

    public Supplier(String name) {
    	super();
        this.name = name;
        this.dates = new ArrayList<String>();
        dates.add(new Date().toString());
        dishes = null;
    }

    public Supplier(String name, String address, double lat, double lng) {
    	super();
    	this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.dates = new ArrayList<String>();
        dates.add(new Date().toString());
        dishes = null;
    }

    public List<Dish> selectDishes(String date) {
        List<Dish> result = new ArrayList<Dish>();

        if (dishes == null)
            syncWithDatabase();

        for (Dish dish : dishes) {
            if (compareStringDates(date, dish.getDateBegin()) >= 0 && compareStringDates(date, dish.getDateEnd()) <= 0)
                result.add(dish);
        }

        return result;
    }

    private int compareStringDates(String date1, String date2) {
        try {
            DateFormat df = new SimpleDateFormat("ddMMyyyy");
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            return d1.compareTo(d2);
        } catch (ParseException e) {
            return 0;
        }
    }

    private void syncWithDatabase() {
        // TODO: 19.8.15 add some actual request to a database
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        Date yesterday = new Date(today.getTime() - (1000 * 60 * 60 * 24));

        DateFormat df = new SimpleDateFormat("ddMMyyyy");

        String sToday = df.format(today);
        String sTomorrow = df.format(tomorrow);
        String sYesterday = df.format(yesterday);

        dishes = new ArrayList<Dish>();
        dishes.add(new Dish("Soup", "Motherboard compound", 12000, sToday, sTomorrow));
        dishes.add(new Dish("Soup", "Black hole blackness", 14300, sToday, sTomorrow));
        dishes.add(new Dish("Soup", "Adam's Adam's apple", 23999, sToday, sToday));
        dishes.add(new Dish("Dessert", "Virgin's tears syrup", 230500, sYesterday, sYesterday));
        dishes.add(new Dish("Supplier ID", Long.toString(id), 23000, sToday, sToday));
        dishes.add(new Dish("Main Dish", "chicken fillet seasoned with garlic", 40000, sToday, sTomorrow));
        dishes.add(new Dish("Main Dish", "french fries", 23000, sTomorrow, sTomorrow));
        dishes.add(new Dish("Main Dish", "not so french fries", 43000, sToday, sToday));
        dishes.add(new Dish("Dessert", "Sweet Cherry Pie", 34000, sTomorrow, sTomorrow));
        dishes.add(new Dish("Dessert", "Ice Cream Sandwitch", 23000, sToday, sToday));
        dishes.add(new Dish("Exclusive", "Yesterday Exclusive", 23000, sYesterday, sYesterday));
        dishes.add(new Dish("Exclusive", "Today Exclusive", 23000, sToday, sToday));
        dishes.add(new Dish("Exclusive", "Tomorrow Exclusive", 23000, sTomorrow, sTomorrow));

    }

    //For JSON

    public void setId(int id) {
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
