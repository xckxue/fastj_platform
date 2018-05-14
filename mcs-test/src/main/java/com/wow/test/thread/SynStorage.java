package com.wow.test.thread;

import java.util.LinkedList;

/**
 * Created by wow on 2018/3/15.
 */
public class SynStorage {

    private final int MAX_SIZE = 100;
    private LinkedList<Object> list = new LinkedList<>();

    private synchronized void produce(String producer) {
        while (list.size() >= MAX_SIZE) {
            System.out.println("仓库已满，【" + producer + "】： 暂时不能执行生产任务!");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(new Object());
        this.notifyAll();
    }

    private synchronized void consume(String consumer) {
        while (list.size() == 0) {
            System.out.println("仓库已空，【" + consumer + "】： 暂时不能执行消费任务!");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.remove();
        this.notifyAll();
    }

    public class Producer extends Thread {
        private String producer;
        private SynStorage storage;

        public Producer(SynStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            while (true) {
                produce(producer);
            }
        }

        public void produce(String producer) {
            storage.produce(producer);
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public SynStorage getStorage() {
            return storage;
        }

        public void setStorage(SynStorage storage) {
            this.storage = storage;
        }
    }

    public class Consumer extends Thread {
        private String consumer;
        private SynStorage storage;

        public Consumer(SynStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            while (true) {
                consume(consumer);
            }
        }

        public void consume(String consumer) {
            storage.consume(consumer);
        }

        public SynStorage getStorage() {
            return storage;
        }

        public void setStorage(SynStorage storage) {
            this.storage = storage;
        }

        public String getConsumer() {
            return consumer;
        }

        public void setConsumer(String consumer) {
            this.consumer = consumer;
        }
    }
}
