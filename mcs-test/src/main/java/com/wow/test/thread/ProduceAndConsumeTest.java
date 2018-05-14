package com.wow.test.thread;

/**
 * Created by wow on 2018/4/8.
 */
public class ProduceAndConsumeTest {
    class Producer implements Runnable {
        private String name;
        private BlockStorage storage;

        public Producer(String name, BlockStorage storage) {
            this.name = name;
            this.storage = storage;
        }

        public void run() {
            while (true) {
                try {
                    storage.produce(name);
                } catch (InterruptedException e) {


                }
            }
        }
    }

    class Consumer implements Runnable {
        private String name;
        private BlockStorage storage;

        public Consumer(String name, BlockStorage storage) {
            this.name = name;
            this.storage = storage;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    storage.consume(name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceAndConsumeTest pac = new ProduceAndConsumeTest();
        BlockStorage storage = new BlockStorage();
        Thread p1 = new Thread(pac.new Producer("producer1", storage));

        Thread c1 = new Thread(pac.new Consumer("consumer1", storage));
        Thread c2 = new Thread(pac.new Consumer("consumer2", storage));

        p1.start();
        c1.start();
        c2.start();
        //c3.start();
    }
}
