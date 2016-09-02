package com.apppartner.androidprogrammertest.controller;

//import static okhttp3.logging.HttpLoggingInterceptor.*;


import com.apppartner.androidprogrammertest.callback.LoginService;
import com.apppartner.androidprogrammertest.helper.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kaushal.Mandayam on 8/31/2016.
 */
public class RetrofitManager {

    private LoginService mService;

    public LoginService getLoginService(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if(mService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASR_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            mService = retrofit.create(LoginService.class);
        }
        return mService;
    }
}
