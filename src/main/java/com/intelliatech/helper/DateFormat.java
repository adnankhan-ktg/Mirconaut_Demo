package com.intelliatech.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static void main(String[] args) {
         getDate();
    }

    public static void getDate()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);

    }
}
