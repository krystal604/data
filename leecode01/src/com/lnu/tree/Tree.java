package com.lnu.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Tree {
    /**
      * @Description: todo 中序遍历
      * @Date: 2022/1/21 13:10
      * @Param root:
      * @return: java.util.List<java.lang.Integer>
      * @Version: 1.0
      **/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        help(root,ans);
        return ans;
    }

    public void help(TreeNode root , List<Integer> ans){
        if (root == null ){
            return;
        }

        help(root.left,ans);
        ans.add(root.val);
        help(root.right,ans);
    }
    /**
      * @Description: todo 共同祖先
      * @Date: 2022/1/21 17:57
      * @Param root:
      * @Param p:
      * @Param q:
      * @return: com.lnu.tree.TreeNode
      * @Version: 1.0
      **/
    private TreeNode ans;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }





}
