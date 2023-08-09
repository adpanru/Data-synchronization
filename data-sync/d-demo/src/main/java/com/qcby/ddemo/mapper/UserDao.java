package com.qcby.ddemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.qcby.ddemo.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {


}

