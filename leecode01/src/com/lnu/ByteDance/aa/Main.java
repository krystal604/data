package com.lnu.ByteDance.aa;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int D = input.nextInt();
        int[] positions = new int[1000000];
        int i = 0;
        for (i=0 ; i < N ; i++){
            positions[i] = input.nextInt();
        }
        int ans =0;
        int left = 0 ;
        int right = 2 ;
        while (left+2 == N){
            if (positions[right] - positions[left] < D){
                ans += right-left-1;
                right++;
            }else {
                left++;
            }
        }
        System.out.println(ans);
    }
}
