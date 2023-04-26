package com.pbph.shoppingmall.module.salespromotion.fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SalesPromotionFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    public GridView gridView;
    private SalesPromotionDataAdapter adapter;

    String id = "#";

    @Override
    public String getid() {
        return id;
    }

    public static SalesPromotionFragment newInstance(String id) {

        SalesPromotionFragment f = new SalesPromotionFragment();
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
        return R.layout.fragment_salespromotion;
    }

    @Override
    public void initView(View view) {


        gridView = view.findViewById(R.id.gridView);
        gridView.setNumColumns(line_nums);

        adapter = new SalesPromotionDataAdapter(this, gridView, SalesPromotionViewHolder.class);
        adapter.wh = img_wh;

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view1, position, id) -> {

        });

    }

    @Override
    public void onResume() {
        super.onResume();
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


    int line_nums = 2;
    int img_wh = 0;

    private void scaleWH() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        img_wh = outMetrics.widthPixels;
        int wh = context.getResources().getDimensionPixelSize(R.dimen.dp_16) * (line_nums - 1 + 2);
        img_wh -= wh;
        img_wh /= line_nums;
    }

}
