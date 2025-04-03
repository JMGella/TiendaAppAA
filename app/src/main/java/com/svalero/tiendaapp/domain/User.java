package com.svalero.tiendaapp.domain;

import androidx.room.Entity;


@Entity
public class User {
    private long id;
    private String name;
    private String email;
    private String birthDate;
    private Boolean active;
    private String address;
    private String phone;
    private String creationDate;
    private String latitude;
    private String longitude;


    public User(String name, String email, String birthDate, Boolean active, String address, String phone, String creationDate, String latitude, String longitude) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.active = active;
        this.address = address;
        this.phone = phone;
        this.creationDate = creationDate;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }



    public void setActive(Boolean active){
        this.active = active;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }



    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getBirthDate(){
        return birthDate;
    }

    public Boolean getActive(){
        return active;
    }

    public String getAddress(){
        return address;
    }

    public String getPhone(){
        return phone;
    }

    public String getCreationDate(){
        return creationDate;
    }

    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }


}
