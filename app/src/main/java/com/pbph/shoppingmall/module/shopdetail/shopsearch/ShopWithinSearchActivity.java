package com.pbph.shoppingmall.module.shopdetail.shopsearch;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.dao.SearchRecord;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.search.MsgType;
import com.pbph.shoppingmall.utils.ui.FlowLayout;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/7/3.
 */

public class ShopWithinSearchActivity extends BaseActivity<Presenter> implements Contract.View,
        View.OnClickListener {
    private int storeId;//店铺id
    ImageButton ibtn_left;
    EditText edt_search;
    TextView ibtn_msg;
    private int pageN0 = 1;

    private LinearLayout include_list;//列表
    private RecyclerView search_recycler;
    private TypeGoodsListAdapter typeGoodsListAdapter;

    private LinearLayout include_recent;//搜索历史
    private ImageButton ibtn_del_recent;
    private FlowLayout flowLayout;

    private SmartRefreshLayout smartRefreshLayout;


    @Override
    protected int layoutResID() {
        return R.layout.activity_shopwithinsearch;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void initData() {
        storeId = getIntent().getIntExtra("storeId", 0);
        typeGoodsListAdapter = new TypeGoodsListAdapter(this);
        typeGoodsListAdapter.setTransmitData(goodsId -> {
            context.startActivity(new Intent(context, GoodsDetailActivity.class).putExtra
                    ("goodsInfoId", goodsId));
        });
    }

    @Override
    protected void initView() {
        ibtn_left = findViewById(R.id.ibtn_left);
        ibtn_left.setOnClickListener(this);

        edt_search = findViewById(R.id.edt_search);

        ibtn_msg = findViewById(R.id.ibtn_msg);
        ibtn_msg.setOnClickListener(this);

        include_list = findViewById(R.id.include_list);
        search_recycler = findViewById(R.id.search_recycler);
        search_recycler.setLayoutManager(new LinearLayoutManager(context));
        search_recycler.setHasFixedSize(true);
        search_recycler.setAdapter(typeGoodsListAdapter);

        include_recent = findViewById(R.id.include_recent);
        ibtn_del_recent = findViewById(R.id.ibtn_del_recent);
        ibtn_del_recent.setOnClickListener(this);
        flowLayout = findViewById(R.id.flowLayout);

        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        smartRefreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能

        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
//            presenter.doSearch();
            pageN0 = 1;
            presenter.doSearch(edt_search.getText().toString().trim(), storeId, pageN0);
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pageN0++;
            presenter.doSearch(edt_search.getText().toString().trim(), storeId, pageN0);
        });
    }

    @Override
    public void initSearchRecords(List<SearchRecord> list) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        TextView cb;

        for (int i = 0, count = flowLayout.getChildCount(); i < count; i++) {
            cb = (TextView) flowLayout.getChildAt(i);
            cb.setVisibility(View.GONE);
        }
        if (list == null) return;

        for (int i = 0, count = list.size(); i < count; i++) {

            SearchRecord vo = list.get(i);

            if (i < flowLayout.getChildCount()) {
                cb = (TextView) flowLayout.getChildAt(i);
                setRecordTextView(cb, i, vo.getSearchText());
            } else {
                cb = (TextView) inflater.inflate(R.layout.layout_recent_textview, null);
                setRecordTextView(cb, i, vo.getSearchText());
                flowLayout.addViewByLayoutParams(cb);
            }
            cb.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void changeView(MsgType type) {
        switch (type) {
            case Msg_Type_Recent: {
                include_list.setVisibility(View.GONE);
                include_recent.setVisibility(View.VISIBLE);
                smartRefreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
                smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能

            }
            break;
            case Msg_Type_Fragment: {
                include_list.setVisibility(View.VISIBLE);
                include_recent.setVisibility(View.GONE);
                smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
                smartRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
            }
            break;
        }
    }


    @Override
    public void setHttpData(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean
            .DataBean> strings) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if (pageN0==1){
            typeGoodsListAdapter.setStrings(strings);
        }else {
            if (strings.size()<20){
                smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
            }
            typeGoodsListAdapter.addStrings(strings);
        }
    }

    private void setRecordTextView(TextView cb, int i, String str) {
        cb.setId(i);
        cb.setText(str);
        cb.setOnClickListener(onFlowClickListener);
        cb.setMaxLines(1);
    }

    OnSingleClickListener onFlowClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View view) {
            TextView tv = (TextView) view;
            String str = tv.getText().toString().trim();
            edt_search.setText(str);
            presenter.doSearch(str, storeId, pageN0);
        }
    };


    @Override
    public void httpError() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_left: {
                finish();
            }
            break;
            case R.id.ibtn_msg: {
                presenter.doSearch(edt_search.getText().toString().trim(), storeId, pageN0);

            }
            break;

            case R.id.ibtn_del_recent: {
                presenter.deleteSearchRecords();
            }
            break;
        }
    }
}
