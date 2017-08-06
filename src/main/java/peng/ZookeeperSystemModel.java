package peng;

/**
 * zookeeper系统模型
 * @author wsp
 * @since 2017/08/06
 */
public abstract class ZookeeperSystemModel {

    /**
     * 数据模型:zookeeper的数据模型是一个类似于Unix文件系统的树,树中的每个数据节点都以(/)分割按层次化结构
     *        组织.每个数据节点都是一个ZNode,ZNode是zookeeper中的最小,基本数据单元.
     */
    abstract void dataModel();

    /**
     * ZXID(事务提案ID):zookeeper中的事务ID,当客户端对zookeeper服务做事务请求操作时,zookeeper会为当前事务
     *                 生成一个全局唯一递增ID,就是ZXID.
     */
    abstract void zxid();


    /**
     * ZNode节点版本号:zookeeper的数据节点ZNode节点具有版本号概念,每当我们对ZNode节点做任何更新操作,都
     *               会引起ZNode版本号的变更.
     * ZNode的三个版本号:
     *                1:version:当前ZNode的数据内容版本号
     *                2:Cversion:当前ZNode的子节点变更版本号
     *                3:aversion:当前ZNode的ACL变更版本号
     * ZNode的版本号概念:ZNode的版本号是代表当前ZNode的数据内容(version),子节点列表(从version),
     *                 ACL信息(aversion)的修改次数,也就是说当我们队ZNode数据内容,子节点列表,ACL
     *                 发生修改对应的版本号就会加1.
     * ZNode的版本号作用:ZNode的版本号主要用来作为乐观锁的写入校验以此来实现zookeeper的乐观锁.
     *                 类似于CAS.
     */
    abstract void version();

}
