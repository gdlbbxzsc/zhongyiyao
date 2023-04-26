package com.pbph.shoppingmall.module.search.shops;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.search.shops.fragment.SearchShopsListFragment;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SearchShopsTypeFragment extends BaseFragmentV4<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    private RadioButton[] radioButtons = new RadioButton[3];
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_searchshopstype;
    }

    @Override
    public void initView(View view) {
        mViewPager = view.findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = view.findViewById(R.id.radioButton1);
        radioButtons[1] = view.findViewById(R.id.radioButton2);
        radioButtons[2] = view.findViewById(R.id.radioButton3);

        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setOnCheckedChangeListener(this);
        }

        initViewPager();

        radioButtons[0].setChecked(true);
    }

    private void initViewPager() {
        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        pagerAdapter.list.add(SearchShopsListFragment.newInstance(1));
        pagerAdapter.list.add(SearchShopsListFragment.newInstance(2));
        pagerAdapter.list.add(SearchShopsListFragment.newInstance(3));

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


    private void check(int pos) {
        mViewPager.setCurrentItem(pos);
    }

    @Override
    public void onPageSelected(int position) {
        radioButtons[position].setChecked(true);
        presenter.postRxBus4SendSeachText2Item();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
}
