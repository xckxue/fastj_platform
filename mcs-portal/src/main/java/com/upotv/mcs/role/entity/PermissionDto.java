package com.upotv.mcs.role.entity;

import java.util.List;

public class PermissionDto{
    private int roleId;
    private int menuId;
    private String menuPriv;


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuPriv() {
        return menuPriv;
    }

    public void setMenuPriv(String menuPriv) {
        this.menuPriv = menuPriv;
    }

    @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionDto that = (PermissionDto) o;

        if (roleId != that.roleId) return false;
        if (menuId != that.menuId) return false;
        return !(menuPriv != null ? !menuPriv.equals(that.menuPriv) : that.menuPriv != null);

    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + menuId;
        result = 31 * result + (menuPriv != null ? menuPriv.hashCode() : 0);
        return result;
    }
}
