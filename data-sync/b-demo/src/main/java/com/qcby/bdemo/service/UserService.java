package com.qcby.bdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcby.bdemo.po.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getUsersByShard(int shardTotal, int shardIndex);
}