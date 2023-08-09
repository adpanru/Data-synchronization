package com.qcby.bdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcby.bdemo.po.User2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface User2Dao extends BaseMapper<User2> {
}

