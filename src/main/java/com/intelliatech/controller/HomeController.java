package com.intelliatech.controller;


import com.intelliatech.bean.User;
import com.intelliatech.dto.OptionDto;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.*;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller("/home")
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


    @Post("/view")
     public Page<User> getUser(@QueryValue Integer page, @QueryValue Integer size)
     {
         List<User> list = new ArrayList<>();
         list.add(new User("aditya","8964882358"));
         list.add(new User("shashank","8964882356"));
         list.add(new User("pankaj","8964882354"));
         list.add(new User("amit","8964882357"));
         list.add(new User("sami","8964882359"));

         Pageable pageable = Pageable.from(page,size);
         if(list.size() / size < page)
         {
             List list1 = Collections.emptyList();
             Page pageObject1 = Page.of(list1,pageable,list.size());
             return  pageObject1;
         }else {
//         Sort.Order order = new Sort.Order("userName", Order.Direction.ASC, true);
             int max = (size * (page + 1) > list.size()) ? list.size() : size * (page + 1);
             Page<User> pageObject = Page.of(list.subList(page * size, max), pageable, list.size());
             return pageObject;
         }
     }
 }
