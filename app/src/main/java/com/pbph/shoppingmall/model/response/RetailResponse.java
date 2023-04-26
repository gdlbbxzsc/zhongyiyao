package com.pbph.shoppingmall.model.response;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class RetailResponse {
    int shopId;
    boolean isChecked;
    double price ;
    int num;
    boolean isGoodChecked;

    public boolean isGoodChecked() {
        return isGoodChecked;
    }

    public void setGoodChecked(boolean goodChecked) {
        isGoodChecked = goodChecked;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
