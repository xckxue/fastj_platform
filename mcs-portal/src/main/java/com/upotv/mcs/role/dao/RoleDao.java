package com.upotv.mcs.role.dao;

import com.upotv.mcs.core.base.McsBaseDao;
import com.upotv.mcs.role.entity.*;

import java.util.List;

/**
 * Created by wangyunpeng on 2017/7/17.
 */
public interface RoleDao extends McsBaseDao {

    List<Role> getRoleList(RoleVo vo);

    Role getRoleByName(String name);

    int insert(RoleVo vo);

    int update(RoleVo vo);

    int delete(int roleid);

    List<MenuPriv> getMenuPriv(int roleId, int menuId);

    List<Menu> getMenuList(int parentId);

    int deletePermission(int roleId);

    int insertPermission(List<PermissionDto> list);
}
