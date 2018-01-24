package com.upotv.mcs.main.entity;

import java.util.List;

/**
 * Created by tianapple on 2017/6/2.
 */
public class Mcs_menu {
    private int menuId;
    private int parentId;
    private String name;
    private String path;
    private String remark;
    private int priority;
    private String icon;
    private int opened;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }

    /**
     *  是否启用，1：启用，0：不启用
     */
    private boolean isEnable;
    /**
     * 0：否
     * 1：是
     */
    private boolean isAdmin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mcs_menu mcsMenu = (Mcs_menu) o;

        return menuId == mcsMenu.menuId;
    }

    @Override
    public int hashCode() {
        return menuId;
    }

    private List<Mcs_menu> childMenus;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Mcs_menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Mcs_menu> childMenus) {
        this.childMenus = childMenus;
    }
}
