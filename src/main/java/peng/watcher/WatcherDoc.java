package peng.watcher;

/**
 * Watcher:(zookeeper事件观察者/事件通知者)具体请看{@link peng.ZookeeperDoc#watcher}
 * Watcher的工作机制:watcher的工作机制主要分为三步:1:zookeeper客户端注册,2:zookeeper服务器存储,管理,事件触发
 * 3:zookeeper客户端的回调执行
 * watcher的特性:
 *             一次性:watcher的每次注册都是一次性的,只要服务端触发了某个节点的watcher,zookeeper服务端和客户端
 *                   就会删除这个watcher,下次再用需要重复注册.这样减少了服务端的存储压力及网络的开销.
 *             串行顺序执行:服务端对于客户端在某个节点注册的多个watcher,是串行顺序去执行的.
 *             轻量级:zookeeper的每个watcher只包含了事件类型,通知状态,节点路径,zookeeper服务端和客户端进行
 *                   watcher交互式,只通知发生了什么,具体变更内容需要根据业务自行拉取.并且客户端注册watcher时
 *                   也只是在发送请求时设置了标记并存储了一个连接,这种轻量级设计让zookeeper服务端的内存和网络
 *                   开销都很低.
 *             聚合性:zookeeper的watcher在对节点进行注册时,是一个针对所有事件的聚合,也就是说任何事件都会触发
 *                   watcher,由业务自行判断
 *   ps:zookeeper在创建watcher时有二种方式:在创建会话时创建watcher,作为默认watcher会一直存在于会话期间.
 *                                      在会话期间做操作时指定watcher一次性.
 */
public class WatcherDoc {

   /*****zookeeper工作机制三步骤******/


    /**
     * 第一步:zookeeper客户端注册
     */
    public void step1(){
         //1:request打标记,设置当前请求操作有watcher
        //2:生成watchRegistration对象保存watcher与节点路径关系
        //3:在ClientCnxn进行网络传输时,将watcher信息封装成packet,放入到发送队列里
        //4:发送成功后把watcher放入到当前zookeeper会话对象的ZkWatchManager管理对象中
        //注意客户端注册时不会把watch实体发送到服务端所以节省了网络开销.
    }



    /**
     * 第二步:zookeeper服务端存储,管理,事件触发
     */
    public void step2(){
         //1:判断客户端是否注册了watcher
         //2:如果注册了,将客户端的ServerCnxn客户端连接对象和节点路径存储在WatchManager对象的watchTable
             //和watch2Paths中
        //3:通过DataTree的watcheManager来管理watche,使用watcheManager的triggerWatch方法触发事件
        //4:事件的触发其实就是找到节点的每个watch及对应的ServerCnxn连接对象一个一个回调.
    }


    /**
     * 第三步:zookeeper客户端回调执行
     */
    public void step3(){
        // TODO: 17/8/20 下次看
    }

}
