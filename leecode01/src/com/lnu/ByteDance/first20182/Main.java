package com.lnu.ByteDance.first20182;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Map<Integer, List<Integer>> users = new HashMap<>();
        for (int i = 0 ; i < n ; i++ ){
            int key = input.nextInt();
            if (!users.containsKey(key)){
                List<Integer> list = new ArrayList<>();
                users.put(key,list);
            }
            users.get(key).add(i+1);
        }


        int q = input.nextInt();
        int[][] teams = new int[q][3];
        for (int i = 0 ; i < q ;i++){
            for (int j = 0 ; j < 3 ; j++){
                teams[i][j] = input.nextInt();
            }
            List<Integer> list = users.get(teams[i][2]);

            int start = 0 , end = list.size()-1;

            while (start+1 < end){
                int mid = (end + start)/2;
                if (list.get(mid) <= teams[i][0]){
                    start = mid;
                }else {
                    end = mid;
                }
            }

            int listStart = list.size();
            if (list.get(start) >= teams[i][0]){
                listStart = start;
            }else if (list.get(end) >= teams[i][0]){
                listStart = end;
            }

            int ans = 0 ;
            while (listStart < list.size()){
                if (list.get(listStart) <= teams[i][1]){
                    listStart++;
                    ans ++;
                }else {
                    break;
                }
            }
            System.out.println(ans);
        }
    }
}
