package com.upotv.mcs.core.config;

import com.upotv.mcs.main.shiro.LoginHandler;
import com.upotv.mcs.main.shiro.LoginHandlerDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wow on 2017/11/16.
 */
@Configuration
public class MCSAutoConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(MCSAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(LoginHandler.class)
    public LoginHandler loginHandler(){
        LOGGER.debug("注册默认登陆插件....");
        return new LoginHandlerDefault();
    }
}
