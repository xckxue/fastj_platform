package com.wow.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wow on 2018/4/8.
 */
public class OneByOne {

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private String firstChar = "A";

    private int count = 3;

    class A implements Runnable{
        public void run(){
            try{
                lock.lock();
                for(int i = 0 ; i < count; i++){
                    while(!Thread.currentThread().getName().equals(firstChar)){
                        conditionA.await();
                    }
                    System.out.println("A");
                    firstChar = "B";
                    conditionB.signal();
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }

    class B implements Runnable{
        public void run(){
            try{
                lock.lock();
                for(int i = 0 ; i < count; i++){
                    while(!Thread.currentThread().getName().equals(firstChar)){
                        conditionB.await();
                    }
                    System.out.println("B");
                    firstChar = "A";
                    conditionA.signal();
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args){
        OneByOne obo = new OneByOne();
        Thread a = new Thread(obo.new A());
        Thread b = new Thread(obo.new B());
        a.setName("A");
        b.setName("B");
        a.start();
        b.start();
    }
}
