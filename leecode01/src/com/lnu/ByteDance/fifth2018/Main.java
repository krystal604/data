package com.lnu.ByteDance.fifth2018;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int [] roomPid = new int[n];
        int [] roomTimes = new int[n];
        for (int i = 0 ; i < n ; i++){
            roomPid[i] = input.nextInt() -1 ;
            roomTimes[i] = 0 ;
        }
        int nowRoom = 0 ;
        int ans = 0 ;
        while (nowRoom != n){
            //访问偶数此 去pi
            if (roomTimes[nowRoom] %2 == 0){
                roomTimes[nowRoom]++;
                nowRoom = roomPid[nowRoom];
            }else {
                roomTimes[nowRoom]++;
                nowRoom = nowRoom+1;
            }
            ans++;
        }
        System.out.println(ans);
    }
}
