package com.pbph.shoppingmall.module.seckill.index.brand;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SecKillIndexBrandFragment extends BaseFragmentV4<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private HorizontalScrollView horizontalScrollView;
    private RadioGroup radioGroup;
    private RadioButton[] radioButtons;
    private LinearLayout llLinesLayout;
    private View[] rbLines;

    private ViewPager mViewPager;
    private SecKillIndexBrandFSPAdapter pagerAdapter;


    LayoutInflater inflater;
    int width;
    int height;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_seckillindex_brand;
    }

    @Override
    public void initView(View view) {

        inflater = LayoutInflater.from(context);
        width = getContext().getResources().getDimensionPixelSize(R.dimen.dp_130);
        height = getContext().getResources().getDimensionPixelSize(R.dimen.dp_50);
        //        int width = DensityUtils.dip2px(getContext(), 100);
//        int height = DensityUtils.dip2px(getContext(), 40);

        horizontalScrollView = view.findViewById(R.id.horizontalScrollView1);
        // imgLeft = (ImageView) findViewById(R.id.ImageViewleftsj);
        // imgRight = (ImageView) findViewById(R.id.ImageViewrightsj);

        radioGroup = view.findViewById(R.id.radioGroup1);
        llLinesLayout = view.findViewById(R.id.radioGroupBottomLine);

        // 设置了滚动条，如果选中的东西不在滚动条中部，而又可以滚动到中部，则将控件移动到中部。言之无物，看效果就知道了
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton rb = view.findViewById(checkedId);
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


        mViewPager = view.findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        isVisible = isVisibleToUser;

        if (isVisibleToUser) {

        } else {

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) return;

        int pos = buttonView.getId();

        for (int i = 0; i < rbLines.length; i++) {
            rbLines[i].setVisibility(i == pos ? View.VISIBLE : View.INVISIBLE);
        }
        mViewPager.setCurrentItem(pos);
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
    public void clearHttpDatas() {

    }

    @Override
    public void setHttpDatas(List<String> list) {

        radioButtons = new RadioButton[list.size()];
        rbLines = new View[list.size()];

        for (int i = 0, count = list.size(); i < count; i++) {
            addItem(i, list.get(i));
        }
        pagerAdapter = new SecKillIndexBrandFSPAdapter(getChildFragmentManager(), list);
        mViewPager.setAdapter(pagerAdapter);

        radioButtons[0].setChecked(true);
        for (int i = 0; i < rbLines.length; i++) {
            rbLines[i].setVisibility(i == 0 ? View.VISIBLE : View.INVISIBLE);
        }
    }

    void addItem(int i, String vo) {

        {
            LinearLayout temp = (LinearLayout) inflater.inflate(R.layout.layout_redline, null);
            llLinesLayout.addView(temp);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) temp.getLayoutParams();
            params.width = width;
            rbLines[i] = temp.findViewById(R.id.radioButtonline);
        }
        {
            radioButtons[i] = (RadioButton) inflater.inflate(R.layout.radiobutton_seckillindexbrand, null);
            radioGroup.addView(radioButtons[i]);
            RadioGroup.LayoutParams params = (RadioGroup.LayoutParams) radioButtons[i].getLayoutParams();
            params.width = width;
            params.height = height;
            radioButtons[i].setId(i);
            radioButtons[i].setText(vo + i);
            radioButtons[i].setOnCheckedChangeListener(this);
        }
    }
}
