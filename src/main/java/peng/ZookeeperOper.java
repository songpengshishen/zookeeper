package peng;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.util.List;

/**
 * zookeeper操作示例
 * @author wangsongpeng
 * @since 2017/07/28
 */
public class ZookeeperOper {
    private final ZookeeperClient zookeeperClient = new ZookeeperClient();
    private static String zNodePath = null;//zookeeper创建节点后新节点的路径,异步赋值
    private static List<String> childrenPath = null;//zookeeper获取子节点列表,异步赋值

    /**
     * 同步的方式创建一个Znode
     */
    public void createZNode(){
        try {
           String path = "/wsp";//ZNode节点路径
           byte[] data = new String("hello zookeeper").getBytes();//字节数组,ZNode的数据,需要字节序列化
           List<ACL> acls = ZooDefs.Ids.OPEN_ACL_UNSAFE;//ZNode的ACL策略
           CreateMode createMode = CreateMode.PERSISTENT;//ZNode的创建模型具体看CreateMode枚举
           ZooKeeper zooKeeper =  zookeeperClient.getZooKeeper();
           //使用同步的方式创建ZNode
           zNodePath = zooKeeper.create(path,data,acls,createMode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 异步的方式创建ZNode
     */
    public void createZNodeSync(){
        try {
            String path = "/wsp/sync/1";//ZNode节点路径
            byte[] data = new String("hello zookeeper").getBytes();//字节数组,ZNode的数据,需要字节序列化
            List<ACL> acls = ZooDefs.Ids.OPEN_ACL_UNSAFE;//ZNode的ACL策略
            CreateMode createMode = CreateMode.PERSISTENT;//ZNode的创建模型具体看CreateMode枚举
            //以下两个参数是异步创建ZNode时传入
            AsyncCallback.StringCallback scb = new CreateNodeCallback(); //ZNode创建完成后的回调函数
            String ctx = "i am context";//回调方法执行时传入的对象,通常是上下文对象
            ZooKeeper zooKeeper =  zookeeperClient.getZooKeeper();
            //使用异步的方式创建ZNode
            zooKeeper.create(path,data,acls,createMode,scb,ctx);
            ThreadUtils.wait_();
        }catch (Exception e){
            e.printStackTrace();
        }
    }






    /**
     * 同步的删除
     */
    public void delete(){
        try {
            String path = "/wsp/sync/1";//ZNode节点路径
            int version = 0;//要删除的zNode的数据版本,只删除这个版本的数据
            ZooKeeper zooKeeper =  zookeeperClient.getZooKeeper();
            //使用同步的方式创建ZNode
            zooKeeper.delete(path,version);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 异步的删除
     */
    public void deleteSync(){
        try {
            String path = "/wsp/sync";//ZNode节点路径
            int version = 0;//要删除的zNode的数据版本,只删除这个版本的数据
            AsyncCallback.VoidCallback voidCallback = new DeleteNodeCallback();
            String ctx = "delete success!";
            ZooKeeper zooKeeper =  zookeeperClient.getZooKeeper();
            //使用同步的方式创建ZNode
            zooKeeper.delete(path,version,voidCallback,ctx);
            ThreadUtils.wait_();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    static class DeleteNodeCallback implements AsyncCallback.VoidCallback{

        public void processResult(int i, String s, Object o) {
            System.out.println("delete znode resCode : " + i +"znode path :" +s +"ctx : " + o);
            ThreadUtils.notify_();
        }
    }


    static class CreateNodeCallback implements AsyncCallback.StringCallback{

        public void processResult(int i, String s, Object o, String s1) {
            System.out.println("create znode resCode: " + i +"znode path :" + s + "ctx : " + o +
                    "znode name is : " + s1);
            zNodePath = s;
            ThreadUtils.notify_();
        }
    }

    public static void main(String[] args) {
       ZookeeperOper zookeeperOper = new ZookeeperOper();
       //创建
       /*zookeeperOper.createZNodeSync();
       System.out.println("create ZNode path is : " + zNodePath);*/
       //删除
        zookeeperOper.deleteSync();
    }
}
