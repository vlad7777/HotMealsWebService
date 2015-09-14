package com.ericpol.hotmeals.client;

import java.util.List;

import com.ericpol.hotmeals.model.Category;
import com.ericpol.hotmeals.model.Dish;
import com.ericpol.hotmeals.model.Supplier;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by vlad on 14.8.15.
 */
public interface HotmealsApi {

    @GET("/hotmeals/suppliers")
    public List<Supplier> fetchSuppliers();
    
    @POST("/hotmeals/suppliers")
    public Supplier addSupplier(@Body Supplier s);

    @GET("/hotmeals/suppliers/{userId}/dishes")
    public List<Dish> fetchDishes(@Path("userId") String userId);

    @GET("/hotmeals/suppliers/{supplierId}/dishes/{date}")
    public List<Dish> fetchDishes(@Path("supplierId") String supplierId, @Path("date") String date);

    @GET("/hotmeals/dishes")
    public List<Dish> fetchDishes();

    @GET("/hotmeals/category")
    public List<Category> fetchCategories();

    @POST("/hotmeals/category")
    public Category addCategory(@Body Category c);

    @GET("/hotmeals/suppliers/{supplierId}/categories")
    public List<Category> fetchCategories(@Path("supplierId") String supplierId);

}
