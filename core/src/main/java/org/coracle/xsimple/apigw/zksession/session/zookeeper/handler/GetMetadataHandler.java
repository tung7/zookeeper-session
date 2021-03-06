/**
 * Storevm.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package org.coracle.xsimple.apigw.zksession.session.zookeeper.handler;

import org.apache.zookeeper.data.Stat;
import org.coracle.xsimple.apigw.zksession.session.metadata.SessionMetaData;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.AbstractZookeeperHandler;
import org.coracle.xsimple.apigw.zksession.session.zookeeper.ZookeeperHandler;
import org.coracle.xsimple.apigw.zksession.utils.SerializationUtils;

/**
 * 返回元数据的处理器实现
 * @author Administrator
 * @version $Id: GetMetadataHandler.java, v 0.1 2012-4-8 下午7:10:47 Administrator Exp $
 */
public class GetMetadataHandler extends AbstractZookeeperHandler {
    /**
     * 构造方法
     * @param id
     */
    public GetMetadataHandler(String id) {
        super(id);
    }

    /** 
     * @see ZookeeperHandler#handle()
     */
    @Override
    public <T> T handle() throws Exception {
        if (zookeeper != null) {
            String path = SESSION_GROUP_PATH + NODE_SEP + id;
            // 检查节点是否存在
            Stat stat = zookeeper.exists(path, false);
            //stat为null表示无此节点
            if (stat == null) {
                return null;
            }
            //获取节点上的数据
            byte[] data = zookeeper.getData(path, false, null);
            if (data != null) {
                //反序列化
                Object obj = SerializationUtils.deserialize(data);
                //转换类型
                if (obj instanceof SessionMetaData) {
                    SessionMetaData metadata = (SessionMetaData) obj;
                    //设置当前版本号
                    metadata.setVersion(stat.getVersion());
                    return (T) metadata;
                }
            }
        }
        return (T) null;
    }
}
