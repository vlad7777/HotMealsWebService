package Entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Supplier{

    private int id;

    private String name;

    private String address;

    private double lat;

    private double lng;

    private List<String> dates;

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
        this.dates = new ArrayList<>();
        dates.add(new Date().toString());
    }

    public Supplier(int id, String name, String address, double lat, double lng)
    {
        this.name = name;
        this.id = id;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.dates = new ArrayList<>();
        dates.add(new Date().toString());
    }

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

}
