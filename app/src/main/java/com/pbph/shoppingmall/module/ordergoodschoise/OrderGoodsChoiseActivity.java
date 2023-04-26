package com.pbph.shoppingmall.module.ordergoodschoise;

import android.os.Bundle;

import com.google.gson.Gson;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.choice.until.ShopDetailBean;
import com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.choice.weight
        .ShoppingSelectView;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;

import java.io.InputStream;
import java.util.List;

/**
 * 订单商品选择
 */
public class OrderGoodsChoiseActivity extends BaseActivity<Presenter> implements Contract.View {


    CommonTitlebar commonTitlebar;
    private ShoppingSelectView shoppingSelectView;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_ordergoodschoise;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(OrderGoodsChoiseActivity.this, "订单商品选择", true);
        shoppingSelectView = findViewById(R.id.shoppingSelectView);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void clearHttpDatas() {

    }

    @Override
    public void setHttpDatas(List<String> list) {
        initJsonData();
        Gson gson = new Gson();
        ShopDetailBean shopDetailBean = gson.fromJson(jsonString, ShopDetailBean.class);
//        shoppingSelectView.setData(shopDetailBean);
//        shoppingSelectView.setOnSelectedListener((title, titleId, smallTitle, id) -> {
//
//        });
    }

    private String jsonString = "";

    //本地数据测试专用
    private void initJsonData() {
        try {
            InputStream is = context.getAssets().open("shopdetails.json");//打开json数据
            byte[] by = new byte[is.available()];//转字节
            is.read(by);
            jsonString = new String(by, "utf-8");
            is.close();//关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
