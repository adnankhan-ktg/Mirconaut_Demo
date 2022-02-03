package com.intelliatech.helper;

public class ArbajBhaiDemo {

    public static void main(String[] args) {

        System.out.println("hello world");
        int c = sum(10,20);
        System.out.println(c);

    }

     public static int sum(int a ,int b)
     {
         int [] arr = new int[]{10,29,23};


         for(int i = 0; i < arr.length; ++i)
         {
             System.out.println(arr[i]);
         }

         int  z = a + b;
         return z;
     }


}
