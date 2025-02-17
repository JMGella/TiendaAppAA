package com.svalero.tiendaapp.domain;

import java.time.LocalDate;

public class User {
    private long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Boolean active;
    private String address;
    private String phone;
    private LocalDate creationDate;
    private String latitude;
    private String longitude;


    public User(String name, String email, LocalDate birthDate, Boolean active, String address, String phone, LocalDate creationDate, String latitude, String longitude) {
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

    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
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

    public void setCreationDate(LocalDate creationDate){
        this.creationDate = creationDate;
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

    public LocalDate getBirthDate(){
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

    public LocalDate getCreationDate(){
        return creationDate;
    }

    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }


}
