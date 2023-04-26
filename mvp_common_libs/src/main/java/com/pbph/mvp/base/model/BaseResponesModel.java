package com.pbph.mvp.base.model;

public abstract class BaseResponesModel extends BaseModel {


    private String code;

    private String msg;

    public int getCode() {
        return Integer.parseInt(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
