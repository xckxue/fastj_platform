package com.upotv.mcs.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by wow on 2017/3/21.
 */
public class ResultMessage {

    //成功
    public static final String SUCCESS = "0000";

    //失败
    public static final String FAILE = "9999";

    // 状态码
    private String retnCode;

    // 错误信息
    private Object retnMessage;

    //错误堆栈
    @JsonIgnore
    private String errorStack;

    public ResultMessage() {
    }

    public ResultMessage(String retnCode, Object retnMessage) {
        this.retnCode = retnCode;
        this.retnMessage = retnMessage;
    }

    public String getRetnCode() {
        return retnCode;
    }

    public void setRetnCode(String retnCode) {
        this.retnCode = retnCode;
    }

    public Object getRetnMessage() {
        return retnMessage;
    }

    public void setRetnMessage(Object retnMessage) {
        this.retnMessage = retnMessage;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public String getErrorStack() {
        return errorStack;
    }
}
