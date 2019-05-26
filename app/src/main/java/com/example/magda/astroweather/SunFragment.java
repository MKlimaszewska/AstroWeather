package com.example.magda.astroweather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class SunFragment extends Fragment {
    private View fragmentView;
    private TextView czasSun;
    private TextView szerokość_geog,długość_geog,wschódSun,zachódSun,switCywilny,zachodCywilny;

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
        return fragmentView;
    }

    public void RefreashTime(){
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        SunFragment.this.getActivity().runOnUiThread(new Runnable() {
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
