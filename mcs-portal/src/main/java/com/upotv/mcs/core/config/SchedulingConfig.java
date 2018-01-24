package com.upotv.mcs.core.config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by wow on 2017/8/3.
 */
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SchedulingConfig.class);

}
