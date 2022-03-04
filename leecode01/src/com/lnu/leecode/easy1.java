package com.lnu.leecode;

import java.util.*;


public class easy1 {
    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     **/
    public int[] towSum(int[] num, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        //一次循环复杂度O（n）
        for (int i = 0; i < num.length; i++) {
            //map生成红黑树之前复杂度为O（1） 生成红黑树后复杂度O（log n）
            if (map.containsKey(target - num[i])) {
                return new int[]{map.get(target - num[i]), i};
            }
            //判断后加 防止出现2*num[i] = target 情况
            map.put(num[i], i);
        }
        
        return null;
    }

    //9
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            //每次取末尾数字
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (res > 214748364 || (res == 214748364 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < -214748364 || (res == -214748364 && tmp < -8)) {
                return 0;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        return res;

    }

    //9. 回文数
    public boolean isPalindrome(int x) {
        int res = 0;
        int xTemp = x;
        while (x != 0) {
            //每次取末尾数字
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (res > 214748364 || (res == 214748364 && tmp > 7)) {
                return false;
            }
            //判断是否 小于 最小32位整数
            if (res < -214748364 || (res == -214748364 && tmp < -8)) {
                return false;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        if (xTemp <= 0) {
            xTemp = -xTemp;
        }
        return res == xTemp;
    }

    //13. 罗马数字转整数
    public static int romanToInt(String s) {
        if (s.length() > 15) {
            throw new RuntimeException("s is too big");
        }
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            //如果后一个必前一个大
            if (i > 0) {
                if (map.get(s.charAt(i - 1)) < map.get(s.charAt(i))) {
                    Integer pop = stack.pop();
                    stack.push(map.get(s.charAt(i)) - pop);
                    continue;
                }
            }
            stack.push(map.get(s.charAt(i)));
        }
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }


    //14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }


    //20.有效括号
    public static boolean isValid(String s) {
        if (s.length() <= 1 || s.length() % 2 == 1) {
            return false;
        }
        //设置优先级
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('(', 1);
        map.put(')', 1);
        map.put('[', 2);
        map.put(']', 2);
        map.put('{', 3);
        map.put('}', 3);
        Stack<Character> stack = new Stack<>();
        //记录上一次优先级
        int first = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {

                //如果最后一位仍未左括号 返回失败
                if (i + 1 == s.length()) {
                    return false;
                }
                stack.push(s.charAt(i));
                first = map.get(s.charAt(i));
            } else {
                if (!stack.isEmpty()) {
                    if (!map.get(s.charAt(i)).equals(map.get(stack.pop()))) {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        }
        //如果栈没被清空
        return stack.isEmpty();
    }

    //21.合并两个有序链表
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }



    //26. 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;

    }

    //27. 移除元素
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 0, slow = 0;
        while (fast < n) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    //28. 实现 strStr()
    public static int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }


    //35. 搜索插入位置
    //二分查找
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;

    }

    //58. 最后一个单词的长度
    public static int lengthOfLastWord(String s) {
        int length = s.length();
        Stack<String> stack = new Stack<>();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) != ' ') {
                stringBuffer.append(s.charAt(i));
                if (i==length-1){
                    stack.push(stringBuffer.toString());
                }
            }
            if (stringBuffer.length() != 0 && s.charAt(i) == ' ') {
                stack.push(stringBuffer.toString());
                //清空
                while (stringBuffer.length()!=0){
                    stringBuffer.deleteCharAt(stringBuffer.length()-1);
                }
            }

        }
        return stack.pop().length();

    }

    //8. 字符串转换整数 (atoi)
    public int myAtoi(String s) {
        int length = s.length();
        boolean isDownZero = false;
        int ans = 0;
        for (int i = 0 ; i < length ; i++){
            if (s.charAt(i) == '-' && ans == 0){
                isDownZero = true;
            }else if (s.charAt(i) - '0'>=0 || s.charAt(i) - '0' <= 9){
                ans = ans*10 + (s.charAt(i)-'0');
            }else if (ans == 0 && s.charAt(i) != ' ' ){
                return 0;
            }

        }

//        if (ans > 2<<30){
//            if (isDownZero){
//                return -1<<31;
//            }else {
//                return 1<<31;
//            }
//        }
        if (isDownZero){
            return -1*ans;
        }else {
            return ans;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    /**
      * @Description: todo
      * @Date: 2022/1/20 20:06
      * @Param heights:
      * @return: int
      * @Version: 1.0
      **/
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> value = new Stack<>();
        Stack<Integer> index = new Stack<>();
        value.push(-1);
        index.push(-1);
        int ans = 0;
        for (int i = 0 ; i < heights.length ; i++){
            if (heights[i] <= value.peek()) {
                while (value.peek() > heights[i]) {
                    int tempValue = value.pop();
                    int tempIndex = index.pop();
                    ans = Math.max(ans, tempValue);
                    ans = Math.max(ans, (i - tempIndex) * tempValue);
                }
            }
            value.push(heights[i]);
            index.push(i);
        }
        while (value.peek() > -1 ){
            int tempValue = value.pop();
            int tempIndex = index.pop();
            ans = Math.max(ans,tempValue);
            ans = Math.max(ans,(heights.length-1-tempIndex) * tempValue);
        }

        return ans;
    }
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    /**
      * @Description: todo 74. 搜索二维矩阵
      * @Date: 2022/1/21 18:14
      * @Param matrix:
      * @Param target:
      * @return: boolean
      * @Version: 1.0
      **/
    public boolean searchMatrix(int[][] matrix, int target) {
        int height = matrix.length;
        int length = matrix[0].length;
        int y = -1;
        for (int i = 0 ; i < height ; i++){
            if (matrix[i][0] <= target && matrix[i][length-1] >= target ){
                y=i;
            }
        }
        if (y == -1 ){
            return false;
        }

        int start = 0 , end = length-1;
        while (start+1 < end){
            int mid = start + (end - start )/2;
            if (matrix[y][mid] <= target ){
                start = mid;
            }else {
                end = mid;
            }
        }

        if (matrix[y][start] == target){
            return true;
        }else return matrix[y][end] == target;
    }

    /**
      * @Description: todo 两数相除
      * @Date: 2022/1/21 18:24
      * @Param dividend:
      * @Param divisor:
      * @return: int
      * @Version: 1.0
      **/
    public int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor ==Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }


        boolean res = false;
        if (divisor < 0){
            divisor = -divisor;
            res = true;
        }
        if (dividend < 0 ){
            dividend = -dividend;
            res = !res;
        }


        int start = 0 , end = dividend;
        while (start+1 < end){
            int mid = start + (end - start)/2;
            if (mid * divisor <= dividend){
                start = mid;
            }else {
                end = mid;
            }
        }

        if (end * divisor <= dividend){
            return res ? -end : end;
        }else if (start * divisor <= dividend){
            return res ? -start : start;
        }
        return 0;
    }


    public int smallestDifference(int[] a, int[] b) {
        int ans = Integer.MAX_VALUE;
        for (int j : a) {
            for (int k : b) {
                int temp = Math.max(j, k) - Math.min(j, k);
                if (temp < 0){
                    temp = - temp;
                }
                ans = Math.min(ans, temp);
            }
        }
        return ans;
    }

    /**
      * @Description: todo 66. 加一
      * @Date: 2022/1/23 20:26
      * @Param digits:
      * @return: int[]
      * @Version: 1.0
      **/
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }

        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }


    /**
      * @Description: todo 12
      * @Date: 2022/1/27 12:51
      * @Param num:
      * @return: java.lang.String
      * @Version: 1.0
      **/
    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        Map<Integer, String> map = new HashMap<>();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0 ; i < values.length ; i++ ){
            int temp = num / values[i];
            num %= values[i];
            for (int j = 0 ; j < temp ; j ++){
                ans.append(symbols[i]);
            }
        }
        return ans.toString();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(len < 3) {
            return ans;
        }
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) {
                // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
                break;
            }
            if(i > 0 && nums[i] == nums[i-1]){
                continue; // 去重
            }
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                } else if (sum < 0){
                    L++;
                }
                else if (sum > 0) {
                    R--;
                }
            }
        }
        return ans;
    }

    /**
      * @Description: todo 16
      * @Date: 2022/1/27 14:19
      * @Param nums:
      * @Param target:
      * @return: int
      * @Version: 1.0
      **/
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = Integer.MAX_VALUE;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode list : lists) {
            ans = mergeTwoLists2(ans, list);
        }
        return ans;
    }

    public ListNode mergeTwoLists2(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public int coinChange(int[] coins, int amount) {
        int length = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for (int i = 0 ; i <= amount ; i++){
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount];
    }




    public int threeSumClosest1(int[] nums, int target) {
        int length = nums.length;
        int minDifference = Integer.MAX_VALUE;
        int minSum = 0;

        Arrays.sort(nums);
        for (int i = 0 ; i < length ; i++){
            int left = i+1 ,right = length-1;
            while (left < right){
                int sumTemp = nums[i] + nums[left] + nums[right];
                if (sumTemp == target){
                    return target;
                }

                int max = Math.max(target - sumTemp, sumTemp - target);
                if (max < minDifference){
                    minSum = sumTemp;
                }
                minDifference = Math.min(minDifference , max);

                if (sumTemp < target){
                    left++;
                }else {
                    right--;
                }
            }
        }

        return minSum;
    }

    public int findRadius(int[] houses, int[] heaters) {
        int ans = 1;
        boolean[] dp = new boolean[houses.length];
        while (allTrue(dp)){
            for (int i = 0; i < heaters.length; i++) {
                dp[heaters[i]] = true;
                int left = -1 ,right = 1;
                while (heaters[i]+left >= 0 && houses[heaters[i]] - houses[heaters[i]+left] <= ans){
                    dp[heaters[i]+left] = true;
                    left --;
                }
                while (heaters[i] + right <houses.length && houses[heaters[i]+right] - houses[heaters[i]] <= ans){
                    dp[heaters[i] + right] = true;
                    right++;
                }
            }
            ans ++;
        }

        return ans;
    }
    public boolean allTrue(boolean[] dp){
        for (boolean b : dp) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

}