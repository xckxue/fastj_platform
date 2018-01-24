package com.upotv.mcs.main.entity;

import org.spider.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  用户所对应的授权信息
 *
 * Created by tianapple on 2017/6/1.
 */
public class UserPermission {
    private String menuName;
    private String path;
    private String priv;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPriv() {
        return priv;
    }

    public void setPriv(String priv) {
        this.priv = priv;
    }
}
