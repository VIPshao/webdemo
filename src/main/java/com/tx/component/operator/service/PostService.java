/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.operator.dao.PostDao;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.model.Post;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * Post的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("postService")
public class PostService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(PostService.class);
    
    @Resource(name = "postDao")
    private PostDao postDao;
    
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    /**
      * 将组织信息装载入职位信息中
      *<功能详细描述>
      * @param postList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void setupOrganizationInfo(List<Post> postList) {
        if (CollectionUtils.isEmpty(postList)) {
            return;
        }
        
        for (Post post : postList) {
            setupOrganizationInfo(post);
        }
    }
    
    /**
      * 为职位装载组织信息<br/>
      *<功能详细描述>
      * @param post [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void setupOrganizationInfo(Post post) {
        //当职位中的所在组织不存在，或已经将所在组织的组织名已经进行了装载的则不重复进行装载
        if (post == null || post.getOrganization() == null
                || StringUtils.isEmpty(post.getOrganization().getId())
                || !StringUtils.isEmpty(post.getOrganization().getName())) {
            return;
        }
        
        Organization org = this.organizationService.findOrganizationById(post.getOrganization()
                .getId());
        if (org != null) {
            post.setOrganization(org);
        }
    }
    
    /**
      * 将post实例插入数据库中保存
      * 1、如果post为空时抛出参数为空异常
      * 2、如果post中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertPost(Post post) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(post, "post is null.");
        AssertUtils.notEmpty(post.getCode(), "post.code is empty.");
        AssertUtils.notEmpty(post.getName(), "post.name is empty.");
        AssertUtils.notNull(post.getOrganization(),
                "post.organization is null.");
        AssertUtils.notEmpty(post.getOrganization().getId(),
                "post.organization.id is empty.");
        
        //查询所在组织
        Organization org = this.organizationService.findOrganizationById(post.getOrganization()
                .getId());
        AssertUtils.notNull(org,
                "post.organization.id:{} is not exist.",
                post.getOrganization().getId());
        
        //父级组织应该与指定的组织一致
        if (!StringUtils.isEmpty(post.getParentId())) {
            Post parentPost = findPostById(post.getParentId());
            AssertUtils.notNull(parentPost,
                    "parentPostId:{} is not exist.",
                    post.getParentId());
            AssertUtils.isTrue(org.getId().equals(parentPost.getOrganization().getId()));
        }
        
        
        //生成职位全名
        String postFullName = org.getFullName() + post.getName();
        post.setFullName(postFullName);
        
        this.postDao.insertPost(post);
    }
    
    /**
     * 根据id删除post实例
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
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //校验业务意义上的删除是否合法
        List<Post> childPostList = queryPostListByParentId(id);
        AssertUtils.isEmpty(childPostList,"职位存在下级职位不能被删除");
        Post res = findPostById(id);
        AssertUtils.notNull(res,"Post id:{} is not exist",id);
        //将要删除的数据放入历史表
        this.postDao.insertPostToHis(res);
        
        //删除
        Post condition = new Post();
        condition.setId(id);
        int resInt = this.postDao.deletePost(condition);
        return resInt > 0;
    }
    
    /**
      * 根据Id查询Post实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return Post [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public Post findPostById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Post condition = new Post();
        condition.setId(id);
        Post res = this.postDao.findPost(condition);
        
        //为查询结果装载组织信息
        setupOrganizationInfo(res);
        return res;
    }
    
    /**
      * 根据权限查看职位列表
      *     如果没有查看所有组织职位的权限，
      *     则默认只能查看当前组织及其下级的组织含有的职位<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<Post> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Post> queryPostListByAuth() {
        //在底层查询中，会根据权限，查询当前人员能够看到的所有职位
        //如果为超级管理员则能查看所有的组织的所有职位
        List<Post> resList = this.postDao.queryPostList(null);
        
        //为查询结果装载组织信息
        setupOrganizationInfo(resList);
        return resList;
    }
    
    /**
     * 根据Post实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Post> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<Post> queryAllPostList() {
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        //利用该条件能够屏蔽掉底层sql中权限查询的逻辑
        params.put("organization", new Organization());
        
        List<Post> resList = this.postDao.queryPostList(params);
        
        //为查询结果装载组织信息
        setupOrganizationInfo(resList);
        return resList;
    }
    
    /**
      * 根据父级职位id查询子集职位id
      *<功能详细描述>
      * @param parentPostId
      * @return [参数说明]
      * 
      * @return List<Post> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Post> queryPostListByParentId(String parentPostId) {
        AssertUtils.notEmpty(parentPostId, "parentPostId is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", parentPostId);
        //利用该条件能够屏蔽掉底层sql中权限查询的逻辑
        params.put("organization", new Organization());
        
        List<Post> resList = this.postDao.queryPostList(params);
        
        //为查询结果装载组织信息
        setupOrganizationInfo(resList);
        return resList;
    }
    
    /**
      * 根据组织id查询职位列表<br/>
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return List<Post> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Post> queryPostListByOrganizationId(String organizationId) {
        AssertUtils.notEmpty(organizationId, "organizationId is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        //传入了organizationId,权限判断植入的代码即不生效
        params.put("organizationId", organizationId);
        
        List<Post> resList = this.postDao.queryPostList(params);
        
        //为查询结果装载组织信息
        setupOrganizationInfo(resList);
        
        return resList;
    }
    
    /**
      * 查询post列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean postCodeIsExist(String code, String excludePostId) {
        AssertUtils.notEmpty(code, "code is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        params.put("excludePostId", excludePostId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.postDao.countPost(params);
        
        return res > 0;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param post
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Post post) {
        AssertUtils.notNull(post, "post is null.");
        AssertUtils.notEmpty(post.getId(), "post.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", post.getId());
        
        updateRowMap.put("remark", post.getRemark());
        updateRowMap.put("name", post.getName());
        updateRowMap.put("fullName", post.getFullName());
        updateRowMap.put("code", post.getCode());
        
        int updateRowCount = this.postDao.updatePost(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
