package com.lnu.ByteDance.Second2019;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int ans = 0;

    public static void back(List<Integer> temp ,int N , int D , int start ,int[] positions){
        if (temp.size() ==3 && temp.get(2)-temp.get(0) > D ){
            return;
        }
        if (temp.size() >= 3){
            ans++;
            return;
        }
        for (int i = start ; i < N ;i++ ){
            temp.add(positions[i]);

            back(temp,N,D,i+1,positions);

            temp.remove(temp.size()-1);

        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int D = input.nextInt();
        int[] positions = new int[1000000];
        int i = 0;
        for (i=0 ; i < N ; i++){
            positions[i] = input.nextInt();
        }
        List<Integer> temp = new ArrayList<>();
        if (N >= 3){
            back(temp,N,D,0,positions);
            System.out.println(ans);
        }else {
            System.out.println(0);
        }

    }
}
