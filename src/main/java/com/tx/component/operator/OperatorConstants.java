/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-12
 * <修改描述:>
 */
package com.tx.component.operator;


 /**
  * 操作员常量<br/>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2013-9-12]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface OperatorConstants {
    
    /** 组织职位树 节点类型：组织 */
    public static final int TREENODE_TYPE_ORGANIZATION = 0;
    
    /** 组织职位树 节点类型：职位 */
    public static final int TREENODE_TYPE_POST = 1;
    
    /** 人员有效  */
    public static final int OPERATOR_VALID = 1;
    
    /** 人员无效  */
    public static final int OPERATOR_INVALID = 0;
    
    /** 人员锁定 */
    public static final int OPERATOR_LOCKED_TRUE = 1;
    
    /** 人员未锁定 */
    public static final int OPERATOR_LOCKED_FALSE = 0;
}