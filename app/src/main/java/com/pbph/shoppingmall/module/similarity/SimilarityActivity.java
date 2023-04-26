package com.pbph.shoppingmall.module.similarity;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;

import java.util.List;

public class SimilarityActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {


    CommonTitlebar commonTitlebar;


    private ImageView ivPic;
    private TextView tvName;
    private TextView tvPriceNew;


    public GridView gridView;
    public SimilarityDataAdapter adapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_similarity;
    }


    @Override
    protected void initData() {
        scaleWH();
    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(this, "看相似", false);

        ivPic = findViewById(R.id.iv_pic);
        tvName = findViewById(R.id.tv_name);
        tvPriceNew = findViewById(R.id.tv_price_new);

        gridView = findViewById(R.id.gridView);
        gridView.setNumColumns(line_nums);

        adapter = new SimilarityDataAdapter(this, gridView, SimilarityViewHolder.class);
        adapter.wh = img_wh;

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {

        });
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


    int line_nums = 2;
    int img_wh = 0;

    private void scaleWH() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        img_wh = outMetrics.widthPixels;
        int wh = context.getResources().getDimensionPixelSize(R.dimen.dp_16) * (line_nums - 1 + 2);
        img_wh -= wh;
        img_wh /= line_nums;
    }

}
