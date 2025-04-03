package com.svalero.tiendaapp.api;

import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.domain.Order;
import com.svalero.tiendaapp.domain.OrderDetail;
import com.svalero.tiendaapp.domain.Product;
import com.svalero.tiendaapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TiendaApiInterface {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("categories")
    Call<List<Category>> getCategories();

    @DELETE("categories/{categoryId}")
    Call<Void> deleteCategory(@Path("categoryId") long categoryId);

    @PUT("categories/{categoryId}")
    Call<Category> updateCategory(@Path("categoryId") long categoryId, @Body Category category);

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("categories/{categoryId}")
    Call<Category> getCategoryById(@Path("categoryId") long categoryId);

    @GET("users/{userId}")
    Call<User> getUserById(@Path("userId") long userId);

    @POST("users")
    Call<User> addUser(@Body User user);

    @DELETE("users/{userId}")
    Call<Void> deleteUser(@Path("userId") long userId);


    @DELETE("products/{productId}")
    Call<Void> deleteProductById(@Path("productId") long productId);

    @POST("categories/{categoryId}/products")
    Call<Product> addProduct(@Path("categoryId") long categoryId, @Body Product product);

    @GET("products/{productId}")
    Call<Product> getProductById(@Path("productId") long productId);

    @PUT("categories/{categoryId}/products/{productId}")
    Call<Product> updateProduct(@Path("categoryId") long categoryId, @Path("productId") long productId, @Body Product product);


    @POST("categories")
    Call<Category> addCategory(@Body Category category);



}
