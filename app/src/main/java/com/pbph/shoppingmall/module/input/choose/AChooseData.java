package com.pbph.shoppingmall.module.input.choose;

import com.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public abstract class AChooseData<T extends ChooseDataVo> {

    public AChooseData() {
        List<T> temp = initData();
        if (temp != null && temp.size() > 0) input_choose_datas.addAll(temp);

        String str = initTitle();
        if (!StringUtils.isEmpty(str)) input_choose_title = str;
    }


    /////////////
    public String input_choose_title = "请选择";

    public abstract String initTitle();


    /////////////

    public List<T> input_choose_datas = new ArrayList<>();

    public abstract List<T> initData();

    public int getChoosePosition(T vo) {
        if (vo == null) return -1;
        for (int i = 0, c = input_choose_datas.size(); i < c; i++) {
//            if (StringUtils.isEqual(vo.text, list.get(i).text)) return i;
            if (vo.id == input_choose_datas.get(i).id) return i;
        }
        return -1;
    }

    public int getChoosePosition(int id) {
        if (id == -1) return -1;
        for (int i = 0, c = input_choose_datas.size(); i < c; i++) {
//            if (StringUtils.isEqual(vo.text, list.get(i).text)) return i;
            if (id == input_choose_datas.get(i).id) return i;
        }
        return -1;
    }


}
