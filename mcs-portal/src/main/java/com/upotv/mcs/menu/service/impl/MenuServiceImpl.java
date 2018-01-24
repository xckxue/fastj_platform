package com.upotv.mcs.menu.service.impl;

import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.menu.dao.MenuDao;
import com.upotv.mcs.menu.entity.Menu;
import com.upotv.mcs.menu.entity.MenuPrivVo;
import com.upotv.mcs.menu.entity.MenuTreeGrid;
import com.upotv.mcs.menu.entity.MenuVo;
import com.upotv.mcs.menu.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2017/6/20.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

    public List<MenuTreeGrid> getMenu(int parentId){
        return initAllMenu(parentId);
    }

    private List<MenuTreeGrid> initAllMenu(int pid) {
        List<MenuTreeGrid> treeDataList = new ArrayList<>();

        List<Menu> menuList = menuDao.getMenuList(pid);

        for (Menu menu : menuList) {
            MenuTreeGrid treeData = new MenuTreeGrid();
            treeData.setId(menu.getMenuId());
            treeData.setIconCls(menu.getIconCls());
            treeData.setName(menu.getName());
            treeData.setPriority(menu.getPriority());
            treeData.setPath(menu.getPath());
            treeData.setPrivId(menu.getPrivId());
            treeData.setPrivName(menu.getPrivName());
            treeData.setIsAdmin(menu.getIsAdmin());
            treeData.setIsEnable(menu.getIsEnable());
            treeData.setRemark(menu.getRemark());
            treeData.setCreatetime(menu.getCreatetime());
            treeData.setUpdatetime(menu.getUpdatetime());
            treeDataList.add(treeData);
            List<MenuTreeGrid> child = initAllMenu(menu.getMenuId());
            treeData.setChildren(child);
            treeData.setState("open");
        }
        return treeDataList;
    }

    @Override
    public ResultMessage insert(MenuVo vo) {
        int ct = menuDao.insert(vo);
        MenuPrivVo privVo = new MenuPrivVo();
        privVo.setMenuid(vo.getMenuid());
        privVo.setPriv_id("view");
        privVo.setPriv_name("页面权限");
        menuDao.insertMenuPriv(privVo);
        return new ResultMessage(ResultMessage.SUCCESS,ct);
    }

    @Override
    public ResultMessage update(MenuVo vo) {
        int ct = menuDao.update(vo);
        return new ResultMessage(ResultMessage.SUCCESS,ct);
    }

    @Override
    public ResultMessage delete(Integer menuid) {
        int checkMenuCount = menuDao.checkMenu(menuid);
        if(checkMenuCount > 0){
            return new ResultMessage(ResultMessage.FAILE,"该菜单下面包含子菜单，不允许删除！");
        }else{
            menuDao.delete(menuid);
            menuDao.deleteMenuPriv(menuid);
            menuDao.deletePermission(menuid);
        }
        return new ResultMessage(ResultMessage.SUCCESS,"删除成功！");
    }

    @Override
    public ResultMessage insertMenuPriv(MenuPrivVo vo) {
        menuDao.deleteMenuPriv(vo.getMenuid());
        String[] priv_ids = vo.getPriv_id().split(",");
        String[] priv_names = vo.getPriv_name().split(",");
        for (int i = 0; i < priv_ids.length; i++) {
            if (StringUtils.isNotBlank(priv_ids[i])) {
                vo.setPriv_id(priv_ids[i]);
                vo.setPriv_name(priv_names[i]);
                menuDao.insertMenuPriv(vo);
            }
        }
        return new ResultMessage(ResultMessage.SUCCESS,"1");
    }

    @Override
    public ResultMessage order(String point, int sourceId, int targetId) {
        Menu sourceMenu = menuDao.getMenuById(sourceId);
        Menu targetMenu = menuDao.getMenuById(targetId);
        int parentId = sourceMenu.getParentId();
        if("top".equals(point)){
            parentId = targetMenu.getParentId();
        }else if("bottom".equals(point)){
            parentId = targetMenu.getParentId();
        }else if("append".equals(point)){
            parentId = targetMenu.getMenuId();
        }else{
            return new ResultMessage(ResultMessage.FAILE,"操作错误");
        }
        int ct = menuDao.updatePosition(parentId,sourceMenu.getMenuId());

        List<Menu> childMenuList = menuDao.getChildMenuById(parentId,sourceId);

        List<Map> orderMapList = new ArrayList<>();

        for(Menu menu : childMenuList){
            if(menu.getMenuId() == targetId){
                Map map = new HashMap<>();
                map.put("menuId",targetId);

                Map map2 = new HashMap<>();
                map2.put("menuId",sourceId);

                if("top".equals(point)){
                    orderMapList.add(map2);
                    orderMapList.add(map);
                }else if("bottom".equals(point)) {
                    orderMapList.add(map);
                    orderMapList.add(map2);
                }
            }else{
                Map map = new HashMap<>();
                map.put("menuId",menu.getMenuId());
                orderMapList.add(map);
            }
        }

        int idx = 0;
        for(Map map : orderMapList){
            map.put("rowno",++idx);
        }

        if(orderMapList.size() > 0){
            Map map = new HashMap<>();
            map.put("params",orderMapList);
            menuDao.updateOrder(map);
        }

        return new ResultMessage(ResultMessage.SUCCESS,ct);
    }
}
