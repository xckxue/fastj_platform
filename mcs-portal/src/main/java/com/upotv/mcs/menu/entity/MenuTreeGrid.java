package com.upotv.mcs.menu.entity;

import com.upotv.mcs.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by wow on 2017/8/21.
 */
public class MenuTreeGrid {
    private String path;
    private String remark;
    private int priority;
    private int isEnable;
    private int isAdmin;
    private String privId;
    private String privName;
    private Date updatetime;
    private Date createtime;

    private Integer id;
    private String name;
    private String iconCls;
    private String state = "closed";
    private List<MenuTreeGrid> children;

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPrivName() {
        return privName;
    }

    public void setPrivName(String privName) {
        this.privName = privName;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPrivId() {
        return privId;
    }

    public void setPrivId(String privId) {
        this.privId = privId;
    }

    public String getUpdatetime() {
        return DateUtil.getDateTimeFormat(updatetime);
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreatetime() {
        return DateUtil.getDateTimeFormat(createtime);
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<MenuTreeGrid> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeGrid> children) {
        this.children = children;
    }
}
