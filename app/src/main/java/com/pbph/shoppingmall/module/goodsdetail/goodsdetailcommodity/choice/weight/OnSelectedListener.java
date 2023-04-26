package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.choice.weight;

import android.widget.CompoundButton;

/**
 * 选择成功回调
 */
public interface OnSelectedListener {
    void onSelected(CompoundButton view, String title, int titleId, String smallTitle, int id);
}
