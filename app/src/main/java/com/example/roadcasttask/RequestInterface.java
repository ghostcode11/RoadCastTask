package com.example.roadcasttask;

import com.example.roadcasttask.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestInterface {

    @GET("users")
    Call<List<UserModel>> getUsers();
}
