package com.lnu.ByteDance.fourth2018;

import java.util.Scanner;

public class Main {
    // 用于判断 当前长度是否有满足m此变换形成的连续子串
    boolean func(int step , int[] count , int n ,int m ,String s ){
        for(int i=0;i+step<n;i++){
            int left = i;
            int right = i+step;
            // 因为设计 left -1 操作 防止越界判断
            if (i > 0){
                if(m >= (right-left+1) - (count[right] - count[left-1]) ) {
                    return true;    //检查 a  公式 m>=区间内 b 的个数
                }
                if(m >= count[right]-count[left -1 ]) {
                    return true;    //检查 b  公式： m>=区间内 a 的个数
                }
            }else {// left == 0时
                // 如果第一个字母是a 那么 从left 到 right a的个数就等于 count[right]
                if (s.charAt(i) == 'a'){
                    if(m >= (right-left+1) - (count[right]) ) {
                        return true;
                    }
                    if(m >= count[right]) {
                        return true;
                    }
                }else {
                    // 如果第一个字母 不 是a 那么 从left 到 right a的个数就等于 count[right] - 1
                    if(m >= (right-left+1) - (count[right]) -1 ) {
                        return true;
                    }
                    if(m >= count[right] +1 ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        String s = input.next();
        int max = 0;

        //求前缀和操作 求从头到 i 共有多少个 a
        int sum = 0 ;
        int[] count = new int[n];
        for (int i = 0 ; i < n ;i++ ){
            if (s.charAt(i) == 'a'){
                count[i] = ++sum;
            }else {
                count[i] = sum;
            }
        }

        Main main = new Main();
        int start = 0 , end = n -1 , mid;
        while (start+1 < end){
            mid = (start+end)/2;
            //如果当前长度可以 移动左指针 进一步放大长度
            if (main.func(mid,count,n,m,s)){
                start = mid;
            }else {
                end = mid ;
            }
        }
        //因为左指针一定行 又是取最大 优先判断右指针
        if (main.func(end,count,n,m,s)){
            max = Math.max(max,end);
        }else if (main.func(start,count,n,m,s)){
            max = Math.max(max,start);
        }
        // 最终长度 因为在数组中 left到right的长度为 right - left +1 所以最终max 要加1
        System.out.println(max+1);
    }

}
