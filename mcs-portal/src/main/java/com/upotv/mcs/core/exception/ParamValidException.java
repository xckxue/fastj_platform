package com.upotv.mcs.core.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by wow on 2017/7/24.
 */
public class ParamValidException extends RuntimeException {
    private List<ObjectError> fieldErrors;

    public ParamValidException(List<ObjectError> errors) {
        this.fieldErrors = errors;
    }

    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer();
        for (ObjectError error : fieldErrors) {
            String msg = error.getDefaultMessage();
            String objectName = error.getObjectName();
            sb.append(objectName).append(":").append(msg).append(";");
        }
        return sb.toString();
    }
}
