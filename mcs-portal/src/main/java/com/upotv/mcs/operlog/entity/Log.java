package com.upotv.mcs.operlog.entity;

import com.upotv.mcs.util.DateUtil;

import java.util.Date;

/**
 * Created by wow on 2017/7/17.
 */
public class Log {
    private int logid;
    private String username;
    private String path;
    private String param;
    private String ip;
    private long duration;
    private int status;
    private String remark;
    private Date createtime;

    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return DateUtil.getDateTimeFormat(createtime);
    }


    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
