package com.intelliatech.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckLikeNote {

    public static void main(String[] args) {

//        String value = "26.4534";
//        DecimalFormat df = new DecimalFormat("0.00");
////        System.out.println("Real Value = "+value);
//        String s = df.format(BigDecimal.valueOf(Double.valueOf(value)));
////        System.out.println("Rounded Value = "+df.format(value).toString());
//        System.out.println("Real Value = "+s);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        System.out.println(simpleDateFormat.format(new Date()));
    }

}
