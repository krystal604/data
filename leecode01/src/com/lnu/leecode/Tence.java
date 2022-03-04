package com.lnu.leecode;

public class Tence {


    public int maxArea1(int[] height) {
        int n= height.length;
        int[] maxLeft=new int[n];
        int[] maxRight=new int[n];
        int ans=0;
        maxLeft[0]=height[0];
        for (int i=1 ;i<n ;i++){
            maxLeft[i]=Math.max(height[i],maxLeft[i-1]);
        }

        maxRight[0]=height[0];
        for (int i=n-2 ;i>=0 ;i--){
            maxRight[i]=Math.max(height[i],maxRight[i-1]);
        }

        for (int i=0;i<n;i++){
            ans+=Math.max(maxLeft[i],maxRight[i]-height[i]);
        }
        return ans;
    }
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int length = height.length;
        int left = 0, right = length - 1;
        int max = 0;
        while (left < right) {
            int temp = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(temp, max);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }

    public void reverseString(char[] s) {
        int length = s.length;
        int left=0,right=length-1;
        char temp;
        for (int i=0 ;i<length/2;i++){
            temp=s[left];
            s[left]=s[right];
            s[right]=temp;
            left++;
            right--;
        }
    }



}
