package com.upotv.mcs.user.entity;

import com.upotv.mcs.util.DateUtil;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by wow on 2017/6/22.
 */
public class User {

    private int userId;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private int isAdmin;
    private int isLock;
    private String remark;
    private Date updateTime;
    private Date createTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateTime() {
        return DateUtil.getDateTimeFormat(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return DateUtil.getDateTimeFormat(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
