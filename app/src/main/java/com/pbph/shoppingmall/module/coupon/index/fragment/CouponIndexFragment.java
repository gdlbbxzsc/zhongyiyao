package com.pbph.shoppingmall.module.coupon.index.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetAllCouponListResponse;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class CouponIndexFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private DataAdapter adapter;

    int id = 0;

    @Override
    public int getid() {
        return id;
    }


    public static CouponIndexFragment newInstance(int id) {

        CouponIndexFragment f = new CouponIndexFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
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
        id = getArguments() != null ? getArguments().getInt("id") : 0;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_couponindex;
    }

    @Override
    public void initView(View view) {
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = view.findViewById(R.id.lv_shops1);

        adapter = new DataAdapter(this, listView, CouponIndexViewHolder.class);
        adapter.setListener(onItemViewClickListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

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

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (rid, holder, objects) -> {

        switch (rid) {
            case R.id.btn_coupon: {
                GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean vo = (GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean) holder.item;

                if (vo.getGetStatus() == 0) {
                    presenter.receiveCouponRequest(vo.getPpid());
                } else {
                    toastShort("请带我去一个新界面吧。");
                }
            }
            break;
        }
    };

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
    public void setHttpDatas(List<GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean> list) {

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            adapter.addData(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void receiveCouponRequest(int id) {
        for (int i = 0, c = adapter.getCount(); i < c; i++) {
            GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean vo = (GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean) adapter.getItem(i);
            if (vo.getPpid() != id) continue;
            vo.setGetStatus(1);
            adapter.notifyDataSetChanged();
        }
    }
}
