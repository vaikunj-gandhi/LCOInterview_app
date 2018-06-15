package com.vaikunj.myinerview_app.retrofit;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chandresh on 18/10/2017.
 */

public abstract  class RestCallback<T> implements Callback<T> {

     Context mContext;
    public RestCallback(Context context) {
        this.mContext=context;
    }

    //success response
    public abstract void Success(  Response<T> response);
    //error
    public abstract void failure() throws Exception;
    @Override
    public void onResponse(Call<T> call, Response<T> response)
    {
        if(response.body()!=null && response.body() instanceof ArrayList && ((ArrayList) response.body()).size()>0)
        {

                Success(response);


        }
        else
        {

            try {
                failure();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        Log.d("Error",t.getMessage());
        try {
            failure();
        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
