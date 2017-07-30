package peng;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 一个简单的zookeeper会话连接示例
 * @author wangsongpeng
 * @since 2017/07/28
 */
public class ZookeeperClient {

   private ZooKeeper zooKeeper ; //zookeeper会话连接实例
   private static CountDownLatch connectedSemaphore = new CountDownLatch(1);//zookeeper会话连接信息量


    /**
     * 通过创建一个zookeeper对象来与zookeeper服务器进行连接,这个连接属于异步的,
     * 对象创建后可能并没有真正的建立一个可以的会话连接,状态处于连接中.当会话真正创建
     * 完毕,服务器会向客户端发生一个事件通知告诉客户端会话创建完毕.
     * @return Zookeeper
     */
    public ZooKeeper getZooKeeper()throws Exception{
        //zookeeper服务器列表连接字符串,可以是单台也可以是多个节点的集群.形式是host:port,也可以在后面加入znode的路径
        //这样这个会话连接就是基于这个Znode操作
        String connectString = "127.0.0.1:2181";
        //会话超时时间,以毫秒为单位.在会话周期内客户端和服务器通过心跳检测来保持会话有效性,在sessionTimeout这个时间内
        //如果没有进行有效的心跳检测会话就失效.
        int sessionTimeout = 5000;
        //long sessionId, byte[] sessionPasswd zookeeper支持session复用,创建zookeeper时将上次会话的sessionId和
        //sessionpasswd传入即可.
        zooKeeper = new ZooKeeper(connectString,sessionTimeout,new SessionWatcher());
        System.out.println("zookeeper session connection status : "+zooKeeper.getState().name());
        connectedSemaphore.await();//当计数器信息量不为0时一直阻塞
        System.out.println("zookeeper session connection status : "+zooKeeper.getState().name());
        return zooKeeper;
   }

   /**
    * 会话通知观察者
    * 接受服务器关于会话的事件通知
    */
   static class SessionWatcher implements Watcher{

       public void process(WatchedEvent event) {
           System.out.println("receive zookeeper server event : " + event);
           if(event.getState() == Event.KeeperState.SyncConnected){
               System.out.println("session connection success!");
               connectedSemaphore.countDown();//计数器信号量递减一
           }
       }
   }





    public static void main(String[] args) {
        ZookeeperClient zkClient = new ZookeeperClient();
        ZooKeeper zookeeper = null;
        try {
            zookeeper =  zkClient.getZooKeeper();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
