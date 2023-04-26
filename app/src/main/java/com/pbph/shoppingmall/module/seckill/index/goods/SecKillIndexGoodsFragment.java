package com.pbph.shoppingmall.module.seckill.index.goods;

import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.LongTime2HMS;
import com.pbph.shoppingmall.utils.ui.SecTimer;
import com.utils.DateUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SecKillIndexGoodsFragment extends BaseFragmentV4<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private HorizontalScrollView horizontalScrollView;
    private RadioGroup radioGroup;
    private RadioButton[] radioButtons;

    private TextView tvState;
    private TextView tvHh;
    private TextView tvMm;
    private TextView tvSs;

    SecTimer secTimer;

    private ViewPager mViewPager;
    private SecKillIndexGoodsFSPAdapter pagerAdapter;


    LayoutInflater inflater;
//    int width;
//    int height;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_seckillindex_goods;
    }

    @Override
    public void initView(View view) {

        inflater = LayoutInflater.from(context);
//        width = getContext().getResources().getDimensionPixelSize(R.dimen.dp_130);
//        height = getContext().getResources().getDimensionPixelSize(R.dimen.dp_50);
        //        int width = DensityUtils.dip2px(getContext(), 100);
//        int height = DensityUtils.dip2px(getContext(), 40);

        horizontalScrollView = view.findViewById(R.id.horizontalScrollView1);
        // imgLeft = (ImageView) findViewById(R.id.ImageViewleftsj);
        // imgRight = (ImageView) findViewById(R.id.ImageViewrightsj);

        radioGroup = view.findViewById(R.id.radioGroup1);


        // 设置了滚动条，如果选中的东西不在滚动条中部，而又可以滚动到中部，则将控件移动到中部。言之无物，看效果就知道了
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == -1) return;

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

        tvState = view.findViewById(R.id.tv_state);

        tvHh = view.findViewById(R.id.tv_hh);
        tvMm = view.findViewById(R.id.tv_mm);
        tvSs = view.findViewById(R.id.tv_ss);

        final long beginTime = new DateUtils().getLong();
        secTimer = new SecTimer() {
            @Override
            public void onTick(long passTime) throws Exception {
                LongTime2HMS time = LongTime2HMS.longTime2HMS(beginTime - passTime);
                tvHh.setText(time.getH());
                tvMm.setText(time.getM());
                tvSs.setText(time.getS());
            }
        };


        mViewPager = view.findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);
        pagerAdapter = new SecKillIndexGoodsFSPAdapter(getChildFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        secTimer.start();
    }

    @Override
    public void onPause() {
        secTimer.cancel();
        super.onPause();
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

        mViewPager.setCurrentItem(buttonView.getId());
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

        radioGroup.clearCheck();
        radioGroup.removeAllViews();
        pagerAdapter.list.clear();
        pagerAdapter.notifyDataSetChanged();

        radioButtons = new RadioButton[list.size()];

        for (int i = 0, count = list.size(); i < count; i++) {
            addItem(i, list.get(i));
        }
        pagerAdapter.list.addAll(list);
        pagerAdapter.notifyDataSetChanged();

        radioButtons[0].setChecked(true);
    }

    void addItem(int i, String vo) {

        radioButtons[i] = (RadioButton) inflater.inflate(R.layout.radiobutton_seckillindexgoods, null);
        radioGroup.addView(radioButtons[i]);
//            RadioGroup.LayoutParams params = (RadioGroup.LayoutParams) radioButtons[i].getLayoutParams();
//            params.width = width;
//            params.height = height;
        radioButtons[i].setId(i);
        radioButtons[i].setText(getText("00:00", i % 2 == 0 ? SecKillState.State_Start : SecKillState.State_End));
        radioButtons[i].setOnCheckedChangeListener(this);

    }

    SpannableString getText(String time, SecKillState state) {

        int ts1 = getContext().getResources().getDimensionPixelSize(R.dimen.sp_20);
        int ts2 = getContext().getResources().getDimensionPixelSize(R.dimen.sp_12);

        StringBuilder sb = new StringBuilder();
        sb.append(time);
        sb.append("\n");
        switch (state) {
            case State_Wait:
                sb.append("即将开始");
                break;
            case State_Start:
                sb.append("抢购中");
                break;
            case State_End:
                sb.append("已经结束");
                break;
        }

        SpannableString spannableString = new SpannableString(sb.toString());
        AbsoluteSizeSpan asSpan1 = new AbsoluteSizeSpan(ts1, false);
        AbsoluteSizeSpan asSpan2 = new AbsoluteSizeSpan(ts2, false);

        spannableString.setSpan(asSpan1, 0, time.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(asSpan2, time.length() + 1, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    enum SecKillState {
        State_Wait, State_Start, State_End
    }

}
