package com.tl.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author tanglei
 *
 *    阻塞队列
 *
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        //数组结构
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        //链表结构
        //BlockingQueue blockingQueue1 = new LinkedBlockingQueue();

        //BlockingQueue blockingQueue2 = new SynchronousQueue();

        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        //blockingQueue.put("a");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());



        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.peek());


        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/


        //增加元素
        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));*/
        //System.out.println(blockingQueue.add("a"));

        //获取元素
        //System.out.println(blockingQueue.element());

        //删除元素
        /*System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/









    }
}
