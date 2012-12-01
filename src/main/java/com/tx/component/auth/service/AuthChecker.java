/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-1
 * <修改描述:>
 */
package com.tx.component.auth.service;


/**
 * <权限核查器>
 * 通过实现该权限核查器接口可以对后续可能扩展的
 * 多纬度的授权体系进行支持，可动态增加权限权限检查器
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AuthChecker {
    
    /**
      *<权限核查器，支持类型>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String getCheckAuthType();
    
    /**
      *<检查权限>
      * 在该功能中，能够通过权限容器获取到会话权限容器，
      * 从而获取到request，session等信息进行检查
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean checkAuth(String authKey);
}
