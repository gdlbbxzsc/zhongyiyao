package com.pbph.shoppingmall.model.vo;

/**
 * Created by 连嘉凡 on 2018/4/11.
 */

public class ShopGoodsListOrGridBean {
    private boolean cbx;
    public static String RxString = "com.pbph.shoppingmall.model.vo.ListOrGridBean";

    public boolean isCbx() {
        return cbx;
    }

    public void setCbx(boolean cbx) {
        this.cbx = cbx;
    }

    public ShopGoodsListOrGridBean(boolean cbx) {
        this.cbx = cbx;
    }

    public ShopGoodsListOrGridBean() {
    }
}
