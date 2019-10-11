package com.tl.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author tanglei
 */
public class NotSafeDemo {

    public static void main(String[] args) {

        //ArrayList  线程不安全  多线程并发肯定出问题
        //List<String> list = new ArrayList<>();

        //第一种方法可以解决多线程并发的问题  太low了
        //List<String> list = new Vector<>();


        //第二种方法可以解决多线程并发的问题  比较low
        //List<String> list = Collections.synchronizedList(new ArrayList<>());

        //第三种方法解决多线程并发的问题
        List<String> list = new CopyOnWriteArrayList<>();


        /*List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::println);*/

        /**
         * java.util.ConcurrentModificationException
         */

        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }


}
