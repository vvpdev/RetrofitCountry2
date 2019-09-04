package com.hfad.retrofitcountry.SettingsRetrofit;

import com.hfad.retrofitcountry.Interface.InterfaceForREST_Methods;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;



public class NetworkService {


         // переменная того же типа, что и класс
         private static NetworkService mInstance;

         // базовый URl
         private static final String BASE_URL = "https://restcountries.eu";

          // переменная для ретрофита
          Retrofit mRetrofit;


    // конструктор
    public NetworkService (){

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    // паттерн singleton - создает и возвращает только один элемент класса
    public static NetworkService getInstance(){

        if (mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }



    // методя для возврата класса на основе интерфейса
    public InterfaceForREST_Methods getJSONCreate (){
        return mRetrofit.create(InterfaceForREST_Methods.class);
    }



}
