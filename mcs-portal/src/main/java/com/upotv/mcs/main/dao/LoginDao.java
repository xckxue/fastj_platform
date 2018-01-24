package com.upotv.mcs.main.dao;

import com.upotv.mcs.main.entity.Mcs_menu;
import com.upotv.mcs.main.entity.UserPermission;
import com.upotv.mcs.core.base.McsBaseDao;
import com.upotv.mcs.main.entity.Mcs_user;

import java.util.List;

/**
 * Created by tianapple on 2017/5/10.
 */
public interface LoginDao extends McsBaseDao {
    List<UserPermission> getPermissions(int userId); //获取用户权限数据

    List<UserPermission> getSuperAdminPermissions(); //超级管理员

    List<UserPermission> getAdminPermissions(); //管理员

    List<Mcs_menu> getMenuList(int userId, int parentId); //获取普通用户授权的菜单

    List<Mcs_menu> getAdminMenuList(int parentId);  //获取管理员的菜单

    List<Mcs_menu> getSuperAdminMenuList(int parentId);  //获取管理员的菜单

    Mcs_user getUserByUserName(String userName);
}
