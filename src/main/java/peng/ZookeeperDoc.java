package peng;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.Watcher;

/**
 * 这里记录有关学习过程中关于zookeeper的概念
 */
public class ZookeeperDoc {

    /*
     * watcher(zookeeper事件监听器/观察者):用来监听(观察)zookeeper服务器集群,并接受
     * zookeeper服务器向客户端发送的任何事件类型的通知.
     * 注意:使用watcher必须在执行zookeeper操作时显示的注册,并且服务的发送一次事件通知后,watche就失效了必须反复注册
     * 服务端的通知通常不会包含变更内容,需要重新去拉取.
     * */
    private Watcher  watcher;

    /***
     *  AsyncCallback(异步回调接口):提供了processResult方法来处理以异步方式执行增,删,改,查等操作的结果.
     *  这个接口有很多内部接口,都是异步回调接口只是回调类型不一致.这些接口的目的是为了支持zookeeper以
     *  异步方式执行增,删,改,查时获取执行结果并处理.
     */
    private AsyncCallback asyncCallback;


    private void setVal()throws Exception{
        ZookeeperClient zookeeperClient = new ZookeeperClient();
        /**
         * zookeeper的Set方法是典型的CAS
         * 版本号参数类似于期望值拿它和指定的ZNode当前版本号对比如果一致则进行更新否则不更新.
         * 利用set方法可以实现分布式锁.(有个前提条件更新时即便值没变,版本号也会变)
         */
        zookeeperClient.getZooKeeper().setData("","".getBytes(),1);
    }
}
