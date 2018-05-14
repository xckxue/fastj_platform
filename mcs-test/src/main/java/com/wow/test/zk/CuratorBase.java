package com.wow.test.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * Created by wow on 2018/2/1.
 */
public class CuratorBase {
    /**
     * zookeeper地址
     */
    static final String CONNECT_ADDR = "192.168.11.10:2181,192.168.11.10:2182,192.168.11.10:2183";
    /**
     * session超时时间
     */
    static final int SESSION_OUTTIME = 5000;//ms

    public static void main(String[] args) throws Exception {
        //1 重试策略：初试时间为1s 重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

        //2 通过工厂创建连接,线程安全，一个集群一个curatorFramework即可
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_OUTTIME)
                .retryPolicy(retryPolicy)
                .build();

        //3 开启连接
        cf.start();

        //4 建立节点 指定节点类型（不加withMode默认为持久类型节点）、路径、数据内容
        String path = cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/mcs/c2","测试1".getBytes());

        //5 删除节点
        //cf.delete().guaranteed().deletingChildrenIfNeeded().forPath("/mcs");

        /*读取节点*/
        String ret1 = new String(cf.getData().forPath("/mcs/c2"));
        System.out.println(ret1);

        /*修改节点*/
        cf.setData().forPath("/mcs/c2", "修改c2内容".getBytes());
        String ret2 = new String(cf.getData().forPath("/mcs/c2"));
        System.out.println(ret2);


        List<String> list = cf.getChildren().forPath("/");
        System.out.println(list);
    }
}
