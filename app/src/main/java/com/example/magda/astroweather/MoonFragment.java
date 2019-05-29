package com.example.magda.astroweather;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.os.Looper.getMainLooper;

public class MoonFragment extends Fragment {
    private View fragmentView;
    private TextView czasMoon;
    private TextView szerokość_geog,długość_geog,wschódMoon,zachódMoon,faza,nastepnyNów,następnaPełnia,dzieńMiesiąca;
    private AstroCalculatorClass astroCalculatorClass;

    private int refreshTime=5;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            setRetainInstance(true);
            fragmentView = inflater.inflate(R.layout.fragment_moon, container, false);
            szerokość_geog=fragmentView.findViewById(R.id.textSzerokosc);
            długość_geog=fragmentView.findViewById(R.id.textDlugosc);
            wschódMoon=fragmentView.findViewById(R.id.textWschodMoon);
            zachódMoon=fragmentView.findViewById(R.id.textZachodMoon);
            faza=fragmentView.findViewById(R.id.textFazaKsiezyca);
            nastepnyNów=fragmentView.findViewById(R.id.textNajblizszyNowKsiezyca);
            następnaPełnia=fragmentView.findViewById(R.id.textNajblizszaPelniaKsiezyca);
            dzieńMiesiąca=fragmentView.findViewById(R.id.textDzienMiesiaca);
            astroCalculatorClass = new AstroCalculatorClass(AstroDateTimeClass.astroDateTime, AstroLocalizationClass.location);
          RefreashTime();
          InitData();
          return fragmentView;
    }


    public void InitData() {
        szerokość_geog.setText(AstroLocalizationClass.getLatitude().toString());
        długość_geog.setText(AstroLocalizationClass.getLongitude().toString());
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                someHandler.postDelayed(this, 10);
                szerokość_geog.setText(AstroLocalizationClass.getLatitude().toString());
                długość_geog.setText(AstroLocalizationClass.getLongitude().toString());
                astroCalculatorClass.setLocation(AstroLocalizationClass.location);
                wschódMoon.setText(astroCalculatorClass.getMoonrise().toString());
                zachódMoon.setText(astroCalculatorClass.getMoonset().toString());
                następnaPełnia.setText(astroCalculatorClass.getNextFullMoon().toString());
                nastepnyNów.setText(astroCalculatorClass.getNextNewMoon().toString());
                faza.setText(Double.toString(astroCalculatorClass.getIlumination()));
                dzieńMiesiąca.setText(Double.toString(astroCalculatorClass.getAge()));


            }
        }, 10);


        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                someHandler.postDelayed(this, 60000 * refreshTime);
                astroCalculatorClass.setDateTime(AstroDateTimeClass.astroDateTime);

            }
        }, 10);

    }

    public void RefreashTime(){
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        MoonFragment.super.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                String dateString = sdf.format(date);
                                czasMoon = fragmentView.findViewById(R.id.CzasMoon);
                                czasMoon.setText(dateString);
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
