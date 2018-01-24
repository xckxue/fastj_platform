package com.upotv.mcs.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.main.entity.Mcs_user;
import com.upotv.mcs.operlog.entity.Log;
import com.upotv.mcs.operlog.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wow on 2017/6/26.
 */
@Component
public class McsInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(McsInterceptor.class);

    private static final String LOGGER_ENTITY = "logger_entity";
    private static final String BASE_PATH = "basePath";

    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Log logEntity = new Log();

        //请求路径
        String uri = request.getRequestURI();

        //请求方法
        String method = request.getMethod();

        //客户端IP
        logEntity.setIp(request.getRemoteAddr());

        logEntity.setPath(uri);

        Mcs_user user = (Mcs_user)request.getSession().getAttribute("session_user");
        if(user != null){
            logEntity.setUsername(user.getUserName());
        }else{
            logEntity.setUsername("unknow");
        }

        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）

        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

        request.setAttribute(BASE_PATH, basePath);
        request.setAttribute(LOGGER_ENTITY, logEntity);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();
        long consumeTime = endTime - beginTime;

        int status = response.getStatus();

        Log logEntity = (Log) request.getAttribute(LOGGER_ENTITY);
        logEntity.setDuration(consumeTime);
        logEntity.setStatus(status);

        //获取请求参数信息
        Map<String,String> paramDataMap = new HashMap<>();

        Map paramMap = request.getParameterMap();
        String formData = "";
        if(paramMap != null && paramMap.size() > 0){
            formData = JSON.toJSONString(request.getParameterMap(), SerializerFeature.WriteMapNullValue);
        }

        paramDataMap.put("formData",formData);

        String diyData = (String)request.getAttribute("params");
        paramDataMap.put("diyData",diyData);

        logEntity.setParam(JSON.toJSONString(paramDataMap,SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue));

        ResultMessage exception = (ResultMessage)request.getAttribute("mcs_exception");
        if (exception != null) {
            logEntity.setRemark(exception.getErrorStack());
            logEntity.setStatus(500);
        }

        logService.insert(logEntity);

        LOGGER.info(String.format("请求参数,user: %s, uri: %s, params: %s, status: %s, remark: %s", logEntity.getUsername(),logEntity.getPath(), logEntity.getParam(),logEntity.getStatus(), logEntity.getRemark()));

        super.afterCompletion(request, response, handler, ex);
    }
}
