package com.pbph.shoppingmall.module.message.type.interaction;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class MessageTypeInteractionFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    private ListView listView;
    private DataAdapter adapter;


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_messagetype_interaction;
    }

    @Override
    public void initView(View view) {

        listView = view.findViewById(R.id.lv_hudong);

        adapter = new DataAdapter(this, listView, InteractionViewHolder.class);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

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
