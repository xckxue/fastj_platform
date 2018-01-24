package com.upotv.mcs.dict.controller;

import com.github.pagehelper.Page;
import com.upotv.mcs.common.ResultData;
import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.dict.entity.McsCode;
import com.upotv.mcs.dict.entity.McsCodeSelectVo;
import com.upotv.mcs.dict.entity.McsCodeVo;
import com.upotv.mcs.dict.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wow on 2017/6/28.
 */
@Controller
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @RequestMapping("")
    public String toDict(){
        return "dict/dict";
    }

    @RequestMapping("/getDict")
    @ResponseBody
    public List getDict(String typeCode,boolean cache) {
        return dictService.getDict(typeCode,cache);
    }

    @ResponseBody
    @RequestMapping("/getDictListPage")
    @RequiresPermissions("dict/view")
    //获取字典列表
    public ResultData getDictListPage(McsCodeSelectVo vo){
        Page<McsCode> pageList = dictService.getDictListPage(vo);
        return new ResultData(pageList,pageList.getTotal());
    }

    @ResponseBody
    @RequestMapping("/add")
    @RequiresPermissions("dict/manager")
    public ResultMessage add(@Validated McsCodeVo vo){
        return dictService.add(vo);
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("dict/manager")
    public ResultMessage update(@Validated McsCodeVo vo){
        return dictService.update(vo);
    }

    @ResponseBody
    @RequestMapping("/del")
    @RequiresPermissions("dict/manager")
    public ResultMessage del(@NotNull Integer id){
        return dictService.del(id);
    }


    @ResponseBody
    @RequestMapping("/init")
    @RequiresPermissions("dict/manager")
    public ResultMessage init(){
        int cnt = dictService.initDict();
        return new ResultMessage(ResultMessage.SUCCESS,cnt);
    }

    //获取字典类型
    @ResponseBody
    @RequestMapping("/getCodeType")
    public List getCodeType(){
        return dictService.getCodeType();
    }


}
