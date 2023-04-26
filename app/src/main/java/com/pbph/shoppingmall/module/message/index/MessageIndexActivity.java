package com.pbph.shoppingmall.module.message.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.message.type.MessageTypeActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;

public class MessageIndexActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {


    CommonTitlebar commonTitlebar;

    TextView tv_wuliu, tv_tongzhi, tv_hudong;


    private ListView listView;
    private DataAdapter adapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_messageindex;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(this, "消息", true);

        tv_wuliu = findViewById(R.id.tv_wuliu);
        tv_wuliu.setOnClickListener(onSingleClickListener);
        tv_tongzhi = findViewById(R.id.tv_tongzhi);
        tv_tongzhi.setOnClickListener(onSingleClickListener);
        tv_hudong = findViewById(R.id.tv_hudong);
        tv_hudong.setOnClickListener(onSingleClickListener);


        listView = findViewById(R.id.lv_message);

        adapter = new DataAdapter(this, listView, MessageShopViewHolder.class);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


    }

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.tv_wuliu: {
                    startActivity(new Intent(getContext(), MessageTypeActivity.class).putExtra("message_type", MessageTypeActivity.MESSAGE_TYPE_WULIU));
                }
                break;
                case R.id.tv_tongzhi: {
                    startActivity(new Intent(getContext(), MessageTypeActivity.class).putExtra("message_type", MessageTypeActivity.MESSAGE_TYPE_TONGZHI));
                }
                break;
                case R.id.tv_hudong: {
                    startActivity(new Intent(getContext(), MessageTypeActivity.class).putExtra("message_type", MessageTypeActivity.MESSAGE_TYPE_XIAOXI));
                }
                break;
            }
        }
    };


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

}
