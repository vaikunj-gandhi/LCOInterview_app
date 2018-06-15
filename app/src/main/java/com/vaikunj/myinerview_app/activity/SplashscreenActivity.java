package com.vaikunj.myinerview_app.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import com.google.gson.Gson;
import com.vaikunj.myinerview_app.MainActivity;
import com.vaikunj.myinerview_app.Pojo.DsModel;
import com.vaikunj.myinerview_app.Pojo.JSONResponse;
import com.vaikunj.myinerview_app.R;
import com.vaikunj.myinerview_app.retrofit.RestApi;
import com.vaikunj.myinerview_app.utils.IsNetworkConnection;
import com.vaikunj.myinerview_app.utils.MyUtils;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SplashscreenActivity extends AppCompatActivity {

    private ArrayList<DsModel> data;
    private ProgressBar pgressbar;
    private RelativeLayout relativemain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        pgressbar=(ProgressBar)findViewById(R.id.pgressbar);
        relativemain=(RelativeLayout)findViewById(R.id.relativemain);
        pgressbar.setVisibility(View.VISIBLE);
        runSplash();

    }

    private void runSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              // MyUtils.startActivity(SplashscreenActivity.this,MainActivity.class,true);

                getDsQuestionApi();

            }
        }, 2000);

    }

    public void getDsQuestionApi(){
        pgressbar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://learncodeonline.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi request = retrofit.create(RestApi.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {


                pgressbar.setVisibility(View.GONE);
                if(response.body()!=null )
                {

                    data = new ArrayList<>(Arrays.asList(response.body().getQuestions()));
                    Log.e("data",data.toString());
                    Intent i=new Intent(SplashscreenActivity.this, MainActivity.class);
                    Gson gson = new Gson();
                    i.putExtra("data", gson.toJson(data));
                    startActivity(i);
                    finish();

                }else{
                    try {
                        if (IsNetworkConnection.checkNetworkConnection(SplashscreenActivity.this)) {
                            MyUtils.showSnackbar(SplashscreenActivity.this,getResources().getString(R.string.error_crash_error_message),relativemain);

                        } else {
                          showRetry();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t)
            {
                pgressbar.setVisibility(View.GONE);
                try {
                    if (IsNetworkConnection.checkNetworkConnection(SplashscreenActivity.this)) {
                        MyUtils.showSnackbar(SplashscreenActivity.this,getResources().getString(R.string.error_crash_error_message),relativemain);

                    } else {
                        showRetry();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



    }

    public void showRetry(){
        showSnackbar(getString(R.string.error_common_network), "Retry",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //DataPickerDialog1();
                        getDsQuestionApi();

                    }
                });
    }
    private void showSnackbar(final String mainMsg, String btnText,
                              View.OnClickListener listener) {
        Snackbar.make(findViewById(R.id.relativemain),
                mainMsg,
                Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(getResources().getColor(R.color.white))
                .setAction(btnText, listener).show();
    }

}
