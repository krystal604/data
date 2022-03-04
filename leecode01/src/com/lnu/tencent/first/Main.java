package com.lnu.tencent.first;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s= input.next();
        StringBuilder str = new StringBuilder(s);
        StringBuilder ans = new StringBuilder();
        Stack<String> stack = new Stack<>();
        Stack<Integer> stackNumber = new Stack<>();
        for (int i = 0 ; i < str.length() ;i++){
            if (str.charAt(i) != '['){
                ans.append(str.charAt(i));
            }else if (str.charAt(i-1) == '['){

            }
        }
    }
}
