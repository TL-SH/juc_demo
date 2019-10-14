package com.tl.juc;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;




/**
 * @author tanglei
 * 
 * 信号灯
 *    可憎   可减   是由自己控制
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 初始化定义三个线程  模拟三个停车场
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                boolean flag = false;
                try {
                    //同意
                    semaphore.acquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    // 随机暂停 时间
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t------离开车位 ");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (flag){
                        //释放资源
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
    
}

