package com.tl.juc;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{

    private volatile Map<String,String> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key,String value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 写的开始" );
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写的结束" );
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key){

        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 读的开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读的结束 \t" + result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }

    }
}


/**
 * @author tanglei
 *
 * 使用缓存来模拟 ReentrantReadWrireLock
 *
 * 写的时候 加锁
 *
 * 读的时候的 高并发
 *
 */
public class ReadWriteLock {

    public static void main(String[] args){

        MyCache myCache = new MyCache();

        for (int i = 1; i <=10 ; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.put(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10 ; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.get(finalI+"");
            },String.valueOf(i)).start();
        }

    }
}
