package com.lnu;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MaxQueueImpl implements MaxQueue{
    Queue<Integer> queue;//队列，用于入队、出队操作
    Deque<Integer> helper;//辅助队列，用于获取最大值

    public MaxQueueImpl() {
        queue = new LinkedList<Integer>();
        helper = new LinkedList<Integer>();
    }

    @Override
    public void add(int v) {
        queue.offer(v);
        //将队尾比插入值还小的值移出辅助队列
        while(!helper.isEmpty() && v > helper.peekLast()){
            helper.pollLast();
        }
        //辅助队列入队
        helper.offerLast(v);
    }

    @Override
    public int poll() {
        if(queue.isEmpty())
            return -1;
        Integer ans = queue.poll();
        //如果出队的元素是辅助队列的队头元素，辅助队列也要移出队头元素
        if(ans.equals(helper.peekFirst()))
            helper.pollFirst();
        return ans;
    }


    @Override
    public int pollMax() {
        if(queue.isEmpty())
            return -1;
        return helper.peekFirst();
    }
}
