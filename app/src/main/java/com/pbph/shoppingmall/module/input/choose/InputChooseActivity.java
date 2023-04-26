package com.pbph.shoppingmall.module.input.choose;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;

public class InputChooseActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {


    private static final String KEY_CHOOSE_DATA = "KEY_CHOOSE_DATA";
    public static final String KEY_CHOOSE_ITEM = "KEY_CHOOSE_ITEM";

    CommonTitlebar commonTitlebar;

    private ListView listView;
    private DataAdapter adapter;


    AChooseData chooseData;
    ChooseDataVo chooseDataVo;


    int choose_id = -1;

    public static final void startActivityForResult(Activity context, Class<? extends AChooseData> clz, Integer chooseDataVoId, int requestCode) {
        Intent intent = new Intent(context, InputChooseActivity.class);
        intent.putExtra(KEY_CHOOSE_DATA, clz.getName());
        intent.putExtra(KEY_CHOOSE_ITEM, chooseDataVoId);
        context.startActivityForResult(intent, requestCode);
    }

    public static final void startActivityForResult(Fragment context, Class<? extends AChooseData> clz, Integer chooseDataVoId, int requestCode) {
        Intent intent = new Intent(context.getContext(), InputChooseActivity.class);
        intent.putExtra(KEY_CHOOSE_DATA, clz.getName());
        intent.putExtra(KEY_CHOOSE_ITEM, chooseDataVoId);
        context.startActivityForResult(intent, requestCode);
    }


    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_inputchoose;
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        choose_id = intent.getIntExtra(KEY_CHOOSE_ITEM, -1);
        String cls = intent.getStringExtra(KEY_CHOOSE_DATA);
        try {
            chooseData = (AChooseData) Class.forName(cls).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, chooseData.input_choose_title, false);

        listView = findViewById(R.id.lv_message);

        adapter = new DataAdapter(this, listView, ChooseViewHolder.class);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter.setChoice_type(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        adapter.setDatas(chooseData.input_choose_datas);
        adapter.putChoicedNotify(chooseData.getChoosePosition(choose_id));

    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        chooseDataVo = (ChooseDataVo) adapter.getItem(position);
        adapter.putChoicedNotify(position);
        setResult();
    }

    private void setResult() {
        Intent intent = new Intent();
        intent.putExtra(KEY_CHOOSE_ITEM, chooseDataVo);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
