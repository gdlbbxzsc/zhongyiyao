package com.pbph.shoppingmall.module.capture;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;

public class CaptureResultActivity extends BaseActivity<Presenter> implements Contract.View {

    CommonTitlebar commonTitlebar;

    LinearLayout root_view;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_capture_result;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "没有搜索结果", false);

        root_view = findViewById(R.id.root_view);
        root_view.setVisibility(View.GONE);

        gotoScan();
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    final int REQUESTCODE = 2222;

    public void gotoScan() {
//        Intent intent = new Intent(getContext(), CaptureActivity.class);
//        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onScanResult(requestCode, resultCode, data);
    }

    void onScanResult(int requestCode, int resultCode, Intent data) {

//        if (REQUESTCODE != requestCode || resultCode != RESULT_OK) {
//            finish();
//            return;
//        }
//        if (data == null) {
//            finish();
//            return;
//        }
//
//        String result = data.getStringExtra(Constant.CODED_CONTENT);
////        String result = data.getStringExtra("result");
//        Logger.e("扫描结果：" + result);
//
//        result = result.substring(result.lastIndexOf("/"));
//
//        if (!StringUtils.isNumber(result)) {
//            root_view.setVisibility(View.VISIBLE);
//            return;
//        }
////            跳转商品详情
//        startActivity(new Intent(context, GoodsDetailActivity.class)
//                .putExtra("goodsId", Integer.parseInt(result))
//        );
//        finish();
    }

}
