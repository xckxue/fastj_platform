package com.upotv.mcs.main.shiro;

import com.upotv.mcs.main.entity.Mcs_user;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by wow on 2017/11/16.
 */
public interface LoginHandler {

    public void login(HttpSession session, Mcs_user user);

}
