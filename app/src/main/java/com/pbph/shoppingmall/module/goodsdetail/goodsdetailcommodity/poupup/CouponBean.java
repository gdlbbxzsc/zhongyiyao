package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.poupup;

/**
 * Created by 连嘉凡 on 2018/4/24.
 */

public class CouponBean {
    private String title;
    private String content;


    public CouponBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public CouponBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
