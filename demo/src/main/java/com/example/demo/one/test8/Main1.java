package com.example.demo.one.test8;


/**
 * 栈溢出
 */
public class Main1 {
    public static void main(String[] args) {
        recursiveMethod();
    }

    private static void recursiveMethod() {
        // 无限递归，没有终止条件
        recursiveMethod();
    }
}