package com.example.magda.astroweather;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MoonFragment extends Fragment {
    private View fragmentView;
    private TextView czasMoon;

    public MoonFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
       fragmentView = inflater.inflate(R.layout.fragment_moon, container, false);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        czasMoon = fragmentView.findViewById(R.id.CzasMoon);
        czasMoon.setText(currentDateTimeString);
        return fragmentView;
    }


}
