package com.lnu.newcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class bytedance02 {


    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int money=input.nextInt();
        money=1024-money;
        int sum=0;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(1,64);
        map.put(2,16);
        map.put(3,4);
        map.put(4,1);
        int i=1;
        while (true){
            int temp = money;
            if (money<map.get(i)){
                i++;
                continue;
            }
            sum+=money/map.get(i);

            money=temp%map.get(i);
            i++;
            if (money==0 ){
                break;
            }
            if (i>4){
                break;
            }
        }
        System.out.println(sum);
    }
}
