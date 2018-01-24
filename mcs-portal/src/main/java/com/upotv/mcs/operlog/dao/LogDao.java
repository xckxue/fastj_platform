package com.upotv.mcs.operlog.dao;

import com.upotv.mcs.core.base.McsBaseDao;
import com.upotv.mcs.operlog.entity.Log;
import com.upotv.mcs.operlog.entity.LogVo;

import java.util.List;

/**
 * Created by wow on 2017/7/17.
 */
public interface LogDao extends McsBaseDao {
    int insert(Log log);

    public List<Log> getLogListPage(LogVo vo);

    int delete();

}
