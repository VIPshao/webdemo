/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.boda.components.auth.dao;

import java.util.List;
import java.util.Map;

import com.boda.components.auth.model.AuthItemRef;


 /**
  * <权限的持久层>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-11-30]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface AuthDao {
    
    /**
      * 根据用户id查询用户权限引用项列表
      * <功能详细描述>
      * @param operator
      * @return [参数说明]
      * 
      * @return List<AuthItemRef> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<AuthItemRef> queryItemAuthRefListByOperId(String opeId);
    
    /**
      * 根据用户id已经权限类型查询用户权限引用项列表
      *<功能详细描述>
      * @param operId
      * @return [参数说明]
      * 
      * @return List<AuthItemRef> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<AuthItemRef> queryItemAuthRefList(Map<String,Object> params);
    
    /**
      * 插入权限引用项
      * <功能详细描述>
      * @param authItemRef [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void addAuthItemRefList(List<AuthItemRef> authItemRef);
    
    /**
      * 删除权限引用项列表
      * <功能详细描述>
      * @param authItemRef [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void delAuthItemRefList(List<AuthItemRef> authItemRef);
}
