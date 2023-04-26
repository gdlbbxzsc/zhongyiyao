package com.pbph.shoppingmall.module.goodsdetail.goodsdetailsdetail;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.goodsdetail.goodsdetailsdetail.detailinfo.GoodsDetailDetailInfoFragment;
import com.pbph.shoppingmall.module.goodsdetail.goodsdetailsdetail.detailspecifications.GoodsDetailSpecificationsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class GoodsDetailDetailFragment extends BaseFragmentV4 implements Contract.View {

    private int id;
    private TabLayout goods_detail_tablayout;
    private ViewPager goods_detail_viewpager;
    private GoodsDetailsDetailAdapter goodsDetailsDetailAdapter;
    private List<Fragment> fragments ;
    private WebView webView;

    public static GoodsDetailDetailFragment newInstance(int id) {
        GoodsDetailDetailFragment f = new GoodsDetailDetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);
        return f;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments() != null ? getArguments().getInt("id", 0) : 0;


    }

    @Override
    protected IBasePresenter createPresenter() {
        return new Presenter(this);

    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_goods_detail_detail;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void initView(View view) {
        goods_detail_tablayout = view.findViewById(R.id.goods_detail_tablayout);
        goods_detail_viewpager = view.findViewById(R.id.goods_detail_viewpager);
        webView= view.findViewById(R.id.webwiew);

        //        webView.loadUrl("file:///android_asset/test.html");//加载asset文件夹下html

        //使用webview显示html代码
//        webView.loadDataWithBaseURL(null,"<html><head><title> 欢迎您 </title></head>" +
//                "<body><h2>使用webview显示 html代码</h2></body></html>", "text/html" , "utf-8", null);

        webView.addJavascriptInterface(this,"android");//添加js监听 这样html就能调用客户端
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webView.loadUrl("http://www.baidu.com");//加载url

        //不显示webview缩放按钮
//        webSettings.setDisplayZoomControls(false);
        initData();
    }

    private void initData() {
        goodsDetailsDetailAdapter = new GoodsDetailsDetailAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();
        fragments.add(new GoodsDetailDetailInfoFragment());
//        fragments.add(new GoodsDetailPackingFragment());
        fragments.add(new GoodsDetailSpecificationsFragment());
        goodsDetailsDetailAdapter.setFragments(fragments);
        goods_detail_viewpager.setAdapter(goodsDetailsDetailAdapter);
        goods_detail_tablayout.setupWithViewPager(goods_detail_viewpager);

    }

    @Override
    public int getShopId() {
        return id;
    }

    @Override
    public void setHttpData(List<String> i) {

    }
    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient=new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
//            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("ansen","拦截url:"+url);
            if(url.equals("http://www.google.com/")){
                Toast.makeText(context,"国内不能访问google,拦截该url",Toast.LENGTH_LONG).show();
                return true;//表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient=new WebChromeClient(){
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定",null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen","网页标题:"+title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            progressBar.setProgress(newProgress);
        }
    };
}
