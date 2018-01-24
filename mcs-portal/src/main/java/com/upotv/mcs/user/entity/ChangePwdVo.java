package com.upotv.mcs.user.entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by wow on 2017/8/16.
 */
public class ChangePwdVo {
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotEmpty(message = "原始密码不能为空")
    private String oldPassword;

    @NotEmpty(message = "密码不能为空")
    private String password;

    @NotEmpty(message = "密码不能为空")
    private String repassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
