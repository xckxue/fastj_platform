package com.upotv.mcs.role.controller;

import com.github.pagehelper.Page;
import com.upotv.mcs.common.ResultData;
import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.role.entity.Role;
import com.upotv.mcs.role.entity.RoleVo;
import com.upotv.mcs.role.entity.TreeData;
import com.upotv.mcs.role.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("")
    @RequiresPermissions("role/view")
    public String toRole() {
        return "role/role";
    }

    @ResponseBody
    @RequestMapping("/getRoleListPage")
    @RequiresPermissions("role/view")
    public ResultData getUserListPage(RoleVo vo) {
        Page<Role> pagelist = roleService.getRoleListPage(vo);
        return new ResultData(pagelist, pagelist.getTotal());
    }

    @ResponseBody
    @RequestMapping("/insert")
    @RequiresPermissions("role/manager")
    public ResultMessage insert(@Validated RoleVo vo, BindingResult result) {
        return roleService.insert(vo);
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("role/manager")
    public ResultMessage update(@Validated RoleVo vo, BindingResult result) {
        return roleService.update(vo);
    }

    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("role/manager")
    public ResultMessage delete(int roleid) {
        int cnt = roleService.delete(roleid);
        return new ResultMessage("0000", cnt);
    }

    @RequestMapping(value = "/toAuthPriv")
    @RequiresPermissions("role/view")
    public ModelAndView getMenu(Integer roleId) {
        ModelAndView mv = new ModelAndView("role/roleMenu");
        mv.addObject("roleId", roleId);
        return mv;
    }

    @ResponseBody
    @RequestMapping("/getRoleMenu")
    @RequiresPermissions("role/view")
    public List<TreeData> getRoleMenu(@NotNull Integer roleId,@NotNull Integer pid) {
        return roleService.getRoleMenu(roleId, pid);
    }

    @ResponseBody
    @RequestMapping("/authPriv")
    @RequiresPermissions("role/manager")
    public ResultMessage authPriv(@NotNull Integer roleId,@RequestParam(value = "ids[]",required = false) List<String> menuPirv){
        roleService.authPriv(roleId,menuPirv);
        return new ResultMessage("0000", 0);
    }
}
