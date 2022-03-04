package com.lnu.leecode;

import java.util.*;

//字节面试题
public class typeDance {
    //674. 最长连续递增序列
    public int findLengthOfLCIS(int[] nums) {
        int length=nums.length;
        int max=0;
        List<Integer> list=new ArrayList<>();
        for (int i=0 ;i<length;i++){
            if (i==0){
                list.add(nums[i] );
            }else if (nums[i]>nums[i-1]){
                list.add(nums[i]);
            }else {
                list.clear();
                list.add(nums[i]);
            }
            max=Math.max(max,list.size());
        }
        return max;
    }
    //贪心算法
    public int findLengthOfLCIS2(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }


    /**
      * @Description: todo 两个字符串 找最大公共子串
      * @Date: 2021/12/12 22:53
      **/
    public String maxString(){
        return null;
    }
    //79. 单词搜索
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        //遍历所有位置找到起始点
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
            //如果所有word都已经被找到
        } else if (k == s.length() - 1) {
            return true;
        }

        visited[i][j] = true;
        //向四各个方向搜索
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

    //25.K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null || k<=0) {
            return head;
        }
        //用栈来保存链表的节点
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        int n = k;
        while(p!=null && p.next!=null) {
            ListNode tmp = p.next;
            //不断的将节点加入到栈中
            while(tmp!=null && n>0) {
                stack.add(tmp);
                tmp = tmp.next;
                --n;
            }
            //栈中的第一个元素，就是原链表中的第k个
            //这里备份第k+1个节点，用作后面串联用
            ListNode nextNode = stack.peek().next;
            //n==0说明栈中正好存了k个元素，挨个弹出来并串联起来
            //否则就退出循环
            if(n==0) {
                while(stack.size()>0) {
                    p.next = stack.pop();
                    p = p.next;
                }
            } else {
                break;
            }
            //假设链表是1->2->3->4->5，k=3
            //栈的内容是[3,2,1]，栈中第一个元素的next就是4
            //于是将p.next指向4就可以了
            p.next = nextNode;
            n = k;
        }
        return dummy.next;
    }

    //560
    public int subarraySum(int[] nums, int k) {

        return 0;
    }


}
