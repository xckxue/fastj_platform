package com.upotv.mcs.user.entity;

import com.upotv.mcs.util.DateUtil;

import java.util.Date;

/**
 * Created by wow on 2017/7/20.
 */
public class UserRole {
    private int userId;
    private int roleId;
    private String name;
    private String remark;
    private Date createtime;
    private int state;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String getCreatetime() {
        return DateUtil.getDateTimeFormat(createtime);
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
