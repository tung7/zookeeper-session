package org.coracle.xsimple.apigw.zksession;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.zookeeper.ZooKeeper;
import org.coracle.xsimple.apigw.zksession.session.config.Configuration;
import org.coracle.xsimple.apigw.zksession.session.pool.ZookeeperPoolManager;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.DefaultZooKeeperClient;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.ZooKeeperClient;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.ZookeeperHandler;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.handler.CreateGroupNodeHandler;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.handler.CreateNodeHandler;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.handler.RemoveNodeHandler;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }


    public void testRemoveNode() throws Exception {
        //获取配置信息对象
        Configuration config = Configuration.getInstance();

        RemoveNodeHandler handler = new RemoveNodeHandler(ZookeeperHandler.SESSION_GROUP_PATH);

        ZooKeeper zk = new ZooKeeper(config.getString(Configuration.SERVERS), Integer.valueOf(config.getString(Configuration.TIMEOUT)), null);
        handler.setZooKeeper(zk);
        handler.handle();
        zk.close();
    }


    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception {

        //获取配置信息对象
        Configuration config = Configuration.getInstance();

        //初始化ZK实例池
        ZookeeperPoolManager.getInstance().init(config);

        //获取ZK客户端
        ZooKeeperClient client = DefaultZooKeeperClient.getInstance();

        long current = System.currentTimeMillis();
        client.execute(new CreateGroupNodeHandler());
        System.out.println("create group node success");

        String id = ZookeeperHandler.SESSION_GROUP_PATH + "/" + "xxxxxxxx001";
        client.execute(new CreateNodeHandler(id, null));
        System.out.println("create node id:" + id);

        System.out.println("exec time:[" + (System.currentTimeMillis() - current) + "]");

        //关闭ZK实例池
        ZookeeperPoolManager.getInstance().close();
    }
}
