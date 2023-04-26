package com.pbph.shoppingmall.module.myscore;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetMyCustomerPointListResponse;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

public class MyScoreActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {


    CommonTitlebar commonTitlebar;


    TextView tv_score;

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private DataAdapter adapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_myscore;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(this, "我的积分", true);


        tv_score = findViewById(R.id.tv_score);
        tv_score.setText(getIntent().getStringExtra("score"));


        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = findViewById(R.id.lv_message);

        adapter = new DataAdapter(this, listView, 2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore) {
        smartRefreshLayout.setEnableRefresh(finishRefresh);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(finishLoadMore);//是否启用上拉加载功能
    }

    @Override
    public void finishSmartRefresh() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void clearHttpDatas() {
        adapter.clearDatas();
    }

    @Override
    public void setHttpDatas(List<GetMyCustomerPointListResponse.DataBean.ListBean> list) {

        if (list == null || list.size() <= 0) return;


        for (int i = 0, count = list.size(); i < count; i++) {

            GetMyCustomerPointListResponse.DataBean.ListBean vo = list.get(i);


            adapter.addData(vo.getMonth(), MyScoreTimeViewHolder.class);

            List<GetMyCustomerPointListResponse.DataBean.ListBean.PointListBean> slist = vo.getPointList();
            if (slist == null || slist.isEmpty()) continue;

            for (int j = 0, c = slist.size(); j < c; j++) {
                GetMyCustomerPointListResponse.DataBean.ListBean.PointListBean pvo = slist.get(j);
                adapter.addData(pvo, MyScoreContentViewHolder.class);
            }
        }
        adapter.notifyDataSetChanged();
    }

}
