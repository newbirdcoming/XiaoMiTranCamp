package com.example.demo.one.classwork;

import java.util.Random;

public class Test {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int temp=random.nextInt(100);
            System.out.println(temp);
        }

    }
}
