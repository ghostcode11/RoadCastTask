package com.example.roadcasttask.models;

import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class AddressModel {

    @SerializedName("street")
    String street;
    @SerializedName("suite")
    String suite;
    @SerializedName("city")
    String city;
    @SerializedName("zipcode")
    String zipcode;

    public GeoModel getGeoModel() {
        return geoModel;
    }

    public void setGeoModel(GeoModel geoModel) {
        this.geoModel = geoModel;
    }
    @Embedded
    @SerializedName("geo")
    GeoModel geoModel;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
