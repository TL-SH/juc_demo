package com.tl.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t come in on");
        return "hello word";
    }
}
/**
 * @author tanglei
 *
 * 线程获取的第一种方法
 *     继承Thread 类
 * 线程获取的第二种方法
 *      Runable
 *         实现Runable 接口
 *              实现 run 方法
 *
 * 线程获取的第三种方法
 *      Callable
 *          实现Calllable接口
 *             实现call()方法
 *
 * 第二种 与 第三种方法的区别
 *      方法名不一样   是否有返回值    是否抛异常
 *
 *
 *
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask(new MyThread());
        new Thread(futureTask,"AAA").start();
        System.out.println(futureTask.get());


    }
}
