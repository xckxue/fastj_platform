package com.upotv.mcs.operlog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.upotv.mcs.operlog.dao.LogDao;
import com.upotv.mcs.operlog.entity.Log;
import com.upotv.mcs.operlog.entity.LogVo;
import com.upotv.mcs.operlog.service.LogService;
import com.upotv.mcs.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wow on 2017/7/17.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public int insert(Log log) {
        return logDao.insert(log);
    }

    @Override
    public Page<Log> getLogListPage(LogVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getRows());
        return (Page<Log>) logDao.getLogListPage(vo);
    }

    @Scheduled(cron="0 0 1 * * ?")
    private void deletelog() {
        int i = 0;
        int j = 0;

        int i_idx = 0;
        int j_idx = 0;

        do{
            i = logDao.delete();
        }while( i > 0 && ++i_idx < 50);

    }
}
