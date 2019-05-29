package com.example.magda.astroweather;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroDateTime;

import java.text.SimpleDateFormat;

import static android.os.Looper.getMainLooper;

public class SunFragment extends Fragment {
    private View fragmentView;
    private TextView czasSun;
    private TextView szerokość_geog,długość_geog,wschódSun,zachódSun,switCywilny,zachodCywilny;
    private AstroCalculatorClass astroCalculatorClass;
    private int refreshTime;

    public SunFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        fragmentView = inflater.inflate(R.layout.fragment_sun, container, false);
        szerokość_geog=fragmentView.findViewById(R.id.textSzerokosc);
        długość_geog=fragmentView.findViewById(R.id.textDlugosc);
        wschódSun=fragmentView.findViewById(R.id.textWschodSun);
        zachódSun=fragmentView.findViewById(R.id.textZachodSun);
        switCywilny=fragmentView.findViewById(R.id.textŚwitCyw);
        zachodCywilny=fragmentView.findViewById(R.id.textZmierzchCyw);

        RefreashTime();
        InitData();


        astroCalculatorClass = new AstroCalculatorClass(AstroDateTimeClass.astroDateTime, AstroLocalizationClass.location);

        szerokość_geog.setText(AstroLocalizationClass.getLatitude().toString());
        długość_geog.setText(AstroLocalizationClass.getLongitude().toString());
        return fragmentView;
    }

    public void InitData() {

        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                someHandler.postDelayed(this, 10);
                szerokość_geog.setText(AstroLocalizationClass.getLatitude().toString());
                długość_geog.setText(AstroLocalizationClass.getLongitude().toString());
                astroCalculatorClass.setLocation(AstroLocalizationClass.location);
                wschódSun.setText(astroCalculatorClass.getSunrise().toString());
                zachódSun.setText(astroCalculatorClass.getSunset().toString());
                switCywilny.setText(astroCalculatorClass.getTwilightMorning().toString());
               zachodCywilny.setText(astroCalculatorClass.getTwilightEvening().toString());


            }
        }, refreshTime);

    }

    public void RefreashTime(){
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        SunFragment.super.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                String dateString = sdf.format(date);
                                czasSun = fragmentView.findViewById(R.id.CzasSun);
                                czasSun.setText(dateString);

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

    }
}
