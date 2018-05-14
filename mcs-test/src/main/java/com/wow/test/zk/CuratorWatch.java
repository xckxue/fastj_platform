package com.wow.test.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

/**
 * Created by wow on 2018/2/1.
 */
public class CuratorWatch {
    /**
     * zookeeper地址
     */
    static final String CONNECT_ADDR = "192.168.11.10:2181,192.168.11.10:2182,192.168.11.10:2183";
    /**
     * session超时时间
     */
    static final int SESSION_OUTTIME = 5000;//ms

    CuratorFramework cf = null;

    @Before
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(100, 10);
        cf = CuratorFrameworkFactory
                .builder()
                .sessionTimeoutMs(SESSION_OUTTIME)
                .connectString(CONNECT_ADDR)
                .retryPolicy(retryPolicy)
                .build();
        cf.start();
        System.out.println("zk client start successfully!");
    }


    /*
     Curator提供了三种Watcher(Cache)来监听结点的变化：
     Path Cache：监视一个路径下1）孩子结点的创建、2）删除，3）以及结点数据的更新。产生的事件会传递给注册的PathChildrenCacheListener。
     Node Cache：监视一个结点的创建、更新、删除，并将结点的数据缓存在本地。
     Tree Cache：Path Cache和Node Cache的“合体”，监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。
     */
    @Test
    public void testNode() throws Exception {
        PathChildrenCache cache = new PathChildrenCache(cf, "/mcs", false);
        //5 在初始化的时候就进行缓存监听
     //   cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework cf, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADDED :" + event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATED :" + event.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED :" + event.getData().getPath());
                        break;
                    default:
                        break;
                }
            }
        });
        cache.start(StartMode.NORMAL);
        System.out.println("Register zk watcher successfully!");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
