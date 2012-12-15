package com.tx.components.auth.model;

import java.util.List;

import com.tx.core.tree.model.TreeAble;

/**
  * 权限项接口
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-12-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public interface AuthItem extends TreeAble<List<AuthItem>, AuthItem> {
    
    /**
     * 权限项唯一键key 
     * 约定权限项目分割符为"_"
     * 如权限为"wd_"
     * @return 返回 id
     */
    String getId();
    
    /**
     * 父级权限id
     * @return 返回 parentId 
     */
    String getParentId();
    
    /**
     * 权限名
     * @return 返回 name
     */
    String getName();
    
    /**
     * 获取权限描述
     * @return 返回 description
     */
    String getDescription();
    
    /**
     * 返回子权限
     * @return 返回 childs
     */
    List<AuthItem> getChilds();
    
    /**
     * 获取权限类型
     * @return 返回 authType
     */
    String getAuthType();
    
    /**
     * 判断是否为抽象权限
     * （即：不为实际的权限项）
     * @return 返回 isAbstract
     */
    boolean isAbstract();
    
    /**
     * 该权限是否有效
     * @return 返回 isValid
     */
    boolean isValid();
    
}