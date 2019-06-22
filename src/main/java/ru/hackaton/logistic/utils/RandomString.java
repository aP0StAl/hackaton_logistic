package ru.hackaton.logistic.utils;

import java.security.SecureRandom;
import java.util.Locale;

public class RandomString {
    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase(Locale.ROOT);
    public static final String digits = "0123456789";
    public static final String alphanum = upper + lower + digits;

    private static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len, String sequence){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( sequence.charAt( rnd.nextInt(sequence.length()) ) );
        return sb.toString();
    }
}
