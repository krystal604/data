package com.lnu.twoPoints;

import com.lnu.SeraceTree.TreeNode;

import java.util.HashMap;
import java.util.Scanner;

public class first {

    public int search(int[] nums, int target) {
        int start = 0 ;
        int end = nums.length-1;

        while (start+1 < end){
            int mid = (start+end)/2;
            //mid在上线
            if (nums[mid] > nums[end]){
                //如果 target 在上线 且在左半边 即中段
                if (target <= nums[mid] && target >= nums[start] ){
                    end = mid;
                }else {
                    start = mid;
                }
            }else {
                //如果 target 在下线 且在右半边 也是中段
                if (target>= nums[mid] && target<= nums[end]){
                    start = mid;
                }else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target){
            return start;
        }
        if (nums[end] == target){
            return end;
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1 , -1};
        if (nums.length==0){
            return ans;
        }
        //找第一次出现位置
        int start = 0 ,end = nums.length-1;
        while (start+1 < end){
            int mid = (start + end )/2;
            //遇见等于就动end 寻找第一次出现位置
            if (target <= nums[mid]){
                end = mid;
            }else {
                start = mid;
            }
        }
        //优先比较start寻找第一次出现位置 如果没有直接返回-1-1
        if (nums[start] == target){
            ans[0] = start;
        } else if (nums[end] == target){
            ans[0] = end;
        }else {
            return ans;
        }
        //寻找最后一次出现位置
        start = 0 ;
        end = nums.length-1;
        while (start+1 < end){
            int mid = (start + end )/2;
            //遇见等于就动start
            if (target >= nums[mid]){
                start = mid;
            }else {
                end = mid;
            }
        }
        //优先寻找end
        if (nums[end] == target){
            ans[1] = end;
        }else if (nums[start] == target){
            ans[1] = start;
        }

        return ans;
    }

    public int Sqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
    public int mySqrt(int x){
        if (x==1){
            return 1;
        }
        int start = 0 , end = x;
        while (start+1 < end){
            int mid = start+(end-start)/2;
            if ((long)mid*mid <= x){
                start = mid;
            }else {
                end = mid;
            }
        }

        if (start*start <= x ){
            return start;
        }else if (end*end <= x ){
            return end;
        }else{
            return -1;
        }

    }

    public boolean search2(int[] nums, int target) {
        int start = 0 ;
        int end = nums.length-1;

        while (start+1 < end){
            int mid = (start+end)/2;
            //mid在上线
            if (nums[mid] > nums[end]){
                //如果 target 在上线 且在左半边 即中段
                if (target <= nums[mid] && target >= nums[start] ){
                    end = mid;
                }else {
                    start = mid;
                }
            }else {
                //如果 target 在下线 且在右半边 也是中段
                if (target>= nums[mid] && target<= nums[end]){
                    start = mid;
                }else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target){
            return true;
        }
        return nums[end] == target;
    }
    public int findMin(int[] nums) {
        int start = 0 ;
        int end = nums.length-1;

        while (start+1 < end){
            int mid = start + (end - start)/2;
            if (nums[mid] < nums[end]){
                end = mid;
            }else {
                start = mid;
            }
        }
        return Math.min(nums[start],nums[end]);
    }
    /**
      * @Description: todo 162找波峰
      * @Date: 2022/1/16 22:13
      * @Param nums:
      * @return: int
      * @Version: 1.0
      **/
    public int findPeakElement(int[] nums) {
        if (nums.length <= 2){
            if (nums.length==2 && nums[0] < nums[1]){
                return 1;
            }
            return 0;
        }
        int start = 1 ;
        int end = nums.length-1;
        while (start+1 < end){
            int mid = start + (end - start)/2;
            //在上市趋势中
            if (nums[mid] > nums[mid-1]){
                start = mid;
            }
            //在下降趋势中
            else if (nums[mid] < nums[mid-1]){
                end = mid;
            } else {
                return mid;
            }
        }

        return nums[start] < nums[end] ? end:start;
    }

    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = {-1,-1};
        for (int i = 0; i < numbers.length ; i++){
            if (map.containsKey(target - numbers[i])){
                ans[0] = map.get(target - numbers[i]) + 1;
                ans[1] = i+1;
                return ans;
            }
            map.put(numbers[i],i);
        }

        return ans;
    }
    int ans = 0;
    public int countNodes(TreeNode root) {

        countTree(root, ans);
        return ans;
    }

    private void countTree(TreeNode root , int ans ){
        if (root == null){
            return ;
        }
        ans++;
        countTree(root.left,ans);
        countTree(root.right,ans);
    }

    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        Boolean[] dp = new Boolean[n+1];
        for (int i = 0 ; i < n ; i++){
            dp[i] = false;
        }
        for (int i = 0 ; i < n ; i++){
            int next = in.nextInt();
            dp[next] = true;
        }
        for (int i = 0 ; i < n ; i++){
            if (!dp[i]){
                System.out.println(i);
            }
        }

    }
}
