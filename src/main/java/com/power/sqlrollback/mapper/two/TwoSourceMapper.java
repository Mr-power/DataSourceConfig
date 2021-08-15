package com.power.sqlrollback.mapper.two;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.power.sqlrollback.bean.Ppp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TwoSourceMapper  {
    int insert(@Param("p") Ppp p);
}
