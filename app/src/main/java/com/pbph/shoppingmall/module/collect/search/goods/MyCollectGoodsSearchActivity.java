package com.pbph.shoppingmall.module.collect.search.goods;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetCustomerFollowListResponse;
import com.pbph.shoppingmall.module.similarity.SimilarityActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MyCollectGoodsSearchActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {


    ImageButton ibtn_left;
    EditText edt_search;
    TextView ibtn_msg;

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private DataAdapter adapter;

    View fl_gwc;
    TextView tv_gwc_num;
    List<String> addGwcList = new ArrayList<>();//添加到购物车的货物

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_mycollect_goods_search;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

//        View view = findViewById(R.id.titlebar_layout);
//        view.setBackgroundColor(Color.WHITE);

        ibtn_left = findViewById(R.id.ibtn_left);
        ibtn_left.setOnClickListener(onSingleClickListener);

        edt_search = findViewById(R.id.edt_search);
        edt_search.addTextChangedListener(textWatcher);
        edt_search.setOnEditorActionListener(onEditorActionListener);


        ibtn_msg = findViewById(R.id.ibtn_msg);
        ibtn_msg.setOnClickListener(onSingleClickListener);


        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = findViewById(R.id.lv_wuliu);
        listView.setVisibility(View.GONE);
        adapter = new DataAdapter(this, listView, CollectGoodsSearchViewHolder.class);
        adapter.setListener(onItemViewClickListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        fl_gwc = findViewById(R.id.fl_gwc);
        fl_gwc.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                goShoppingCar();
            }
        });
        tv_gwc_num = findViewById(R.id.tv_gwc_num);

    }


    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.ibtn_left: {

                    if (!StringUtils.isEmpty(edt_search.getText().toString())) {
                        edt_search.setText("");
                        adapter.clearDatas();
                        listView.setVisibility(View.GONE);
                        return;
                    }
                    finish();
                }
                break;
                case R.id.ibtn_msg: {
                    presenter.doSearch(edt_search.getText().toString().trim());
                }
                break;
            }
        }
    };


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
//            doSearch(s.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
    TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            //当actionId == XX_SEND 或者 XX_DONE时都触发
            //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                //处理事件
                presenter.doSearch(edt_search.getText().toString().trim());
            }
            return false;
        }
    };


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (!StringUtils.isEmpty(edt_search.getText().toString())) {
                edt_search.setText("");
                presenter.doSearch("");
                adapter.clearDatas();
                listView.setVisibility(View.GONE);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (rid, holder, objects) -> {

        switch (rid) {
            case R.id.tv_look_others: {//看相似
                goSimilarity();
            }
            break;
            case R.id.iv_gwc: { //购物车
                updateShoppingCar((String) holder.item);
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
        listView.setVisibility(View.GONE);
    }

    @Override
    public void setHttpDatas(List<GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean> list) {

        if (list == null || list.size() <= 0) return;

        listView.setVisibility(View.VISIBLE);

        adapter.setDatas(list);
    }

    void goShoppingCar() {

    }

    void goSimilarity() {
        startActivity(new Intent(context, SimilarityActivity.class)
                .putExtra("good_name", "")
                .putExtra("good_price", "")
                .putExtra("good_pic", ""));
    }

    void updateShoppingCar(String item) {
        if (addGwcList.contains(item)) {
            addGwcList.remove(item);
        } else {
            addGwcList.add(item);
        }
        updateShoppingCar();
    }

    void updateShoppingCar2(String item) {

        if (addGwcList.contains(item)) {
            addGwcList.remove(item);
        }
        updateShoppingCar();
    }

    void updateShoppingCar() {

        fl_gwc.setVisibility(addGwcList.size() <= 0 ? View.GONE : View.VISIBLE);

        tv_gwc_num.setText(String.valueOf(addGwcList.size()));
    }
}
