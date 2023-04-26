package com.pbph.shoppingmall.module.input.choose;

import com.pbph.mvp.base.model.BaseModel;

/**
 * Created by Administrator on 2018/4/19.
 */

public class ChooseDataVo extends BaseModel {
    public int id;
    public String text;


    public ChooseDataVo(int id, String text) {
        this.id = id;
        this.text = text;

    }

    public ChooseDataVo() {

    }
}
