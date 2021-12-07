package com.lnu.structure.Tree;

public class BinaryNode<T> {
    public BinaryNode<T> left;
    public BinaryNode<T> right;
    public T data;

    public BinaryNode(BinaryNode<T> left, BinaryNode<T> right, T data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BinaryNode() {
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 判断是否为叶子结点
     */
    public boolean isLeaf(){
        return this.left==null&&this.right==null;
    }
}
