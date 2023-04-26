package com.pbph.shoppingmall.module.message.type.notices;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetMyMessageListResponse;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class MessageTypeNoticesFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private DataAdapter adapter;


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_messagetype_notices;
    }

    @Override
    public void initView(View view) {

        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = view.findViewById(R.id.lv_tongzhi);

        adapter = new DataAdapter(this, listView, NoticesViewHolder.class);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getHttpDatasFirstPage();
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
    public void setHttpDatas(List<GetMyMessageListResponse.DataBean.MessageListBean.ListBean> list) {

        if (list == null || list.size() <= 0) return;

        for (GetMyMessageListResponse.DataBean.MessageListBean.ListBean vo : list) {
            if (vo.getMessageType() == 2)
                adapter.addData(vo);
        }

        adapter.notifyDataSetChanged();
    }
}
