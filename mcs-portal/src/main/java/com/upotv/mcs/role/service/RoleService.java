package com.upotv.mcs.role.service;
import com.github.pagehelper.Page;
import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.role.entity.Role;
import com.upotv.mcs.role.entity.RoleVo;
import com.upotv.mcs.role.entity.TreeData;

import java.util.List;

/**
 * Created by wangyunpeng on 2017/7/18.
 */
public interface RoleService {

    Page<Role> getRoleListPage(RoleVo vo);

    ResultMessage insert(RoleVo vo);

    ResultMessage update(RoleVo vo);

    int delete(int roleid);

    List<TreeData> getRoleMenu(int roleId,int pid);

    int authPriv(int roleId,List<String> menuPirv);
}
