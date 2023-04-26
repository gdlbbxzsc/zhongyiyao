package com.pbph.shoppingmall.module.collect.shops.type;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetCollectionSellerListResponse;
import com.pbph.shoppingmall.module.collect.shops.adapter.CollectShopsListDataAdapter;
import com.pbph.shoppingmall.module.collect.shops.viewholder.CollectShopsListViewHolder;
import com.pbph.shoppingmall.module.shop.ShopActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/19.
 */

public class CollectShopsListTypeFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private CollectShopsListDataAdapter adapter;


    LinearLayout ll_bottom;
    CheckBox cb_all;
    TextView tv_del;


    public static CollectShopsListTypeFragment newInstance() {

        CollectShopsListTypeFragment f = new CollectShopsListTypeFragment();
//        Bundle args = new Bundle();
//        args.putString("id", id);
//        f.setArguments(args);
        return f;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_collectshopslist;
    }

    @Override
    public void initView(View view) {
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = view.findViewById(R.id.lv_wuliu);

        adapter = new CollectShopsListDataAdapter(this, listView, CollectShopsListViewHolder.class);
        adapter.setListener(onItemViewClickListener);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        adapter.setChoice_type(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        ll_bottom = view.findViewById(R.id.ll_bottom);
        cb_all = view.findViewById(R.id.cb_all);

        tv_del = view.findViewById(R.id.tv_del);
        tv_del.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                doDel();
            }
        });


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


    CompoundButton.OnCheckedChangeListener allListener = (buttonView, isChecked) -> {
        chooseAll(isChecked);
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (adapter.isEdit()) {
            adapter.putChoicedNotify(position);
        }
    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (rid, holder, objects) -> {
        switch (rid) {
            case R.id.tv_shopstreet_shops_enter: {
                GetCollectionSellerListResponse.DataBean.CollectionSellerListBean.ListBean vo = (GetCollectionSellerListResponse.DataBean.CollectionSellerListBean.ListBean) holder.item;
                startActivity(new Intent(getContext(), ShopActivity.class)
                        .putExtra("storeId", vo.getStoreId())
                );
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
    public void setHttpDatas(List<GetCollectionSellerListResponse.DataBean.CollectionSellerListBean.ListBean> list) {

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            adapter.addData(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }


    void delData(boolean isDel) {
        adapter.clearChoices();
        adapter.setEdit(isDel);
    }

    void chooseAll(boolean isChoose) {
        if (isChoose) {
            adapter.putChoicedAll();
        } else {
            adapter.clearChoices();
        }
    }


    void doDel() {
        Iterator<Map.Entry<Integer, Object>> iterator = adapter.choiceMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Object> data = iterator.next();
            adapter.removeData(data.getValue());
        }
        adapter.clearChoices();
    }

    @Override
    public void changeEditState(boolean isChecked) {
        if (isChecked) {
//            titlebar_right2.setText("完成");
            ll_bottom.setVisibility(View.VISIBLE);

            cb_all.setOnCheckedChangeListener(null);
            cb_all.setChecked(false);
            cb_all.setOnCheckedChangeListener(allListener);

            delData(true);
        } else {
//            titlebar_right2.setText("编辑");
            ll_bottom.setVisibility(View.GONE);

            delData(false);
        }
    }

}
