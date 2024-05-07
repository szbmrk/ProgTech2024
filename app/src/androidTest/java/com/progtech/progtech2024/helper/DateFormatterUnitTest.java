package com.progtech.progtech2024.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterUnitTest {
    @Test
    public void testDateToString() throws ParseException {
        String dateString = "2024-05-07 15:30:00";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(dateString);

        String formattedDate = DateFormatter.DateToString(date);

        assertEquals("2024-05-07 15:30:00", formattedDate);
    }
}
