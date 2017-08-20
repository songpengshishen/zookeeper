package peng;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.Watcher;

/**
 * 这里记录有关学习过程中关于zookeeper的概念
 * zookeeper:zookeeper是一个高可用,高性能,具有写操作的严格顺序性的分布式一致性和协调通知框架.
 * zookeeper的设计目标:zookeeper将复杂且容易出错的分布式系统中的一致性操作与协调操作封装起来,构成一个高效
 *                   可靠的服务,并提供给客户端简单,易用的接口供编程人员使用
 * 为什么使用zookeeper:zookeeper拥有很强的一致性和协调功能,并且zookeeper本身也是高可用,高性能(吞吐量大),
 *                   的分布式系统.zookeeper内部有很多功能特性,方便我们实现分布式系统中很多必须的
 *                   核心模块如数据发布/订阅,的配置中心,master选举,分布式协调与通知,集群管理,
 *                   分布式锁等,cas乐观锁等.最关键的zookeeper是唯一一个开源且被大规模用在分布式系统中的一致性
 *                   协调框架,所以说zookeeper是分布式系统的开发利器.
 *zookeeper是分布式系统的开发利器,和其他分布式系统框架redis,hbase等一样,它们都是在分布式系统中实现某个核心模块
 * 或者说是在分布式系统的某些场景下实现某种功能如master选举.并不能单独构成分布式系统.
 */
public class ZookeeperDoc {

    /*
     * watcher(zookeeper事件监听器/观察者):用来监听(观察)zookeeper服务器集群,并接受
     * zookeeper服务器向客户端发送的任何事件类型的通知.
     * 注意:使用watcher必须在执行zookeeper操作时显示的注册,并且服务的发送一次事件通知后,watche就失效了必须反复注册
     * 服务端的通知通常不会包含变更内容,需要重新去拉取.
     * */
    public Watcher  watcher;

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
