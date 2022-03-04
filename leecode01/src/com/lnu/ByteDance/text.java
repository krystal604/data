package com.lnu.ByteDance;

import com.lnu.tree.TreeNode;

import java.util.*;

public class text {

    /*
      给你一个数target，一个数组nums，求用nums数组中的数构造出最大的小于target的数
      */
    public int maxTarget(int[] nums , int target){
        //先将target拆分
        List<Integer> split = new ArrayList<>();
        //辅助栈 将拆分target 按从小到大排列
        Stack<Integer> stack = new Stack<>();
        int temp = target;

        while (temp > 10){
            stack.push(temp % 10);
            temp = temp/10;
        }
        stack.push(temp);

        while (!stack.isEmpty()){
            split.add(stack.pop());
        }
        // nums 排序
        Arrays.sort(nums);
        int length = split.size();

        int ans = 0;

        Stack<Integer> preIndex = new Stack<>();
        for (int i = 0; i < length; i++) {
            boolean flag = false;
            for (int j = nums.length - 1 ; j >= 0 ; j--) {
//                if (flag){
//                    ans = ans*10 + nums[nums.length-1];
//                    continue;
//                }

                if (nums[j] == split.get(i)){
                    ans = ans*10 + nums[j];
                    preIndex.push(j);
                    break;
                } else if (nums[j] < split.get(i)){
                    ans = ans*10 + nums[j];
                    while (i < length){
                        ans = ans*10 + nums[nums.length-1];
                        i++;
                    }
                    break;
                }
                //如果在该处实在找不到 比target i 小的 处理前一位
                if (j == 0 && nums[j] > split.get(i)){
                    ans = ans / 10 ;
                    while (true){
                        if (stack.peek() > 0){
                            ans = ans *10 + nums[stack.peek()];
                        } else {
                            stack.pop();
                            break;
                        }
                    }
                    while (i < length){
                        ans = ans*10 + nums[nums.length-1];
                        i++;
                    }

                }

            }
        }

        return ans;
    }



    public int drink (int n){
        int start = n / 2;
        int sum = start;
        int bottle = start % 2 + start/2;
        while (bottle >= 2){
            sum += bottle /2;
            bottle = bottle % 2 + bottle/2;
        }
        int cat = start % 4 + start/4;
        while (cat >= 4){
            sum += cat /4;
            cat = cat % 4 + cat/4;
        }
        return sum;
    }

    public TreeNode increasingBST(TreeNode root) {
        TreeNode pre = new TreeNode();
        TreeNode dummy = pre;
        mid(root,pre);
        return dummy.right;
    }
    public void mid(TreeNode root , TreeNode pre){
        if (root == null ){
            return;
        }

        mid(root.left , pre);
        pre.right = root;
        pre = root;
        mid(root.right, pre);
    }

    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < k; i++) {
            priorityQueue.add(arr[i]);
        }
        for (int i = k+1; i < arr.length; i++) {
            if (priorityQueue.peek() > arr[i]){
                priorityQueue.poll();
                priorityQueue.add(arr[i]);
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = priorityQueue.poll();
        }

        return ans;
    }


    public static void main(String[] args) {
        text t = new text();
        int[] a = {1,2,3,7};
        System.out.println(t.maxTarget(a, 123451235));
        System.out.println(t.drink(8));
    }
}
