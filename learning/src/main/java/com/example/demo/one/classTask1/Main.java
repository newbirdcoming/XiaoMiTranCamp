package com.example.demo.one.classTask1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    /**
     * 筛选字符串中长度大于5且包含字符“a"的字符串并返回
     * 使用Lambda表达式和predicate函数式接口
     */
    static List<String> func1(List<String> str){
        Predicate<String> lengthGhFivePredicate= s->s.length()>5;
        Predicate<String> containsAPredicate= s->s.length()>5;
        Predicate<String> combinedPredicate = lengthGhFivePredicate.and(containsAPredicate);
        List<String> res=str.stream().filter(combinedPredicate).collect(Collectors.toList());
        return res;
    }

    /**
     * ConSummer函数式接口实现 对列表平方并打印
     * @param ints
     */
    static void func2(List<Integer> ints){
        Consumer<Integer> printer= i-> System.out.println(i);
        ints.replaceAll(i->i*i);
        ints.forEach(p->{
//            p=p*p;
            printer.accept(p);
        });

    }


    /**
     *筛选分数大于80 并按分数从高到低排序
     */

    static List<Student> func3(List<Student> students){
        return students.stream().filter(p->p.getGrades()>=80).sorted((p2,p1)->Integer.compare(p1.getGrades(),p2.getGrades())).collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<String> strs= Arrays.asList("asadasd","asdda","adsaadsa");
//        1.打印结果1
        func1(strs).forEach(System.out::println);
        List<Integer> ints=Arrays.asList(1,2,3,4,5,6);
        func2(ints);
//        2.存在问题没有修改列表的值?????
        ints.forEach(System.out::println);
//       3.1创建学生列表
        List<Student> students=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            students.add(new Student("张三" + i, 60 + i));
        }
        List<Student> res1=func3(students);
        for (Student student : res1) {
            System.out.println(student.getName());
            System.out.println(student.getGrades());
        }

    }



//    定义内部类



}
