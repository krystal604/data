package com.lnu.leecode;

import java.util.Scanner;

public class Main{
    public static void main (String[] srgs){
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        for (int n = 0 ; n < N ; n++){
            String str = input.next();
            StringBuffer strs = new StringBuffer(str);
            int length = strs.length();
            for (int i = 1 ; i < strs.length() ; i++){
                //出现第一次
                if (strs.charAt(i) == strs.charAt(i-1)){
                    //判断是否为AABB；
                    if(i+2 <  strs.length() && strs.charAt(i+1) == strs.charAt(i+2)){
                        while (i+2 <  strs.length() && strs.charAt(i+2) == strs.charAt(i+1)){
                            strs.deleteCharAt(i+2);

                        }
                    }
                    //为AAAAA模式
                    while (i <  strs.length() && strs.charAt(i) == strs.charAt(i+1)){
                        strs.deleteCharAt(i+1);
                    }
                }
            }
            String ans = new String(strs);
            System.out.println(ans);
        }
    }


}