package com.lnu.leecode;

import java.util.*;

public class Back {
    //77.组合
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        // 从 1 开始是题目的设定
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private static void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点
        for (int i = begin; i <= n; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
        }
    }

    //17.电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        int length = digits.length();
        List<String> res=new ArrayList<>();
        if (digits.length()==0){
            return res;
        }
        Map<Character, String>  map =new HashMap<>(){
            {
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }};
        StringBuilder path = new StringBuilder();
        letterCombinationsDfs(0,path,digits,res,map);
        return res;
    }

    public void letterCombinationsDfs(int begin,StringBuilder path,String digits,List<String> res, Map<Character, String>  map){
        //结束条件
        if (path.length() == digits.length() ){
            res.add(path.toString());
            return;
        }

        for (int i=0 ; i< map.get(digits.charAt(begin)).length() ;i++){
            // 向路径变量里添加一个数
            path.append(map.get(digits.charAt(begin)).charAt(i));

            letterCombinationsDfs(begin+1,path,digits,res,map);
            //呢向操作
            path.deleteCharAt(path.length()-1);
        }

    }

    /**
      * @Description: todo 22. 括号生成
      * @Date: 2021/12/26 20:11
      * @Param n:
      * @return: java.util.List<java.lang.String>
      * @Version: 1.0
      **/
    public List<String> generateParenthesisMY(int n) {
        Set<String> set =new HashSet<>();
        List<String> res=new ArrayList<>();
        StringBuilder path=new StringBuilder(10);
        generateParenthesis(n,set,res,path,1);


        return res;
    }

    public void generateParenthesis(int n,Set<String> set,List<String> result,StringBuilder path,int direction){
        //结束条件
        if (path.length()==2*n){
            if (!set.contains(path.toString())) {//如果还没有这个组合
                set.add(path.toString());
                result.add(path.toString());
            }
            //如果已经有这个组合直接返回
            return;
        }

        for (int i=1 ;i < 4 ;i++){
            //如果向右插入
            if (direction==3){
                path.append("()");
            }else if (direction==2){
                path.insert(0,"(");
                path.insert(path.length(),")");
            }else if (direction==1){
                path.insert(0,"()");
            }

            generateParenthesis(n,set,result,path,i);

            if (direction==1){
                path.deleteCharAt(0);
                path.deleteCharAt(0);
            }else if (direction==2){
                path.deleteCharAt(0);
                path.deleteCharAt(path.length()-1);
            }else if (direction==3){
                path.deleteCharAt(path.length()-1);
                path.deleteCharAt(path.length()-1);
            }
        }
    }


    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
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



    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        BacktrackSubsets(nums , 0 , list , ans);

        return ans;
    }

    public void BacktrackSubsets(int[] nums , int begin , List<Integer> list , List<List<Integer>> ans){
        ans.add(new ArrayList<>(list));
        for (int i = begin ; i < nums.length ; i++){
            list.add(nums[i]);

            BacktrackSubsets(nums , begin+1 , list , ans);

            list.remove(list.size()-1);
        }
    }


    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;

    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesis("" , n , n ,ans);
        return ans;
    }

    private void generateParenthesis(String s ,int left , int right , List<String> ans){
        if (left == 0 && right == 0){
            ans.add(s);
        }

        if (left>0){
            generateParenthesis(s+"(" , left-1 , right ,ans);
        }
        if (right > left){
            generateParenthesis(s+")" ,left , right-1 ,ans);
        }

    }

}
