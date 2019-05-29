package com.example.magda.astroweather;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.astrocalculator.AstroCalculator;

public class Settings extends AppCompatDialogFragment {

    public EditText czestotliwoscValue;
    public EditText dlugoscValue;
    public EditText szerokoscValue;
    public View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_settings,null);

        builder.setView(view).setTitle("Settings:")
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String dlugosc = dlugoscValue.getText().toString();
                        String szerokosc = szerokoscValue.getText().toString();
                        String czestotliwosc = czestotliwoscValue.getText().toString();


                        if(dlugosc.matches("")||szerokosc.matches("")||czestotliwosc.matches("")){
                            Toast.makeText(getActivity(), "Nie wszystkie wartości zostały podane poprawnie", Toast.LENGTH_SHORT).show();
                        }else{
                            AstroLocalizationClass.setLatitude(Double.parseDouble(szerokosc));
                            AstroLocalizationClass.setLongitude(Double.parseDouble(dlugosc));
                            AstroLocalizationClass.setRefreshTime(Integer.parseInt(dlugosc));
                        }

                    }
                });
        czestotliwoscValue = view.findViewById(R.id.settingsCzestotliwosc);
        dlugoscValue = view.findViewById(R.id.settingsDlugosc);
        szerokoscValue = view.findViewById(R.id.settingsSzerokosc);
        return builder.create();
    }

}
