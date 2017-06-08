/**
 * Storevm.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package org.coracle.xsimple.apigw.zksession.session.zookeeper.handler;

import org.coracle.xsimple.apigw.zksession.session.metadata.SessionMetaData;

/**
 * 返回节点键值名称集合的处理器
 * @author hzxiongwenwu.tan
 * @version $Id: GetNodeNames.java, v 0.1 2012-4-9 下午12:10:56 hzxiongwenwu.tan Exp $
 */
public class GetNodeNames extends GetMetadataHandler {

    /**
     * @param id
     */
    public GetNodeNames(String id) {
        super(id);
    }

    /** 
     * @see GetMetadataHandler#handle()
     */
    @Override
    public <T> T handle() throws Exception {
        if (zookeeper != null) {
            String path = SESSION_GROUP_PATH + NODE_SEP + id;

            //获取元数据
            SessionMetaData metadata = super.handle();
            //如果不存在或是无效，则直接返回null
            if (metadata == null || !metadata.getValidate()) {
                return null;
            }
            //获取所有子节点
            return (T) zookeeper.getChildren(path, false);
        }
        return (T) null;
    }

}
