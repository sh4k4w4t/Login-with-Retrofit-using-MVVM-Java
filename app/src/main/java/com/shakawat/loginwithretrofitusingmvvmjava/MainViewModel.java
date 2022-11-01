package com.shakawat.loginwithretrofitusingmvvmjava;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<Integer> mProgressMutableData= new MutableLiveData<>();
    MutableLiveData<String> mLoginResultMutableData = new MutableLiveData<String>();
    MainRepository mainRepository;

    public MainViewModel(){
        mProgressMutableData.postValue(View.INVISIBLE);
        mLoginResultMutableData.postValue("Not Logged in.");
    }

    public void login(String email, String password){
        mProgressMutableData.postValue(View.VISIBLE);
        mLoginResultMutableData.postValue("Checking");
        mainRepository.loginRemote(new LoginBody(email, password), new MainRepository.iLoginResponse() {
            @Override
            public void onResponse(LoginReponse loginReponse) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mLoginResultMutableData.postValue("Login Response");
            }

            @Override
            public void onFailure(Throwable throwable) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mLoginResultMutableData.postValue("Login Failure"+throwable.getLocalizedMessage());
            }
        });
    }

    public LiveData<String > getLoginResult(){
        return mLoginResultMutableData;
    }

}
