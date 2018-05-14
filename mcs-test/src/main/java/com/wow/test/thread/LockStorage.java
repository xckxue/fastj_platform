package com.wow.test.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wow on 2018/4/8.
 */
public class LockStorage {
    private Lock lock = new ReentrantLock();
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();

    private final int MAX_SIZE = 10;

    private LinkedList<Object> list = new LinkedList<>();

    public void produce(String name) {
        try {
            lock.lock();
            while (list.size() == MAX_SIZE) {
                fullCondition.await();
                System.out.println("仓库已满，" + name + "暂停生产");
            }
            list.add(new Object());
            System.out.println(name + "生产一个，当前仓库容量" + list.size());
            emptyCondition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume(String name) {
        try {
            lock.lock();
            while (list.size() == 0) {
                emptyCondition.await();
                System.out.println("仓库以空，" + name + "暂停消费");
            }
            list.remove();
            System.out.println(name + "消费一个，当前仓库容量" + list.size());
            fullCondition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
