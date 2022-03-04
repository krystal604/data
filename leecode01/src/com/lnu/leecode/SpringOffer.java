package com.lnu.leecode;

import javax.swing.tree.TreeNode;
import java.lang.reflect.Method;
import java.util.*;

//leetcode春招题
public class SpringOffer {
    //给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    //二分查找yyds！！！！！
    //4. 寻找两个正序数组的中位数
    //给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
    //算法的时间复杂度应该为 O(log (m+n)) 。
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            //总数为偶数的情况下 要取(m+n)/2 和 (m+n)/2+1两个树的平均值
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    private static int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                //k=(m+n)/2 即为中位数位置   在数组中取第n个元素对应下标为n-1所以要减1
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            //防止越界
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            //更新两个数组拟被排除部分最大值
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            //比较 较小的将会被抛弃
            if (pivot1 <= pivot2) {
                //对应下表移动k/2 要用新下表减旧下标再加一
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
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


    //238. 除自身以外数组的乘积
    //给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
    // 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left =new int[n];
        int[] right=new int[n];
        int[] result=new int[n];
        //从左向右所有乘积
        left[0]=1;
        for (int i = 1 ; i < n ;i++  ){
            left[i]=left[i-1]*nums[i-1];
        }
        //从右向左所有乘积
        right[n-1]=1;
        for (int i= n-2; i>=0 ;i--){
            right[i]=right[i+1]*nums[i+1];
        }
        //对于i来说 left[i]与right[i] 都不包含（未乘以i项）所以使用左右直接相乘即可
        for (int i = 0; i<n ; i++){
            result[i]=left[i]*right[i];
        }
        return result;
    }

    //415. 字符串相加
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }


    //974. 和可被 K 整除的子数组
    public static int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> record = new HashMap<>();
        record.put(0, 1);
        int sum = 0, ans = 0;
        for (int elem: nums) {
            sum += elem;
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = (sum % k + k) % k;
            int same = record.getOrDefault(modulus, 0);
            ans += same;
            record.put(modulus, same + 1);
        }
        return ans;
    }


    //560. 和为 K 的子数组
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap < Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }

        return count;
    }

    //200. 岛屿数量
    public static int numIslands(char[][] grid) {
        int nx=grid.length;
        int ny= grid[0].length;
        int ans=0;
        for (int x=0 ; x<nx ; x++){
            for (int y=0 ; y<ny ; y++){
                if (grid[x][y]=='1'){
                    ans++;
                }
                landsDfs(grid,x,y);
            }
        }
        return ans;
    }
    public static void landsDfs(char[][] grid,int x , int y){
        int nx=grid.length;
        int ny= grid[0].length;
        if (x<0 || y<0 || x>=nx || y>=ny || grid[x][y]=='0'){
            return;
        }
        grid[x][y]='0';
        landsDfs(grid, x-1, y);
        landsDfs(grid, x+1, y);
        landsDfs(grid, x, y-1);
        landsDfs(grid, x, y+1);

    }
    //990. 等式方程的可满足性
    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }


    //5. 最长回文子串
    public String longestPalindrome1(String s) {
        int length = s.length();
        HashSet<Character> set = new HashSet<>();
        int rk=-1;//右指针
        int ans=0;
        for (int i=0 ; i < length ;i++){
            if (i!=0){
                //左指针向右移动
                set.remove(s.charAt(i));
            }
            while (rk +1 < length ){
                //第一种情况


                ++rk;
            }

            ans=Math.max(ans, set.size());
        }
        return null;
    }


    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }
    //53. 最大子序和
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    //42. 接雨水

    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }
    //1014. 最佳观光组合
    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0, mx = A[0] + 0;
        for (int j = 1; j < A.length; ++j) {
            ans = Math.max(ans, mx + A[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, A[j] + j);
        }
        return ans;
    }

    //121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int length=prices.length;
        int min=prices[0];
        int ans=0;
        for (int i=1 ;i<length;i++){
            min=Math.min(min,prices[i]);
            ans=Math.max(ans,prices[i]-min);
        }
        return ans;
    }
    //139. 单词拆分
    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
    //199
    public List<Integer> rightSideView(MyTreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null){
            return ans;
        }
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() != 0){
            for (int i = 0 ; i < queue.size() ; i++ ){
                MyTreeNode poll = queue.poll();

                if (poll.left != null){
                    queue.offer(poll.left);
                }
                if (poll.right != null){
                    queue.offer(poll.right);
                }

                if (i==queue.size()-1){
                    ans.add(poll.val);
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {

//        lengthOfLongestSubstring("abcabcbb");

        int [] a={4,5,0,-2,-3,1};
        int [] b={1,2,3};
        char [][] c={{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
//        findMedianSortedArrays(a,b);
//        productExceptSelf(a);
//        subarraysDivByK(a,5);
        System.out.println(numIslands(c));
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
        wordBreak("leetcode",list);
    }
}
