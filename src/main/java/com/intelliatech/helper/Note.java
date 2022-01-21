package com.intelliatech.helper;

import com.intelliatech.dto.optionDto;
import com.intelliatech.dto.response;
import com.intelliatech.queryExtractor.QuestionOptionExtractor;

import java.util.ArrayList;
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
        String search = "in progress";
        System.out.println(search.replace(" ","").trim());


    }
}
