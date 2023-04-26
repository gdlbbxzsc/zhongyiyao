package com.pbph.shoppingmall.module.input.choose.type;

import com.pbph.shoppingmall.module.input.choose.AChooseData;
import com.pbph.shoppingmall.module.input.choose.ChooseDataVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SexData extends AChooseData<ChooseDataVo> {

    @Override
    public String initTitle() {
        return "修改性别";
    }

    public List<ChooseDataVo> initData() {
        List<ChooseDataVo> list = new ArrayList<>();
        list.add(new ChooseDataVo(1, "男"));
        list.add(new ChooseDataVo(2, "女"));
        list.add(new ChooseDataVo(0, "保密"));
        return list;
    }
}
