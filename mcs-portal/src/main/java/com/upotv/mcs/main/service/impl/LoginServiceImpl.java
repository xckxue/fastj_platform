package com.upotv.mcs.main.service.impl;

import com.upotv.mcs.main.entity.Mcs_user;
import com.upotv.mcs.main.dao.LoginDao;
import com.upotv.mcs.main.entity.Mcs_menu;
import com.upotv.mcs.main.entity.UserPermission;
import com.upotv.mcs.main.service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wow on 2017/6/21.
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDao loginDao;

    @Override
    public Mcs_user getUserByUserName(String userName) {
        return loginDao.getUserByUserName(userName);
    }

    @Override
    public List<UserPermission> getPermissions(@Param("userId") int userId) {
        return loginDao.getPermissions(userId);
    }

    @Override
    public List<UserPermission> getSuperAdminPermissions() {
        return loginDao.getSuperAdminPermissions();
    }

    @Override
    public List<UserPermission> getAdminPermissions() {
        return loginDao.getAdminPermissions();
    }

    @Override
    public List<Mcs_menu> getMenuList(int userId, int parentId) {
        return loginDao.getMenuList(userId,parentId);
    }

    @Override
    public List<Mcs_menu> getAdminMenuList(int parentId) {
        return loginDao.getAdminMenuList(parentId);
    }

    @Override
    public List<Mcs_menu> getSuperAdminMenuList(int parentId) {
        return loginDao.getSuperAdminMenuList(parentId);
    }

}
