package com.upotv.mcs.dict.dao;

import com.upotv.mcs.core.base.McsBaseDao;
import com.upotv.mcs.dict.entity.McsCode;
import com.upotv.mcs.dict.entity.McsCodeSelectVo;
import com.upotv.mcs.dict.entity.McsCodeType;
import com.upotv.mcs.dict.entity.McsCodeVo;

import java.util.List;

/**
 * Created by wow on 2017/6/28.
 */
public interface DictDao extends McsBaseDao {

    public List<McsCode> getDictListPage(McsCodeSelectVo code);

    /**
     * 根据指定类型下的可用的字典
     * @param type
     * @return
     */
    public List<McsCode> getDictByType(String type);

    /**
     * 获得所有的可用的字典
     * @return
     */
    public List<McsCode> getAllDict();

    int checkDictByTypeAndId(McsCodeVo vo);

    int add(McsCodeVo vo);

    int update(McsCodeVo vo);

    int del(Integer id);

    List<McsCodeType> getCodeType();

    List<McsCode> getMtypeList(List<Integer> list);
}

