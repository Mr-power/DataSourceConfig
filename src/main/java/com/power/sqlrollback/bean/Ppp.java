package com.power.sqlrollback.bean;

import lombok.Data;

@Data
public class Ppp {
    private int id;
    private String idName;
    private String remark;

    public Ppp(int id, String idName, String remark) {
        this.id = id;
        this.idName = idName;
        this.remark = remark;
    }
}
