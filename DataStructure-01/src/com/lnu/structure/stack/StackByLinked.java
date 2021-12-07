package com.lnu.structure.stack;

import java.io.Serializable;
/**
  * @Description: todo 链表实现栈
  * 通过 下一个元素指向上一个的方式构建链表 头在链表最尾端
  * @Date: 2021/12/4 15:38
  * @Version: 1.0
  **/
public class StackByLinked<T> implements Stack<T>, Serializable {

    private static final long serialVersionUID= 2L;
    //栈顶指针
    private Node<T> top;



    private int size;

    public StackByLinked() {
        this.top = new Node<>();

    }

    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top==null || top.getData()==null;
    }

    @Override
    public void push(T data) {
        if (data==null){
            throw new RuntimeException("data can't be null");
        }
        if(this.top==null){//调用pop()后top可能为null
            this.top=new Node<>(data);
        }else if(this.top.getData()==null){
            this.top.setData(data);
        }else {
            top= new Node<>(data, this.top);//更新栈顶
        }

        size++;
    }

    @Override
    public T peek() {
        if (isEmpty()){
            throw  new RuntimeException("null");
        }
        return top.getData();
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new RuntimeException("Stack empty");
        }

        T data=top.getData();
        top=top.getNext();
        size--;
        return data;

    }

//    @Override
//    public T pop() {
//
//        if(isEmpty()){
//            throw new RuntimeException("Stack empty");
//        }
//        //拿到栈顶元素data并在最后返回
//        T data = top.getData();
//        //获取栈底
//        Node<T> p =new Node<>();
//        p=base;
//        // 循环栈 找到data上一个元素
//        while (p.getNext()!=data){
//            p.setData((T) p.getNext());
//        }
//        //更新栈顶 size--
//        top=p;
//        size--;
//        return data;
//
//    }

    public static void main(String[] args){
        StackByLinked<String> sl=new StackByLinked<>();
        sl.push("A");
        sl.push("B");
        sl.push("C");
        int length=sl.size();
        for (int i = 0; i < length; i++) {
            System.out.println("sl.pop->"+sl.pop());
        }
    }

}
