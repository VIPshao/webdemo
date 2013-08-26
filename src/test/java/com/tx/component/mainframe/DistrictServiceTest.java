/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-11
 * <修改描述:>
 */
package com.tx.component.mainframe;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tx.component.mainframe.model.District;
import com.tx.component.mainframe.service.DistrictService;
import com.tx.core.paged.model.PagedList;
import com.tx.webdemo.TestBase;


 /**
  * District业务层单元测试类
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-12-11]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
        "classpath:spring/beans-ds.xml",
        "classpath:spring/beans-tx.xml", 
        "classpath:spring/beans-mybatis.xml",
        "classpath:spring/beans.xml" })
public class DistrictServiceTest {
    
    /** 设置jndi */
    @BeforeClass
    public static void setUp() {
        TestBase.bindDsToJNDI();
    }
    
    @Resource(name="districtService")
    private DistrictService districtService;
    
    /**
      * 生成district实例
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return District [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected District getDistrict(){
        District res = new District();
        
        
        
        return res;
    }
    
    /**
      * 贯通测试，增加，查询，删除
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Test
    public void testInsertAndFindAndDelete(){
        //
        try {
            District district = getDistrict();
            
            this.districtService.insertDistrict(district);
            
            Assert.assertNotNull(district.getId());
            
            String pk = district.getId();
            
            District res = this.districtService.findById(pk);
            
            Assert.assertNotNull(res);
            
            int count = this.districtService.deleteById(res.getId());
            
            Assert.assertTrue(count > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
    /**
      * 测试查询列表
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Test
    public void testQueryList(){
        try {
            int count = this.districtService.countDistrict();
            
            Assert.assertTrue(count >= 0);
            
            List<District> districtList = this.districtService.queryDistrictList();
            
            Assert.assertNotNull(districtList);
            
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
    /**
      * 测试分页查询功能
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Test
    public void testQueryPageList(){
        try {
            PagedList<District> districtPageList = this.districtService.queryDistrictPagedList(1, 10);
            
            Assert.assertNotNull(districtPageList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
}
