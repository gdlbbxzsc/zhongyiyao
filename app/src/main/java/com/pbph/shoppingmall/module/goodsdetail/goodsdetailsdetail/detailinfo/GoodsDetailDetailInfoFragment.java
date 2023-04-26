package com.pbph.shoppingmall.module.goodsdetail.goodsdetailsdetail.detailinfo;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.message.GoodsDetailDescHtmlMsg;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class GoodsDetailDetailInfoFragment extends BaseFragmentV4<Presenter> implements Contract
        .View {

    private int id;
    private WebView good_info_webview;
    private  String goodsDetailDescHtml = "";


    public static GoodsDetailDetailInfoFragment newInstance(int id) {
        GoodsDetailDetailInfoFragment f = new GoodsDetailDetailInfoFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);
        return f;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments() != null ? getArguments().getInt("id", 0) : 0;
        RxBusF.register0(this, GoodsDetailDescHtmlMsg.class, it -> {
            goodsDetailDescHtml = it.goodsDetailDescHtml;
            getActivity().runOnUiThread(() -> simpleJsClick());
        });
    }


    @Override
    protected int layoutResID() {
        return R.layout.fragment_goods_detailinfo;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void initView(View view) {
        good_info_webview = mContentView.findViewById(R.id.good_info_webview);
//        good_info_webview.getSettings().setJavaScriptEnabled(true);
//        good_info_webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//        good_info_webview.setHorizontalScrollBarEnabled(false);
//        good_info_webview.getSettings().setSupportZoom(true);
//        good_info_webview.getSettings().setBuiltInZoomControls(true);
//        good_info_webview.setInitialScale(70);
//        good_info_webview.setHorizontalScrollbarOverlay(true);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scale = dm.densityDpi;
        if (scale == 240) { //
            good_info_webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (scale == 160) {
            good_info_webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else {
            good_info_webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        }
        simpleJsClick();
    }

    public void simpleJsClick() {
        String data = "<!DOCTYPE html>" +
                "<html>" +
                "   <head>" +
                "       <meta charset=\"utf-8\">" +
                "       <meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,user-scalable=no\">" +
                "       <meta http-equiv=\"pragma\" content=\"no-cache\">" +
                "       <meta http-equiv=\"Cache-Control\" content=\"no-cache, no-store, must-revalidate\" />" +
                "       <meta http-equiv=\"Pragma\" content=\"no-cache\" />" +
                "       <meta http-equiv=\"Expires\" content=\"0\" />" +
                "   <style>img{max-width:100%;}</style>" +
                "   </head>" +
                "   <body>"
                +goodsDetailDescHtml+
                "   </body>" +
                "</html>";

        good_info_webview.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
        good_info_webview.getSettings().setJavaScriptEnabled(true);
        good_info_webview.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public int getShopId() {
        return id;
    }

    @Override
    public void setHttpData(List<String> i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (good_info_webview!=null){
            good_info_webview.onResume();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (good_info_webview!=null){
            good_info_webview.onPause();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (good_info_webview != null) {
            good_info_webview = null;
        }
        RxBusF.removeDisposable0(this, GoodsDetailDescHtmlMsg.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
