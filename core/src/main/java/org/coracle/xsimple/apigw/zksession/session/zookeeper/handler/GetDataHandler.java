/**
 * Storevm.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package org.coracle.xsimple.apigw.zksession.session.zookeeper.handler;

import org.apache.zookeeper.data.Stat;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.ZookeeperHandler;
import org.coracle.xsimple.apigw.zksession.utils.SerializationUtils;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.AbstractZookeeperHandler;

/**
 * 返回指定键值节点数据的处理器
 * @author hzxiongwenwu.tan
 * @version $Id: GetDataHandler.java, v 0.1 2012-4-9 上午09:59:56 hzxiongwenwu.tan Exp $
 */
    public class GetDataHandler extends AbstractZookeeperHandler {
    /** 键值名称 */
    protected String key;

    /**
     * @param id
     */
    protected GetDataHandler(String id) {
        super(id);
    }

    /**
     * 构造方法
     * @param id
     * @param key
     */
    public GetDataHandler(String id, String key) {
        this(id);
        this.key = key;
    }

    /** 
     * @see ZookeeperHandler#handle()
     */
    @Override
    public <T> T handle() throws Exception {
        if (zookeeper != null) {
            String path = SESSION_GROUP_PATH + NODE_SEP + id;
            // 检查指定的Session节点是否存在
            Stat stat = zookeeper.exists(path, false);
            if (stat != null) {
                //查找数据节点是否存在
                String dataPath = path + NODE_SEP + key;
                stat = zookeeper.exists(dataPath, false);
                Object obj = null;
                if (stat != null) {
                    //获取节点数据
                    byte[] data = zookeeper.getData(dataPath, false, null);
                    if (data != null) {
                        //反序列化
                        obj = SerializationUtils.deserialize(data);
                    }
                }
                return (T) obj;
            }
        }
        return (T) null;
    }

}
