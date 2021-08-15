package com.power.sqlrollback.controller;

import com.power.sqlrollback.service.PService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sql")
public class TestController {
    @Autowired
    private PService pService;

    @GetMapping("/lookTra")
    public String getOver(){

        pService.panDuan1();
        return null;
    }
}
