package com.lnu.structure.stack;

import java.io.Serializable;
import java.util.EmptyStackException;

public class StackImpl<T> implements Stack<T>, Serializable {

    private static final long serialVersionUID= 1L;
    /**
     * 栈顶指针,-1代表空栈
     */
    private int top=-1;

    /**
     * 容量大小默认为10
     */
    private int capacity=10;

    /**
     * 存放元素的数组
     */
    private T[] array;

    private int size;


    public StackImpl(int capacity){
        array = (T[]) new Object[capacity];
    }

    public StackImpl(){
        array= (T[]) new Object[this.capacity];
    }

    public  int size(){
        return size;
    }


    @Override
    public boolean isEmpty() {
        return top==-1;
    }

    /**
      * @Description: todo 扩容方法
      * @Date: 2021/12/3 23:09
      * @Param capacity:
      * @return: void
      * @Version: 1.0
      **/
    public void ensureCapacity(int capacity){
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (capacity<size){
            return;
        }
        T[] old = array;
        array = (T[]) new Object[capacity];
        //复制元素
        if (size >= 0) System.arraycopy(old, 0, array, 0, size);


    }

    /**
      * @Description: todo
      * @Date: 2021/12/3 23:08
      * @Param data:
      * @return: void
      * @Version: 1.0
      **/
    @Override
    public void push(T data) {
        //判断容量是否充足
        if(array.length==size)
            ensureCapacity(size*2+1);//扩容

        //从栈顶添加元素
        array[++top]=data;

        size++;
    }


    /**
     * 获取栈顶元素的值,不删除
     */
    @Override
    public T peek() {
        if(isEmpty()){
            new EmptyStackException();
        }
        return array[top];
    }


    /**
     * 从栈顶(顺序表尾部)删除
     */
    @Override
    public T pop() {
        if(isEmpty()){
            new EmptyStackException();
        }
        size--;
        return array[top--];
    }
    public static void main(String[] args){
        StackImpl<String> s=new StackImpl<>();
        s.push("A");
        s.push("B");
        s.push("C");
        System.out.println("size->"+s.size());
        int l=s.size();//size 在减少,必须先记录
        for (int i=0;i<l;i++){
            System.out.println("s.pop->"+s.pop());
        }

        System.out.println("s.peek->"+s.peek());
    }

}
