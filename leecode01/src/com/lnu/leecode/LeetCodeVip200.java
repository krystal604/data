package com.lnu.leecode;

import com.lnu.tree.TreeNode;

import java.util.*;

public class LeetCodeVip200 {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int length = intervals.length;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        priorityQueue.add(intervals[0][1]);

        for (int i = 1; i < length; i++) {
            if (priorityQueue.peek() <= intervals[i][0] ){
                priorityQueue.poll();
            }
            priorityQueue.add(intervals[i][1]);
        }
        return priorityQueue.size();
    }

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode preRight = null , father = null;
        while (root != null){
            //先记录下 left
            TreeNode left = root.left;
            //当前节点的左子节点更新为父节点的右子节点
            root.left = preRight;
            //记录下当前节点的右子节点
            preRight = root.right;
            //当前节点的右子节点更新为原父节点
            root.right = father;
            //记录下当前节点作为下一个待遍历节点的父节点（新右子节点）
            father = root;
            root = left;
        }
        return father;
    }

    public String[] permutation(String s) {
        List<String> ans = new ArrayList<>();

        return null;
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int length = s.length();
        int ans = 0;
        for (int i = 0; i < length; i++) {
            Set<Character> set = new HashSet<>();
            set.add(s.charAt(i));
            for (int j = i+1; j <length ; j++) {
                set.add(s.charAt(j));
                if (set.size() > k ){
                    break;
                }
                ans = Math.max(ans, j -i);
            }
        }
        return ans;
    }

    public int[][] generateMatrix(int n) {
        int maxSize = n * n;
        int currSize = 1;
        int[][] dp = new int[n][n];

        //向右 下 左 上
        int[][] direction = new int[][]{{ 0 , 1 } ,{ 1 , 0} ,{ 0 , -1 } , {-1 , 0} };
        int currentDirection = 0;
        int row = 0 , clo = 0;
        while (currSize <= maxSize ){
            dp[row][clo] = currSize;
            currSize++;
            int nextRow = row + direction[currentDirection][0];
            int nextClo = clo + direction[currentDirection][1];
            if (nextRow >= n || nextRow < 0 || nextClo >= n || nextClo < 0 || dp[nextRow][nextClo] != 0 ){
                currentDirection = (currentDirection + 1) % 4;
            }
            row = row + direction[currentDirection][0];
            clo = clo + direction[currentDirection][1];
        }
        return dp;
    }

}
