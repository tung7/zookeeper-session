/**
 * Storevm.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package org.coracle.xsimple.apigw.zksession.session.zookeeper.handler;

/**
 * 
 * @author hzxiongwenwu.tan
 * @version $Id: CreateGroupNodeHandler.java, v 0.1 2012-4-9 上午09:27:34 hzxiongwenwu.tan Exp $
 */
public class CreateGroupNodeHandler extends CreateNodeHandler {
    /**
     * 构造方法
     */
    public CreateGroupNodeHandler() {
        this(SESSION_GROUP_PATH);
    }

    /**
     * 构造方法
     * @param id
     */
    public CreateGroupNodeHandler(String id) {
        super(id, null);
    }

}
