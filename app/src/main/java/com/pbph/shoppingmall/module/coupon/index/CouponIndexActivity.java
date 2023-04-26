package com.pbph.shoppingmall.module.coupon.index;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetCouponCategoryListResponse;
import com.pbph.shoppingmall.module.coupon.index.pop.CouponIndexTypeListPop;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;

public class CouponIndexActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    CommonTitlebar commonTitlebar;

    private HorizontalScrollView horizontalScrollView;
    private RadioGroup radioGroup;


    private CheckBox cb_all;
    private RadioButton[] radioButtons;

    private ImageView iv_all_pop;
    CouponIndexTypeListPop pop;

    private ViewPager mViewPager;
    private CouponIndexFSPAdapter pagerAdapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_salespromotion;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "领券中心", false);

        horizontalScrollView = findViewById(R.id.horizontalScrollView1);
        // imgLeft = (ImageView) findViewById(R.id.ImageViewleftsj);
        // imgRight = (ImageView) findViewById(R.id.ImageViewrightsj);

        cb_all = findViewById(R.id.cb_all);

        radioGroup = findViewById(R.id.radioGroup1);
        // 设置了滚动条，如果选中的东西不在滚动条中部，而又可以滚动到中部，则将控件移动到中部。言之无物，看效果就知道了
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton rb = findViewById(checkedId);
            int left = rb.getLeft() + rb.getWidth() / 2;

            int half = 0;
            try {
                half = horizontalScrollView.getWidth() >> 1;
            } catch (Exception e) {
            }
            if (half <= 0) {
                return;
            }

            // if (left <= half) {
            // imgLeft.setVisibility(LinearLayout.INVISIBLE);
            // } else {
            // imgLeft.setVisibility(LinearLayout.VISIBLE);
            // }
            // if (left >= radioGroup.getWidth() - half) {
            // imgRight.setVisibility(LinearLayout.INVISIBLE);
            // } else {
            // imgRight.setVisibility(LinearLayout.VISIBLE);
            // }

            horizontalScrollView.smoothScrollBy((left - horizontalScrollView.getScrollX() - half), 0);

        });

        iv_all_pop = findViewById(R.id.iv_all_pop);
        pop = new CouponIndexTypeListPop(this, commonTitlebar.layout);
        pop.gridView.setOnItemClickListener((parent, view, position, id) -> {
            pop.adapter.putChoicedNotify(position);
            radioButtons[position].setChecked(true);
            pop.dismiss();
        });
        iv_all_pop.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                pop.show();
            }
        });

        mViewPager = findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) return;
        pop.adapter.putChoicedNotify(buttonView.getId());
        mViewPager.setCurrentItem(buttonView.getId());

        cb_all.setChecked(buttonView.getId() == 0);
    }


    @Override
    public void onPageSelected(int position) {
        radioButtons[position].setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void setHttpDatas(List<GetCouponCategoryListResponse.DataBean.CouponCategoryListBean> list) {
        LayoutInflater inflater = LayoutInflater.from(context);

        radioButtons = new RadioButton[list.size()];

        for (int i = 0, count = list.size(); i < count; i++) {

            GetCouponCategoryListResponse.DataBean.CouponCategoryListBean vo = list.get(i);

            radioButtons[i] = (RadioButton) inflater.inflate(R.layout.radiobutton_salespromotion, null);
            radioGroup.addView(radioButtons[i]);
            radioButtons[i].setId(i);
            radioButtons[i].setText(vo.getCatName());
            radioButtons[i].setOnCheckedChangeListener(this);

            if (i == 0) {
                cb_all.setText(vo.getCatName());
                cb_all.setOnClickListener(v -> {
                    cb_all.setChecked(true);
                    radioButtons[0].setChecked(true);
                });
            }
            pop.adapter.addData(vo);
        }
        pop.adapter.notifyDataSetChanged();

        pagerAdapter = new CouponIndexFSPAdapter(getSupportFragmentManager(), list);
        mViewPager.setAdapter(pagerAdapter);

        radioButtons[0].setChecked(true);
        pop.adapter.putChoicedNotify(0);
    }
}
