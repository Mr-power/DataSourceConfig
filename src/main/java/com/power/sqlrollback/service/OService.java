package com.power.sqlrollback.service;

import com.power.sqlrollback.bean.Ppp;
import com.power.sqlrollback.mapper.one.OneSourceMapper;
import com.power.sqlrollback.mapper.two.TwoSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

@Service
public class OService {
    @Autowired
    private OneSourceMapper oneSourceMapper;

    @Autowired
    private TwoSourceMapper twoSourceMapper;
    //当前应该two 支持回滚！
    @Transactional(rollbackFor = Exception.class)
    public void panDuan(){
        //Ppp p1=new Ppp(1,"213","54544");
        Ppp p2=new Ppp(444444,"ddddddddd","aaaaaaaaaaaaa");
        //oneSource.insert(p1);
        twoSourceMapper.insert(p2);
        throw  new RuntimeException("灰姑娘");
    }
}
