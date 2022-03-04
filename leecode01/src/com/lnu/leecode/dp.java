package com.lnu.leecode;

import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.List;

public class dp {
    public int fib(int n) {

        return Fn(n);
    }

    public int Fn(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return Fn(n - 2) + Fn(n - 1);
    }

    public int tribonacci(int n) {


        return Tn(n);
    }

    public int Tn(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        return Tn(n - 1) + Tn(n - 2) + Tn(n - 3);
    }

    /**
     * @Description: todo 剑指 Offer II 103. 最少的硬币数目
     * @Date: 2022/1/3 19:11
     * @Param coins:
     * @Param amount:
     * @return: int
     * @Version: 1.0
     **/
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        int length = coins.length;
        int[] dp = new int[amount + 1];
        //初始条件
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = 9999;
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        if (dp[amount] == 9999) {
            return -1;
        }
        return dp[amount];
    }

    /**
     * @Description: todo 5. 最长回文子串
     * @Date: 2022/1/3 19:41
     * @Param s:
     * @return: java.lang.String
     * @Version: 1.0
     **/
    public String longestPalindrome(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // 1. 状态定义
        // dp[i][j] 表示s[i...j] 是否是回文串


        // 2. 初始化
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        // 3. 状态转移
        // 注意：先填左下角
        // 填表规则：先一列一列的填写，再一行一行的填，保证左下方的单元格先进行计算
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 头尾字符不相等，不是回文串
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // 相等的情况下
                    // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 状态转移
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要dp[i][j] == true 成立，表示s[i...j] 是否是回文串
                // 此时更新记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        // 4. 返回值
        return s.substring(begin, begin + maxLen);
    }

    public static int jump(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        int[] dp = new int[length];
        dp[0] = 0;
        for (int i = 1; i < length; i++) {
            dp[i] = 999;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (i - j <= nums[i]) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                } else {
                    break;
                }
            }
        }
        return dp[length - 1];
    }

    public int jump1(int[] nums) {

        int[] dp = new int[nums.length];

        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = nums.length + 1;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }

            }
        }

        return dp[dp.length - 1];
    }

    //55.跳跃游戏
    public boolean canJump(int[] nums) {
        int length = nums.length;
        boolean[] dp = new boolean[length];
        dp[0] = true;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (i - j <= nums[j] && dp[j]) {
                    dp[i] = true;
                }
            }
        }
        return dp[length - 1];
    }

    /**
     * @Description: todo 62. 不同路径
     * @Date: 2022/1/4 14:18
     * @Param m:
     * @Param n:
     * @return: int
     * @Version: 1.0
     **/
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //设置初始状态 第一行和第一列都是1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * @Description: todo 64. 最小路径和
     * @Date: 2022/1/4 14:36
     * @Param grid:
     * @return: int
     * @Version: 1.0
     **/
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        //设置初始状态 第一行和第一列都是直线相加
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * @Description: todo 198. 打家劫舍
     * @Date: 2022/1/5 19:20
     * @Param nums:
     * @return: int
     * @Version: 1.0
     **/
    public int rob(int[] nums) {
        if (nums.length <= 1) {
            return nums[nums.length - 1];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    /**
     * @Description: todo
     * @Date: 2022/1/5 19:19
     * @Param cost:
     * @return: int
     * @Version: 1.0
     **/
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length < 2) {
            return 0;
        }

        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);

        }
        return dp[cost.length];
    }

    /**
     * @Description: todo 53. 最大子数组和
     * @Date: 2022/1/5 19:19
     * @Param nums:
     * @return: int
     * @Version: 1.0
     **/
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * @Description: todo 152. 乘积最大子数组
     * @Date: 2022/1/5 19:20
     * @Param nums:
     * @return: int
     * @Version: 1.0
     **/
    public int maxProduct(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxProduct = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] * nums[i]);
            maxProduct = Math.max(dp[i], maxProduct);
        }

        return maxProduct;
    }

    /**
     * @Description: todo 1014. 最佳观光组合
     * @Date: 2022/1/6 19:27
     * @Param values:
     * @return: int
     * @Version: 1.0
     **/
    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0, mx = A[0] + 0;
        for (int j = 1; j < A.length; ++j) {
            ans = Math.max(ans, mx + A[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, A[j] + j);
        }
        return ans;
    }

    /**
     * @Description: todo 121. 买卖股票的最佳时机
     * @Date: 2022/1/6 19:32
     * @Param prices:
     * @return: int
     * @Version: 1.0
     **/
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int min = prices[0];
        int ans = 0;
        for (int i = 1; i < length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }


    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    public int trap(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i + 1]);
        }
        int ans = 0;
        for (int i = 1; i < height.length; i++) {
            ans += Math.max(0, Math.min(left[i], right[i]) - height[i]);
        }
        return ans;
    }


    /**
     * @Description: todo 91. 解码方法
     * @Date: 2022/1/19 15:24
     * @Param s:
     * @return: int
     * @Version: 1.0
     **/
    public int numDecodings(String s) {
        if (s.length() == 1) {
            if (s.charAt(0) != '0'){
                return 1;
            }else {
                return 0;
            }
        }
        if (s.charAt(0) == '0'){
            return 0;
        }
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0'){
                dp[i] += dp[i - 1];
            }
            if (s.charAt(i - 1) - '0' <= 2 && s.charAt(i) - '0' <= 6) {
                if (i>1){
                    dp[i] += dp[i - 2];
                }else {
                    dp[i] +=1;
                }
            }
        }
        return dp[s.length() - 1];
    }

    public int numDecodings2(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }

    /**
     * @Description: todo 63. 不同路径2
     * @Date: 2022/1/4 14:18
     * @Param m:
     * @Param n:
     * @return: int
     * @Version: 1.0
     **/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int length = obstacleGrid[0].length;
        int height = obstacleGrid.length;
        int[][] dp = new int[height][length];

        for (int i = 0 ; i < length ; i++){
            if (obstacleGrid[0][i] != 1){
                dp[0][i] = 1;
            }else {
                break;
            }
        }
        for (int i = 0 ; i < height ; i++){
            if (obstacleGrid[i][0] != 1){
                dp[i][0] = 1;
            }else {
                break;
            }
        }

        for (int i = 1 ;i < height ; i++ ){
            for (int j = 1 ; j < length ;j++){
                if (obstacleGrid[i][j] != 1){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }else {
                    dp[i][j] = 0;
                }
            }
        }


        return dp[height-1][length-1];
    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int ans = -prices[0];
        int[][][] dp = new int[n][k+1][2];
        for (int i = k ; i >= 1 ; i--){
            dp[0][k][0] = 0;
            dp[0][k][1] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = k ; k>=1 ; k--){
                dp[i][j][0] = Math.max(dp[i-1][j][0] ,dp[i-1][j][1] + prices[i] );
                dp[i][j][1] = Math.max(dp[i-1][j-1][0] - prices[i] ,dp[i-1][j][1] );
                ans = Math.max(ans,dp[i][j][0]);
            }
        }
        return dp[n-1][1][0];
    }
    /**
      * @Description: todo 118
      * @Date: 2022/2/13 22:44
      * @Param numRows:
      * @return: java.util.List<java.util.List<java.lang.Integer>>
      * @Version: 1.0
      **/
    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0 ; i < numRows ; i++){
            List<Integer> temp = new ArrayList<>();
            for (int j = 0 ; j < i+1 ; j++){
                if (j == i){
                    temp.add(1);
                }else if (j == 0){
                    temp.add(1);
                }else {
                    temp.add(ans.get(i-1).get(j) + ans.get(i-1).get(j-1) );
                }
            }
            ans.add(temp);
        }

        return ans;
    }


    /**
      * @Description: todo 120
      * @Date: 2022/2/14 15:07
      * @Param triangle:
      * @return: int
      * @Version: 1.0
      **/
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] dp = new int[size][size];

        for (int i = 0 ; i < size ; i ++ ){
            for (int j = 0 ; j < size ; j++){
                dp[i][j]= Integer.MAX_VALUE;
            }
        }

        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1 ; i < size ; i ++ ){
            for (int j = 0 ; j <= i ; j++){
                if (j==0){
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                }else {
                    dp[i][j] = Math.min(dp[i-1][j] , dp[i-1][j-1] ) + triangle.get(i).get(j);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0 ; i < size ; i++){
            ans = Math.min(dp[size-1][i],ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        dp dp = new dp();
        int[][] a = {
                {0,0,0},
                {0,1,0},
                {0,0,0},
        };
        dp.uniquePathsWithObstacles(a);
        int[] dpa = {2,4,1};
        dp.maxProfit(2,dpa);
    }
}


    
