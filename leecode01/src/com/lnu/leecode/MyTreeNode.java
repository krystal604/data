package com.lnu.leecode;

import javax.swing.tree.TreeNode;

public class MyTreeNode {
    int val;
    MyTreeNode left;
    MyTreeNode right;
    void MyTreeNode() {}
    void MyTreeNode(int val) { this.val = val; }
    void MyTreeNode(int val, MyTreeNode left, MyTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
