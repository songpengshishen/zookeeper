package peng.zkClient;

/**
 * 创建zookeeper的Znode
 * @author wsp
 * @since 2017/08/04
 */
public class Create_Zookeeper_ZNode {

    private Create_Zookeeper_Session czs;

    /**
     * 同时创建父节点和子节点
     */
    public void createParentAndSub(){
        /**
         * zkclient内部支持同时创建父节点和子节点,不用每次判断父节点是否存在,在来创建子节点.
         * 这个参数为true代表支持递归创建父节点
         */
        boolean parent = true;
        String zNodePath = "/zkclient/c1";
        getCzs().getZkClient().createPersistent(zNodePath,parent);
    }


    public Create_Zookeeper_Session getCzs(){
        return new Create_Zookeeper_Session();
    }


    public static void main(String[] args) {
        new Create_Zookeeper_ZNode().createParentAndSub();
    }
}
