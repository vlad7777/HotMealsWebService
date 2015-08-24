package Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.qos.logback.core.pattern.color.YellowCompositeConverter;

public class Supplier{

    private int id;

    private String name;

    private String address;

    private double lat;

    private double lng;

    private List<String> dates;

    private List<Dish> dishes;

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public Supplier(int id, String name)
    {
        this.name = name;
        this.id = id;
        this.dates = new ArrayList<String>();
        dates.add(new Date().toString());
        dishes = null;
    }

    public Supplier(int id, String name, String address, double lat, double lng)
    {
        this.name = name;
        this.id = id;
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
        dishes.add(new Dish(0, "Soup", "Motherboard compound", 12000, sToday, sTomorrow));
        dishes.add(new Dish(1, "Soup", "Black hole blackness", 14300, sToday, sTomorrow));
        dishes.add(new Dish(2, "Soup", "Adam's Adam's apple", 23999, sToday, sToday));
        dishes.add(new Dish(3, "Dessert", "Virgin's tears syrup", 230500, sYesterday, sYesterday));
        dishes.add(new Dish(4, "Supplier ID", Integer.toString(id), 23000, sToday, sToday));
        dishes.add(new Dish(5, "Main Dish", "chicken fillet seasoned with garlic", 40000, sToday, sTomorrow));
        dishes.add(new Dish(6, "Main Dish", "french fries", 23000, sTomorrow, sTomorrow));
        dishes.add(new Dish(7, "Main Dish", "not so french fries", 43000, sToday, sToday));
        dishes.add(new Dish(8, "Dessert", "Sweet Cherry Pie", 34000, sTomorrow, sTomorrow));
        dishes.add(new Dish(9, "Dessert", "Ice Cream Sandwitch", 23000, sToday, sToday));
        dishes.add(new Dish(10, "Exclusive", "Yesterday Exclusive", 23000, sYesterday, sYesterday));
        dishes.add(new Dish(11, "Exclusive", "Today Exclusive", 23000, sToday, sToday));
        dishes.add(new Dish(12, "Exclusive", "Tomorrow Exclusive", 23000, sTomorrow, sTomorrow));

    }

    //For JSON

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
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

    @JsonIgnore
    public List<Dish> getDishes() {
        return dishes;
    }

    @JsonIgnore
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
