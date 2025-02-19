package com.svalero.tiendaapp.api;

import com.svalero.tiendaapp.domain.Category;
import com.svalero.tiendaapp.domain.Order;
import com.svalero.tiendaapp.domain.OrderDetail;
import com.svalero.tiendaapp.domain.Product;
import com.svalero.tiendaapp.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TiendaApiInterface {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("categories")
    Call<List<Category>> getCategories();

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("orders")
    Call<List<Order>> getOrders();

    @GET("oderDetails")
    Call<List<OrderDetail>> getOrderDetails();

    @GET("categories/{categoryId}")
    Call<Category> getCategoryById(@Path("categoryId") long categoryId);

    @GET("users/{userId}/orders")
    Call<List<Order>> getUserOrders(@Path("userId") long userId);

    @GET("users/{userId}/orders/{orderId}/details")
    Call<List<OrderDetail>> getOrderDetails(@Path("userId") long userId, @Path("orderId") long orderId);

    @GET("categories/{categoryId}/products")
    Call<List<Product>> getCategoryProducts(@Path("categoryId") long categoryId);

    @GET("users/{userId}")
    Call<User> getUserById(@Path("userId") long userId);

    @POST("users/{userId}/orders")
    Call<Order> createOrder(@Path("userId") long userId, @Body Order order);

    @POST("users/{userId}/orders/{orderId}/details")
    Call<OrderDetail> addOrderDetails(@Path("userId") long userId, @Path("orderId") int orderId, @Body OrderDetail orderDetail);



}
