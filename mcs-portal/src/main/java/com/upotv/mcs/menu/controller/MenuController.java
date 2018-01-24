package com.upotv.mcs.menu.controller;

import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.menu.entity.*;
import com.upotv.mcs.menu.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;


@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("")
    @RequiresPermissions("menu/view")
    public String toMenu() {
        return "menu/menu";
    }

    @ResponseBody
    @RequestMapping("/menuListPage")
    @RequiresPermissions("menu/view")
    public List<MenuTreeGrid> getMenu(Integer parentId){
        return menuService.getMenu(parentId);
    }

    @ResponseBody
    @RequestMapping("/insert")
    public ResultMessage insert(@Validated MenuVo vo, BindingResult result) {
        return menuService.insert(vo);
    }

    @ResponseBody
    @RequestMapping("/update")
    public ResultMessage update(@Validated MenuVo vo, BindingResult result) {
        return menuService.update(vo);
    }

    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("menu/manager")
    public ResultMessage delete(@NotNull Integer menuid) {
        return menuService.delete(menuid);
    }

    @ResponseBody
    @RequestMapping("/insertMenuPriv")
    public ResultMessage insertMenuPriv(@Validated MenuPrivVo vo, BindingResult result) {
        return menuService.insertMenuPriv(vo);
    }

    @ResponseBody
    @RequestMapping("/order")
    public ResultMessage order(String point,int sourceId,int targetId){
        return menuService.order(point,sourceId,targetId);
    }
}
