package com.upotv.mcs.main.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tianapple on 2017/2/4.
 */
public class Mcs_user implements Serializable{
    private int userId;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private int gender;

    private List<Integer> roleids;

    /**
     * 是否管理员
     * 1：是
     * 0：否
     */
    private boolean isAdmin;

    /**
     * 是否锁定
     * 1：是
     * 0：否
     */
    private boolean isLock;

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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    public List<Integer> getRoleids() {
        return roleids;
    }

    public void setRoleids(List<Integer> roleids) {
        this.roleids = roleids;
    }
}
