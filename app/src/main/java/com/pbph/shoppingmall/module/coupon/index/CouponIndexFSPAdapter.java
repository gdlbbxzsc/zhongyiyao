package com.pbph.shoppingmall.module.coupon.index;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pbph.shoppingmall.model.response.GetCouponCategoryListResponse;
import com.pbph.shoppingmall.module.coupon.index.fragment.CouponIndexFragment;

import java.util.List;

/**
 * Created by longhope on 2017/4/26.
 */

public class CouponIndexFSPAdapter extends FragmentStatePagerAdapter {

    public List<GetCouponCategoryListResponse.DataBean.CouponCategoryListBean> list;

    public CouponIndexFSPAdapter(FragmentManager fm, List<GetCouponCategoryListResponse.DataBean.CouponCategoryListBean> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public CouponIndexFragment getItem(int position) {
        return CouponIndexFragment.newInstance(list.get(position).getPpid());
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
