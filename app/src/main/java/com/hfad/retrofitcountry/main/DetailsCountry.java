package com.hfad.retrofitcountry.main;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.hfad.retrofitcountry.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DetailsCountry extends Fragment {


    ImageView imageFlag;
    TextView textNameCountry;
    TextView textCapital;

    int numberOfCountry = 0;    // выбранная страна из списка

    ListCountry listCountry_obj = new ListCountry();


    Uri uri;


    // добавляем, чтоб во фрагменте можно было работать с элементами View
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_country, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textNameCountry = (TextView) view.findViewById(R.id.textNameCountry);
        imageFlag = (ImageView) view.findViewById(R.id.imageFlag);
        textCapital = (TextView) view.findViewById(R.id.textCapital);


        numberOfCountry = listCountry_obj.getSelected_country();
        System.out.println("выбранная страна = " + numberOfCountry);


        String nameCountry = listCountry_obj.arrayFullDataCountry.get(numberOfCountry).getName();
        String nameCapital = listCountry_obj.arrayFullDataCountry.get(numberOfCountry).getCapital();

        // векторная картинка!!!!!!!!!
        String imageURL_Flag = listCountry_obj.arrayFullDataCountry.get(numberOfCountry).getFlag();

        System.out.println("флаг = " + imageURL_Flag);

        textNameCountry.setText(nameCountry);
        textCapital.setText(nameCapital);




    }




}