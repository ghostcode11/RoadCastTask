package com.example.roadcasttask.models;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("username")
    String username;
    @SerializedName("email")
    String email;
    @SerializedName("phone")
    String phone;
    @SerializedName("website")
    String website;



    @SerializedName("address")
    AddressModel address;
    @SerializedName("company")
    CompanyModel companyModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public CompanyModel getCompanyModel() {
        return companyModel;
    }

    public void setCompanyModel(CompanyModel companyModel) {
        this.companyModel = companyModel;
    }

}
