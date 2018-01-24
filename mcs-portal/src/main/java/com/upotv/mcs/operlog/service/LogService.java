package com.upotv.mcs.operlog.service;

import com.github.pagehelper.Page;
import com.upotv.mcs.operlog.entity.Log;
import com.upotv.mcs.operlog.entity.LogVo;

import java.util.List;

/**
 * Created by wow on 2017/7/17.
 */
public interface LogService {
    public int insert(Log log);

    public Page<Log> getLogListPage(LogVo vo);
}
