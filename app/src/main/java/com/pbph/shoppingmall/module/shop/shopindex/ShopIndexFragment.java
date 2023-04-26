package com.pbph.shoppingmall.module.shop.shopindex;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStorePageFloorResponse;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.shop.ShopActivity;

/**
 * Created by Administrator on 2018/1/19.
 */

public class ShopIndexFragment extends BaseFragmentV4<Presenter> implements Contract.View, View
        .OnClickListener {
    int storeId;
    private View view1f, view2f, view3f, view4f;


    private TextView tv_shop_home_one_title;
    private ImageView iv_shop_home_1f_one;

    private TextView tv_shop_home_two_title;
    private ImageView iv_shop_home_2f_one, iv_shop_home_2f_two;

    private TextView tv_shop_home_three_title;
    private ImageView iv_shop_home_3f_one, iv_shop_home_3f_two, iv_shop_home_3f_three;

    private TextView tv_shop_home_four_title;
    private ImageView iv_shop_home_4f_one, iv_shop_home_4f_two;

    private ImageView iv_default_image;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_shop_index;
    }

    @Override
    public void initView(View view) {
        view1f = view.findViewById(R.id.include_1f);
        initView1f(view1f);
        view2f = view.findViewById(R.id.include_2f);
        initView2f(view2f);
        view3f = view.findViewById(R.id.include_3f);
        initView3f(view3f);
        view4f = view.findViewById(R.id.include_4f);
        initView4f(view4f);

        iv_default_image = view.findViewById(R.id.iv_default_image);


    }

    private void initView1f(View view1f) {
        tv_shop_home_one_title = view1f.findViewById(R.id.tv_shop_home_one_title);
        iv_shop_home_1f_one = view1f.findViewById(R.id.iv_shop_home_1f_one);
        iv_shop_home_1f_one.setOnClickListener(this);
    }

    private void initView2f(View view2f) {
        tv_shop_home_two_title = view2f.findViewById(R.id.tv_shop_home_two_title);
        iv_shop_home_2f_one = view2f.findViewById(R.id.iv_shop_home_2f_one);
        iv_shop_home_2f_two = view2f.findViewById(R.id.iv_shop_home_2f_two);
        iv_shop_home_2f_one.setOnClickListener(this);
        iv_shop_home_2f_two.setOnClickListener(this);

    }

    private void initView3f(View view3f) {
        tv_shop_home_three_title = view3f.findViewById(R.id.tv_shop_home_three_title);
        iv_shop_home_3f_one = view3f.findViewById(R.id.iv_shop_home_3f_one);
        iv_shop_home_3f_two = view3f.findViewById(R.id.iv_shop_home_3f_two);
        iv_shop_home_3f_three = view3f.findViewById(R.id.iv_shop_home_3f_three);
        iv_shop_home_3f_one.setOnClickListener(this);
        iv_shop_home_3f_two.setOnClickListener(this);
        iv_shop_home_3f_three.setOnClickListener(this);

    }

    private void initView4f(View view4f) {
        tv_shop_home_four_title = view4f.findViewById(R.id.tv_shop_home_four_title);
        iv_shop_home_4f_one = view4f.findViewById(R.id.iv_shop_home_4f_one);
        iv_shop_home_4f_two = view4f.findViewById(R.id.iv_shop_home_4f_two);
        iv_shop_home_4f_one.setOnClickListener(this);
        iv_shop_home_4f_two.setOnClickListener(this);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        storeId = ((ShopActivity) context).storeId;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getGetStorePageFloor(storeId);

    }

    GetStorePageFloorResponse.DataBean.FloorMapBean.OneFloorBean oneFloorBean;

    @Override
    public void setDefaultImage(String imageUrl) {
        if (!imageUrl.isEmpty()) {
            Glide.with(this).load(imageUrl).into(iv_default_image);
            view1f.setVisibility(View.GONE);
            view2f.setVisibility(View.GONE);
            view3f.setVisibility(View.GONE);
            view4f.setVisibility(View.GONE);

        }
    }

    @Override
    public void setOneFloor(GetStorePageFloorResponse.DataBean.FloorMapBean.OneFloorBean oneFloor) {
        if (oneFloor == null) return;
        oneFloorBean = oneFloor;
        tv_shop_home_one_title.setText(oneFloor.getFloorTiTle());
        Glide.with(this).load(oneFloor.getGoodsInfoAndImgMap().getMain().getGoodsInfoImageUrl())
                .into(iv_shop_home_1f_one);
    }

    GetStorePageFloorResponse.DataBean.FloorMapBean.TwoFloorBean twoFloorBean;

    @Override
    public void setTwoFloor(GetStorePageFloorResponse.DataBean.FloorMapBean.TwoFloorBean twoFloor) {
        if (twoFloor == null) return;
        twoFloorBean = twoFloor;
        tv_shop_home_two_title.setText(twoFloor.getFloorTiTle());
        Glide.with(this).load(twoFloor.getGoodsInfoAndImgMap().getLeft().getGoodsInfoImageUrl())
                .into(iv_shop_home_2f_one);
        Glide.with(this).load(twoFloor.getGoodsInfoAndImgMap().getRight().getGoodsInfoImageUrl())
                .into(iv_shop_home_2f_two);
    }

    GetStorePageFloorResponse.DataBean.FloorMapBean.ThreeFloorBean threeFloorBean;

    @Override
    public void setThreeFloor(GetStorePageFloorResponse.DataBean.FloorMapBean.ThreeFloorBean
                                          threeFloor) {
        if (threeFloor == null) return;
        threeFloorBean = threeFloor;
        tv_shop_home_three_title.setText(threeFloor.getFloorTiTle());
        Glide.with(this).load(threeFloor.getGoodsInfoAndImgMap().getLeft().getGoodsInfoImageUrl()
        ).into(iv_shop_home_3f_one);
        Glide.with(this).load(threeFloor.getGoodsInfoAndImgMap().getMain().getGoodsInfoImageUrl()
        ).into(iv_shop_home_3f_two);
        Glide.with(this).load(threeFloor.getGoodsInfoAndImgMap().getRight().getGoodsInfoImageUrl
                ()).into(iv_shop_home_3f_three);
    }

    GetStorePageFloorResponse.DataBean.FloorMapBean.FourFloorBean fourFloorBean;

    @Override
    public void setFourFloor(GetStorePageFloorResponse.DataBean.FloorMapBean.FourFloorBean
                                         fourFloor) {
        if (fourFloor == null) return;
        fourFloorBean = fourFloor;
        tv_shop_home_four_title.setText(fourFloor.getFloorTiTle());
        Glide.with(this).load(fourFloor.getGoodsInfoAndImgMap().getLeft().getGoodsInfoImageUrl())
                .into(iv_shop_home_4f_one);
        Glide.with(this).load(fourFloor.getGoodsInfoAndImgMap().getRight().getGoodsInfoImageUrl()
        ).into(iv_shop_home_4f_two);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shop_home_1f_one: {
                int goodsInfoId = oneFloorBean.getGoodsInfoAndImgMap().getMain().getGoodsInfoId();
                goGoodsDetail(goodsInfoId);
            }
            break;
            case R.id.iv_shop_home_2f_one: {
                int goodsInfoId = twoFloorBean.getGoodsInfoAndImgMap().getLeft().getGoodsInfoId();
                goGoodsDetail(goodsInfoId);
            }
            break;
            case R.id.iv_shop_home_2f_two: {
                int goodsInfoId = twoFloorBean.getGoodsInfoAndImgMap().getRight().getGoodsInfoId();
                goGoodsDetail(goodsInfoId);
            }
            break;
            case R.id.iv_shop_home_3f_one: {
                int goodsInfoId = threeFloorBean.getGoodsInfoAndImgMap().getLeft().getGoodsInfoId();
                goGoodsDetail(goodsInfoId);
            }
            break;
            case R.id.iv_shop_home_3f_two: {
                int goodsInfoId = threeFloorBean.getGoodsInfoAndImgMap().getMain().getGoodsInfoId();
                goGoodsDetail(goodsInfoId);
            }
            break;
            case R.id.iv_shop_home_3f_three: {
                int goodsInfoId = threeFloorBean.getGoodsInfoAndImgMap().getRight()
                        .getGoodsInfoId();
                goGoodsDetail(goodsInfoId);
            }
            break;
            case R.id.iv_shop_home_4f_one: {
                int goodsInfoId = fourFloorBean.getGoodsInfoAndImgMap().getLeft().getGoodsInfoId();
                goGoodsDetail(goodsInfoId);
            }
            break;
            case R.id.iv_shop_home_4f_two: {
                int goodsInfoId = fourFloorBean.getGoodsInfoAndImgMap().getRight().getGoodsInfoId();
                goGoodsDetail(goodsInfoId);
            }
            break;
        }
    }

    private void goGoodsDetail(int goodsInfoId) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra("goodsInfoId", goodsInfoId);
        startActivity(intent);
    }
}
