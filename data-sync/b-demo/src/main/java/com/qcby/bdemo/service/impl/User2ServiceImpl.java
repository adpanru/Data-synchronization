package com.qcby.bdemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcby.bdemo.mapper.User2Dao;

import com.qcby.bdemo.po.User2;
import com.qcby.bdemo.service.User2Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User2ServiceImpl extends ServiceImpl<User2Dao, User2> implements User2Service {
}
