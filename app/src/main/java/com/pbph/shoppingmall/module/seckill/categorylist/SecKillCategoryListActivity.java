package com.pbph.shoppingmall.module.seckill.categorylist;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.LongTime2HMS;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.SecTimer;
import com.utils.DateUtils;

import java.util.List;

public class SecKillCategoryListActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {


    CommonTitlebar commonTitlebar;


    ImageView iv_category_banner;


    private TextView tvState;
    private TextView tvHh;
    private TextView tvMm;
    private TextView tvSs;

    SecTimer secTimer;

    private ListView listView;
    private DataAdapter adapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_seckillcategorylist;
    }


    @Override
    protected void initData() {
        scaleWH();
    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(this, "品类秒杀", true);

        iv_category_banner = findViewById(R.id.iv_category_banner);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) iv_category_banner.getLayoutParams();
        params1.height = img_h;
        iv_category_banner.setLayoutParams(params1);

        tvState = findViewById(R.id.tv_state);

        tvHh = findViewById(R.id.tv_hh);
        tvMm = findViewById(R.id.tv_mm);
        tvSs = findViewById(R.id.tv_ss);

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

        listView = findViewById(R.id.lv_wuliu);

        adapter = new DataAdapter(this, listView, SecKillCategoryListViewHolder.class);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
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

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void clearHttpDatas() {
        adapter.clearDatas();
    }

    @Override
    public void setHttpDatas(List<String> list) {

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            adapter.addData(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    int img_h;

    private void scaleWH() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        img_h = outMetrics.widthPixels;
        int wh = context.getResources().getDimensionPixelSize(R.dimen.dp_16) * (2);
        img_h -= wh;

        img_h = img_h * 3 / 10;


    }

}
