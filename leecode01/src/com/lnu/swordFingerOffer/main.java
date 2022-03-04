package com.lnu.swordFingerOffer;

import com.lnu.tree.TreeNode;

import java.util.*;

public class main {
    //剑指 Offer 06. 从尾到头打印链表
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }

    //剑指 Offer 12. 矩阵中的路径
    public boolean exist(char[][] board, String word) {
        int height = board.length;
        int length = board[0].length;
        if (height*length < word.length()){
            return false;
        }
        boolean ans = false;
        for (int i = 0 ; i < height ; i++){
            for (int j = 0 ; j < length ; j++){
                if (board[i][j] == word.charAt(0)){
                    ans = existDFS(board,word,1,i,j);
                    if (ans){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean existDFS(char[][] board, String word , int current ,int x, int y){
        if (current >= word.length()){
            return true;
        }
        boolean ans = false;
        if (x+1 < board.length  && board[x+1][y] == word.charAt(current)){
            ans = existDFS(board,word,current+1,x+1,y);
            if (ans){
                return true;
            }
        }
        if (x-1 >=0  && board[x-1][y] == word.charAt(current)){
            ans=existDFS(board,word,current+1,x-1,y);
            if (ans){
                return true;
            }
        }
        if (y+1 < board[0].length   && board[x][y+1] == word.charAt(current)){
            ans = existDFS(board,word,current+1,x,y+1);
            if (ans){
                return true;
            }
        }
        if (y-1 >= 0    && board[x][y-1] == word.charAt(current)){
            ans = existDFS(board,word,current+1,x,y-1);
            if (ans){
                return true;
            }
        }
        return false;
    }


    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num : pushed) {
            stack.push(num); // num 入栈
            while(!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
    /**
      * @Description: todo 10
      * @Date: 2022/1/29 19:06
      * @Param n:
      * @return: int
      * @Version: 1.0
      **/
    public int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1000_000_007;
        }
        return dp[n];
    }


    public int minArray(int[] numbers) {
        int start = 0 , end = numbers.length-1;

        while (start+1 < end){
            int mid = start + (end - start)/2;
            if (numbers[mid] > numbers[end]){
                start = mid;
            }else if (numbers[mid] < numbers[end]){
                end = mid;
            }else {
                end -=1;
            }
        }
        return Math.min(numbers[start], numbers[end]);
    }
    /**
      * @Description: todo 13
      * @Date: 2022/2/3 20:07
      * @Param m:
      * @Param n:
      * @Param k:
      * @return: int
      * @Version: 1.0
      **/
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        Queue<int[]> queue = new LinkedList<int[]>();
        // 向右和向下的方向数组
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        boolean[][] vis = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        vis[0][0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 2; ++i) {
                int tx = dx[i] + x;
                int ty = dy[i] + y;
                if (tx < 0 || tx >= m || ty < 0 || ty >= n || vis[tx][ty] || get(tx) + get(ty) > k) {
                    continue;
                }
                queue.offer(new int[]{tx, ty});
                vis[tx][ty] = true;
                ans++;
            }
        }
        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    /**
      * @Description: todo 026. 重排链表
      * @Date: 2022/2/16 21:11
      * @Param head:
      * @return: void
      * @Version: 1.0
      **/
    public void reorderList(ListNode head) {
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        int i = 0;
        while (temp != null){
            ListNode next = temp.next;
            if (i % 2 == 0 ){
                temp.next = temp.next.next;
            }else {
                stack.push(temp);
            }
            i++;
            temp = next;
        }
        ListNode temp2 = head;
        while (!stack.isEmpty()){
            ListNode next = temp2.next;
            ListNode pop = stack.pop();
            temp.next = pop;
            pop.next = next;
            temp2 = next;
        }
    }
    /**
      * @Description: todo 003
      * @Date: 2022/2/24 18:38
      * @Param nums:
      * @return: int
      * @Version: 1.0
      **/
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)){
                return num;
            }
            set.add(num);
        }
        return 0;
    }

    /**
     * @Description: todo 004
     * @Date: 2022/2/24 18:38
     * @Param nums:
     * @return: int
     * @Version: 1.0
     **/
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row = matrix.length;
        int clo = matrix[0].length;

        for (int[] ints : matrix) {
            if (ints[0] <= target) {
                int start = 0, end = clo - 1;
                while (start + 1 < end) {
                    int mid = (end + start) / 2;
                    if (ints[mid] <= target) {
                        start = mid;
                    } else {
                        end = mid;
                    }
                }
                if (ints[start] == target || ints[end] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @Description: todo 022
     * @Date: 2022/2/24 18:38
     * @Param nums:
     * @return: int
     * @Version: 1.0
     **/
    public ListNode getKthFromEnd(ListNode head, int k) {
        int length = getLength(head);
        for (int i = 0; i < length - k; i++) {
            head = head.next;
        }
        return head;
    }

    public int getLength (ListNode head){
        int ans = 0;
        while (head != null){
            ans ++ ;
            head = head.next;
        }
        return ans;
    }



    public String[] permutation(String s) {
        int length = s.length();
        boolean[] visited = new boolean[length];
        List<String> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        Arrays.sort(str);
        help(str,0,new StringBuilder(),visited);
        return ans.toArray(new String[0]);
    }
    public void help(char[] str , int deep , StringBuilder temp , boolean[] visited){
        if (deep == str.length){
            return;
        }

        for (int j = 0; j < str.length; j++) {
            if (visited[j] || (j > 0 && !visited[j - 1] && str[j - 1] == str[j])) {
                continue;
            }
            visited[j] = true;
            temp.append(str[j]);
            help(str, deep + 1, temp, visited);
            temp.deleteCharAt(temp.length() - 1);
            visited[j] = false;
        }

    }

    /**
      * @Description: todo 062
      * @Date: 2022/2/24 19:00
      * @Param n:
      * @Param m:
      * @return: int
      * @Version: 1.0
      **/
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < n ; i++){
            list.add(i);
        }
        int index = 0;
        while (list.size() != 1){
            int size = list.size();
            index = index + m -1;
            if (index >= size ){
                index = index % size;
            }
            list.remove(index);
        }
        return list.get(0);
    }

    /**
      * @Description: todo 10-1
      * @Date: 2022/2/24 20:52
      * @Param n:
      * @return: int
      * @Version: 1.0
      **/
    public int fib(int n) {
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i - 2];
        }
        return dp[n-1];
    }

    public int maxProfit(int[] prices) {
        int ans = 0;
        int min = prices[0];
        int[] dp = new int[prices.length];
        for (int price : prices){
            min = Math.min(price,min);
            ans = Math.max(price-min,ans);
        }
        return ans;
    }



    /**
      * @Description: todo 042
      * @Date: 2022/2/25 14:16
      * @Param nums:
      * @return: int
      * @Version: 1.0
      **/
    public int maxSubArray(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i] );
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }

    public int maxValue(int[][] grid) {
        int row = grid.length;
        int clo = grid[0].length;
        int[][] dp = new int[row][clo];
        //起始状态
        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < clo; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < clo; j++) {
                dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[row-1][clo-1];
    }

    public int translateNum(int num) {
        String str = String.valueOf(num);
        int length = str.length();
        int[] dp = new int[length+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < length+1; i++) {
            if (str.charAt(i-2) == '1' || (str.charAt(i-2) == '2' && str.charAt(i-1) < 6)){
                dp[i] = dp[i-2] + dp[i-1];
            } else {
                dp[i] = dp[i-1];
            }
        }
        return dp[length];
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0);
        ListNode dummy = temp;
        while (l1 != null && l2 != null){
            if (l1 .val < l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    /**
      * @Description: todo 052
      * @Date: 2022/2/26 13:41
      * @Param headA:
      * @Param headB:
      * @return: com.lnu.swordFingerOffer.ListNode
      * @Version: 1.0
      **/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null){
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null){
            if (set.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;

    }


    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null ) {
            return false;
        }
        return isSubStructure(A.right , B) || is(A.left , B) || is(A , B);
    }
    public boolean is (TreeNode A, TreeNode B){
        if (B == null ){
            return true;
        }
        if (A == null || A.val != B.val){
            return false;
        }
        return is(A.left , B.left) && is(A.right , B.right);
    }


    public int maxDepth(TreeNode root) {

        return dfs(root);
    }
    public int dfs(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        return Math.max(left,right)+1;
    }

    public boolean isBalanced(TreeNode root) {
        return root == null || (Math.abs(dfs(root.left) - dfs(root.right) ) <= 1 && isBalanced(root.left) && isBalanced(root.right));
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true){
            if (root.val < p.val && root.val<q.val){
                root = root.right;
            }else if (root.val > p.val && root.val>q.val){
                root = root.left;
            } else {
                return root;
            }
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> list1 = new ArrayList<>();
        List<TreeNode> list2 = new ArrayList<>();
        lowestCommonAncestor2(root, p, list1);
        lowestCommonAncestor2(root, q, list2);
        for (int i = 1; i < Math.min(list1.size(),list2.size()); i++) {
            if (list1.get(i)!=list2.get(i) && list1.get(i-1) == list2.get(i-1) ){
                return list1.get(i-1);
            }
        }
        if (list1.size()<list2.size()){
            return list1.get(list1.size()-1);
        }else {
            return list2.get(list2.size()-1);
        }

    }

    void lowestCommonAncestor2(TreeNode root, TreeNode p , List<TreeNode> list){
        if (root == null){
            return;
        }
        list.add(root);
        lowestCommonAncestor2(root.left,p , list);
        lowestCommonAncestor2(root.right,p , list);
        if (list.get(list.size()-1) != p){
            list.remove(list.size()-1);
        }
    }
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(i);
        }
        for (int num : nums){
            set.remove(num);
        }
        for (int ans : set){
            return ans;
        }
        return 0;
    }
//    void isBalancedDfs(TreeNode root , int depth,int max , int min){
//        if (root == null){
//            return;
//        }
//        if (root.right == null && root.left == null){
//            min = Math.min(min,depth);
//            max = Math.max(max,depth);
//        }
//        isBalancedDfs(root.left, depth+1, max, min);
//        isBalancedDfs(root.right, depth+1, max, min);
//
//    }


    public String replaceSpace(String s) {
        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' '){
                str.deleteCharAt(i);
                str.insert(i,"%20");
            }
        }
        return str.toString();
    }

    public int[] levelOrder(TreeNode root) {
        if (root==null){
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                ans.add(poll.val);
                if (poll.left!=null){
                    queue.offer(poll.left);
                }
                if (poll.right!=null){
                    queue.add(poll.right);
                }
            }
        }
        int[] answer= new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }

    List<Integer> replace (List<Integer> temp){
        Stack<Integer> stack = new Stack<>();
        for (Integer integer : temp) {
            stack.push(integer);
        }
        temp.clear();
        while (!stack.isEmpty()){
            temp.add(stack.pop());
        }
        return temp;
    }


    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        for (int i = 0; i < k; i++) {
            priorityQueue.add(arr[i]);
        }
        for (int i = k; i <arr.length ; i++) {
            if (arr[i] < priorityQueue.peek()){
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

    public boolean isStraight(int[] nums) {
        int king = 0 ;
        Arrays.sort(nums);
        int length = nums.length;
        for (int num : nums) {
            if (num == 0) {
                king++;
            }
        }
        for (int i = 1; i < length ; i++) {
            if (nums[i] - nums[i-1] == 1 || nums[i] == 0 || nums[i-1] == 0){
                continue;
            }else if (nums[i] - nums[i-1] >= 2 && king >= nums[i] - nums[i-1] - 1){
                king -= nums[i] - nums[i-1] - 1;
            } else {
                return false;
            }
        }
        return true;
    }

    //双指针解题
    public String reverseWords(String s) {
        int i= s.length();
        StringBuffer sb=new StringBuffer();
        //统计空格数量
        int space = 0;
        while(i>0)
        {
            int j=i;

            while(i>0&&s.charAt(i-1)!=' ') {
                i--;
            }

            for (int k = 0; k < space; k++) {
                if(sb.length()>0){
                    sb.append(' ');
                }
            }
            //空格归零
            space = 0;
            sb.append(s, i, j);
            //移动指针至新单词 并统计中间空格数量
            while(i>0&&s.charAt(i-1)==' '){
                space ++;
                i--;
            }
        }
        return sb.toString();
    }


    public double myPow(double x, int n) {
        if (n < 0){
            x = 1/x;
            n = -n;
        }
        if (n % 2 == 0){
            x = x * myPow(x , n / 2);
        }else {
            x = x * x * myPow(x , n / 2);
        }
        return x;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0 ,right = 0;
        int ans = 1;
        while (right < s.length()){
            if (!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                ans = Math.max(ans,set.size());
            } else {
                while (s.charAt(left ) == s.charAt(right)){
                    set.remove(s.charAt(left ));
                    left++;
                }
            }

            right++;
        }
        return ans;
    }
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0 ;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int heigth = 1 ;
        queue.add(root);
        while (!queue.isEmpty()){
            for (int i = 0 ; i < queue.size() ; i++){
                TreeNode poll = queue.poll();
                if (poll .left == null && poll.right == null){
                    return heigth;
                }
                if (poll.left!=null){
                    queue.add(poll);
                }
                if (poll.right!=null){
                    queue.add(poll);
                }
            }
            heigth++;
        }
        return 0;
    }
    /**
      * @Description: todo 57
      * @Date: 2022/3/3 13:33
      * @Param nums:
      * @Param target:
      * @return: int[]
      * @Version: 1.0
      **/
    public int[] twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(target - num)) {
                return new int[]{num, target - num};
            }
            set.add(num);
        }
        return new int[2];
    }
    /**
      * @Description: todo 58
      * @Date: 2022/3/3 13:36
      * @Param s:
      * @return: java.lang.String
      * @Version: 1.0
      **/
    public String reverseWords1(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
            while(i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    public boolean exist1(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existDfs(board,word,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existDfs(char[][] board, String word , int index ,int row , int clo){
        if (index > word.length()){
            return true;
        }
        if (row > board.length || row < 0 ||clo > board[0].length || clo < 0|| board[row][clo] != word.charAt(index)){
            return false;
        }
        return existDfs(board, word, index+1, row+1, clo) || existDfs(board, word, index+1, row-1, clo)
                || existDfs(board, word, index+1, row, clo+1) ||existDfs(board, word, index+1, row, clo-1);
    }

    public int majorityElement(int[] nums) {
        int length = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num: map.values()) {
            if (map.get(num) > length/2){
                return num;
            }
        }
        return 0;
    }


    public String multiply(String num1, String num2) {
        int i = stringToInt(num1) * stringToInt(num2);
        StringBuilder str= new StringBuilder();
        while (i >= 10 ){
            int temp = i%10;
            str.insert(0,temp);
            i = i/10;
        }
        str.insert(0,i);
        return str.toString();
    }
    public int stringToInt(String s){
        int length = s.length();
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = length-1; i >=0 ; i--) {

            stack.push(s.charAt(i) - '0');
        }
        while (!stack.isEmpty()){
            ans = ans*10 + stack.pop();
        }
        return ans;
    }
    public int[] constructArr(int[] a) {
        int length = a.length;
        int[] left = new int[length];
        int[] right = new int[length];
        left[0] = a[0];
        for (int i = 1; i < length; i++) {
            left[i] = left[i-1] * a[i];
        }
        right[length-1] = a[length-1];
        for (int i = length-2; i >=0 ; i--) {
            right[i] = right[i+1] * a[i];
        }
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            if (i==0){
                ans[i] = right[1];
            }else if (i == length - 1){
                ans[i] = left[length-2];
            } else {
                ans[i] = left[i-1] * right[i+1];
            }
        }
        return ans;
    }
    public int hammingWeight(int n) {
        int ans = 0;
        while (n>0){
            if (n%2 == 1){
                ans++;
            }
            n /=10;
        }
        return ans;
    }

    public int kthLargest(TreeNode root, int k) {
        if (root == null){
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (priorityQueue.size() < k){
                    priorityQueue.add(root.val);
                }else {
                    if (priorityQueue.peek() < root.val){
                        priorityQueue.poll();
                        priorityQueue.add(root.val);
                    }
                }
                if (poll.left != null){
                    queue.offer(poll.left);
                }
                if (poll.right != null){
                    queue.offer(poll.right);
                }
            }
        }
        while (priorityQueue.size() != 1){
            priorityQueue.poll();
        }
        return priorityQueue.poll();
    }

//    public Node treeToDoublyList(Node root) {
//
//    }
//    public void treeToDoublyList(Node root , List<Integer> list){
//        if (root == null){
//            return;
//        }
//        treeToDoublyList(root.left,list);
//        list.add()
//    }
    public static void main(String[] args) {
        main main = new main();
        System.out.println(main.reverseWords("we  are from   China"));
    }
}
