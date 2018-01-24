package com.upotv.mcs.operlog.controller;

import com.github.pagehelper.Page;
import com.upotv.mcs.common.ResultData;
import com.upotv.mcs.operlog.entity.Log;
import com.upotv.mcs.operlog.entity.LogVo;
import com.upotv.mcs.operlog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wow on 2017/7/17.
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("")
    public String toLog(){
        return "log/log";
    }

    @RequestMapping("/getLogListPage")
    @ResponseBody
    public ResultData getDict(LogVo vo) {
        Page<Log> pagelist =logService.getLogListPage(vo);
        return new ResultData(pagelist, pagelist.getTotal());
    }
}
