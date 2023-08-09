package com.qcby.bdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.qcby.bdemo.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select * from user where id % #{shardTotal} = #{shardIndex} and id <20")
    List<User> getUsersByShard(@Param("shardTotal") int shardTotal, @Param("shardIndex") int shardIndex);

}

