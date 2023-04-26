package com.pbph.shoppingmall.module.input.edit.type;

import com.pbph.shoppingmall.module.input.edit.AEditData;

/**
 * Created by Administrator on 2018/4/19.
 */

public class NickNameData extends AEditData {


    @Override
    public String initTitle() {
        return "修改昵称";
    }

    @Override
    public String initHint() {
        return "请输入昵称";
    }
 
    @Override
    public String initError() {
        return "昵称输入不正确";
    }

    @Override
    public String initEmptyError() {
        return "请输入昵称";
    }

    @Override
    public String initToast() {
        return "4-20个字符,可由中英文、数字、“_”、“-”组成";
    }

    @Override
    public int initMaxLength() {
        return 20;
    }
}
