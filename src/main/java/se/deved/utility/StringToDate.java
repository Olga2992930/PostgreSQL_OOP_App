package se.deved.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {
    public static Date parseString(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(s);
            return date;
        } catch (Exception e) {
            return null;
        }
    }
}