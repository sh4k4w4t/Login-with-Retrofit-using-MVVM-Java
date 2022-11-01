package com.shakawat.loginwithretrofitusingmvvmjava;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginServices {

    @POST("/api/login")
    Call<LoginReponse> login(@Body LoginBody loginBody);
}
