package com.progtech.progtech2024.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String DateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }
}
