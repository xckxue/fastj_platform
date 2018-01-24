package com.upotv.mcs.role.entity;

import com.upotv.mcs.util.DateUtil;

import java.util.Date;

/**
 * Created by wangyunpeng on 2017/7/17.
 */
public class Role {

    private int roleid;
    private String name;
    private String remark;
    private Date updateTime;
    private Date createTime;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateTime() {
        return  DateUtil.getDateTimeFormat(updateTime);
    }

    public String getCreateTime() {
        return  DateUtil.getDateTimeFormat(createTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
