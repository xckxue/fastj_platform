package com.wow.test.thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by wow on 2018/4/8.
 */
public class BlockStorage {
    private final int MAX_SIZE = 10;
    private ArrayBlockingQueue<Object> list = new ArrayBlockingQueue<Object>(MAX_SIZE);

    public void produce(String name) throws InterruptedException {
        if(list.size() == MAX_SIZE){
            System.out.println("仓库已满，【" + name + "】： 暂时不能执行生产任务!");
        }
        list.put(new Object());

        System.out.println("【" + name + "】：生产了一个产品\t【现仓储量为】:" + list.size());
    }

    public void consume(String name) throws InterruptedException {
        if(list.size() == 0){
            System.out.println("仓库空，【" + name + "】： 暂时不能执行消费任务!");
        }
        list.take();

        System.out.println("【" + name + "】：消费了一个产品\t【现仓储量为】:" + list.size());
    }

}
