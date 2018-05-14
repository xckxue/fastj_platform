package com.wow.test.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

/**
 * Created by wow on 2018/2/1.
 */
public class CuratorDistrLock {
    /**
     * zookeeper地址
     */
    static final String CONNECT_ADDR = "192.168.11.10:2181,192.168.11.10:2182,192.168.11.10:2183";
    /**
     * session超时时间
     */
    static final int SESSION_OUTTIME = 5000;//ms

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_ADDR, new RetryNTimes(10, 1000));
        client.start();

        Thread t1 = new Thread(() -> {
            dolock(client);
        }, "t1");

        Thread t2 = new Thread(() -> {
            dolock(client);
        }, "t2");

        t1.start();
        t2.start();
    }

    public static void dolock(CuratorFramework client) {
        InterProcessMutex lock = new InterProcessMutex(client, "/mcs");
        try {
            if (lock.acquire(10 * 1000, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " hold lock");
                Thread.sleep(5000L);
                System.out.println(Thread.currentThread().getName() + " release lock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
