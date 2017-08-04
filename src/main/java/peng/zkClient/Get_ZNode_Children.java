package peng.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * 获取ZNode子节点
 * @author wsp
 * @since 2017/08/04
 */
public class Get_ZNode_Children {

    public static void main(String[] args)throws  Exception {
        String zkServer = "127.0.0.1:2181";
        String parentPath = "/zkclient";
        ZkClient zkClient = new ZkClient(zkServer,5000);
        //直接在当前会话连接对象上注册事件,与原生API不同这个事件监听是绑定当前会话的不是一次性的,不需要重复注册
        //而且是在会话连接上直接通过不同方法绑定不同事件接口来注册,相比原生的针对不同的方法注册watch代码可读性
        //更好.
        zkClient.subscribeChildChanges(parentPath,new IZkChildListener(){
            public void handleChildChange(String parentPath, List<String> list) throws Exception {
                System.out.println("childChanges notice : " + "parent is : " + parentPath + "" +
                        "current child is : " +list);

            }
        });
        zkClient.createPersistent(parentPath);
        Thread.sleep(1000);
        zkClient.createPersistent(parentPath+"/c1");
        Thread.sleep(1000);
        zkClient.delete(parentPath+"/c1");
        Thread.sleep(1000);
        zkClient.delete(parentPath);
        Thread.sleep(4000);
    }
}
