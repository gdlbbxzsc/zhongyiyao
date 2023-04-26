package com.pbph.shoppingmall.model.dao;

import com.pbph.mvp.base.model.BaseDaoModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/7.
 */

@Entity
public class CouponSearchRecord extends BaseDaoModel {
    @Id(autoincrement = true)
    private Long id;//主键  自增长
    @NotNull   //不许为空
    private String searchText;
    @NotNull   //不许为空-
    private Date createTime;

    @Generated(hash = 1025609827)
    public CouponSearchRecord(Long id, @NotNull String searchText,
                              @NotNull Date createTime) {
        this.id = id;
        this.searchText = searchText;
        this.createTime = createTime;
    }

    @Generated(hash = 1464416994)
    public CouponSearchRecord() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchText() {
        return this.searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
