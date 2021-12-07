package com.lnu.structure.Queue.Element;

import com.lnu.structure.Queue.QueueByArray;

/**
  * @Description: todo 多项式加法乘法
  * @Date: 2021/12/4 20:32
  * @Version: 1.0
  **/
public class element {

    public static void addElement(){
        QueueByArray<ElementNode> p1 = new QueueByArray<>();
        QueueByArray<ElementNode> p2 = new QueueByArray<>();

        QueueByArray<ElementNode> p3 = new QueueByArray<>();
        //测试添加数据
        for (int i=4 ;i>=0  ;i--){
            p1.offer(new ElementNode(2*i,i));
            if (i%2==0)
                p2.offer(new ElementNode(i,i));
        }

        //当p1p2 都不为空时
        while (!p1.isEmpty() && !p2.isEmpty()){
            //如果p1系数大于p2 或p2为空时
            if (p1.peek().getExponential()>p2.peek().getExponential() || p2.isEmpty()){
                p3.offer(p1.peek());
                p1.remove();
                //如果p2系数大于p1 或p2为空时
            }else if (p1.peek().getExponential()<p2.peek().getExponential() || p1.isEmpty()){
                p3.offer(p2.peek());
                p2.remove();
                //如果指数相等
            }else if (p1.peek().getExponential()==p2.peek().getExponential()) {
                //在p3中加入 p1p2系数相加，p1p2的指数
                p3.offer(new ElementNode(p1.peek().getCoefficients() + p2.peek().getCoefficients(), p1.peek().getExponential()));
                //抛出p1p2 remove 加强的poll 能抛出异常
                p1.remove();
                p2.remove();

            }

        }

        while (!p3.isEmpty()){
            System.out.println(p3.poll().toString());
        }
    }

    public static void multiplicationElement(){
        QueueByArray<ElementNode> p1 = new QueueByArray<>();
        QueueByArray<ElementNode> p2 = new QueueByArray<>();

        QueueByArray<ElementNode> p3 = new QueueByArray<>();
        //测试添加数据
        for (int i=8 ;i>=0  ;i--){
            p1.offer(new ElementNode(2*i,i));
            if (i%2==0)
                p2.offer(new ElementNode(i,i));
        }

        QueueByArray<ElementNode> temp1 = new QueueByArray<>();
        QueueByArray<ElementNode> temp2 = new QueueByArray<>();
        temp1=p2;
        int i=0;
        QueueByArray<ElementNode> tempResult;
        while (!p1.isEmpty()){
            //使用两个队列循环取值 一个取一个存
            if (i%2==0 ){
                while (!temp1.isEmpty()){
                    p3.add(new ElementNode(p1.peek().getCoefficients()*temp1.peek().getCoefficients(),
                            p1.peek().getExponential()+temp1.peek().getExponential()));
                    temp2.add(temp1.poll());
                }
            }else if(i%2==1){
                while (!temp2.isEmpty()){
                    p3.add(new ElementNode(p1.peek().getCoefficients()*temp2.peek().getCoefficients(),
                            p1.peek().getExponential()+temp2.peek().getExponential()));
                    temp1.add(temp2.poll());
                }
            }
            p1.remove();
            i++;
        }


        while (!p3.isEmpty()){
            System.out.println(p3.poll().toString());
        }

    }


    public static void main(String[] args) {
//        addElement();
        multiplicationElement();
    }
}
