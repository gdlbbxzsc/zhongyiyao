package com.pbph.shoppingmall.module.coupon.mine.invalid;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SelectMyCouponListResponse;
import com.pbph.shoppingmall.module.coupon.mine.adapter.CouponListDataAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/19.
 */

public class CouponInvalidFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private CouponListDataAdapter adapter;


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_couponvalid;
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

        adapter = new CouponListDataAdapter(this, listView, CouponInvalidViewHolder.class);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        adapter.setChoice_type(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getHttpDatas(true);
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
        if (adapter.isEdit()) {
            adapter.putChoicedNotify(position);
        }
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
    public void setHttpDatas(List<SelectMyCouponListResponse.DataBean.CouponListBean.ListBean> list) {

        if (list == null || list.size() <= 0) return;

        adapter.addDatas(list);
//        for (int i = 0, count = list.size(); i < count; i++) {
//            adapter.addData(list.get(i));
//        }
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void delData(boolean isDel) {
        adapter.clearChoices();
        adapter.setEdit(isDel);
    }

    @Override
    public void chooseAll(boolean isChoose) {
        if (isChoose) {
            adapter.putChoicedAll();
        } else {
            adapter.clearChoices();
        }
    }

    @Override
    public List<SelectMyCouponListResponse.DataBean.CouponListBean.ListBean> getDelIds() {

        List<SelectMyCouponListResponse.DataBean.CouponListBean.ListBean> list = new ArrayList<>();

        Iterator<Map.Entry<Integer, Object>> iterator = adapter.choiceMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, Object> data = iterator.next();

            SelectMyCouponListResponse.DataBean.CouponListBean.ListBean vo = (SelectMyCouponListResponse.DataBean.CouponListBean.ListBean) data.getValue();
            list.add(vo);
        }
        return list;
    }

    @Override
    public void doDel(List<SelectMyCouponListResponse.DataBean.CouponListBean.ListBean> list) {
        for (SelectMyCouponListResponse.DataBean.CouponListBean.ListBean vo : list) {
            adapter.removeData(vo);
        }
        adapter.clearChoices();
    }
}
