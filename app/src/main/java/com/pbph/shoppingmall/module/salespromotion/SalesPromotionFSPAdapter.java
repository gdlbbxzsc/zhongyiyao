package com.pbph.shoppingmall.module.salespromotion;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pbph.shoppingmall.module.salespromotion.fragment.SalesPromotionFragment;

import java.util.List;

/**
 * Created by longhope on 2017/4/26.
 */

public class SalesPromotionFSPAdapter extends FragmentStatePagerAdapter {

    public List<String> list;

    public SalesPromotionFSPAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public SalesPromotionFragment getItem(int position) {

        return SalesPromotionFragment.newInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
