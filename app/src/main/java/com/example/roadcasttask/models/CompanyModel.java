package com.example.roadcasttask.models;

import com.google.gson.annotations.SerializedName;

public class CompanyModel {

    @SerializedName("name")
    String name;
    @SerializedName("catchPhrase")
    String catchPhrase;
    @SerializedName("bs")
    String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }


}
