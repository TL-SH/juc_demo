package com.tl.juc;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{

    /*private int number = 30;
    public synchronized void sale(){
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+"  卖了第票:"+(number--)+"   还剩多少票"+number);
        }
    }*/

    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void sale(){
        try {
            lock.lock();
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"\t卖了第:"+(number--)+"\t还剩多少票"+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}

/**
 *            juc 多线程并发

 *            -- 高内聚  低耦合

 *           线程    操作     资源类
 */
public class SaleTicket {

    public static void main(String[] args) throws Exception{
        Ticket ticket = new Ticket();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=50 ; i++) {
                    ticket.sale();
                }
            }
        },"a").start();*/
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i < 40 ; i++) {
                executorService.submit(()->{
                    ticket.sale();
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }

        /*new Thread(()-> {for (int i = 1; i <=40 ; i++) ticket.sale();},"A").start();
        new Thread(()-> {for (int i = 1; i <=40 ; i++) ticket.sale();},"B").start();
        new Thread(()-> {for (int i = 1; i <=40 ; i++) ticket.sale();},"C").start();*/
    }
}
