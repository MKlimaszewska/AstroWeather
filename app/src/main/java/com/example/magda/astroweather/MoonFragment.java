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
import android.widget.Toast;

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

    private int refreshTime=10000;
    private  Handler someHandler = new Handler();

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

public void InitData(){
    mToastRunnable.run();
}

    private Runnable mToastRunnable= new Runnable() {

                    @Override
                    public void run() {
                        InitialDate();
                        refreshTime=AstroLocalizationClass.getRefreshTime();
                        Log.i("refreshTime",String.valueOf(refreshTime));
                        szerokość_geog.setText(AstroLocalizationClass.getLatitude().toString());
                        długość_geog.setText(AstroLocalizationClass.getLongitude().toString());
                        astroCalculatorClass.setLocation(AstroLocalizationClass.location);
                        wschódMoon.setText(astroCalculatorClass.getMoonrise().toString());
                        zachódMoon.setText(astroCalculatorClass.getMoonset().toString());
                        następnaPełnia.setText(astroCalculatorClass.getNextFullMoon().toString());
                        nastepnyNów.setText(astroCalculatorClass.getNextNewMoon().toString());
                        Log.i("refres",astroCalculatorClass.getIlumination().toString());
                        faza.setText(Double.toString(astroCalculatorClass.getIlumination()));
                        dzieńMiesiąca.setText(Double.toString(astroCalculatorClass.getAge()));
                        someHandler.postDelayed(mToastRunnable,refreshTime*1000);

                };
    };


    public void RefreashTime(){
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        if(MoonFragment.super.getActivity()!= null){
                            MoonFragment.super.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    long date = System.currentTimeMillis();
                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                    String dateString = sdf.format(date);
                                    czasMoon = fragmentView.findViewById(R.id.CzasMoon);
                                    if (czasMoon!=null)
                                        czasMoon.setText(dateString);
                                    refreshTime= AstroLocalizationClass.getRefreshTime();
                                }
                            });
                            }
                        }

                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

    }
    private void InitialDate() {
        AstroDateTimeClass.setYear(Integer.parseInt(new SimpleDateFormat("yyyy", Locale.US).format(new Date())));
        AstroDateTimeClass.setMonth(Integer.parseInt(new SimpleDateFormat("MM", Locale.US).format(new Date())));
        AstroDateTimeClass.setDay(Integer.parseInt(new SimpleDateFormat("dd", Locale.US).format(new Date())));
        AstroDateTimeClass.setHour(Integer.parseInt(new SimpleDateFormat("HH", Locale.US).format(new Date())));
        AstroDateTimeClass.setMinute(Integer.parseInt(new SimpleDateFormat("mm", Locale.US).format(new Date())));
        AstroDateTimeClass.setSecond(Integer.parseInt(new SimpleDateFormat("ss", Locale.US).format(new Date())));
    }




}
