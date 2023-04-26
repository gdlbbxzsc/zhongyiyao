package com.pbph.shoppingmall.module.collect.goods.common;

import android.content.Intent;
import android.os.Bundle;
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
import com.pbph.shoppingmall.model.response.GetBrowseRecordListResponse;
import com.pbph.shoppingmall.model.response.GetCustomerFollowListResponse;
import com.pbph.shoppingmall.module.collect.goods.adapter.CollectGoodsListDataAdapter;
import com.pbph.shoppingmall.module.collect.goods.viewholder.CollectGoodsListViewHolder;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.similarity.SimilarityActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/19.
 */

public class CollectGoodsListCommonFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private CollectGoodsListDataAdapter adapter;

    String id = "#";

    LinearLayout ll_bottom;
    CheckBox cb_all;
    TextView tv_del;

    View fl_gwc;
    TextView tv_gwc_num;
    List<String> addGwcList = new ArrayList<>();//添加到购物车的货物
    List<GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean> listBean = new ArrayList<>();

    @Override
    public String getid() {
        return id;
    }

    public static CollectGoodsListCommonFragment newInstance(String id) {

        CollectGoodsListCommonFragment f = new CollectGoodsListCommonFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        f.setArguments(args);

        return f;
    }

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
        return R.layout.fragment_collectgoodslist;
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

        adapter = new CollectGoodsListDataAdapter(this, listView, CollectGoodsListViewHolder.class);
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
                StringBuffer sb = new StringBuffer();
                Iterator<Integer> iterator = adapter.choiceMap.keySet().iterator();
                while (iterator.hasNext()){
                    int position=iterator.next();
                    GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean obj= (GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean) adapter.getItem(position);
                    sb.append(",").append(obj.getGoodsInfoId());
                    listBean.remove(obj);
                }
                String id = null;
                if (sb.length()>0){
                    id = sb.substring(1);
                }
                presenter.doDel(id);
            }
        });

        fl_gwc = view.findViewById(R.id.fl_gwc);
        fl_gwc.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                goShoppingCar();
            }
        });
        tv_gwc_num = view.findViewById(R.id.tv_gwc_num);


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
        GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean vo = (GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean) adapter.getItem(position);
        startActivity(new Intent(getContext(), GoodsDetailActivity.class)
                .putExtra("goodsInfoId", vo.getGoodsInfoId())
        );
    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (rid, holder, objects) -> {
        switch (rid) {
            case R.id.tv_look_others: {//看相似
                goSimilarity();
            }
            break;
            case R.id.iv_gwc: { //购物车

                updateShoppingCar((GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean)holder.item);
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
    public void setHttpDatas(List<GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean> list) {

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


    @Override
    public List<GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean> getDelIds() {

        List<GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean> list = new ArrayList<>();

        Iterator<Map.Entry<Integer, Object>> iterator = adapter.choiceMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, Object> data = iterator.next();

            GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean vo = (GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean) data.getValue();
            list.add(vo);
        }
        return list;
    }

    @Override
    public void doDel() {
    /*    for (GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean vo : list) {
            adapter.removeData(vo);
            updateShoppingCar2(vo);
        }*/
        adapter.setDatas(listBean);
        adapter.clearChoices();
    }


    void goShoppingCar() {

    }

    void goSimilarity() {
        startActivity(new Intent(getContext(), SimilarityActivity.class)
                .putExtra("good_name", "")
                .putExtra("good_price", "")
                .putExtra("good_pic", ""));
    }

    void updateShoppingCar(GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean item) {

             presenter.addShoppingCar(item.getGoodsInfoId(), 1, 1, "1");
//            addGwcList.add(item);

//        updateShoppingCar();
    }

    void updateShoppingCar2(GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean item) {

        if (addGwcList.contains(item)) {
            addGwcList.remove(item);
        }
        updateShoppingCar();
    }

    void updateShoppingCar() {

        fl_gwc.setVisibility(addGwcList.size() <= 0 ? View.GONE : View.VISIBLE);

        tv_gwc_num.setText(String.valueOf(addGwcList.size()));
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
