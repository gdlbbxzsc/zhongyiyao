package com.pbph.shoppingmall.module.main.type;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pbph.shoppingmall.model.response.GetClassifyResponse;
import com.pbph.shoppingmall.module.main.type.childtypelist.ChildTypesFragment;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/5.
 */

public class TypeAdapter extends FragmentPagerAdapter {
    List<GetClassifyResponse.DataBean.CatesBean> catesBeans;

    public void setTitles(List catesBeans) {
        this.catesBeans = catesBeans;
        notifyDataSetChanged();

    }

    public TypeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ChildTypesFragment();
        Bundle bundle = new Bundle();
        int str = catesBeans.get(position).getPpid();
        bundle.putString("typename", String.valueOf(str));
        bundle.putInt("typePos",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return catesBeans != null ? catesBeans.size() : 0;
    }
}
