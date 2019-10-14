package com.tl.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author tanglei
 *
 * 倒计时锁
 *     不断的等,等待所有线程离开就让主线程去执行
 *         await()   不见不散
 *     给一定的时间让前面的线程去执行,时间一到就执行
 *         await(1,TimeUnit.seconds)  过时不候
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <=10 ; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                //不断的去计数,到0为止
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        //countDownLatch.await(2, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName()+"\t 关门离开");

    }


}
