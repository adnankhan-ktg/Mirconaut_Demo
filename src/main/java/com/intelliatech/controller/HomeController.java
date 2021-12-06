package com.intelliatech.controller;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {


    @Get("/get/previous/six/months")
    public List<String> getPreviouSixMonths()
    {
        List<String> listOfPreviosSixMonths = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            YearMonth date = YearMonth.now().minusMonths(i);
            String monthName = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            listOfPreviosSixMonths.add(monthName);
            System.out.println(monthName + "(" + date.getYear() + ")");
        }
        return listOfPreviosSixMonths;
    }
 }
