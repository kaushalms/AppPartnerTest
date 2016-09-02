package com.apppartner.androidprogrammertest.callback;

import com.apppartner.androidprogrammertest.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Kaushal.Mandayam on 8/31/2016.
 */
public interface LoginService {
    @FormUrlEncoded
    @POST("/AppPartnerDeveloperTest/scripts/login.php")
    Call<LoginResponse> getLoginData(@Field("username") String username,
                                     @Field("password") String password);
}
