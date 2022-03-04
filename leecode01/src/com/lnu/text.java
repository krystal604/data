package com.lnu;

public class text {
    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueueImpl();
        maxQueue.add(3);
        maxQueue.add(4);
        maxQueue.add(1);
        maxQueue.add(2);
        System.out.println(maxQueue.pollMax());
        System.out.println(maxQueue.poll());
        //测试用例中先poll了一个3 所以队列中的max仍然应该是4 所以要再poll一次才能达到测试用例中的效果
        maxQueue.poll();
        System.out.println(maxQueue.pollMax());
        System.out.println(maxQueue.poll());
    }
}
