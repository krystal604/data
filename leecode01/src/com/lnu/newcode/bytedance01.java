package com.lnu.newcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bytedance01 {
    public static void judge(String str){
        int length = str.length();


    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str="helloo";
        StringBuilder ans = new StringBuilder(str);
        int length = str.length();
        List<Integer> start=new ArrayList<>();
        List<Integer> end=new ArrayList<>();

        int lk=-1;

        for (int i = 1;i<length;i++){
            if (lk !=-1 && str.charAt(lk)!=str.charAt(i)){
                start.add(lk);
                end.add(i-1);
            }
            if (str.charAt(i)==str.charAt(i-1)){
                lk = i-1;
            }
        }
        int lastEnd=-1;
        int size = start.size();
        if (size==0){
            System.out.println(str);
        }else {
            for (int i=0 ;i<size ;i++){

                ans.delete(start.get(i)+2,end.get(i));

                //两个连续aaabb情况
                if (i!=0 && lastEnd == start.get(i)+1 ){
                    ans.deleteCharAt(start.get(i)+1);
                }

                lastEnd=end.get(i);

            }

        }

        System.out.println(ans.toString());

    }
}
