package com.upotv.mcs.dict.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.upotv.mcs.common.ResultMessage;
import com.upotv.mcs.dict.dao.DictDao;
import com.upotv.mcs.dict.entity.McsCode;
import com.upotv.mcs.dict.entity.McsCodeSelectVo;
import com.upotv.mcs.dict.entity.McsCodeType;
import com.upotv.mcs.dict.entity.McsCodeVo;
import com.upotv.mcs.dict.service.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wow on 2017/6/28.
 */
@Service
public class DictServiceImpl implements DictService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictService.class);

    private static HashMap<String, CopyOnWriteArrayList<McsCode>> typecodes = new HashMap<String, CopyOnWriteArrayList<McsCode>>();

    @Autowired
    private DictDao dictDao;

    @PostConstruct
    public int initDict() {
        LOGGER.debug("初始化字典表");
        typecodes.clear();
        List<McsCode> dictList = dictDao.getAllDict();
        for (McsCode mcsCode : dictList) {
            if (!typecodes.containsKey(mcsCode.getCodeType())) {
                typecodes.put(mcsCode.getCodeType(), new CopyOnWriteArrayList<McsCode>());
            }
            CopyOnWriteArrayList<McsCode> lists = typecodes.get(mcsCode.getCodeType());
            lists.add(mcsCode);
        }
        return typecodes.size();
    }

    /*
     * 获得type下的所有List
	 */
    public List<McsCode> getDict(String typecode, boolean cache) {
        List<McsCode> list = new CopyOnWriteArrayList<McsCode>();
        if (cache) {
            List<McsCode> cacheList = typecodes.get(typecode);
            if(cacheList != null){
                list = cacheList;
            }
        } else {
            list = dictDao.getDictByType(typecode);
        }
        return list;
    }

    @Override
    public Page<McsCode> getDictListPage(McsCodeSelectVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getRows());
        return (Page<McsCode>) dictDao.getDictListPage(vo);
    }

    @Override
    public ResultMessage add(McsCodeVo vo) {
        //校验同一字典类型下的字典id不能重复
        int checkCount = dictDao.checkDictByTypeAndId(vo);
        if(checkCount == 0){
            int count = dictDao.add(vo);
            return new ResultMessage(ResultMessage.SUCCESS,"添加成功");
        }else{
            return new ResultMessage(ResultMessage.FAILE,"添加失败");
        }
    }

    @Override
    public ResultMessage update(McsCodeVo vo) {
        //校验同一字典类型下的字典id不能重复
        int checkCount = dictDao.checkDictByTypeAndId(vo);
        if(checkCount == 0){
            int count = dictDao.update(vo);
            return new ResultMessage(ResultMessage.SUCCESS,"添加成功");
        }else{
            return new ResultMessage(ResultMessage.FAILE,"添加失败");
        }
    }

    @Override
    public ResultMessage del(Integer id) {
        int count = dictDao.del(id);
        return new ResultMessage(ResultMessage.SUCCESS,"删除成功");
    }

    @Override
    public List<McsCodeType> getCodeType() {
        return dictDao.getCodeType();
    }

    @Override
    public List<McsCode> getMtypeList(List<Integer> searchMtype) {
        //将类型顺序倒置
        Collections.reverse(searchMtype);
        return dictDao.getMtypeList(searchMtype);
    }
}
