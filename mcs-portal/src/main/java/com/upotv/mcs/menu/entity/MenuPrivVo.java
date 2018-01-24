package com.upotv.mcs.menu.entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by wangyunpeng on 2017/7/25.
 */
public class MenuPrivVo {
    private int menuid;
    @NotEmpty(message="菜单功能不能为空")
    private String priv_id;
    private String priv_name;

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getPriv_id() {
        return priv_id;
    }

    public void setPriv_id(String priv_id) {
        this.priv_id = priv_id;
    }

    public String getPriv_name() {
        return priv_name;
    }

    public void setPriv_name(String priv_name) {
        this.priv_name = priv_name;
    }
}
