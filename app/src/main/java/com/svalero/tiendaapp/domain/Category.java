package com.svalero.tiendaapp.domain;

import java.time.LocalDate;


public class Category {
    private long id;
    private String name;
    private String description;
    private String creationDate;
    private Boolean active;
    private String image;

    public Category (String name, String description,String creationDate, Boolean active, String image){
        this.name = name;
        this.description = description;
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

    public void setCreationDate(String creationDate){
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

    public String getCreationDate(){
        return creationDate;
    }

    public Boolean getActive(){
        return active;
    }

    public String getImage(){
        return image;
    }

}
