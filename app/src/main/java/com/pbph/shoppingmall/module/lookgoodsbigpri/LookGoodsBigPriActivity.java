package com.pbph.shoppingmall.module.lookgoodsbigpri;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;

import java.util.ArrayList;
import java.util.List;

public class LookGoodsBigPriActivity extends BaseActivity<Presenter> implements Contract.View {

    ViewPager vp_look_goods_big_pri;
    private LookGoodsBigPriAdapter lookGoodsBigPriAdapter;
    private ArrayList<String> imgs;
    private ArrayList<ImageView> imageViews;
    private TextView tv_table_num, tv_num;
    private int pos = 0;
    private ImageButton titlebar_left;

    @Override
    protected int layoutResID() {
        return R.layout.activity_lookgoodsbigpri;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        pos = getIntent().getIntExtra("pos", 0);
        imgs = getIntent().getStringArrayListExtra("imgs");
        imageViews = new ArrayList<>();
        lookGoodsBigPriAdapter = new LookGoodsBigPriAdapter(this);
        for (int i = 0; i < imgs.size(); i++) {
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(imgs.get(i))
                    .error(R.drawable.chakakndatu_750x750)
                    .into(imageView);
            imageViews.add(imageView);
        }
        lookGoodsBigPriAdapter.setImageViews(imageViews);
    }

    @Override
    protected void initView() {
        vp_look_goods_big_pri = findViewById(R.id.vp_look_goods_big_pri);
        tv_table_num = findViewById(R.id.tv_table_num);
        tv_num = findViewById(R.id.tv_num);
        titlebar_left = findViewById(R.id.titlebar_left);
        titlebar_left.setOnClickListener(v -> {
            finish();
        });
        tv_table_num.setText("/" + String.valueOf(imageViews.size()));
        vp_look_goods_big_pri.setAdapter(lookGoodsBigPriAdapter);
        vp_look_goods_big_pri.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_num.setText(String.valueOf(position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_look_goods_big_pri.setCurrentItem(pos);

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void setHttpData(List<String> strings) {

    }


}
