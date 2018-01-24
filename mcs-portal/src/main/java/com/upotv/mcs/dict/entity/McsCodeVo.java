package com.upotv.mcs.dict.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * Created by wow on 2017/6/28.
 */
public class McsCodeVo {

    private Integer id;
    @NotEmpty(message = "字典类型不能为空")
    private String codeType;
    @NotEmpty(message = "字典ID不能为空")
    private String codeId;
    @NotEmpty(message = "字典名称不能为空")
    private String codeName;
    private String remark;
    private Integer priority;
    private Integer isEnable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
