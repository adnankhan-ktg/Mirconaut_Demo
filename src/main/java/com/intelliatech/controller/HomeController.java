package com.intelliatech.controller;


import com.intelliatech.bean.User;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
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


    @Get("/get/data/pagination/{pageNumber}/{offset}")
     public Page<User> getUser(Integer pageNumber, Integer offset)
     {
         List<User> list = new ArrayList<>();
         list.add(new User("aditya","8964882358"));
         list.add(new User("shashank","8964882356"));
         list.add(new User("pankaj","8964882354"));
         list.add(new User("amit","8964882357"));
         list.add(new User("sami","8964882359"));

         String sortOrder = "desc";
         String sortBy = "id";
         if(list.size() / offset < pageNumber)
         {
             return Page.empty();
         }

         Sort sort = "ASc";
         List<Sort.Order> orders = new ArrayList<>();
         orders.add(new Sort.Order(Sort.Order.asc("userName").isIgnoreCase());
         Pageable pageable = Pageable.from(pageNumber,offset);
         int max = (offset*(pageNumber+1) > list.size()) ? list.size() : offset*(pageNumber+1);
         Page<User> page = Page.of(list.subList(pageNumber*offset,max),pageable,list.size());
         return page;
     }
 }
