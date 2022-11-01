package com.shakawat.loginwithretrofitusingmvvmjava;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    public void loginRemote(LoginBody loginBody,iLoginResponse iLoginResponse){
        ILoginServices iLoginServices= RetrofitClientInstance.getRetrofit().create(ILoginServices.class);
        Call<LoginReponse> call = iLoginServices.login(loginBody);
        call.enqueue(new Callback<LoginReponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginReponse> call, @NonNull Response<LoginReponse> response) {
                if (response.isSuccessful()){
                    iLoginResponse.onResponse(response.body());
                }else {
                    iLoginResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginReponse> call, @NonNull Throwable t) {
                iLoginResponse.onFailure(t);
            }
        });
    }


    public interface iLoginResponse{
        void onResponse(LoginReponse loginReponse);
        void onFailure(Throwable throwable);
    }

}
