package com.hfad.retrofitcountry.Interface;

import com.hfad.retrofitcountry.POJO.POJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceForREST_Methods {



    // метод для получения массива с названиями стран
    @GET("/rest/v2/all")      // конечная точка запроса
    public Call <List <POJO> > getAllData ();   // получить только названия стран


}
