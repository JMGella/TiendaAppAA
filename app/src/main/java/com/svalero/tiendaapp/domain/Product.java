package com.svalero.tiendaapp.domain;

import com.svalero.tiendaapp.contract.CategoryContract;

public class Product {
    private long id;
    private String name;
    private String description;
    private Float price;
    private String creationDate;
    private Boolean active;
    private String image;
    private long categoryId;


    public Product(String name, String description, Float price, String creationDate, Boolean active, String image, long categoryId){
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.active = active;
        this.image = image;
        this.categoryId = categoryId;
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

    public void setCreationDate(String creationDate){
        this.creationDate = creationDate;
    }

    public void setActive(Boolean active){
        this.active = active;
    }

    public void setImage(String image){
        this.image = image;
    }

    public void setCategoryId(long categoryId){
        this.categoryId = categoryId;
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

    public String getCreationDate(){
        return creationDate;
    }

    public Boolean getActive(){
        return active;
    }

    public String getImage(){
        return image;
    }

    public long getCategoryId(){
        return categoryId;
    }

}
