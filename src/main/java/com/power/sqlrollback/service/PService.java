package com.power.sqlrollback.service;


import com.power.sqlrollback.bean.Ppp;
import com.power.sqlrollback.mapper.one.OneSourceMapper;
import com.power.sqlrollback.mapper.two.TwoSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PService {
    @Autowired
    private OneSourceMapper oneSourceMapper;

    @Autowired
    private TwoSourceMapper twoSourceMapper;
    @Autowired
    private OService oService;
    //当前应该two 支持回滚！
    @Transactional("twoTransactionManager")
    public void panDuan(){
        Ppp p1=new Ppp(1,"213","54544");
        Ppp p2=new Ppp(123,"ddddddddd","aaaaaaaaaaaaa");
        oneSourceMapper.insert(p1);
        twoSourceMapper.insert(p2);
        throw  new RuntimeException("灰姑娘");
    }
    @Transactional(rollbackFor = Exception.class)
    public void panDuan1(){
        Ppp p1=new Ppp(55,"213","54544");
        //Ppp p2=new Ppp(123,"ddddddddd","aaaaaaaaaaaaa");
        oneSourceMapper.insert(p1);
        oService.panDuan();
       // twoSource.insert(p2);
      //  throw  new RuntimeException("灰姑娘");
    }
}
