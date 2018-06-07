package com.wow.test.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

public class BoundedExecutor {
    private final Executor exec;
    private final Semaphore semaphore;
      
    public BoundedExecutor(Executor exec,int bound){  
        this.exec = exec;  
        this.semaphore = new Semaphore(bound);  
    }  
      
    public void submitTask(Command command) throws InterruptedException{
        try{  
            semaphore.acquire();  
            exec.execute(new Runnable(){
                @Override
                public void run() {
                    try{
                        command.dothing();
                    }finally{
                        System.out.println("执行完成 ，release...");
                        semaphore.release();
                    }
                }
            });
        }catch(RejectedExecutionException e){
            System.out.println("队列已满，拒绝执行");  
            semaphore.release();
        }
    }  
      
    public static void main(String[] args) {  
        //虽然线程池大小为4，但是Semaphore限制每次只能有两个任务被执行  
        Executor exec = Executors.newCachedThreadPool();
        BoundedExecutor b = new BoundedExecutor(exec,2);

        Command c1 = new Command("c1");
        Command c2 = new Command("c2");
        Command c3 = new Command("c3");
        Command c4 = new Command("c4");
        Command c5 = new Command("c5");
        try {  
            b.submitTask(c1);  
            b.submitTask(c2);  
            b.submitTask(c3);  
            b.submitTask(c4);  
            b.submitTask(c5);  
        } catch (InterruptedException execption) {  
            execption.printStackTrace();  
        }  
    }



    /*
    public static void main(String[] args) {
        //线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //定义信号量，只能5个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        //模拟20个线程同时访问
        for (int i = 0; i < 20; i++) {
             final int NO = i;
             Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        //获取许可
                        semaphore.acquire();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                        System.out.println("线程" + Thread.currentThread().getName() +"进入，当前已有" + (5-semaphore.availablePermits()) + "个并发");
                        System.out.println("index:"+NO);
                        Thread.sleep(new Random().nextInt(1000)*10);

                        System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
                        //访问完后，释放
                        semaphore.release();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            executor.execute(runnable);
        }
        // 退出线程池
        executor.shutdown();
    }
     */
}  