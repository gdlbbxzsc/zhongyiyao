package com.pbph.shoppingmall.module.addressmng.list;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetAddressListResponse;
import com.pbph.shoppingmall.module.addressmng.submit.AddOrEditAddressActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.dialog.YesNoDialog;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;

public class AddressMngActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    public static final int RESULT_CODE_BACK = 0x102;

    //标题栏
    CommonTitlebar commonTitlebar;

    private ListView listView;
    private DataAdapter adapter;

    boolean need_result;//是否需要返回数据

    int service_id;//其他使用地址的界面现在用到的地址信息的服务器id

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_addressmng;
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        need_result = intent.getBooleanExtra("need_result", false);
        service_id = intent.getIntExtra("service_id", -1);
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "收货地址", true);
        commonTitlebar.titlebar_left.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

                if (!need_result) {
                    finish();
                    return;
                }
                setResultBack();
            }
        });

        listView = findViewById(R.id.lv_wuliu);

        adapter = new DataAdapter(this, listView, AddressMngViewHolder.class);
        adapter.setListener(onItemViewClickListener);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        adapter.setChoice_type(AbsListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        findViewById(R.id.button).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                startActivityForResult(new Intent(context, AddOrEditAddressActivity.class), REQUEST_CODE_ADD_EDIT);
            }
        });
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private final int REQUEST_CODE_ADD_EDIT = 0x101;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        resultOnAdd(requestCode, resultCode, data);
        resultOnEdit(requestCode, resultCode, data);
    }

    void resultOnAdd(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_ADD_EDIT || resultCode != AddOrEditAddressActivity.RESPONSE_CODE_ADD) {
            return;
        }
        //            presenter.getHttpDatasFirstPage();

        GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) data.getSerializableExtra("result");

        adapter.addData(0, vo);

        if (vo.getDefaultVal() == 1) {
            for (int i = 0, c = adapter.getCount(); i < c; i++) {
                GetAddressListResponse.DataBean temp = (GetAddressListResponse.DataBean) adapter.getItem(i);

                if (vo.getDefaultVal() == 1) temp.setDefaultVal(0);

                if (temp.getPpid() != vo.getPpid()) continue;

                temp.setDefaultVal(vo.getDefaultVal());

                adapter.putChoiced(i);
            }
            adapter.notifyDataSetChanged();
        }
        setResultSucc(vo);
    }

    void resultOnEdit(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_ADD_EDIT || resultCode != AddOrEditAddressActivity.RESPONSE_CODE_EDIT) {
            return;
        }
        //            presenter.getHttpDatasFirstPage();

        GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) data.getSerializableExtra("result");

        for (int i = 0, c = adapter.getCount(); i < c; i++) {
            GetAddressListResponse.DataBean temp = (GetAddressListResponse.DataBean) adapter.getItem(i);

            if (vo.getDefaultVal() == 1) temp.setDefaultVal(0);

            if (temp.getPpid() != vo.getPpid()) continue;

            temp.setPpid(vo.getPpid());
            temp.setAddressName(vo.getAddressName());
            temp.setAddressMoblie(vo.getAddressMoblie());

            temp.setProvinceName(vo.getProvinceName());
            temp.setCityName(vo.getCityName());
            temp.setDistrictName(vo.getDistrictName());

            temp.setAddressProvince(vo.getAddressProvince());
            temp.setAddressCity(vo.getAddressCity());
            temp.setAddressCounty(vo.getAddressCounty());

            temp.setAddressDetail(vo.getAddressDetail());
            temp.setDefaultVal(vo.getDefaultVal());

            adapter.putChoiced(i);
        }
        adapter.notifyDataSetChanged();

        setResultSucc(vo);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (!need_result) {
            return super.onKeyDown(keyCode, event);
        }

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            setResultBack();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!need_result) return;

        GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) adapter.getItem(position);
        setResultSucc(vo);
    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = new DataAdapter.OnItemViewClickListener() {
        @Override
        public void onItemViewClick(int rid, ViewHolder holder, Object... objects) {

            switch (rid) {
                case R.id.tv_del: {//删除
                    YesNoDialog.show(context, "确定删除地址么?", 0, position -> {
                        GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) holder.item;
                        presenter.delHttpAddress(vo.getPpid());
                    });
                }
                break;
                case R.id.tv_edit: { //编辑
                    GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) holder.item;
                    startActivityForResult(new Intent(context, AddOrEditAddressActivity.class)
                            .putExtra("address_id", vo.getPpid()), REQUEST_CODE_ADD_EDIT);
                }
                break;
                case R.id.ll_def: { //设置默认

                    if (adapter.isChoiced(holder.position)) return;

                    adapter.putChoicedNotify(holder.position);

                    GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) holder.item;
                    presenter.submitHttpDefaultAddress(vo);
                }
                break;
            }
        }
    };

    @Override
    public void clearHttpDatas() {
        adapter.clearDatas();
    }

    @Override
    public void setHttpDatas(List<GetAddressListResponse.DataBean> list) {

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            GetAddressListResponse.DataBean vo = list.get(i);
            adapter.addData(vo);

            if (vo.getDefaultVal() == 1) adapter.putChoiced(i);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void flushDefaultAddress(int id) {
        for (int i = 0, c = adapter.getCount(); i < c; i++) {
            GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) adapter.getItem(i);
            if (id == vo.getPpid()) {
                adapter.putChoiced(i);
                vo.setDefaultVal(1);
            } else {
                vo.setDefaultVal(0);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void flushDatas4Del(int id) {

        int choose_pos = -1;// 当前的默认项位置 -1表示没有默认项
        //注意这里是倒叙循环，原因很简单，因为这个循环要删除一条数据。
        for (int i = adapter.getCount() - 1; i >= 0; i--) {
            GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) adapter.getItem(i);

            if (adapter.isChoiced(i)) {
                //如果这条判断成立，那么代表当前选中的默认项的位置在要删除数据位置之上。
                choose_pos = i;
            }
            if (id != vo.getPpid()) {
                //如果不等 就不是我们要删除的数据
                continue;
            }
//            删除数据
            adapter.removeData(vo);

            if (choose_pos == i) {
//                如果这条判断成立，代表了我们删除的数据就是默认项。那么就没有默认项了所以直接 break就好
                adapter.clearChoices();
                break;
            }
//            如果choose_pos 大于等于零 就代表 之前的isChoiced（）成立，就代表数据的位置要变小1位
            if (choose_pos >= 0) adapter.putChoiced(choose_pos - 1);

            break;
        }
        adapter.notifyDataSetChanged();
    }


    void setResultSucc(GetAddressListResponse.DataBean vo) {
        if (!need_result) return;
        Intent intent = new Intent();
        intent.putExtra("result", vo);
        setResult(RESULT_OK, intent);
        finish();
    }


    void setResultBack() {
        if (!need_result) return;

        Intent intent = new Intent();
        if (service_id != -1) {
            for (int i = 0, c = adapter.getCount(); i < c; i++) {
                GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) adapter.getItem(i);

                if (vo.getPpid() != service_id) continue;

                intent.putExtra("result", vo);
                break;
            }
        }
        setResult(RESULT_CODE_BACK, intent);
        finish();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        presenter.getHttpDatasFirstPage();
//    }
}
