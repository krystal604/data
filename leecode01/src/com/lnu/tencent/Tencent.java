package com.lnu.tencent;

import com.lnu.leecode.ListNode;

import java.util.List;
import java.util.Stack;

public class Tencent {
    /**
      * @Description: todo 206反转链表
      * @Date: 2022/1/4 18:58
      * @Param head:
      * @return: com.lnu.leecode.ListNode
      * @Version: 1.0
      **/
    public ListNode reverseList(ListNode head) {
        if (head==null){
            return null;
        }
        Stack<Integer> stack=new Stack<>();
        ListNode p=new ListNode();
        ListNode ans1=new ListNode();
        ListNode ans=new ListNode();
        ans1=ans;
        p=head;
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }
        while (!stack.isEmpty()){
            ans.val=stack.pop();
            if(stack.isEmpty()){
                break;
            }
            ans.next=new ListNode();
            ans=ans.next;
        }
        return ans1;
    }
    /**
      * @Description: todo 704.二分查找
      * @Date: 2022/1/4 19:10
      * @Param nums:
      * @Param target:
      * @return: int
      * @Version: 1.0
      **/
    public int search(int[] nums, int target) {
        if (nums.length == 0){
            return -1;
        }
        int length = nums.length;
        int start=0,end=length-1;
        int mid;
        while (start <= end){
            mid=(start+end)/2;
            if (nums[mid]==target){
                return mid;
            }
            if (nums[mid]>target){
                end=mid-1;
            }
            if (nums[mid]<target){
                start=mid+1;
            }

        }
        return -1;
    }



    public String compress (String str) {
        int length = str.length();
        Stack<Integer> times = new Stack<>();
        Stack<String> temp = new Stack<>();
        StringBuilder ans = new StringBuilder();
        ans.append(str.charAt(0));
        for (int i = 1 ; i < length ; i++){
            //数字处理部分
            if (str.charAt(i-1) == '[' ){
                int num = i;
                while (str.charAt(i) == '|'){
                    i++;
                }
                String substring = str.substring(num, i);
                int sum = 0 ;
                for (int j = 0 ; j < substring.length() ;j++ ){
                    sum = sum*10 + (substring.charAt(j) - '0');
                }
                times.push(sum);
            }
            //字符处理
            if (str.charAt(i-1) == '|'){
                int num = i;
                while (str.charAt(i) == ']' || str.charAt(i) == '[' ){
                    i++;
                }
                String substring = str.substring(num, i);
                temp.push(substring);
            }
            //出栈操作
            if (str.charAt(i-1) == ']' && str.charAt(i) != ']'){
                StringBuilder subTemp = new StringBuilder();
                while (!temp.isEmpty()){
                    Integer popTimes = times.pop();
                    String popSub = temp.pop();
                    String tempTemp = popSub + subTemp.toString();
                    subTemp.delete(0,subTemp.length());
                    for (int k = 0 ; k < popTimes ; k++){
                        subTemp.append(tempTemp);
                    }
                }
                ans.append(subTemp.toString());
            }

            if (str.charAt(i)-'A' >= 0 && str.charAt(i)-'A' <= 26  && str.charAt(i-1) != '[' && str.charAt(i-1) != '|'){
                ans.append(str.charAt(i));
            }
        }
        return ans.toString();
    }


    public int[] findBuilding (int[] heights) {
        // write code here
        int[] ans = new int[heights.length];
        for (int i = 0 ; i < heights.length ; i++ ){
            int left = i-1 , right=i+2;
            int leftMax = heights[i+1] , rightMax = heights[i+1];

        }
        return ans;
    }
    public static void main(String[] args) {
        String s = "HG[3|B[2|CA]]F";
        Tencent tencent = new Tencent();
        System.out.println( tencent.compress(s));
    }
}
