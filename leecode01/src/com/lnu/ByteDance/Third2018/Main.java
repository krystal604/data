package com.lnu.ByteDance.Third2018;

import java.util.*;

public class Main{
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        String[] ans = new String[t];
        for (int i = 0 ; i < t ; i++ ){
            int n = input.nextInt();
            int k = input.nextInt();
            int d1 = input.nextInt();
            int d2 = input.nextInt();
            // d1全部认为 二队比一队多d1分
            //两组情况 第一种 三队比二队多d2分
            boolean first = k - n >= d1 + 2 * d2 && ((k - n) - (d1 + 2 * d2)) % 3 == 0;
            //第二种 三队比二队少d2分
            boolean second = n - k >= d1 + (d1 - d2) && (n - k) - (d1 + (d1 - d2)) % 3 == 0;
            if (first || second){
                ans[i] = "yes";
            }else {
                ans[i] = "no";
            }
        }
        for (int i = 0 ; i < t ; i++ ){
            System.out.println(ans[i]);
        }
    }

}