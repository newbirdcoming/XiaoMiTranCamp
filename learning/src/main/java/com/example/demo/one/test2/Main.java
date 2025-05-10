package com.example.demo.one.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(100);
        list.add(1001);
        list.add(1002);
        list.add(1003);
        list.add(100);
        int[] nums=new int[]{1,2,3,42,4,232,32,3,231,23,12,3,21,3};
        long sum=Arrays.stream(nums).sum();
        System.out.println("sun:"+sum);

        long sum1=Arrays.stream(nums).parallel().sum();
        System.out.println("sum:"+sum1);


    }


}
