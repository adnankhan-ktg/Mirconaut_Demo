package com.intelliatech.helper;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Note {

//    public static void main(String[] args) {
//
//        List<QuestionOptionExtractor> list = new ArrayList<>();
//
//        list.add(new QuestionOptionExtractor(1,"what is cow","animal"));
//        list.add(new QuestionOptionExtractor(1,"what is cow","animal and bird"));
//        list.add(new QuestionOptionExtractor(1,"what is cow","none"));
//        list.add(new QuestionOptionExtractor(1,"what is cow","human"));
//        list.add(new QuestionOptionExtractor(2,"math is","animal"));
//        list.add(new QuestionOptionExtractor(2,"math is","animal and bird"));
//        list.add(new QuestionOptionExtractor(2,"math is","none"));
//        list.add(new QuestionOptionExtractor(2,"math is","bird"));
//
//          int flag = 0;
//        response res = new response();
//        List<optionDto> list1 = new ArrayList<>();
//        int incre = 0;
//        List<response> listOfResponse = new ArrayList<>();
//        for(QuestionOptionExtractor qoi: list)
//        {
//
//            if(qoi.getQuestionId() != flag)
//            {
//                   res.setQuestionId(qoi.getQuestionId());
//                   res.setQuestion(qoi.getQuestion());
//                   list1.add(new optionDto(qoi.getOption()));
//                   flag = qoi.getQuestionId();
//                   ++incre;
//
//            }else{
//                list1.add(new optionDto(qoi.getOption()));
//                ++incre;
//            }
//            if(incre == 4)
//            {
//                res.setList(list1);
//                flag = 0;
//                 incre = 0;
//                 listOfResponse.add(res);
//            }
//        }
//
//    }

    public static void main(String[] args) {
//        String search = "in progress";
//        System.out.println(search.replace(" ","").trim());

//        List<String> list = Arrays.asList("Product Manager","Business Manager","Product Head");
        List<String> list = Arrays.asList("Product Manager","Product Head");

        String roles = "";
        for(int i = 0; i <list.size(); ++i)
        {
            roles = roles+","+list.get(i);
        }
        if(list.size() != 0)
        roles = roles.substring(1);
        System.out.println(roles);
        String products = "";
        String jsonString = "{\"roles\":\""+roles+"\",\"products\":\""+products+"\"}";
        System.out.println(jsonString);

        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
        System.out.println("digit = "+formatted);
//
//


    }
}
