package com.pbph.shoppingmall.module.seckill.goodslist.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SecKillGoodsListItemCardFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    private ListView listView;
    private DataAdapter adapter;

    String id = "#";

    @Override
    public String getid() {
        return id;
    }

    public static SecKillGoodsListItemCardFragment newInstance(String id) {

        SecKillGoodsListItemCardFragment f = new SecKillGoodsListItemCardFragment();
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
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_seckill_goodslist_itemcard;
    }

    @Override
    public void initView(View view) {


        listView = view.findViewById(R.id.lv_wuliu);

        adapter = new DataAdapter(this, listView, SecKillGoodsListItemCardViewHolder.class);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

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
}
