package com.lnu.structure.Queue;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class QueueByArray<T> implements Serializable,Queue<T> {

    private static final long serialVersionUID = -1664818681270068094L;
    private static final int  DEFAULT_SIZE = 10;

    private T elementData[];

    private int front,rear;

    private int size;


    public QueueByArray() {
        elementData= (T[]) new Object[DEFAULT_SIZE];
        front=rear=0;
    }
    public QueueByArray(int capacity) {
        elementData= (T[]) new Object[capacity];
        front=rear=0;
    }

    public T[] getElementData() {
        return elementData;
    }

    public void setElementData(T[] elementData) {
        this.elementData = elementData;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front==rear;
    }

    /**
      * @Description: todo 入队 成功返回true 失败返回false 可扩容
      * @Date: 2021/12/4 16:18
      * @Param data:
      * @return: boolean
      * @Version: 1.0
      **/
    @Override
    public boolean add(T data) {
        //判断是否满队
        if (this.front==(this.rear+1)%this.elementData.length){
            ensureCapacity(elementData.length*2+1);
        }
        //添加data
        elementData[this.rear]=data;
        //更新rear指向下一个空元素的位置
        this.rear=(this.rear+1)%elementData.length;
        size++;
        return true;
    }

    /**
      * @Description: todo 添加元素 不进行扩容 如果data为空或队满抛出异常
      * @Date: 2021/12/4 17:58
      * @Param data:
      * @return: boolean
      * @Version: 1.0
      **/

    @Override
    public boolean offer(T data) {
        if (data==null){
            throw new NullPointerException("data is null");
        }
        //队满抛出异常
        if (this.front==(this.rear+1)%this.elementData.length){
            throw new IllegalArgumentException("The capacity of Queue has reached its maximum");
        }
        //添加data
        elementData[this.rear]=data;
        //更新rear指向下一个空元素的位置
        this.rear=(this.rear+1)%elementData.length;
        size++;

        return true;

    }

    /**
      * @Description: todo 返回队头元素,不执行删除操作,若队列为空,返回null
      * @Date: 2021/12/4 18:02
      * @return: T
      * @Version: 1.0
      **/
    @Override
    public T peek() {
        return elementData[this.front];
    }

    /**
      * @Description: todo 返回队头元素,不执行删除操作,若队列为空,抛出异常:NoSuchElementException
      * @Date: 2021/12/4 18:03
      * @return: T
      * @Version: 1.0
      **/

    @Override
    public T element() {
        if (isEmpty()){
            throw new NoSuchElementException("The Queue is empty");
        }
        return peek();
    }
    /**
      * @Description: todo 出队,执行删除操作,返回队头元素,若队列为空,返回null
      * @Date: 2021/12/4 18:05
      * @return: T
      * @Version: 1.0
      **/
    @Override
    public T poll() {
        T data = peek();
        //移动front
        this.front=(this.front+1)%this.elementData.length;
        size--;
        return data;
    }

    /**
      * @Description: todo 出队,执行删除操作,若队列为空,抛出异常:NoSuchElementException
      * @Date: 2021/12/4 18:08
      * @return: T
      * @Version: 1.0
      **/
    @Override
    public T remove() {
        if (isEmpty()){
            throw new NoSuchElementException("The Queue is empty");
        }
        return poll();
    }

    @Override
    public void clearQueue() {
        for (int i=this.front; i!=this.rear ; i=(i+1)%elementData.length) {
            elementData[i] = null;
        }
        //复位
        this.front=this.rear=0;
        size=0;
    }

    /**
     * 扩容的方法
     * @param capacity
     */
    public void ensureCapacity(int capacity) {
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (capacity<size)
            return;

        T[] old = elementData;
        elementData= (T[]) new Object[capacity];
        int j=0;
        //复制元素
        for (int i=this.front; i!=this.rear ; i=(i+1)%old.length) {
            elementData[j++] = old[i];
        }
        //恢复front,rear指向
        this.front=0;
        this.rear=j;
    }

}
