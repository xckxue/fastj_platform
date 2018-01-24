package com.upotv.mcs.core.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.upotv.mcs.common.ResultMessage;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by wow on 2017/6/22.
 */
@ControllerAdvice
public class McsExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(McsExceptionHandler.class);


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResultMessage exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        ResultMessage resp = new ResultMessage();

        if (ex instanceof DuplicateKeyException) {
            resp.setRetnCode("10001");
            resp.setRetnMessage("数据库中已存在该记录");
        } else if (ex instanceof AuthorizationException) {
            resp.setRetnCode("20001");
            resp.setRetnMessage("没有权限，请联系管理员授权");
        } else if (ex instanceof ParamValidException) {
            resp.setRetnCode("30001");
            resp.setRetnMessage(ex.getMessage());
        } else if (ex instanceof BindException) {
            resp.setRetnCode("20002");
            resp.setRetnMessage("查询参数错误");
        }else if (ex instanceof McsException) {
            resp.setRetnCode("80001");
            resp.setRetnMessage(ex.getMessage());
        } else {
            resp.setRetnCode("9999");
            resp.setRetnMessage("系统错误，请联系管理员");
        }

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw, true));
        resp.setErrorStack(sw.toString());

        request.setAttribute("mcs_exception", resp);

        //记录异常日志
        LOGGER.error(ex.getMessage(), ex);

        return resp;
    }
}
