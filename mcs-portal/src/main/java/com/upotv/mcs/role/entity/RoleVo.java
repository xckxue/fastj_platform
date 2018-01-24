package com.upotv.mcs.role.entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by wangyunpeng on 2017/7/17.
 */
public class RoleVo {

    private Integer roleid;
    @NotEmpty(message = "角色名称不能为空")
    private String name;
    private String remark;

    private int rows;
    private int page;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
