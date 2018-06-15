package com.vaikunj.myinerview_app.retrofit;

import com.vaikunj.myinerview_app.Pojo.DsModel;
import com.vaikunj.myinerview_app.Pojo.JSONResponse;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by androidwarriors on 12/3/2015.
 */
public interface RestApi {


    @POST("/api/android/datastructure.json")
    Call<JSONResponse> getJSON();
    /*Call<DsModel> getDSlist();*/

}