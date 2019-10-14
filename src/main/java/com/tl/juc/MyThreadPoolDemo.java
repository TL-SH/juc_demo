package com.tl.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author tanglei
 *
 * ThreadPoolExecutor 线程池
 *        第一参数:   corePoolSize: 线程池中的常驻黑心线程数
 *        第二参数:   maximumPoolSize: 线程池中能够容纳同时执行的最大线程数,此值必须大于等于1
 *        第三参数:   keepAliceTime: 多余的空闲线程的存活时间当前池中线程数量超过corePoolSize
 *        第四参数:   until keepAliveTime的单位
 *        第五参数:   workQueuea 阻塞队列,别提交尚未被执行的任务
 *        第六参数:   threadFactory 表示生成线程池中工作线程的线程工厂,用于创建线程,一般默认即可
 *        第七参数:   handler  拒绝策略,表示当队列满了,并且工作线程大于等于线程池的最大线程数时,如何拒绝请求执行的runnable的策略'
 *
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        //手写线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                2l,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(3),Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i <=8 ; i++) {
                threadPoolExecutor.submit(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务 "+new Random().nextInt(10));
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            threadPoolExecutor.shutdown();
        }

        //一池5线程
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //单个线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //一池n个线程
        //ExecutorService executorService = Executors.newCachedThreadPool();


        /*try {
            for (int i = 1; i <=20 ; i++) {
                executorService.submit(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务 "+new Random().nextInt(10));
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            executorService.shutdown();
        }*/

    }

}
