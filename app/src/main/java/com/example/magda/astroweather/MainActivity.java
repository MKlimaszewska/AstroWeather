package com.example.magda.astroweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    public String czestotliwosc;
    public String dlugos;
    public String szerokosc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Boolean isTablet = getResources().getBoolean(R.bool.isTablet);

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
