package com.upotv.mcs.dict.service;

import com.github.pagehelper.Page;
import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.dict.entity.McsCode;
import com.upotv.mcs.dict.entity.McsCodeSelectVo;
import com.upotv.mcs.dict.entity.McsCodeType;
import com.upotv.mcs.dict.entity.McsCodeVo;

import java.util.List;

/**
 * Created by wow on 2017/6/28.
 */
public interface DictService {

    public int initDict();

    public List<McsCode> getDict(String typecode, boolean cache);

    Page<McsCode> getDictListPage(McsCodeSelectVo vo);

    ResultMessage add(McsCodeVo vo);

    ResultMessage update(McsCodeVo vo);

    ResultMessage del(Integer id);

    List<McsCodeType> getCodeType();

    List<McsCode> getMtypeList(List<Integer> searchMtype);
}
