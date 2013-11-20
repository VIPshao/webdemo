/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.operator.OperatorConstants;
import com.tx.component.operator.dao.OperatorDao;
import com.tx.component.operator.model.Operator;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * Operator的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("operatorService")
public class OperatorService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OperatorService.class);
    
    @SuppressWarnings("unused")
    private Logger serviceLogger;
    
    @Resource(name = "operatorDao")
    private OperatorDao operatorDao;
    
    /** 校验密码最大错误次数 */
    private int checkPasswordMaxErrorCount = 3;
    
    /**
      * 校验用户名密码，如果用户密码错误，则将用户密码输入错误次数+1，否则置为0
      *<功能详细描述>
      * @param loginName
      * @param password
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean checkPassword(String loginName, String password) {
        AssertUtils.notEmpty(loginName, "loginName is empty");
        AssertUtils.notEmpty(password, "password is empty");
        
        //根据用户名查询用户
        Operator res = findOperatorByLoginName(loginName);
        //如果不存在对应用户名则直接返回
        if (res == null) {
            return false;
        }
        
        //校验密码是否正确
        Operator condition = new Operator();
        condition.setLoginName(loginName);
        condition.setPassword(password);
        Operator oper = this.operatorDao.findOperator(condition);
        if (oper != null) {
            //如果密码正确
            //更新密码错误次数为0
            Map<String, Object> updateRowMap = new HashMap<String, Object>();
            updateRowMap.put("id", res.getId());
            updateRowMap.put("pwdErrCount", 0);//密码错误次数重置为0
            this.operatorDao.updateOperator(updateRowMap);
            return true;
        } else {
            Map<String, Object> updateRowMap = new HashMap<String, Object>();
            int errorCount = res.getPwdErrCount() + 1;
            updateRowMap.put("id", res.getId());
            updateRowMap.put("pwdErrCount", errorCount);
            if (errorCount > checkPasswordMaxErrorCount) {
                updateRowMap.put("locked",
                        OperatorConstants.OPERATOR_LOCKED_TRUE);
            }
            this.operatorDao.updateOperator(updateRowMap);
            return false;
        }
    }
    
    /**
      * 将operator实例插入数据库中保存
      * 1、如果operator为空时抛出参数为空异常
      * 2、如果operator中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertOperator(Operator operator) {
        AssertUtils.notNull(operator, "operator is null.");
        AssertUtils.notEmpty(operator.getId(), "operator.id is empty.");
        AssertUtils.notEmpty(operator.getLoginName(),
                "operator.loginName is empty.");
        AssertUtils.notEmpty(operator.getPassword(),
                "operator.password is empty.");
        
        //业务意义上的验证
        //新增人员需要指定人员所属组织<br/>
        AssertUtils.notNull(operator.getOrganization(),
                "operator.organization is null.");
        AssertUtils.notEmpty(operator.getOrganization().getId(),
                "operator.organization.id is empty.");
        
        //TODO:密码加密
        
        //写入默认时间
        Date now = new Date();
        operator.setCreateDate(now);
        operator.setLastUpdateDate(now);
        operator.setPwdErrCount(0);
        
        this.operatorDao.insertOperator(operator);
    }
    
    /**
     * 根据id删除operator实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数
     * @param id
     * @return 返回删除的数据条数，<br/>
     * 有些业务场景，如果已经被别人删除同样也可以认为是成功的
     * 这里讲通用生成的业务层代码定义为返回影响的条数
     * @return int [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Operator condition = new Operator();
        condition.setId(id);
        
        return this.operatorDao.deleteOperator(condition);
    }
    
    /**
      * 根据id禁用操作员<br/>
      *<功能详细描述>
      * @param id [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean disableOperatorById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        updateRowMap.put("valid", false);
        updateRowMap.put("invalidDate", new Date());
        updateRowMap.put("pwdErrCount", 0);
        
        int updateRowCount = this.operatorDao.updateOperator(updateRowMap);
        return updateRowCount > 0;
    }
    
    /**
      * 根据Id查询Operator实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return Operator [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public Operator findOperatorById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Operator condition = new Operator();
        condition.setId(id);
        return this.operatorDao.findOperator(condition);
    }
    
    /**
     * 根据LoginName查询Operator实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return Operator [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
    */
    public Operator findOperatorByLoginName(String loginName) {
        AssertUtils.notEmpty(loginName, "loginName is empty.");
        
        Operator condition = new Operator();
        condition.setLoginName(loginName);
        return this.operatorDao.findOperator(condition);
    }
    
    //    /**
    //      * 根据Operator实体列表
    //      * 
    //      * <功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return List<Operator> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<Operator> queryOperatorList() {
    //        Map<String, Object> params = new HashMap<String, Object>();
    //        
    //        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
    //        List<Operator> resList = this.operatorDao.queryOperatorList(params);
    //        
    //        return resList;
    //    }
    
    /**
     * 分页查询Operator实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<Operator> queryOperatorPagedListByOrganizationId(
            String organizationId, int pageIndex, int pageSize) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Operator> resPagedList = this.operatorDao.queryOperatorPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param operator
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Operator operator) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(operator, "operator is null.");
        AssertUtils.notEmpty(operator.getId(), "operator.id is empty.");
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", operator.getId());
        
        //TODO:需要更新的字段
        //type:java.lang.String
        updateRowMap.put("mainPost", operator.getMainPost());
        updateRowMap.put("valid", operator.isValid());
        updateRowMap.put("pwdErrCount", operator.getPwdErrCount());
        updateRowMap.put("historyPwd", operator.getHistoryPwd());
        updateRowMap.put("invalidDate", operator.getInvalidDate());
        updateRowMap.put("password", operator.getPassword());
        updateRowMap.put("lastUpdateDate", operator.getLastUpdateDate());
        //type:java.lang.String
        updateRowMap.put("organization", operator.getOrganization());
        updateRowMap.put("pwdUpdateDate", operator.getPwdUpdateDate());
        updateRowMap.put("userName", operator.getUserName());
        updateRowMap.put("locked", operator.isLocked());
        updateRowMap.put("createDate", operator.getCreateDate());
        updateRowMap.put("examinePwd", operator.getExaminePwd());
        
        int updateRowCount = this.operatorDao.updateOperator(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
