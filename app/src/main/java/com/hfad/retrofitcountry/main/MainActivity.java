package com.hfad.retrofitcountry.main;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hfad.retrofitcountry.R;


public class MainActivity extends AppCompatActivity {


    FragmentTransaction fragmentTransaction;    // транзация

    // объявляем объекты типа Fragment
    Fragment listCountry;
    Fragment detailsCountry;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listCountry = new ListCountry();
        detailsCountry = new DetailsCountry();




        // начальное состояние - отображение списка стран
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layoutForFragments, listCountry);
        fragmentTransaction.commit();   // совершить транзацию


        }


        public void onClickButton (View view){


            switch (view.getId()){

                case R.id.buttonListCountry:

                    onShowFragment(0);
                    break;

                case R.id.buttonDetailsCountry:

                    onShowFragment (1);
                    break;
            }
        }



        // метод для перехода на фрагменты
        public void onShowFragment (int index){

                switch (index){

                    case 0:

                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.layoutForFragments, listCountry).addToBackStack(null);
                        fragmentTransaction.commit();
                        break;

                    case 1:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.layoutForFragments, detailsCountry).addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                }

        }

}
