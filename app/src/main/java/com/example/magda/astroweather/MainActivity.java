package com.example.magda.astroweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.astrocalculator.AstroDateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        InitialDate();

        if(!isTablet) {
            sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            viewPager = (ViewPager) findViewById(R.id.pager);
            viewPager.setAdapter(sectionsPagerAdapter);
                    }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                openSettings();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openSettings() {
       Settings refreshTimeDialog = new Settings();
        refreshTimeDialog.show(getSupportFragmentManager(),"refreshTimeDialog");
    }


    private void InitialDate() {
        AstroDateTimeClass.setYear(Integer.parseInt(new SimpleDateFormat("yyyy", Locale.US).format(new Date())));
        AstroDateTimeClass.setMonth(Integer.parseInt(new SimpleDateFormat("MM", Locale.US).format(new Date())));
        AstroDateTimeClass.setDay(Integer.parseInt(new SimpleDateFormat("dd", Locale.US).format(new Date())));
        AstroDateTimeClass.setHour(Integer.parseInt(new SimpleDateFormat("HH", Locale.US).format(new Date())));
        AstroDateTimeClass.setMinute(Integer.parseInt(new SimpleDateFormat("mm", Locale.US).format(new Date())));
        AstroDateTimeClass.setSecond(Integer.parseInt(new SimpleDateFormat("ss", Locale.US).format(new Date())));
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    SunFragment sunFragment = new SunFragment();
                    return sunFragment;
                case 1:
                    MoonFragment moonFragment = new MoonFragment();
                    return moonFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
