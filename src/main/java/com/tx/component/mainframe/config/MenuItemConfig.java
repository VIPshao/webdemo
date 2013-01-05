/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.component.mainframe.config;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 菜单配置<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@XStreamAlias("menu")
public class MenuItemConfig {
    
    /** 菜单唯一id */
    @XStreamAsAttribute
    private String id;
    
    /** 菜单名 */
    @XStreamAsAttribute
    private String text;
    
    /** 菜单鼠标悬停提示信息 */
    @XStreamAsAttribute
    private String tips;
    
    /** 菜单href值 */
    @XStreamAsAttribute
    private String href = "javascript:void(0);return false;";
    
    /** 菜单对应权限 */
    @XStreamAsAttribute
    private String authKey;
    
    /** 菜单图标 */
    @XStreamAsAttribute
    private String icon;
    
    /** 是否有效 */
    @XStreamAsAttribute
    private boolean isValid;
    
    /** 是否可见 */
    @XStreamAsAttribute
    private boolean isVisible;
    
    /** 打开类型 ： mainTabs,openDialog,triggerGlobalEvent */
    @XStreamAsAttribute
    private String target = "mainTabs";
    
    /** mainTabs时：选中后是否需要刷新  */
    @XStreamAsAttribute
    private boolean selectRefresh = false;
    
    /** mainTabs时：是否每次都打开新tab */
    @XStreamAsAttribute
    private boolean openNewEveryTime = false;
    
    /** openDialog: 宽 */
    @XStreamAsAttribute
    private int width = 750;
    
    /** openDialog: 高 */
    @XStreamAsAttribute
    private int height = 350;
    
    /** openDialog: 是否模态 */
    @XStreamAsAttribute
    private boolean isModal = false;
    
    /** triggerGlobalEvent: 事件类型 */
    @XStreamAsAttribute
    private String eventType = "";
    
    /** triggerGlobalEvent: 传递数据的json字符串 */
    @XStreamAsAttribute
    private String params = "";
    
    /** 子菜单项 */
    @XStreamImplicit(itemFieldName="menu")
    private List<MenuItemConfig> childs = new ArrayList<MenuItemConfig>();
    
    /**
     * @return 返回 text
     */
    public String getText() {
        return text;
    }
    
    /**
     * @param 对text进行赋值
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * @return 返回 tips
     */
    public String getTips() {
        return tips;
    }
    
    /**
     * @param 对tips进行赋值
     */
    public void setTips(String tips) {
        this.tips = tips;
    }
    
    /**
     * @return 返回 href
     */
    public String getHref() {
        return href;
    }
    
    /**
     * @param 对href进行赋值
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return 返回 childs
     */
    public List<MenuItemConfig> getChilds() {
        return childs;
    }

    /**
     * @param 对childs进行赋值
     */
    public void setChilds(List<MenuItemConfig> childs) {
        this.childs = childs;
    }

    /**
     * @return 返回 authKeys
     */
    public String getAuthKey() {
        return authKey;
    }
    
    /**
     * @param 对authKeys进行赋值
     */
    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
    
    /**
     * @return 返回 icon
     */
    public String getIcon() {
        return icon;
    }
    
    /**
     * @param 对icon进行赋值
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    /**
     * @return 返回 isVisible
     */
    public boolean isVisible() {
        return isVisible;
    }
    
    /**
     * @param 对isVisible进行赋值
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return 返回 isValid
     */
    public boolean isValid() {
        return isValid;
    }
    
    /**
     * @param 对isValid进行赋值
     */
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * @return 返回 target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param 对target进行赋值
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return 返回 selectRefresh
     */
    public boolean isSelectRefresh() {
        return selectRefresh;
    }

    /**
     * @param 对selectRefresh进行赋值
     */
    public void setSelectRefresh(boolean selectRefresh) {
        this.selectRefresh = selectRefresh;
    }

    /**
     * @return 返回 openNewEveryTime
     */
    public boolean isOpenNewEveryTime() {
        return openNewEveryTime;
    }

    /**
     * @param 对openNewEveryTime进行赋值
     */
    public void setOpenNewEveryTime(boolean openNewEveryTime) {
        this.openNewEveryTime = openNewEveryTime;
    }

    /**
     * @return 返回 width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param 对width进行赋值
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return 返回 height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param 对height进行赋值
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return 返回 isModal
     */
    public boolean isModal() {
        return isModal;
    }

    /**
     * @param 对isModal进行赋值
     */
    public void setModal(boolean isModal) {
        this.isModal = isModal;
    }

    /**
     * @return 返回 eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param 对eventType进行赋值
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return 返回 params
     */
    public String getParams() {
        return params;
    }

    /**
     * @param 对params进行赋值
     */
    public void setParams(String params) {
        this.params = params;
    }
}