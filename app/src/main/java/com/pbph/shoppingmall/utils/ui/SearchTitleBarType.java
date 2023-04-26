package com.pbph.shoppingmall.utils.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.goodstype.GoodsTypeActivity;
import com.pbph.shoppingmall.module.search.SearchActivity;
import com.pbph.shoppingmall.utils.pop.CommonTitlebarRightListPop;

/**
 * Created by 连嘉凡 on 2018/5/2.
 */

public class SearchTitleBarType {
    Activity activity;
    public View layout;
    ImageView iv_back, iv_more;
    TextView edt_search, tv_shop_type;
    CommonTitlebarRightListPop pop;
    private int searchType;
    private int id;
    public SearchTitleBarType(Activity activity, String title, boolean right, int searchType) {

        this.activity = activity;
        this.searchType = searchType;
        layout = activity.findViewById(R.id.linear_title);

        iv_back = activity.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(onSingleClickListener);


        edt_search = activity.findViewById(R.id.edt_search);
        edt_search.setText(title);
        edt_search.setOnClickListener(onSingleClickListener);

        iv_more = activity.findViewById(R.id.iv_more);
        iv_more.setOnClickListener(onSingleClickListener);

        tv_shop_type = activity.findViewById(R.id.tv_shop_type);
        tv_shop_type.setOnClickListener(onSingleClickListener);
        pop = new CommonTitlebarRightListPop(activity, iv_more);

    }

    public SearchTitleBarType(Activity activity, String title, boolean right, int searchType,int
            id) {

        this.activity = activity;
        this.searchType = searchType;
        this.id = id;
        layout = activity.findViewById(R.id.linear_title);

        iv_back = activity.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(onSingleClickListener);


        edt_search = activity.findViewById(R.id.edt_search);
        edt_search.setText(title);
        edt_search.setOnClickListener(onSingleClickListener);

        iv_more = activity.findViewById(R.id.iv_more);
        iv_more.setOnClickListener(onSingleClickListener);

        tv_shop_type = activity.findViewById(R.id.tv_shop_type);
        tv_shop_type.setOnClickListener(onSingleClickListener);
        pop = new CommonTitlebarRightListPop(activity, iv_more);

    }
    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.iv_back: {
                    activity.finish();
                }
                break;
                case R.id.iv_more: {
                    if (pop != null) pop.show();
                }
                break;
                case R.id.edt_search: {
                    if (searchType==1){
                        Toast.makeText(activity, "店内搜索", Toast.LENGTH_SHORT).show();
                    }else {
                        activity.startActivity(new Intent(activity, SearchActivity.class).putExtra
                                ("search_type", searchType));
                    }
                   
                }
                break;
                case R.id.tv_shop_type:{
                    activity.startActivity(new Intent(activity, GoodsTypeActivity.class).putExtra("storeId",id));
                }

                    break;
            }
        }
    };
}
