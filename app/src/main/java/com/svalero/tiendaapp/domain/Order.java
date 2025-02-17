package com.svalero.tiendaapp.domain;

import java.time.LocalDate;

public class Order {
    private long id;
    private String status;
    private Float total;
    private String address;
    private LocalDate creationDate;
    private String paymentMethod;

    public Order(String status, Float total, String address, LocalDate creationDate, String paymentMethod){
        this.status = status;
        this.total = total;
        this.address = address;
        this.creationDate = creationDate;
        this.paymentMethod = paymentMethod;
    }

    public long getId(){
        return id;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setTotal(Float total){
        this.total = total;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setCreationDate(LocalDate creationDate){
        this.creationDate = creationDate;
    }

    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public String getStatus(){
        return status;
    }

    public Float getTotal(){
        return total;
    }

    public String getAddress(){
        return address;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }



}
