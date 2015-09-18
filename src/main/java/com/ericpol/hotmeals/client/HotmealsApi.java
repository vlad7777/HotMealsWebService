package com.ericpol.hotmeals.client;

import java.util.List;

import com.ericpol.hotmeals.model.Category;
import com.ericpol.hotmeals.model.Dish;
import com.ericpol.hotmeals.model.Receipt;
import com.ericpol.hotmeals.model.Supplier;
import com.ericpol.hotmeals.model.User;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by vlad on 14.8.15.
 */
public interface HotmealsApi {

    @POST("/hotmeals/suppliers")
    public Supplier addSupplier(@Body Supplier s);

    @GET("/hotmeals/suppliers/{name}")
    public Supplier getSupplier(@Path("name") String name);
    
    @GET("/hotmeals/suppliers")
    public List<Supplier> fetchSuppliers();
    
    @GET("/hotmeals/suppliers/{userId}/dishes")
    public List<Dish> fetchDishes(@Path("userId") String userId);

    @GET("/hotmeals/suppliers/{supplierId}/dishes/{date}")
    public List<Dish> fetchDishes(@Path("supplierId") String supplierId, @Path("date") String date);

    @GET("/hotmeals/dishes")
    public List<Dish> fetchDishes();

    @POST("/hotmeals/dishes")
    public Dish addDish(@Body Dish d);

    @GET("/hotmeals/dishes/{supplierId}/{categoryId}/{name}")
    public Dish getDish(@Path("supplierId") Long supplierId, @Path("categoryId") Long categoryId, @Path("name") String name);

    @POST("/hotmeals/category")
    public Category addCategory(@Body Category c);

    @GET("/hotmeals/category")
    public List<Category> fetchCategories();

    @GET("/hotmeals/category/{supplierId}/{name}")
    public Category getCategory(@Path("supplierId") Long supplierId, @Path("name") String name);

    @GET("/hotmeals/suppliers/{supplierId}/categories")
    public List<Category> fetchCategories(@Path("supplierId") String supplierId);

    @POST("/hotmeals/users")
    public User addUser(@Body User u);

    @GET("/hotmeals/users")
    public List<User> fetchUsers();

    @GET("/hotmeals/users/{login}")
    public User getUser(@Path("login") String login);

    @POST("/hotmeals/orders")
    public Receipt addOrder(@Body Receipt r);

}
