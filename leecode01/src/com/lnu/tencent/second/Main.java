package com.lnu.tencent.second;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public int[] findBuilding (int[] heights) {
        int[] ans = new int[heights.length];
        int left=0,right=0;
        for (int i = 0 ; i < heights.length ; i++){
            left=i-1;
            right=i+1;
            int temp = 0;
            while (left>0 && heights[i] > heights[left]){
                left--;
                temp++;
            }
            while (right < heights.length && heights[i] > heights[right]){
                right++;
                temp++;
            }
            ans[i] = temp;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Integer> nms = new ArrayList<>();
        while (input.hasNext()){
            nms.add(input.nextInt());
        }
        List<Integer> ans = new ArrayList<>();
        int left=0,right=0;
        for (int i = 0 ; i < nms.size() ; i++){
            left=i-1;
            right=i+1;
            int temp = 0;
            while (left>0 && nms.get(i) > nms.get(left)){
                left--;
                temp++;
            }
            while (right < nms.size() && nms.get(i) > nms.get(right)){
                right++;
                temp++;
            }
            ans.add(temp);
        }
        System.out.println(ans);
    }
}
