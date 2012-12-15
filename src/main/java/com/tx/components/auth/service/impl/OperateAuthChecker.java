/*
 * 描          述:  <描述>
 * 修  改   人:  grace
 * 修改时间:  2012-12-10
 * <修改描述:>
 */
package com.tx.components.auth.service.impl;

import com.tx.components.auth.AuthConstant;
import com.tx.components.auth.model.AuthItem;
import com.tx.components.auth.model.AuthItemRef;


 /**
 * 操作权限检查器
 * <功能详细描述>
 * 
 * @author  grace
 * @version  [版本号, 2012-12-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperateAuthChecker extends BaseAuthChecker{

	/**
	 * @return
	 */
	@Override
	public String getCheckAuthType() {
		return AuthConstant.TYPE_OPERATE;
	}

    /**
     * @param authItem
     * @param authItemRef
     * @param objects
     * @return
     */
    @Override
    public boolean isHasAuth(AuthItem authItem, AuthItemRef authItemRef,
            Object... objects) {
        return true;
    }
}
