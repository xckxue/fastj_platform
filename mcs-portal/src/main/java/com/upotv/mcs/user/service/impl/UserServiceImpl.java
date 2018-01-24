package com.upotv.mcs.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.upotv.mcs.user.dao.UserDao;
import com.upotv.mcs.user.entity.*;
import com.upotv.mcs.user.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import com.upotv.mcs.common.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wow on 2017/6/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public Page<User> getUserListPage(UserVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getRows());
        return (Page<User>) userDao.getUserListPage(vo);
    }

    @Override
    public int delete(int userId) {
        return userDao.delete(userId);
    }


    @Override
    public ResultMessage insert(UserVo vo) {
        User user = userDao.getUserByUserName(vo.getUserName());
        if(user != null){
            return new ResultMessage(ResultMessage.FAILE,"用户已经存在");
        }
        vo.setPassword(md5(vo.getPassword(), 1));
        int cnt = userDao.insert(vo);
        return new ResultMessage(ResultMessage.SUCCESS,cnt+"");
    }

    @Override
    public ResultMessage update(UserVo vo) {
        User user = userDao.getUserByUserName(vo.getUserName());
        if(user == null){
            return new ResultMessage(ResultMessage.FAILE,"用户不存在");
        }

        int cnt = userDao.update(vo);
        return  new ResultMessage(ResultMessage.SUCCESS,cnt+"");
    }

    @Override
    public Page<UserRole> getUserRoleListPage(UserRoleQryVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getRows());
        return (Page<UserRole>)userDao.getUserRoleListPage(vo);
    }

    @Override
    public int relateRole(UserRoleVo vo) {
        if(vo.getRoles().size() == 0){
            return 0;
        }
        if("add".equals(vo.getAction())){
            return userDao.insertUserRole(vo);
        }else{
            return userDao.delUserRole(vo);
        }
    }

    @Override
    public ResultMessage changepwd(ChangePwdVo vo) {
        if(!vo.getPassword().equals(vo.getRepassword())){
            return new ResultMessage(ResultMessage.FAILE,"输入的两次密码不一致");
        }

        User user = userDao.getUserByUserName(vo.getUserName());
        if(user == null){
            return new ResultMessage(ResultMessage.FAILE,"用户不存在");
        }

        if(!user.getPassword().equals(md5(vo.getOldPassword(), 1))){
            return new ResultMessage(ResultMessage.FAILE,"原密码不正确");
        }

        vo.setPassword(md5(vo.getPassword(), 1));

        int cnt = userDao.changepwd(vo);
        return  new ResultMessage(ResultMessage.SUCCESS,cnt+"");
    }


    /**
     * MD5
     * @param credentials
     * @param hashIterations 循环几次
     * @return
     */
    private String md5(String credentials,int hashIterations){
        String hashAlgorithmName = "MD5";
        //ByteSource credentialsSalt = ByteSource.Util.bytes(vo.getUserId());//加slat
        Object obj = new SimpleHash(hashAlgorithmName, credentials,null, hashIterations);
        return obj.toString();
    }
}
