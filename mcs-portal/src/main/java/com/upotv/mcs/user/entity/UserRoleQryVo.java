package com.upotv.mcs.user.entity;

/**
 * Created by wow on 2017/7/24.
 */
public class UserRoleQryVo {
    private int userId;
    private int rows;
    private int page;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
