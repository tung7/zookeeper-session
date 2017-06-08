/**
 * Storevm.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package org.coracle.xsimple.apigw.zksession.session.zookeeper;

import org.apache.zookeeper.ZooKeeper;

/**
 * ZK客户端操作接口
 *
 * @author Administrator
 * @version $Id: ZookeeperOperate.java, v 0.1 2012-4-8 下午6:07:55 Administrator Exp $
 */
public interface ZookeeperHandler {
    /**
     * 项目的ZK组节点名
     */
    String BASE_GROUP_NAME = "/API_GATEWAY";
    /**
     * ZK-SESSION组节点名称
     */
    String SESSION_GROUP_PATH = BASE_GROUP_NAME;

    String NODE_SEP = "/";

    /**
     * 执行具体操作
     *
     * @throws Exception
     */
    <T> T handle() throws Exception;

    /**
     * 设置ZK客户端对象
     *
     * @param zookeeper ZK客户端
     */
    void setZooKeeper(ZooKeeper zookeeper);
}
