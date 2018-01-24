package com.upotv.mcs.role.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.role.dao.RoleDao;
import com.upotv.mcs.role.entity.*;
import com.upotv.mcs.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public Page<Role> getRoleListPage(RoleVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getRows());
        return (Page<Role>) roleDao.getRoleList(vo);
    }

    @Override
    public ResultMessage insert(RoleVo vo) {
        Role role = roleDao.getRoleByName(vo.getName());
        if(role != null){
            return new ResultMessage(ResultMessage.FAILE,"角色已经存在");
        }
        int ct = roleDao.insert(vo);
        return new ResultMessage(ResultMessage.SUCCESS,ct+"");
    }

    @Override
    public ResultMessage update(RoleVo vo) {
        Role role = roleDao.getRoleByName(vo.getName());
        if(role != null && vo.getRoleid() != role.getRoleid()){
            return new ResultMessage(ResultMessage.FAILE,"角色已经存在");
        }
        int ct = roleDao.update(vo);
        return new ResultMessage(ResultMessage.SUCCESS,ct+"");
    }

    @Override
    public int delete(int roleid) {
        return roleDao.delete(roleid);
    }

    @Override
    public List<TreeData> getRoleMenu(int roleId,int pid) {
        List<TreeData> treeDataList = new ArrayList<>();

        List<Menu> menuList = roleDao.getMenuList(pid);

        for (Menu menu : menuList) {
            TreeData treeData = new TreeData();
            treeData.setId(menu.getMenuId()+"");
            treeData.setIconCls(menu.getIcon());
            treeData.setText(menu.getName());

            TreeData.TreeAttribute attribute = new TreeData().new TreeAttribute();
            attribute.setUrl(menu.getPath());
            treeData.setAttributes(attribute);
            treeDataList.add(treeData);

            List<TreeData> child = getRoleMenu(roleId,menu.getMenuId());
            if(child.size() == 0) {
                List<MenuPriv> menuPrivs = roleDao.getMenuPriv(roleId, menu.getMenuId());
                for (MenuPriv menuPriv : menuPrivs) {
                    TreeData treeData2 = new TreeData();
                    treeData2.setId(menuPriv.getId());
                    treeData2.setText(menuPriv.getName());
                    treeData2.setChecked(menuPriv.isChecked());
                    treeData2.setState("open");
                    treeData2.setIconCls("icon-anchor");
                    child.add(treeData2);
                }
            }
            treeData.setChildren(child);
            treeData.setState("open");
        }
        return treeDataList;
    }

    @Override
    public int authPriv(int roleId, List<String> menuPirvlist) {
        roleDao.deletePermission(roleId);

        List<PermissionDto> list = new ArrayList<PermissionDto>();

        if(menuPirvlist != null){
            for(String menuPirv : menuPirvlist){
                PermissionDto dto = new PermissionDto();
                dto.setRoleId(roleId);
                if(menuPirv.indexOf("-") != -1){
                    String menuId = menuPirv.split("-")[0];
                    String priv = menuPirv.split("-")[1];
                    dto.setMenuId(Integer.parseInt(menuId));
                    dto.setMenuPriv(priv);
                    list.add(dto);
                    if("manager".equals(priv)){
                        PermissionDto viewDto = new PermissionDto();
                        viewDto.setRoleId(roleId);
                        viewDto.setMenuId(Integer.parseInt(menuId));
                        viewDto.setMenuPriv("view");
                        list.add(viewDto);
                    }
                }else{
                    dto.setMenuId(Integer.parseInt(menuPirv));
                    dto.setMenuPriv("view");
                    list.add(dto);
                }
            }
        }

        list = list.stream().distinct().collect(Collectors.toList()); //排除重复
        if(list.size() == 0){
            return 0;
        }
        return roleDao.insertPermission(list);
    }
}
