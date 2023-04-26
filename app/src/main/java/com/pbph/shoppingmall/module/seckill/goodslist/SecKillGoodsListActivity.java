package com.pbph.shoppingmall.module.seckill.goodslist;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.seckill.pop.SecKillGoodsTypeListPop;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;

public class SecKillGoodsListActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    CommonTitlebar commonTitlebar;

    private HorizontalScrollView horizontalScrollView;
    private RadioGroup radioGroup;
    private RadioButton[] radioButtons;
    private LinearLayout llLinesLayout;
    private View[] rbLines;

    private ImageView iv_all_pop;
    SecKillGoodsTypeListPop pop;

    private ViewPager mViewPager;
    private SecKillGoodsListAdapter pagerAdapter;

    LayoutInflater inflater;
    int width;
    int height;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_seckillgoodslist;
    }


    @Override
    protected void initData() {
        inflater = LayoutInflater.from(context);
        width = getContext().getResources().getDimensionPixelSize(R.dimen.dp_130);
        height = getContext().getResources().getDimensionPixelSize(R.dimen.dp_50);

    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "限时秒杀", false);


        horizontalScrollView = findViewById(R.id.horizontalScrollView1);
        // imgLeft = (ImageView) findViewById(R.id.ImageViewleftsj);
        // imgRight = (ImageView) findViewById(R.id.ImageViewrightsj);


        radioGroup = findViewById(R.id.radioGroup1);
        llLinesLayout = findViewById(R.id.radioGroupBottomLine);

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
        pop = new SecKillGoodsTypeListPop(this, commonTitlebar.layout);
        pop.gridView.setOnItemClickListener((parent, view, position, id) -> {
            pop.adapter.putChoicedNotify(position);
            radioButtons[position].setChecked(true);
            pop.dismiss();
        });
        pop.setOnDismissListener(() -> iv_all_pop.setImageLevel(0));
        iv_all_pop.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                iv_all_pop.setImageLevel(1);
                pop.show();
            }
        });

        mViewPager = findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);


    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) return;

        int pos = buttonView.getId();
        pop.adapter.putChoicedNotify(pos);
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
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void setHttpDatas(List<String> list) {
        LayoutInflater inflater = LayoutInflater.from(context);

        radioButtons = new RadioButton[list.size()];
        rbLines = new View[list.size()];

        for (int i = 0, count = 10; i < count; i++) {
            addItem(i, list.get(i));

            pop.adapter.addData("" + i);
        }
        pop.adapter.notifyDataSetChanged();

        pagerAdapter = new SecKillGoodsListAdapter(getSupportFragmentManager(), list);
        mViewPager.setAdapter(pagerAdapter);

        radioButtons[0].setChecked(true);
        for (int i = 0; i < rbLines.length; i++) {
            rbLines[i].setVisibility(i == 0 ? View.VISIBLE : View.INVISIBLE);
        }
        pop.adapter.putChoicedNotify(0);
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
