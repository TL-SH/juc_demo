package com.tl.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner{ //资源类

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try {
            lock.lock();
            // 1.判断
            while (number!=0){
                condition.await();
            }
            // 2.干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3.业务
            condition.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public  void decrement() throws Exception {
        lock.lock();
        try {
            lock.lock();
            // 1.判断
            while (number == 0){
                condition.await();
            }
            // 2.干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3.业务
            condition.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //传统方法
    /*public synchronized void increment() throws Exception{
        //1.判断
        while (number!=0){
            this.wait();
        }
        //2干活
        number ++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3.通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception {
        //1.判断
        while (number==0){
            this.wait();
        }
        //2干活
        number --;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3.通知
        this.notifyAll();
    }*/
}


/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。
 *
 *
 *
 * 1    高聚低合前提下，线程操作资源类
 * 2    判断/干活/通知
 * 3    小心,防止线程的虚假唤醒用while而不是if
 * 4    注意判断标志位的更新
 *
 *
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();

        new Thread(()->{
            for (int i = 1; i <11 ; i++) {
                try {
                    Thread.sleep(200);
                    airConditioner.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <11 ; i++) {
                try {
                    Thread.sleep(300);
                    airConditioner.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"B").start();

        new Thread(()->{
            for (int i = 1; i <11 ; i++) {
                try {
                    Thread.sleep(400);
                    airConditioner.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();


        new Thread(()->{
            for (int i = 1; i <11 ; i++) {
                try {
                    Thread.sleep(500);
                    airConditioner.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"D").start();


    }
}
