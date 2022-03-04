package com.lnu.ByteDance;

import java.util.*;

public class Third2018{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        int M = input.nextInt();
        int P = input.nextInt();
        int[][]dp = new int[P][4];
        int lastTime = 0;
        for (int i = 0 ; i < P ; i++){
            dp[i][0] = input.nextInt();
            dp[i][1] = input.nextInt();
            dp[i][2] = input.nextInt();
            dp[i][3] = input.nextInt();
            lastTime = Math.max(lastTime , dp[i][1]);
        }
        Arrays.sort(dp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        boolean[] visited = new boolean[P];
        //创建优先队列 长度为程序员数目
        PriorityQueue<Integer> prq = new PriorityQueue<Integer>(M);
//        for (int time = 1 ; time <lastTime ; time++){
//            PriorityQueue<int[][]> tempPre = new PriorityQueue<>(new Comparator<int[]>() {
//                @Override
//                public int compare(int[] o1, int[] o2) {
//                    return o1[2] - o2[1];
//                }
//            });
//            int k = 0;
//            while (dp[k][1] <= time){
//                tempPre.add(dp);
//                k++;
//            }
//            int kong = M - prq.size();
//            while (tempPre.size() == kong){
//                tempPre.poll();
//            }
//
//            prq.add(time + );
//        }

    }
}