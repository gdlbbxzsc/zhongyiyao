package com.pbph.shoppingmall.module.goodsdetail.goodsdetailevaluate;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.response.GetProductCommentResponse;
import com.pbph.shoppingmall.module.orders.evaluateorders.evaluatedetail.GoodsEvaluateDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class GoodsDetailEvaluateFragment extends BaseFragmentV4<Presenter> implements Contract
        .View, View.OnClickListener {

    private RecyclerView recycler_goods_evaluate;
    private GoodsDetailEvaluateAdapter goodsDetailEvaluateAdapter;
    LinearLayoutManager layoutmanager;
    private int goodsInfoId;
    private RadioButton rbn_all_evaluate, rbn_good_evaluate, rbn_in_evaluate, rbn_bad_evaluate;
    private TextView tv_percent;
    private int  startRowNum = 1;
    private SmartRefreshLayout smartRefreshLayout;
    int type = 3;// 3 全部 0好评 1中评 2差评
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_goods_detail_evaluate;
    }

    @Override
    public void initView(View view) {
        recycler_goods_evaluate = view.findViewById(R.id.recycler_goods_evaluate);
        rbn_all_evaluate = view.findViewById(R.id.rbn_all_evaluate);
        rbn_good_evaluate = view.findViewById(R.id.rbn_good_evaluate);
        rbn_in_evaluate = view.findViewById(R.id.rbn_in_evaluate);
        rbn_bad_evaluate = view.findViewById(R.id.rbn_bad_evaluate);
        tv_percent = view.findViewById(R.id.tv_percent);
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        rbn_all_evaluate.setOnClickListener(this);
        rbn_good_evaluate.setOnClickListener(this);
        rbn_in_evaluate.setOnClickListener(this);
        rbn_bad_evaluate.setOnClickListener(this);
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            startRowNum++;
            presenter.getHttpData(startRowNum, Constant.Data.PAGE_COUNT, goodsInfoId, type, "");


        });

        smartRefreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        initData();
    }

    private void initData() {
        rbn_all_evaluate.setChecked(true);
        goodsInfoId = getActivity().getIntent().getIntExtra("goodsInfoId", 0);
        goodsDetailEvaluateAdapter = new GoodsDetailEvaluateAdapter(context);
        goodsDetailEvaluateAdapter.setGotoEvaluateInfo(listBean -> {
            Intent intent = new Intent();
            intent.setClass(context,GoodsEvaluateDetailActivity.class);
            intent.putExtra("evaluateDetail",listBean);
            startActivity(intent);
        });
        //创建LinearLayoutManager 对象
        layoutmanager = new LinearLayoutManager(context);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_goods_evaluate.setLayoutManager(layoutmanager);
        recycler_goods_evaluate.setAdapter(goodsDetailEvaluateAdapter);

    }

    @Override
    public void setGoodsCommentCount(GetProductCommentResponse.DataBean.GoodsCommentCountBean goodsCommentCountBean) {
        // colligate 综合
        rbn_all_evaluate.setText(goodsCommentCountBean.getCount()+"全部");
        rbn_good_evaluate.setText(goodsCommentCountBean.getHighopinion()+"好评");
        rbn_in_evaluate.setText(goodsCommentCountBean.getMiddlingopinion()+"中评");
        rbn_bad_evaluate.setText(goodsCommentCountBean.getInferior()+"差评");
        tv_percent.setText(goodsCommentCountBean.getColligate()+"%");

    }

    @Override
    public void setEvaluateData(List<GetProductCommentResponse.DataBean.CommentPageBeanBean.ListBean> dataBeans) {
    if (dataBeans.size()<20){
        smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
    }else {
        smartRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
    }
     if (startRowNum==1){
         goodsDetailEvaluateAdapter.setmList(dataBeans);
     }else {
         goodsDetailEvaluateAdapter.addDataBeanList(dataBeans);
         smartRefreshLayout.finishRefresh();
         smartRefreshLayout.finishLoadMore();
     }

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getHttpData(startRowNum, Constant.Data.PAGE_COUNT, goodsInfoId, 3, "");
    }


    @Override
    public void onClick(View v) {
        startRowNum = 1;
        switch (v.getId()) {
            case R.id.rbn_all_evaluate:
                type = 3;
                presenter.getHttpData(startRowNum, Constant.Data.PAGE_COUNT, goodsInfoId, type, "");
                break;
            case R.id.rbn_good_evaluate:
                type = 0;
                presenter.getHttpData(startRowNum, Constant.Data.PAGE_COUNT, goodsInfoId, type, "");
                break;
            case R.id.rbn_in_evaluate:
                type = 1;
                presenter.getHttpData(startRowNum, Constant.Data.PAGE_COUNT, goodsInfoId, type, "");
                break;
            case R.id.rbn_bad_evaluate:
                type = 2;
                presenter.getHttpData(startRowNum, Constant.Data.PAGE_COUNT, goodsInfoId, type, "");
                break;

        }
    }
}
