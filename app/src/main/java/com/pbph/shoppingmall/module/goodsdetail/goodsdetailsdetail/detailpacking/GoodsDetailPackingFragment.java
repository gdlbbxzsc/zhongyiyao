package com.pbph.shoppingmall.module.goodsdetail.goodsdetailsdetail.detailpacking;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class GoodsDetailPackingFragment extends BaseFragmentV4 implements Contract.View {

    private int id;
    private final int H = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int W = ViewGroup.LayoutParams.MATCH_PARENT;
    private TableLayout tab;
    private ArrayList<String> tabCol = new ArrayList<>();
    private ArrayList<String> tabH = new ArrayList<>();
    public static GoodsDetailPackingFragment newInstance(int id) {
        GoodsDetailPackingFragment f = new GoodsDetailPackingFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);
        return f;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments() != null ? getArguments().getInt("id", 0) : 0;


    }

    @Override
    protected IBasePresenter createPresenter() {
        return new Presenter(this);

    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_detail_packing;
    }

    @Override
    public void initView(View view) {
//        for (int i = 1; i <= 10; i++) {
//            tabCol.add("第 (" + i + ") 列");
//            tabH.add("第 (" + i + ") 行");
//        }
        tabCol.add("商品编号");
        tabCol.add("Rom");
        tabCol.add("商品编号商品编号商品编号商品编号商品编号");
        tabCol.add("RomRom");

        tabH.add("1231f2d3safsda");
        tabH.add("dsfasdfsafsafsadfsadf");
        tabH.add("ads");
        tabH.add("fdddd");

        tab = view.findViewById(R.id.tab_01);
        //控制行数
        for (int row = 0; row < tabH.size(); row++) {

            TableRow tabRow = new TableRow(context);
            //控制列数
            for (int col = 0; col < 1; col++) {

                TextView tv = new TextView(context);
                tv.setText(tabCol.get(col) + tabH.get(row));
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.bargaining_bg);
                tabRow.addView(tv);

            }
            tab.addView(tabRow, new TableLayout.LayoutParams(W, H));
        }
    }

    @Override
    public int getShopId() {
        return id;
    }

    @Override
    public void setHttpData(List<String> i) {

    }
}
