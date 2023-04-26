package com.pbph.shoppingmall.module.goodstype;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStoreCategoryByStoreIdResponse;
import com.pbph.shoppingmall.module.shopgoodstypelist.TypeGoodsListActivity;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;

import java.util.List;

public class GoodsTypeActivity extends BaseActivity<Presenter> implements Contract.View, View
        .OnClickListener, AdapterView.OnItemClickListener {

    private ListView lv_goods_type;
    private GoodsTypeAdapter goodsTypeAdapter;
    private int storeId;
    List<GetStoreCategoryByStoreIdResponse.DataBean> stringList;
    @Override
    protected int layoutResID() {
        return R.layout.activity_goods_type;
    }


    @Override
    protected void initData() {
        storeId = getIntent().getIntExtra("storeId",0);
        goodsTypeAdapter = new GoodsTypeAdapter(this);

    }

    @Override
    protected void initView() {
        CommonTitlebar commonTitlebar = new CommonTitlebar(GoodsTypeActivity.this, "商品分类", true);
        lv_goods_type = findViewById(R.id.lv_goods_type);
        lv_goods_type.setAdapter(goodsTypeAdapter);
        lv_goods_type.setOnItemClickListener(this);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void setHttpData(List<GetStoreCategoryByStoreIdResponse.DataBean> stringList) {
        this.stringList = stringList;
        goodsTypeAdapter.setStringList(stringList);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getHttpData(storeId);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GetStoreCategoryByStoreIdResponse.DataBean dataBean = stringList.get(position);
        int ppid = dataBean.getPpid();
        String title = dataBean.getStoreCategoryName();
        Intent intent = new Intent(this, TypeGoodsListActivity.class);
        intent.putExtra("storeId",storeId);
        intent.putExtra("ppid",ppid);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}
