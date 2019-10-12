package com.tl.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class ShareResource{
    private int flag = 1; // A 1 ,B 2, C 3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    /*
      Condition condition = new lock.newCondition();  可以做到精准打击
     */
    public void print5(){
        lock.lock();
        try {
            //判断
            while (flag !=1){
                c1.await();
            }
            //干活
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i);
            }
            flag = 2;
            //通知
            c2.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void print10(){
        lock.lock();
        try {
            //判断
            while (flag !=2){
                c2.await();
            }
            //干活
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i);
            }
            flag = 3;
            //通知
            c3.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            //判断
            while (flag !=3){
                c3.await();
            }
            //干活
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i);
            }
            flag = 1;
            //通知
            c1.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

/**
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 *
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮
 *
 * 1    高聚低合前提下，线程操作资源类
 * 2    判断/干活/通知
 * 3    多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 * 4    注意判断标志位的更新
 */
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) throws Exception {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareResource.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareResource.print10();
            }
        },"B").start();


        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareResource.print15();
            }
        },"C").start();
    }

}
