package com.example.demo.one.test9;

import java.util.ArrayList;
import java.util.List;


public class Main1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        while(true){
//            保证元素数量最后持续
            if(list.size()>100)
                list.remove(0);
            list.add(new String(new char[1024]));
        }
    }
}
