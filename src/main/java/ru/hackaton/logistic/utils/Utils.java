package ru.hackaton.logistic.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double random(double a, double b){
        return a + Math.random() * (b-a);
    }

    public static int randint(int a){
        return (int)(a * Math.random());
    }
}
