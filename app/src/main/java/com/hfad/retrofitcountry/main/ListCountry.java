package com.hfad.retrofitcountry.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hfad.retrofitcountry.POJO.POJO;
import com.hfad.retrofitcountry.R;
import com.hfad.retrofitcountry.SettingsRetrofit.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListCountry extends Fragment {


    ListView listCountry;
    ArrayList<String> arrayCountry = new ArrayList<>();                             // массив для стран
    ArrayAdapter<String> arrayAdapter;                                              // адаптер
    static List<POJO> arrayFullDataCountry = new ArrayList<>();              // массив для всех данных по странам
    static ArrayList <String> arrayAlpha3Code = new ArrayList<>();           // массив для кодов стран
    public static int selected_country = 0;                                         // для выбранной страны

    boolean onLoadingRetrofit = false;                                              // индикатор включения Retrofit



    // добавляем, чтоб во фрагменте можно было работать с элементами View
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_country, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        listCountry = (ListView) view.findViewById(R.id.listCountry);


        // паттерн адаптер для списка
        arrayAdapter = new ArrayAdapter <>(

                getActivity().getApplicationContext(),

                android.R.layout.simple_expandable_list_item_1,

                arrayCountry);




        // паттерн слушатель для ListView
        final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selected_country = position;
                getSelected_country();                         // возвращаем новое значение
                ((MainActivity)getActivity()).onShowFragment(1);     // переходим на фрагмент с деталями

            }
        };



        // если массив еще пустой
        if (onLoadingRetrofit == false){

            NetworkService.getInstance()
                    .getJSONCreate()
                    .getAllData()
                    .enqueue(new Callback<List <POJO> >() {    // enqueue - асинхронно, execute - в UI thread

                        // @Override
                        public void onResponse(@NonNull Call<List <POJO>> call, @NonNull Response<List <POJO>> response) {

                            arrayFullDataCountry = response.body(); // записываем все данные в массив с данными класса POJO

                            for (int i = 0; i < arrayFullDataCountry.size(); i++){
                                arrayCountry.add(arrayFullDataCountry.get(i).getName());    // записываем в массив с данными String
                                arrayAlpha3Code.add(arrayFullDataCountry.get(i).getAlpha3Code());
                            }

                            listCountry.setAdapter(arrayAdapter);
                            listCountry.setOnItemClickListener(onItemClickListener);

                        }

                        // @Override
                        public void onFailure(Call <List<POJO>> call, Throwable t) {

                            arrayCountry.add("Error to connetcted with Internet");
                        }


                    });

            onLoadingRetrofit = true;   // ставим индикатору true

        }


        else {

            listCountry.setAdapter(arrayAdapter);
            listCountry.setOnItemClickListener(onItemClickListener);

        }

    }



    public ArrayList <String> getFillArrayAlpha (){
        return arrayAlpha3Code;
    }


    public ArrayList <POJO> getFillArrayData (){
        return (ArrayList<POJO>) arrayFullDataCountry;
    }




    public int getSelected_country (){
        return selected_country;
    }
}
