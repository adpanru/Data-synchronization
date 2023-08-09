package com.qcby.bdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.bdemo.mapper.UserDao;
import com.qcby.bdemo.po.User;
import com.qcby.bdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsersByShard(int shardTotal, int shardIndex) {
        return userDao.getUsersByShard(shardTotal, shardIndex);
    }

}
