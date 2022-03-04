package com.lnu.leecode;

import java.util.*;

public class hash {
    public int firstMissingPositive(int[] nums) {
        Set<Integer>  set = new HashSet<>();
        int length = nums.length;
        int max = 0;
        for (int num : nums) {
            max = Math.max(max,num);
        }
        for (int i = 1; i < max+1; i++) {
            set.add(i);
        }

        if (max >= Integer.MAX_VALUE){
            for (int i = 1; i < max; i++) {
                set.add(i);
            }
        }

        for (int num : nums) {
            set.remove(num);
        }

        int ans = Integer.MAX_VALUE;
        for (int temp : set){
            ans = Math.min(ans,temp);
        }

        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        int ans = 1 ;
        int left = 0 , right = 1;
        for (int i = 0; i < s.length(); i++) {
            while (!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right ++;
            }
            ans = Math.max(right - i ,ans);
            set.remove(s.charAt(i));
        }

        return ans;
    }

    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        quickSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }

    void quickSort(String[] strs, int l, int r) {
        if(l >= r) return;
        int i = l, j = r;
        String tmp = strs[i];
        while(i < j) {
            while((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            while((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }


    public static void main(String[] args) {

        hash h = new hash();
        int[] a ={2147483647};
        h.firstMissingPositive(a);
    }
}
