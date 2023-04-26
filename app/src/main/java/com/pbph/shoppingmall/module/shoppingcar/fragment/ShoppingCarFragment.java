package com.pbph.shoppingmall.module.shoppingcar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.ShoppingCarResponse;
import com.pbph.shoppingmall.module.account.login.LoginActivity;
import com.pbph.shoppingmall.module.firmorder.FirmOrderActivity;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.shop.ShopActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.adapter.DataSpeAdapter;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.utils.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/19.
 * 购物车
 * 孟庆奎
 */

public class ShoppingCarFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    CommonTitlebar titlebar;
    CheckBox titlebar_right2;


    private LinearLayout ll_logout;


    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private DataSpeAdapter adapter;


    private View includeSettlement;
    CheckBox allChecked;
    private TextView total;
    private TextView youhui;


    private View includeEdit;

    boolean hasLeft;

    public static ShoppingCarFragment newInstance(boolean b) {

        ShoppingCarFragment f = new ShoppingCarFragment();
        Bundle args = new Bundle();
        args.putBoolean("hasLeft", b);
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
        hasLeft = getArguments() != null ? getArguments().getBoolean("hasLeft") : false;
    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void initView(View view) {


        ll_logout = view.findViewById(R.id.ll_logout);
        view.findViewById(R.id.tv_go_login).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        });

        titlebar = new CommonTitlebar(this, view, "购物车", false, hasLeft);
        titlebar_right2 = view.findViewById(R.id.titlebar_right2);
        titlebar_right2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                titlebar_right2.setText("完成");
                includeSettlement.setVisibility(View.GONE);
                includeEdit.setVisibility(View.VISIBLE);
            } else {
                titlebar_right2.setText("编辑");
                includeSettlement.setVisibility(View.VISIBLE);
                includeEdit.setVisibility(View.GONE);
            }

        });
        titlebar_right2.setText("编辑");
        titlebar_right2.setChecked(false);

        includeSettlement = view.findViewById(R.id.include_settlement);
        includeEdit = view.findViewById(R.id.include_edit);

        allChecked = view.findViewById(R.id.is_all_checked);
        allChecked.setOnCheckedChangeListener((buttonView, isChecked) -> {
            for (int i = 0; i < adapter.getCount(); i++) {
                Object obj = adapter.getItem(i);
                if (obj instanceof ShoppingCarResponse.DataBean.ListBean) {
                    ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) obj;
                    vo.storeCheck = isChecked;
                    continue;
                }
                if (obj instanceof ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) {
                    ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) obj;
                    vo.goodsCheck = isChecked;
                    continue;
                }
            }
            adapter.notifyDataSetChanged();
            setTotalPrice();
        });
        total = (TextView) view.findViewById(R.id.total);
        youhui = view.findViewById(R.id.youhui);

        view.findViewById(R.id.settlement).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

                List<ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean> temps = new ArrayList<>();

                for (int i = 0; i < adapter.getCount(); i++) {
                    Object obj = adapter.getItem(i);
                    if (obj instanceof ShoppingCarResponse.DataBean.ListBean) {
//                        ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) obj;
                        continue;
                    }
                    ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) obj;
                    if (!vo.goodsCheck) continue;

                    temps.add(vo);
                }
                if (temps.isEmpty()) {
                    return;
                }
                MyApplication.setDataMapData(FirmOrderActivity.class.getName(), temps);
                startActivity(new Intent(context, FirmOrderActivity.class)
                        .putExtra("orderType", FirmOrderActivity.FROM_SHOPPINGCAR)
                );

            }
        });
        view.findViewById(R.id.tv_collection).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

                Map<Integer, Boolean> temps = new HashMap<>();
                for (int i = 0; i < adapter.getCount(); i++) {
                    Object obj = adapter.getItem(i);
                    if (obj instanceof ShoppingCarResponse.DataBean.ListBean) {
                        ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) obj;
                        continue;
                    }
                    ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) obj;
                    if (!vo.goodsCheck) continue;

                    temps.put(vo.getGoodsInfoId(), false);
                }
                if (temps.isEmpty()) {
                    return;
                }
                presenter.saveGoodsFollow(temps);
            }
        });
        view.findViewById(R.id.tv_delete).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

                Map<Integer, Boolean> temps = new HashMap<>();
                for (int i = 0; i < adapter.getCount(); i++) {
                    Object obj = adapter.getItem(i);
                    if (obj instanceof ShoppingCarResponse.DataBean.ListBean) {
                        ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) obj;
                        continue;
                    }
                    ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) obj;
                    if (!vo.goodsCheck) continue;

                    temps.put(vo.getPpid(), false);
                }
                if (temps.isEmpty()) {
                    return;
                }
                presenter.delShoppingCart(temps);
            }
        });

        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new DataSpeAdapter(this, listView, ShopViewHolder.class, GoodsViewHolder.class, obj -> {
            ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) obj;
            return vo.getShoppingCartDTOList();
        });
        adapter.setListener(onItemViewClickListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_shoppingcar1;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (MyApplication.userInfo.getCustomerId() == null || StringUtils.isEmpty(MyApplication.userInfo.getToken())) {
            ll_logout.setVisibility(View.VISIBLE);
            titlebar_right2.setVisibility(View.INVISIBLE);
            titlebar_right2.setEnabled(false);
        } else {
            ll_logout.setVisibility(View.GONE);
            titlebar_right2.setVisibility(View.VISIBLE);
            titlebar_right2.setEnabled(true);
            presenter.getHttpDatasFirstPage();
        }
        allChecked.setChecked(false);
        total.setText("0.00");


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        DataSpeAdapter.KV kv = adapter.getKV(position);
        Object object = adapter.getItemByKV(kv);

//        如果判断成立标识点击的是商品否则认为点击的是商店
        if (kv.spos >= 0) {

            ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean product = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) object;
            startActivity(new Intent(getContext(), GoodsDetailActivity.class)
                    .putExtra("goodsInfoId", product.getGoodsInfoId())
            );
        } else {
            ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) object;
            startActivity(new Intent(getContext(), ShopActivity.class)
                    .putExtra("storeId", vo.getStoreId())
            );
        }
    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (rid, holder, objects) -> {

        DataSpeAdapter.KV kv = adapter.getKV(holder.position);
        switch (rid) {
            case R.id.is_shop_checked: {
                ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) holder.item;
                vo.storeCheck = !vo.storeCheck;

                for (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean product : vo.getShoppingCartDTOList()) {
                    product.goodsCheck = vo.storeCheck;
                }
                adapter.notifyDataSetChanged();

                setTotalPrice();
            }
            break;
            case R.id.is_good_checked: {
                ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean product = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) holder.item;
                product.goodsCheck = !product.goodsCheck;
                adapter.notifyDataSetChanged();
                setTotalPrice();
            }
            break;
            case R.id.reduce: {//-
                ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean product = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) holder.item;
                if (product.getGoodsNum() <= 0) return;

                product.setGoodsNum(product.getGoodsNum() - 1);
                adapter.notifyDataSetChanged();
                presenter.updateShoppingCart(product);
                setTotalPrice();
            }
            break;
            case R.id.plus: { //+
                ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean product = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) holder.item;
                product.setGoodsNum(product.getGoodsNum() + 1);
                adapter.notifyDataSetChanged();
                presenter.updateShoppingCart(product);
                setTotalPrice();
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
    public void setHttpDatas(List<ShoppingCarResponse.DataBean.ListBean> list) {
        adapter.addDatas(list);
    }

    @Override
    public void onDelShoppingCart(Map<Integer, Boolean> temps) {
        for (int i = adapter.getCount() - 1; i >= 0; i--) {
            Object obj = adapter.getItem(i);
            if (obj instanceof ShoppingCarResponse.DataBean.ListBean) {
                ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) obj;
                if (vo.getShoppingCartDTOList().isEmpty()) {
                    adapter.removeData(i);
                }
                continue;
            }

            ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) obj;
            Object id = temps.get(vo.getPpid());
            if (id == null) continue;
            adapter.removeData(i);
        }
        adapter.notifyDataSetChanged();
        setTotalPrice();
    }

    void setTotalPrice() {
        BigDecimal all = new BigDecimal(0);
        for (int i = 0; i < adapter.getCount(); i++) {
            Object obj = adapter.getItem(i);
            if (obj instanceof ShoppingCarResponse.DataBean.ListBean) {
                ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) obj;
                continue;
            }
//            if (obj instanceof ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) {
//                ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) obj;
//                vo.goodsCheck = isChecked;
//                continue;
//            }
            ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) obj;
            if (!vo.goodsCheck) continue;
            BigDecimal price = new BigDecimal(vo.getGoodsJoinPrice());
            BigDecimal nums = new BigDecimal(vo.getGoodsNum());
            all = price.multiply(nums).add(all);
        }
//        all.divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP);
        total.setText(StringUtils.moneyFen2Yuan(all.longValue()));
    }
}
