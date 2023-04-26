package com.pbph.shoppingmall.module.seckill.index.brand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.seckill.brandlist.SecKillBrandListActivity;
import com.pbph.shoppingmall.utils.ui.SecTimer;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SecKillIndexBrandItemCardFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    SecTimer secTimer;

    private ListView listView;
    private SecKillIndexBrandItemCardDataAdapter adapter;

    String id = "#";

    @Override
    public String getid() {
        return id;
    }

    public static SecKillIndexBrandItemCardFragment newInstance(String id) {

        SecKillIndexBrandItemCardFragment f = new SecKillIndexBrandItemCardFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        f.setArguments(args);

        return f;
    }

    /**
     * 当调用该方法时，检索此实例的数量的参数。
     * （When creating, retrieve this instance's number from
     * its arguments.）
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments() != null ? getArguments().getString("id") : null;
        scaleWH();
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_seckillindex_brand_itemcard;
    }

    @Override
    public void initView(View view) {


        listView = view.findViewById(R.id.lv_wuliu);

        adapter = new SecKillIndexBrandItemCardDataAdapter(this, listView, SecKillIndexBrandItemCardViewHolder.class);
        adapter.h = img_h;
        adapter.wh = img_wh;

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        secTimer = new SecTimer() {
            @Override
            public void onTick(long passTime) throws Exception {
                for (int i = 0, c = adapter.interfaceList.size(); i < c; i++) {
                    adapter.interfaceList.get(i).onTimeChange(passTime);
                }
            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();
        secTimer.start();
    }

    @Override
    public void onPause() {
        secTimer.cancel();
        super.onPause();
    }

    boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        isVisible = isVisibleToUser;

        if (isVisibleToUser) {

        } else {

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getContext(), SecKillBrandListActivity.class));
    }

    @Override
    public void clearHttpDatas() {
        adapter.clearDatas();
    }

    @Override
    public void setHttpDatas(List<String> list) {

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            adapter.addData(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    int line_nums = 3;
    int img_wh = 0;

    int img_h = 0;

    private void scaleWH() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        img_wh = outMetrics.widthPixels;
        int wh = context.getResources().getDimensionPixelSize(R.dimen.dp_16) * (2);
        img_wh -= wh;

        img_h = img_wh * 2 / 5;

        img_wh /= line_nums;
        img_wh -= context.getResources().getDimensionPixelSize(R.dimen.dp_6) * 2;
    }
}
