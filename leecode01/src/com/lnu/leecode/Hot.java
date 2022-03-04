package com.lnu.leecode;


import com.lnu.tree.TreeNode;

import java.util.*;

public class Hot {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode dummy = new ListNode();
        dummy.val = 0 ;
        dummy.next = head;

        ListNode current = new ListNode();
        current = dummy;
        int length = lists.length;
        while (true){

            //终止判断
            boolean flag = true;
            for (ListNode list : lists) {
                if (list != null) {
                    flag =false;
                    break;
                }
            }
            if (flag){
                break;
            }


            ListNode temp = new ListNode();
            int temIndex = 0;
            temp.val = Integer.MAX_VALUE;
            for (int i = 0 ; i < length ; i++){
                if (lists[i] == null){
                    continue;
                }
                if (lists[i].val < temp.val){
                    temp = lists[i];
                    temIndex = i;
                }
            }
            lists[temIndex] = lists[temIndex].next;
            current.next = temp;
            current = current.next;
        }

        return dummy.next;
    }


    public int longestValidParentheses(String s) {
        if (s.length() == 0 ){
            return 0;
        }
        int ans = 0 ;
        for (int i = 0 ; i < s.length() ; i++){
            int left = 0 , right = 0;
            for (int j = i ; j < s.length() ; j++){
                if (s.charAt(j) == '('){
                    left++;
                }else {
                    if (left == right+1){
                        ans = Math.max(ans,j-i);
                    }
                    if (left > right){
                        right++;
                    }else {
                        break;
                    }

                }
            }
        }
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(3, )
        return ans;
    }


    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            if (set.contains(num)){
                set.remove(num);
            }else {
                set.add(num);
            }
        }
        for (int ans : set){
            return ans;
        }
        return -1;
    }

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0 ;
        }
        int ans = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();;
        queue.offer(root);
        int height = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0 ; i < size ; i++){
                TreeNode poll = queue.poll();
                if (poll.left == null && poll.right == null){
                    ans = Math.max(ans , height);
                }
                if (poll.left!=null){
                    queue.offer(poll.left);
                }
                if (poll.right!=null){
                    queue.offer(poll.right);
                }
            }
            height++;
        }
        return ans;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> ans = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0 ; i < size ; i++){
                TreeNode poll = queue.poll();
                tempList.add(poll.val);
                if (poll.left!=null){
                    queue.offer(poll.left);
                }
                if (poll.right!=null){
                    queue.offer(poll.right);
                }
            }
            ans.add(tempList);
        }
        List<List<Integer>> ans1 = new ArrayList<>();
        for (int i = ans.size()-1 ; i >= 0 ;i-- ){
            ans1.add(ans.get(i));
        }

        return ans1;
    }


    public void flatten(TreeNode root) {
        TreeNode current = root ;
        while (current != null ){
            if (current.left != null){
                TreeNode next = current.left;
                TreeNode pre = current.left;
                while (pre.right != null ){
                    pre = pre.right;
                }
                pre.right = current.right;
                current.right = next;
                current.left = null;
            }
            current = current.right;
        }
    }


    public int majorityElement(int[] nums) {
        int length = nums.length;
        if (length == 1){
            return nums[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                Integer integer = map.get(num);
                map.put(num, integer + 1);
                if (integer + 1 > length / 2) {
                    return num;
                }
            } else {
                map.put(num, 1);
            }
        }
        return -1;
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int i = nums.length - 1 ; i >= 0 ; i--){
//            if (set.contains(nums[i])){
//                continue;
//            }
            set.add(nums[i]);
            k--;
            if (k==0){
                return nums[i];
            }
        }
        return -1;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        for (int i = 0 ; i < length ; i++){
            for (int j = i+1 ; j < length ; j++){
                ans[i] = 0 ;
                if (temperatures[i] < temperatures[j]){
                    ans[i] = j-i;
                    break;
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum(candidates, target, new ArrayList<>(), ans,0);
        return null;
    }

    public void combinationSum(int[] candidates, int target , List<Integer> temp ,List<List<Integer>> ans , int start){
        //结束条件
        if (getSum(temp) >= target){
            if (getSum(temp) == target){
                ans.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = start ; i < candidates.length ; i++) {
            temp.add(candidates[i]);
            combinationSum(candidates, target, temp, ans , i);
            temp.remove(temp.size() - 1);
        }

    }

    private int getSum(List<Integer> list){
        int sum = 0 ;
        for (int l : list){
            sum += l;
        }
        return sum;
    }



    public int findDuplicate(int[] nums) {
        int length = nums.length;
        for (int i = 0 ; i < length ; i++){
            if (i != length-1 ){
                for (int j = i+1; j < length ; j++) {
                    if (nums[i] == nums[j]){
                        return nums[i];
                    }
                }
            }
        }
        return 0;
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;

        while (temp != null){
            list.add(temp.val);
            temp = temp.next;
        }
        int l=0 , r = list.size()-1;
        while (l < r){
            if (!list.get(l).equals(list.get(r))){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


    public int diameterOfBinaryTree(TreeNode root) {
        int l = deep(root.left);
        int r = deep(root.right);
        return l+r;
    }

    private int deep (TreeNode root ) {
        if (root == null){
            return 0;
        }
        int l = deep(root.left );
        int r = deep(root.right );
        return Math.max(l,r)+1;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= nums.length; i++) {
            set.add(i);
        }

        for (int num : nums) {
            set.remove(num);
        }
        return new ArrayList<>(set);
    }

    int ans = 0;
    public int subarraySum(int[] nums, int k) {
        subarraySum(nums, k, 0, new ArrayList<>());
        return ans;
    }

    public void subarraySum(int[] nums, int k , int start , List<Integer> temp) {
        if (getSum(temp) >= k){
            if (getSum(temp) == k){
                ans ++;
            }
            return;
        }

        for (int i = start ; i < nums.length ; i++ ){
            temp.add(nums[i]);
            subarraySum(nums, k, start+1, temp);
            temp.remove(temp.size()-1);
        }
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    public int GetFragment (String str) {
        // write code here
        Map<Character, Integer> map = new HashMap<>();
        int length = str.length();
        int size = 0;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(str.charAt(i))){
                int integer = map.get(str.charAt(i));
                map.remove(str.charAt(i));
                map.put(str.charAt(i) , integer+1);
            }else {
                for (int num : map.keySet()) {
                    sum += num;
                }
                map.clear();
                map.put(str.charAt(i),1);
                size++;
            }
        }

        return length/size;
    }



    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length ==0 || matrix[0].length == 0){
            return ans;
        }
        int height = matrix.length;
        int length = matrix[0].length;
        boolean[][] visited = new boolean[height+1][length+1];
        //向右 下 左 上;
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
        int goDirection = 0;
        int x=0,y=0;
        for (int i = 0 ; i < height*length ; i++){
            ans.add(matrix[x][y]);
            visited[x][y] = true;

            int nextY = y + direction[goDirection][1], nextX = x + direction[goDirection][0];
            if (nextY < 0 || nextY >= length || nextX < 0 || nextX >= height || visited[nextX][nextY]) {
                goDirection = (goDirection + 1) % 4;
            }

            y += direction[goDirection][1];
            x += direction[goDirection][0];
        }
        return ans;
    }

    public int GetMaxConsecutiveOnes (int[] arr, int k) {
        // write code here
        int length = arr.length;
        int[] preSum = new int[length];
        int pre = 0;
        for (int i = 0 ; i < length ; i++){
            if (arr[i] == 1){
                pre++;
            }
            preSum[i] = pre;
        }
        int ans = 0;
        for (int i = 0 ; i < length ;i ++){
            for (int j = i +1 ; j < length ; j++){
                int hasOne = (preSum[j] - preSum[i]);
                if (arr[i] == 1){
                    hasOne++;
                }
                if ((j-i+1) - hasOne <= k){
                    ans = Math.max(j-i+1 , ans);
                }
            }
        }

        return ans;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            List<String> list;
            if (map.containsKey(Arrays.toString(temp))){
                list = map.get(Arrays.toString(temp));
            }else {
                list = new ArrayList<>();
            }
            list.add(str);
            map.put(Arrays.toString(temp),list);
        }
        return new ArrayList<>(map.values());
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0){
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < l) {
                merged.add(new int[]{l, r});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], r);
            }
        }
        return merged.toArray(new int[0][]);
    }


    int count = 0;
    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }


    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            if (map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });
        for (Integer key : map.keySet()){
            if (priorityQueue.size() < k){
                priorityQueue.add(key);
            }else if (map.get(key) > map.get(priorityQueue.peek())){
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }
        int[] ans = new int[k];
        for (int i = 0 ; i < k ; i++){
            ans[i] = priorityQueue.poll();
        }

        return ans;
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int longest = 0;
        for (int num : nums){
            set.add(num);
            if (set.contains(num-1)){
                int current = 1;
                while (!set.contains(num-current)){
                    current++;
                }
                longest = Math.max(longest,current);
            }

        }

        return longest;
    }

//    public static void main(String[] args) {
//        int[] a = new int[]{5,3,1,1,1,3,73,1};
//        Hot hot = new Hot();
//        hot.topKFrequent(a,2);
//    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1){
            return 1;
        }
        int ans = 0;
        int length = nums.length;

        for (int i = 0; i < length; i++) {
            int temp = 1;
            for (int j = i+1; j < length ; j++) {
                if (nums[j] > nums[i]){
                    temp++;
                } else {
                    break;
                }
            }
            ans = Math.max(ans,temp);
        }

        return ans;
    }
    public void setZeroes(int[][] matrix) {
        int height = matrix.length;
        int length = matrix[0].length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j] == 0){
                    map.put(i,j);
                }
            }
        }
        for (int h : map.keySet()){
            int l = map.get(h);
            for (int i = 0; i < height; i++) {
                matrix[i][l] = 0;
            }
            for (int i = 0; i < length; i++) {
                matrix[h][i] = 0;
            }
        }

    }


    public void rotate(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        List<Integer> pre = new ArrayList<>();
        List<Integer> next = new ArrayList<>();
        for (int i = 0 ; i < (row/2) ; i++){
            //从 row +i 导 row -i

        }
    }

    public boolean isPalindromePlus(ListNode head) {
        ListNode slow = head, fast = head;

        while (slow != null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null){
            slow =slow.next;
        }

        ListNode reverse = reverse(slow);
        ListNode temp = head;
        while (reverse !=null){
            if (temp.val != reverse.val){
                return false;
            }
            temp = temp.next;
            reverse = reverse.next;
        }


        return true;
    }

    public ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode temp = head;

        while (temp != null){
            ListNode next = temp.next;
            temp.next = pre;
            pre = temp;
            temp = next;
        }
        return pre;
    }
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(temp);

        ListNode h = new ListNode(0);
        ListNode dummy = h;

        while (left != null && right!=null){
            if (left.val < right.val){
                h.next = left;
                left = left.next;
            }else {
                h.next = right.next;
                right = right.next;
            }
            h = h.next;
        }

        h.next = left != null ? left : right;

        return dummy.next;
    }

    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGin(root);
        return maxSum;
    }

    public int maxGin(TreeNode root){
        if ( root == null ){
            return 0;
        }

        int left = Math.max(0,maxGin(root.left));
        int right= Math.max(0,maxGin(root.right));

        int currentSum = right+left+root.val;

        maxSum = Math.max(maxSum,currentSum);

        return Math.max(left,right) + root.val;

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            if (ints[0] < target) {
                int left = 0, right = matrix[0].length - 1;

                while (left + 1 < right) {
                    int mid = (right - left) / 2;

                    if (ints[mid] <= target) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }

                if (ints[left] == target) {
                    return true;
                }
                if (ints[right] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i =1 ; i <= n ; i++){
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1 ; j * j < i ; j++){
                dp[i] = Math.min(dp[i] , dp[i- j*j] );
            }
            dp[i] = dp[i] + 1;
        }
        return dp[n];
    }


    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (row == 0 || col == 0){
            return 0;
        }

        int[][] dp = new int[row][col];
        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1'){
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    ans = Math.max(dp[i][j] , ans);
                }
            }
        }
        return ans;
    }

    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int clo = matrix.length;

        int[][][] dp = new int[row][clo][2];
        int ans = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < clo; j++) {
                if (matrix[i][j] == '1'){
                    if (i == 0 || j ==0){
                        dp[i][j][0] = 1;
                        dp[i][j][1] = 1;
                    } else {
                        int minRow = Math.min(dp[i][j-1][0] , Math.min(dp[i-1][j][0] , dp[i-1][j-1][0] ) - 1 );
                        dp[i][j][1] = Math.max(1, minRow+1);

                        int minClo = Math.min(dp[i - 1][j][1], Math.min(dp[i - 1][j - 1][1], dp[i][j - 1][1]) - 1);
                        dp[i][j][1] = Math.max(1, minClo+1);

                        ans = Math.max(ans , dp[i][j][0] * dp[i][j][1]);
                    }
                }
            }
        }
        return ans;
    }

    public boolean canPartition(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int mid = length/2;
        for (int i = 0; i < length; i++) {
            if (getSum(nums,0,mid) == getSum(nums,mid +1 ,length-1)){
                return true;
            } else if (getSum(nums,0,mid) < getSum(nums,mid +1 ,length-1)){
                mid++;
            } else {
                mid --;
            }
        }
        return false;
    }

    public int getSum(int[] nums , int start ,int end){
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j){
                    dp[i][j] = true;
                    ans ++;
                } else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]){
                    dp[i][j] = true;
                    ans++;
                }

            }
        }
        return ans;
    }

    List<List<Integer>> fourSum = new ArrayList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ret(nums, target, 0, new ArrayList<>());
        return fourSum;
    }

    public void ret(int[] nums, int target , int start , List<Integer> temp){
        if (temp.size() >=4 ){
            int sum = getSum(temp);
            if (sum == target){
                temp.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
                fourSum.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            ret(nums, target, start+1, temp);
            temp.remove(temp.size()-1);
        }
    }


    public String minWindow(String s, String t) {
        String ans = s;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            set.add(t.charAt(i));
        }
        Set<Character> window = new HashSet<>();
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))){
                window.add(s.charAt(i));
            }
            if (window.size() == set.size()){
                while (set.contains(s.charAt(left))){
                    left ++;
                }
                if (i-left+1 < ans.length()){
                    ans = s.substring(left , i);
                }
                window.remove(s.charAt(left));
                left++;
            }

        }
        if (window.size() < t.length()){
            return "";
        }
        return ans;
    }

    public String decodeString(String s) {
        int index = 0;
        int length = s.length();
        if (length == 0){
            return "";
        }
        StringBuilder temp = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        // 栈中每次放两个数据 分别是 循环次数 和 前一阶段的temp暂时保留（前一阶段的temp不参与循环 所以要先保留）
        Stack<String>  stack = new Stack<>();
        while (index < length){
            char current = s.charAt(index);
            //如果遇到数字
            if (Character.isDigit(current)){
                StringBuilder num = new StringBuilder();
                num.append(current);
                int i = index;
                while (Character.isDigit(s.charAt(i))){
                    i++;
                }
                String substring = s.substring(index, i);
                stack.push(substring);
                index = i-1;
            }else if (current == '['){
                // 每次遇到左括号就把temp放入栈 并清空
                stack.push(temp.toString());
                temp.delete(0,temp.length());
            }else if (current == ']'){
                // 遇到又括号就把当前temp 循环n此 再将 弹出的前一个temp加入到头部
                String pop1 = stack.pop();
                String pop2 = stack.pop();
                String str = temp.toString();
                for (int i = 0; i < Integer.parseInt(pop2) - 1; i++) {
                    temp.append(str);
                }
                String now =pop1 + temp.toString();
                temp.delete(0,temp.length());
                temp.append(now);
            } else {
                //没有特殊情况就更新当前temp
                temp.append(current);
            }

            index++;
        }
        return temp.toString();
    }



}
