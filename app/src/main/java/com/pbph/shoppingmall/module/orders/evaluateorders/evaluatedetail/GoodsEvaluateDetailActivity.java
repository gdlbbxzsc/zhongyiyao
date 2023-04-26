package com.pbph.shoppingmall.module.orders.evaluateorders.evaluatedetail;


/**
 * 连嘉凡
 * 商品评价详情
 */

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetProductCommentResponse;
import com.pbph.shoppingmall.myview.MyListView;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.utils.DateUtils;

import java.util.List;

public class GoodsEvaluateDetailActivity extends BaseActivity<Presenter> implements Contract.View, View.OnClickListener, AdapterView.OnItemClickListener {

    CommonTitlebar titlebar;

    private MyListView lv_goods_evaluate ;
    private GoodsEvaluateDetailAdapter goodsEvaluateDetailAdapter;
    private GetProductCommentResponse.DataBean.CommentPageBeanBean.ListBean listBean;
    private ImageView iv_goods_evaluste_icon;//评论用户头像
    private TextView tv_goods_evaluste_name;//名称
    private RatingBar rating_goods_evaluste;//评价星星数量
    private TextView tv_goods_evaluste_date;//评价日期
    private TextView tv_iv_goods_evaluste_shop_name;//旗舰店名称
    private TextView tv_goods_content;//评价内容

    @Override
    protected int layoutResID() {
        return R.layout.activity_goods_evaluate_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        listBean = (GetProductCommentResponse.DataBean.CommentPageBeanBean.ListBean) getIntent().getSerializableExtra("evaluateDetail");
        goodsEvaluateDetailAdapter = new GoodsEvaluateDetailAdapter(this);

    }

    @Override
    protected void initView() {
        titlebar = new CommonTitlebar(GoodsEvaluateDetailActivity.this, "评论详情", true);
        lv_goods_evaluate = findViewById(R.id.lv_goods_evaluate);
        lv_goods_evaluate.setAdapter(goodsEvaluateDetailAdapter);
        if (listBean==null) return;
        goodsEvaluateDetailAdapter.setStringList(listBean.getShareImgList());
        iv_goods_evaluste_icon = findViewById(R.id.iv_goods_evaluste_icon);
        tv_goods_evaluste_name = findViewById(R.id.tv_goods_evaluste_name);
        tv_iv_goods_evaluste_shop_name = findViewById(R.id.tv_iv_goods_evaluste_shop_name);
        tv_goods_evaluste_date = findViewById(R.id.tv_goods_evaluste_date);
        tv_goods_content = findViewById(R.id.tv_goods_content);
        rating_goods_evaluste = findViewById(R.id.rating_goods_evaluste);

        Glide.with(context).load(listBean.getCustomerImg())
                .error(R.drawable.pingjiazhong_220x220)
                .into(iv_goods_evaluste_icon);
        tv_goods_evaluste_name.setText(listBean.getCustomerName());
        tv_iv_goods_evaluste_shop_name.setText(listBean.getStoreName());
        DateUtils dateUtils = new DateUtils(listBean.getPublishTime());
        tv_goods_evaluste_date.setText(dateUtils.getString(DateUtils.PATTERN_28));
        tv_goods_content.setText(listBean.getCommentContent());
        rating_goods_evaluste.setRating(listBean.getCommentScore()/20f);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void setHttpData(List<String> strings) {
        goodsEvaluateDetailAdapter.setStringList(strings);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(this, TypeGoodsListActivity.class);
//        startActivity(intent);
    }
}
