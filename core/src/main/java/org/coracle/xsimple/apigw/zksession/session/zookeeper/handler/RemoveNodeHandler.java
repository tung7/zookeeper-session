/**
 * Storevm.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package org.coracle.xsimple.apigw.zksession.session.zookeeper.handler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.data.Stat;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.AbstractZookeeperHandler;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.ZookeeperHandler;
import org.coracle.xsimple.apigw.zksession.utils.SerializationUtils;

/**
 * 删除节点的处理器
 * @author hzxiongwenwu.tan
 * @version $Id: RemoveNodeHandler.java, v 0.1 2012-4-9 上午10:44:39 hzxiongwenwu.tan Exp $
 */
public class RemoveNodeHandler extends AbstractZookeeperHandler {
    /**
     * 构造方法
     * @param id
     */
    public RemoveNodeHandler(String id) {
        super(id);
    }

    /** 
     * @see ZookeeperHandler#handle()
     */
    @Override
    public <T> T handle() throws Exception {
        Map<String, Serializable> datas = new HashMap<String, Serializable>();
        if (zookeeper != null) {
            String path = SESSION_GROUP_PATH + NODE_SEP + id;
            // 检查节点是否存在
            Stat stat = zookeeper.exists(path, false);
            //如果节点存在则删除之
            if (stat != null) {
                //先删除子节点
                List<String> nodes = zookeeper.getChildren(path, false);
                if (nodes != null) {
                    for (String node : nodes) {
                        String dataPath = path + "/" + node;
                        //获取数据
                        byte[] data = zookeeper.getData(dataPath, false, null);
                        if (data != null) {
                            //反序列化
                            Object obj = SerializationUtils.deserialize(data);
                            datas.put(node, (Serializable) obj);
                        }
                        zookeeper.delete(dataPath, -1);
                    }
                }
                //删除父节点
                zookeeper.delete(path, -1);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("删除Session节点完成:[" + path + "]");
                }
            }
        }
        return (T) datas;
    }

}
