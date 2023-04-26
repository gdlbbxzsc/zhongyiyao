package com.pbph.shoppingmall.module.search.goods;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.search.goods.common.SearchGoodsListFragment;
import com.pbph.shoppingmall.module.search.goods.price.SearchGoodsListPriceFragment;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SearchGoodsTypeFragment extends BaseFragmentV4<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    private RadioButton[] radioButtons = new RadioButton[3];
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;
    RadioButton rb_price;

    CheckBox btn_shaixuan;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_searchgoodstype;
    }

    @Override
    public void initView(View view) {

        mViewPager = view.findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = view.findViewById(R.id.radioButton1);
        radioButtons[1] = view.findViewById(R.id.radioButton2);
        rb_price = radioButtons[2] = view.findViewById(R.id.radioButton3);

        btn_shaixuan = view.findViewById(R.id.cb_shaixuan);
        btn_shaixuan.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.postRxBus4showDrawer());

        Drawable drawable = getResources().getDrawable(R.drawable.xia);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        rb_price.setCompoundDrawables(null, null, drawable, null);

        radioButtons[0].setOnCheckedChangeListener(this);
        radioButtons[1].setOnCheckedChangeListener(this);
        radioButtons[2].setOnCheckedChangeListener(this);
//        radioButtons[3].setOnCheckedChangeListener(this);

        rb_price.setOnTouchListener(onPriceClickListener);

        initViewPager();

        radioButtons[0].setChecked(true);

    }

    private void initViewPager() {
        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        pagerAdapter.list.add(SearchGoodsListFragment.newInstance(1));
        pagerAdapter.list.add(SearchGoodsListFragment.newInstance(2));
        pagerAdapter.list.add(new SearchGoodsListPriceFragment());

        mViewPager.setAdapter(pagerAdapter);
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
    public boolean isFragmentVisible() {
        return isVisible;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) return;

        switch (buttonView.getId()) {
            case R.id.radioButton1:
                check(0);
                break;
            case R.id.radioButton2:
                check(1);
                break;
            case R.id.radioButton3:
                check(2);
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    View.OnTouchListener onPriceClickListener = (v, event) -> {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (!rb_price.isChecked())
                    rb_price.setChecked(true);
                else
                    onChangePriceType();
            }
            break;
        }
        return true;
    };


    boolean priceType = true;

    void onChangePriceType() {
        priceType = !priceType;

        if (priceType) {
            Drawable drawable = getResources().getDrawable(R.drawable.xia);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            rb_price.setCompoundDrawables(null, null, drawable, null);
        } else {
            Drawable drawable = getResources().getDrawable(R.drawable.shang);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            rb_price.setCompoundDrawables(null, null, drawable, null);
        }
        presenter.postRxBus4flushListByPrice(priceType);

    }


    private void check(int pos) {
        mViewPager.setCurrentItem(pos);
    }

    @Override
    public void onPageSelected(int position) {
        radioButtons[position].setChecked(true);
        presenter.postRxBus4SendSeachText2Item(false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

}
