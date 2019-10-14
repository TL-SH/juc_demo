package com.tl.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author tanglei
 *
 * 循坏屏障
 *      不断的去增,一直到所有线程执行完之后才 执行main主线程
 *
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("及其7颗龙珠,唤醒神龙");
        });

        for (int i = 1; i <=7 ; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 收集到第: "+ finalI);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }

}
