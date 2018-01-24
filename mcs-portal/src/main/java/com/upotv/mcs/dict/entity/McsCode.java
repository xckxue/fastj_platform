package com.upotv.mcs.dict.entity;


import com.upotv.mcs.util.DateUtil;

import java.util.Date;

/**
 * Created by wow on 2017/6/28.
 */
public class McsCode {
    private Integer id;
    private String codeType;
    private String codeId;
    private String codeName;
    private String remark;
    private Integer priority;
    private Integer isEnable;
    private Date updatetime;
    private Date createtime;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUpdatetime() {
        return DateUtil.getDateTimeFormat(updatetime);
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreatetime() {
        return DateUtil.getDateTimeFormat(createtime);
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
