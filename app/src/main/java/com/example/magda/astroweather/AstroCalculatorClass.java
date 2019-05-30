package com.example.magda.astroweather;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.io.Serializable;

public class AstroCalculatorClass implements Serializable {
    AstroCalculator astroCalculator;

    public AstroCalculatorClass(AstroDateTime time, AstroCalculator.Location location) {
        astroCalculator = new AstroCalculator(time, location);
    }

    public AstroDateTime getSunrise(){
        return astroCalculator.getSunInfo().getSunrise();
    }
    public AstroDateTime getSunset(){
        return astroCalculator.getSunInfo().getSunset();
    }
    public AstroDateTime getTwilightEvening(){ return astroCalculator.getSunInfo().getTwilightEvening(); }
    public AstroDateTime getTwilightMorning(){ return astroCalculator.getSunInfo().getTwilightMorning(); }
    public AstroDateTime getMoonrise(){
        return astroCalculator.getMoonInfo().getMoonrise();
    }
    public AstroDateTime getMoonset(){
        return astroCalculator.getMoonInfo().getMoonset();
    }
    public Double getAge(){
        return astroCalculator.getMoonInfo().getAge();
    }
    public Double getIlumination(){
        return astroCalculator.getMoonInfo().getIllumination();
    }
    public Double getAzymutW(){
        return astroCalculator.getSunInfo().getAzimuthRise();
    }
    public Double getAzymutZ(){
        return astroCalculator.getSunInfo().getAzimuthSet();
    }
    public AstroDateTime getNextNewMoon(){
        return astroCalculator.getMoonInfo().getNextNewMoon();
    }
    public AstroDateTime getNextFullMoon(){ return astroCalculator.getMoonInfo().getNextFullMoon(); }
    public void setLocation(AstroCalculator.Location location){ astroCalculator.setLocation(location); }
    public void setDateTime(AstroDateTime astroDateTime){ astroCalculator.setDateTime(astroDateTime); }
}
