package peng.zkClient;

import org.I0Itec.zkclient.ZkClient;

/**
 * 使用zkClient创建zookeeper会话连接
 * @author wsp
 * @since 2017/08/04
 */
public class Create_Zookeeper_Session {
    private ZkClient zkClient;

    public ZkClient getZkClient(){
        //zkClient将原生的zookeeperAPI包装.使会话连接的创建从异步转为同步
        zkClient = new ZkClient("127.0.0.1:2181",5000);
        return zkClient;
    }


    public static void main(String[] args) {
        Create_Zookeeper_Session czs = new Create_Zookeeper_Session();
        System.out.println(czs.getZkClient());

    }
}
