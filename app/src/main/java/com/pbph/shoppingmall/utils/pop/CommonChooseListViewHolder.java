package com.pbph.shoppingmall.utils.pop;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class CommonChooseListViewHolder extends ViewHolder {


    CheckBox cb_common_choose_list;
    TextView tv_common_choose_list;


    @Override
    protected int getLayout() {
        return R.layout.listview_pop_common_choose_list;
    }

    @Override
    protected void getView(View view) {
        cb_common_choose_list = view.findViewById(R.id.cb_common_choose_list);
        tv_common_choose_list = view.findViewById(R.id.tv_common_choose_list);
    }

    @Override
    protected void showView() {
        cb_common_choose_list.setChecked(adapter.isChoiced(position));
    }


}
