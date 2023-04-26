package com.pbph.shoppingmall.module.seckill.index.goods;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pbph.shoppingmall.module.seckill.index.goods.fragment.SecKillIndexGoodsItemCardFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longhope on 2017/4/26.
 */

public class SecKillIndexGoodsFSPAdapter extends FragmentStatePagerAdapter {

    public List<String> list = new ArrayList<>();

    public SecKillIndexGoodsFSPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public SecKillIndexGoodsItemCardFragment getItem(int position) {

        return SecKillIndexGoodsItemCardFragment.newInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
