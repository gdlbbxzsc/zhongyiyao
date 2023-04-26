package com.pbph.shoppingmall.module.bill.list;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetBillListResponse;
import com.pbph.shoppingmall.module.bill.submit.AddOrEditBillActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.dialog.YesNoDialog;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;

public class BillMngActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

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
        return R.layout.activity_billmng;
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        need_result = intent.getBooleanExtra("need_result", false);
        service_id = intent.getIntExtra("service_id", -1);
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "发票抬头", true);
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

        adapter = new DataAdapter(this, listView, BillMngViewHolder.class);
        adapter.setListener(onItemViewClickListener);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        adapter.setChoice_type(AbsListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        findViewById(R.id.button).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                startActivityForResult(new Intent(context, AddOrEditBillActivity.class), REQUEST_CODE_ADD_EDIT);
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
        if (requestCode != REQUEST_CODE_ADD_EDIT || resultCode != AddOrEditBillActivity.RESPONSE_CODE_ADD) {
            return;
        }
        //            presenter.getHttpDatasFirstPage();

        GetBillListResponse.DataBean vo = (GetBillListResponse.DataBean) data.getSerializableExtra("result");
        adapter.addData(vo);
        if (vo.getDefaultVal() == 1) {
            for (int i = 0, c = adapter.getCount(); i < c; i++) {
                GetBillListResponse.DataBean temp = (GetBillListResponse.DataBean) adapter.getItem(i);

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
        if (requestCode != REQUEST_CODE_ADD_EDIT || resultCode != AddOrEditBillActivity.RESPONSE_CODE_EDIT) {
            return;
        }
        //            presenter.getHttpDatasFirstPage();

        GetBillListResponse.DataBean vo = (GetBillListResponse.DataBean) data.getSerializableExtra("result");

        for (int i = 0, c = adapter.getCount(); i < c; i++) {
            GetBillListResponse.DataBean temp = (GetBillListResponse.DataBean) adapter.getItem(i);

            if (vo.getDefaultVal() == 1) temp.setDefaultVal(0);

            if (temp.getPpid() != vo.getPpid()) continue;

            temp.setPpid(vo.getPpid());
            temp.setBillTitle(vo.getBillTitle());
            temp.setBillType(vo.getBillType());
            temp.setBillParagraph(vo.getBillParagraph());
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

        GetBillListResponse.DataBean vo = (GetBillListResponse.DataBean) adapter.getItem(position);
        setResultSucc(vo);
    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = new DataAdapter.OnItemViewClickListener() {
        @Override
        public void onItemViewClick(int rid, ViewHolder holder, Object... objects) {
            switch (rid) {
                case R.id.tv_del: {//删除
                    YesNoDialog.show(context, "确定删除抬头么?", 0, position -> {
                        GetBillListResponse.DataBean vo = (GetBillListResponse.DataBean) holder.item;
                        presenter.delHttpBill(vo.getPpid());

                    });
                }
                break;
                case R.id.tv_edit: { //编辑
                    GetBillListResponse.DataBean vo = (GetBillListResponse.DataBean) holder.item;
                    startActivityForResult(new Intent(context, AddOrEditBillActivity.class)
                            .putExtra("bill_id", vo.getPpid()), REQUEST_CODE_ADD_EDIT);
                }
                break;
                case R.id.ll_def: { //设置默认

                    if (adapter.isChoiced(holder.position)) return;

                    adapter.putChoicedNotify(holder.position);

                    GetBillListResponse.DataBean vo = (GetBillListResponse.DataBean) holder.item;
                    presenter.submitHttpDefaultBill(vo);
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
    public void setHttpDatas(List<GetBillListResponse.DataBean> list) {

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            GetBillListResponse.DataBean vo = list.get(i);
            adapter.addData(vo);

            if (vo.getDefaultVal() == 1) adapter.putChoiced(i);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void flushDefaultBill(int defId) {
        for (int i = 0, c = adapter.getCount(); i < c; i++) {
            GetBillListResponse.DataBean vo = (GetBillListResponse.DataBean) adapter.getItem(i);
            if (defId == vo.getPpid()) {
                adapter.putChoiced(i);
                vo.setDefaultVal(1);
            } else {
                vo.setDefaultVal(0);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void flushDatas4Del(int delId) {

        int choose_pos = -1;// 当前的默认项位置 -1表示没有默认项
        //注意这里是倒叙循环，原因很简单，因为这个循环要删除一条数据。
        for (int i = adapter.getCount() - 1; i >= 0; i--) {
            GetBillListResponse.DataBean vo = (GetBillListResponse.DataBean) adapter.getItem(i);

            if (adapter.isChoiced(i)) {
                //如果这条判断成立，那么代表当前选中的默认项的位置在要删除数据位置之上。
                choose_pos = i;
            }
            if (delId != vo.getPpid()) {
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

    void setResultSucc(GetBillListResponse.DataBean vo) {
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
                GetBillListResponse.DataBean vo = (GetBillListResponse.DataBean) adapter.getItem(i);

                if (vo.getPpid() != service_id) continue;

                intent.putExtra("result", vo);
                break;
            }
        }
        setResult(RESULT_CODE_BACK, intent);
        finish();
    }

}
