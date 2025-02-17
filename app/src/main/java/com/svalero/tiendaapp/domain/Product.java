package com.svalero.tiendaapp.domain;

import java.time.LocalDate;

public class Product {
    private long id;
    private String name;
    private String description;
    private Float price;
    private LocalDate creationDate;
    private Boolean active;
    private String image;


    public Product(String name, String description, Float price, LocalDate creationDate, Boolean active, String image){
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.active = active;
        this.image = image;
    }

    public long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPrice(Float price){
        this.price = price;
    }

    public void setCreationDate(LocalDate creationDate){
        this.creationDate = creationDate;
    }

    public void setActive(Boolean active){
        this.active = active;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Float getPrice(){
        return price;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }

    public Boolean getActive(){
        return active;
    }

    public String getImage(){
        return image;
    }

}
