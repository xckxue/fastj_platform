package com.upotv.mcs.main.controller;

import com.upotv.mcs.main.entity.Mcs_menu;
import com.upotv.mcs.main.entity.Mcs_user;
import com.upotv.mcs.main.entity.TreeAttribute;
import com.upotv.mcs.main.entity.TreeData;
import com.upotv.mcs.main.shiro.LoginHandler;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.upotv.mcs.main.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tianapple on 2017/5/10.
 */
@Controller
public class LoginController {
    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginHandler loginHandler;

    @RequestMapping("/")
    public String index(Model model) {
        if (isRelogin()) {
            return "main";
        } else {
            return "login";
        }
    }

    /**
     * 用户登陆
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/login") //url
    public void dologin(HttpServletResponse response, HttpSession session, Mcs_user user, Model model) throws IOException {
        String info = loginUser(user);
        session.setAttribute("loginMsg", info);
        session.setMaxInactiveInterval(-1);

        if ("success".equals(info)) {
            Mcs_user session_user = (Mcs_user) SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("session_user",session_user);
            loginHandler.login(session,session_user);
            response.sendRedirect("/main");
        } else {
            response.sendRedirect("/");
        }
    }

    private String loginUser(Mcs_user user) {
        if (isRelogin()) {
            return "success"; // 如果已经登陆，无需重新登录
        }
        return shiroLogin(user); // 调用shiro的登陆验证
    }

    /**
     * shiro登陆
     *
     * @param user
     * @return
     */
    private String shiroLogin(Mcs_user user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        token.setRememberMe(true);
        // shiro登陆验证
        try {
            subject.login(token);
        } catch (UnknownAccountException ex) {
            return "用户不存在或者密码错误！";
        } catch (IncorrectCredentialsException ex) {
            return "用户不存在或者密码错误！";
        } catch (LockedAccountException ex) {
            return "账号被锁定！";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "内部错误，请重试！";
        }
        return "success";
    }

    /**
     * 验证是否登陆
     *
     * @return
     */
    private boolean isRelogin() {
        Subject us = SecurityUtils.getSubject();
        if (us.isAuthenticated()) {
            return true; // 参数未改变，无需重新登录，默认为已经登录成功
        }
        return false; // 需要重新登陆
    }

    /**
     * 登出
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try {
                subject.logout();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        response.sendRedirect("/");
    }

    /**
     * 获得菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMenu")
    public List<TreeData> getMenu(@RequestParam(value = "pid", defaultValue = "-1") Integer pid) {
        List<TreeData> treeDataList = new ArrayList<>();
        List<Mcs_menu> menuList = getMenuList(pid);

        for (Mcs_menu menu : menuList) {
            treeDataList.add(TreeData.parse(menu));
        }

        if(pid != -1){
            initMenu(treeDataList);
        }
        return treeDataList;
    }

    private void initMenu(List<TreeData> rootTreeDataList) {
        for(TreeData rootData : rootTreeDataList){
            List<Mcs_menu> menuList = getMenuList(rootData.getId());
            List<TreeData> treeDataList = new ArrayList<>();
            for (Mcs_menu menu : menuList) {
                treeDataList.add(TreeData.parse(menu));
            }
            rootData.setState("open");
            rootData.setChildren(treeDataList);
            initMenu(treeDataList);
        }
    }

    private List<Mcs_menu> getMenuList(int pid){
        Mcs_user user = (Mcs_user) SecurityUtils.getSubject().getPrincipal();

        List<Mcs_menu> menuList = new ArrayList<>();
        if ("admin".equals(user.getUserName())) {
            menuList = loginService.getSuperAdminMenuList(pid);
        } else {
            //所分配的菜单
            menuList = loginService.getMenuList(user.getUserId(), pid);
            //管理员特定菜单
            if (user.isAdmin()) {
                List<Mcs_menu> menuAdminList = loginService.getAdminMenuList(pid);
                if (menuAdminList.size() > 0) {
                    menuList.addAll(menuAdminList);
                }
            }
        }
        menuList = menuList.stream().distinct().collect(Collectors.toList()); //排除重复
        return menuList;
    }

    private List<TreeData> initMenu(int pid) {
        List<TreeData> treeDataList = new ArrayList<>();

        List<Mcs_menu> menuList = getMenuList(pid);

        for (Mcs_menu menu : menuList) {
            TreeData treeData = new TreeData();
            treeData.setId(menu.getMenuId());
            treeData.setIconCls(menu.getIcon());
            treeData.setText(menu.getName());

            TreeAttribute attribute = new TreeAttribute();
            attribute.setUrl(menu.getPath());
            treeData.setAttributes(attribute);
            treeDataList.add(treeData);

            List<TreeData> child = initMenu(menu.getMenuId());
            treeData.setChildren(child);
            treeData.setState("open");
        }
        return treeDataList;
    }
}
