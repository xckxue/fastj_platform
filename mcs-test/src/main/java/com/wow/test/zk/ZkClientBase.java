package com.wow.test.zk;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * Created by wow on 2018/2/1.
 */
public class ZkClientBase {
    /**
     * zookeeper地址
     */
    static final String CONNECT_ADDR = "192.168.11.10:2181,192.168.11.10:2182,192.168.11.10:2183";
    /**
     * session超时时间
     */
    static final int SESSION_OUTTIME = 5000;//ms

    public static void main(String[] args) {
        /**
         * 创建会话
         * new SerializableSerializer() 创建序列化器接口，用来序列化和反序列化
         */
        ZkClient zkClient = new ZkClient(CONNECT_ADDR,10000,10000,new SerializableSerializer());

        System.out.println("conneted ok!");
    }
}
