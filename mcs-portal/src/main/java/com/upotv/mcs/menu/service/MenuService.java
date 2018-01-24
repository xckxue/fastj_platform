package com.upotv.mcs.menu.service;

import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.menu.entity.*;

import java.util.List;

/**
 * Created by wow on 2017/6/20.
 */
public interface MenuService {

    public List<MenuTreeGrid> getMenu(int parentId);

    ResultMessage insert(MenuVo vo);

    ResultMessage update(MenuVo vo);

    ResultMessage delete(Integer menuid);

    ResultMessage insertMenuPriv(MenuPrivVo vo);

    ResultMessage order(String point,int sourceId,int targetId);

}
