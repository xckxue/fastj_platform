package com.upotv.mcs.menu.dao;

import com.upotv.mcs.menu.entity.Menu;
import com.upotv.mcs.core.base.McsBaseDao;
import com.upotv.mcs.menu.entity.MenuPrivVo;
import com.upotv.mcs.menu.entity.MenuVo;

import java.util.List;
import java.util.Map;

/**
 * Created by wow on 2017/6/20.
 */
public interface MenuDao extends McsBaseDao {

    public List<Menu> getMenuList(Integer parentId);

    Menu getMenuByName(String name);

    Menu getMenuById(int menuId);

    List<Menu> getChildMenuById(int parentId,int exid);

    int insert(MenuVo vo);

    int update(MenuVo vo);

    List<Map> getOrder(int parentId);

    int updatePosition(int parentId,int menuid);

    int updateOrder(Map map);

    int insertMenuPriv(MenuPrivVo vo);

    int checkMenu(Integer menuid);

    void delete(Integer menuid);

    void deleteMenuPriv(Integer menuid);

    void deletePermission(Integer menuid);
}
