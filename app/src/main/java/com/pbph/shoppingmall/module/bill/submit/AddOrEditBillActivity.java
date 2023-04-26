package com.pbph.shoppingmall.module.bill.submit;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableRow;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.request.InsertBillRequest;
import com.pbph.shoppingmall.model.request.UpdateBillRequest;
import com.pbph.shoppingmall.model.response.GetBillByIdResponse;
import com.pbph.shoppingmall.model.response.GetBillListResponse;
import com.pbph.shoppingmall.model.response.InsertBillResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.utils.AMUtils;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

public class AddOrEditBillActivity extends BaseActivity<Presenter> implements Contract.View {

    public static final int RESPONSE_CODE_ADD = 0x103;
    public static final int RESPONSE_CODE_EDIT = 0x104;


    //标题栏
    CommonTitlebar commonTitlebar;

    private LinearLayout layoutRoot;


    RadioButton cb_per;
    RadioButton cb_com;

    EditText edt_title;
    TableRow tr_bill_id_num;
    EditText edt_bill_id_num;

    CheckBox cb_def;

    EditText edt_phone;
    EditText edt_email;
    TableRow tr_com_address;
    EditText edt_com_address;
    TableRow tr_bank;
    EditText edt_bank;
    TableRow tr_bank_acc;
    EditText edt_bank_acc;


    int bill_id;


    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_add_or_edit_bill;
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        bill_id = intent.getIntExtra("bill_id", -1);
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, bill_id == -1 ? "添加抬头" : "编辑抬头", false);

        layoutRoot = findViewById(R.id.layout_root);

        cb_per = findViewById(R.id.cb_per);
        cb_per.setOnCheckedChangeListener(onCheckedChangeListener);
        cb_com = findViewById(R.id.cb_com);
        cb_com.setOnCheckedChangeListener(onCheckedChangeListener);

        edt_title = findViewById(R.id.edt_title);
        edt_bill_id_num = findViewById(R.id.edt_bill_id_num);

        cb_def = findViewById(R.id.cb_def);

        edt_phone = findViewById(R.id.edt_phone);
        edt_email = findViewById(R.id.edt_email);
        edt_com_address = findViewById(R.id.edt_com_address);
        edt_bank = findViewById(R.id.edt_bank);
        edt_bank_acc = findViewById(R.id.edt_bank_acc);


        tr_bill_id_num = findViewById(R.id.tr_bill_id_num);
        tr_com_address = findViewById(R.id.tr_com_address);
        tr_bank = findViewById(R.id.tr_bank);
        tr_bank_acc = findViewById(R.id.tr_bank_acc);


        findViewById(R.id.button).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

                if (bill_id == -1) {//添加操作
                    InsertBillRequest<InsertBillResponse> vo = new InsertBillRequest<>();
                    vo.billType = cb_per.isChecked() ? 0 : 1;

                    if (vo.billType == 0) {
                        if (!validatePer(true, vo, null)) return;
                    } else {
                        if (!validateCom(true, vo, null)) return;
                    }
                    vo.defaultVal = cb_def.isChecked() ? 1 : 0;

                    presenter.submitHttpBill(vo);
                } else {
                    UpdateBillRequest<ResultResponse> vo = new UpdateBillRequest<>();
                    vo.billType = cb_per.isChecked() ? 0 : 1;

                    if (vo.billType == 0) {
                        if (!validatePer(false, null, vo)) return;
                    } else {
                        if (!validateCom(false, null, vo)) return;
                    }
                    vo.defaultVal = cb_def.isChecked() ? 1 : 0;

                    vo.id = bill_id;
                    presenter.updateHttpBill(vo);
                }
            }
        });

        if (bill_id == -1) {
            cb_per.setChecked(true);
        } else {
            cb_com.setChecked(true);
        }
    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = (buttonView, isChecked) -> {
        if (!isChecked) return;

        switch (buttonView.getId()) {
            case R.id.cb_per:
                showPer();
                break;
            case R.id.cb_com:
                showCom();
                break;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
////            setResultBack();
//            return false;
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getId() {
        return bill_id;
    }

    @Override
    public void onSucc(GetBillListResponse.DataBean vo) {
        Intent intent = new Intent();
        intent.putExtra("result", vo);

        setResult(bill_id == -1 ? RESPONSE_CODE_ADD : RESPONSE_CODE_EDIT, intent);

        finish();
    }

    @Override
    public void onGetHttpBillById(GetBillByIdResponse.DataBean vo) {

        if (StringUtils.isEqual(vo.getBillType(), "0")) {
            cb_per.setChecked(true);
            setDataPer(vo);
        } else {
            cb_com.setChecked(true);
            setDataCom(vo);
        }
    }

    void showPer() {

        clearInputData();

        tr_bill_id_num.setVisibility(View.GONE);

        tr_com_address.setVisibility(View.GONE);
        tr_bank.setVisibility(View.GONE);
        tr_bank_acc.setVisibility(View.GONE);
    }

    void showCom() {

        clearInputData();

        tr_bill_id_num.setVisibility(View.VISIBLE);

        tr_com_address.setVisibility(View.VISIBLE);
        tr_bank.setVisibility(View.VISIBLE);
        tr_bank_acc.setVisibility(View.VISIBLE);
    }

    void setDataPer(GetBillByIdResponse.DataBean vo) {

        cb_def.setChecked(vo.getDefaultVal() == 1);
        edt_title.setText(vo.getBillTitle());

        edt_phone.setText(vo.getBillTelephone());
        edt_email.setText(vo.getBillEmail());
    }

    void setDataCom(GetBillByIdResponse.DataBean vo) {

        cb_def.setChecked(vo.getDefaultVal() == 1);
        edt_title.setText(vo.getBillTitle());

        edt_bill_id_num.setText(vo.getBillParagraph());

        edt_phone.setText(vo.getBillTelephone());
        edt_email.setText(vo.getBillEmail());

        edt_com_address.setText(vo.getBillUnit());
        edt_bank.setText(vo.getBillBankName());
        edt_bank_acc.setText(vo.getBillBankAccount());
    }


    boolean validatePer(boolean isInsert, InsertBillRequest<InsertBillResponse> ivo, UpdateBillRequest<ResultResponse> uvo) {
        String title = validateTitle();
        if (StringUtils.isEmpty(title)) return false;

        String phone = validatePhone();
        if (phone == null) return false;

        String email = validateEmail();
        if (email == null) return false;

        if (isInsert) {
            ivo.billTitle = title;
            ivo.billTelephone = phone;
            ivo.billEmail = email;
        } else {
            uvo.billTitle = title;
            uvo.billTelephone = phone;
            uvo.billEmail = email;
        }
        return true;
    }

    boolean validateCom(boolean isInsert, InsertBillRequest<InsertBillResponse> ivo, UpdateBillRequest<ResultResponse> uvo) {

        String title = validateTitle();
        if (StringUtils.isEmpty(title)) return false;

        String billIdNum = validateBillIdNum();
        if (StringUtils.isEmpty(billIdNum)) return false;

        String phone = validatePhone();
        if (phone == null) return false;

        String email = validateEmail();
        if (email == null) return false;


        String add = validateAddress();
        String bank = validateBank();
        String bankAcc = validateBanAcc();

        if (isInsert) {
            ivo.billTitle = title;
            ivo.billParagraph = billIdNum;
            ivo.billTelephone = phone;
            ivo.billEmail = email;
            ivo.billUnit = add;
            ivo.billBankName = bank;
            ivo.billBankAccount = bankAcc;
        } else {
            uvo.billTitle = title;
            uvo.billParagraph = billIdNum;
            uvo.billTelephone = phone;
            uvo.billEmail = email;
            uvo.billUnit = add;
            uvo.billBankName = bank;
            uvo.billBankAccount = bankAcc;
        }

        return true;
    }


    String validateTitle() {

        String str = edt_title.getText().toString();
        if (StringUtils.isEmpty(str)) {
            toastShort("请输入抬头");
            return null;
        }
        return str;
    }

    String validateBillIdNum() {

        String str = edt_bill_id_num.getText().toString();
        if (StringUtils.isEmpty(str)) {
            toastShort("请输入税号");
            return null;
        }
        return str;
    }

    String validatePhone() {

        String phone = edt_phone.getText().toString();
        if (StringUtils.isEmpty(phone)) return "";

        if (!AMUtils.isMobile(phone)) {
            toastShort("手机号码不正确");
            return null;
        }

        return phone;
    }

    String validateEmail() {

        String email = edt_email.getText().toString();
        if (StringUtils.isEmpty(email)) return "";

        if (!AMUtils.isEmail(email)) {
            toastShort("邮箱格式不正确");
            return null;
        }

        return email;
    }

    String validateAddress() {

        String address = edt_com_address.getText().toString();

        return address;
    }

    String validateBank() {

        String bank = edt_bank.getText().toString();

        return bank;
    }


    String validateBanAcc() {

        String banAcc = edt_bank_acc.getText().toString();

        return banAcc;
    }


    void clearInputData() {
        edt_title.setText(null);
        edt_bill_id_num.setText(null);

        cb_def.setChecked(false);

        edt_phone.setText(null);
        edt_email.setText(null);
        edt_com_address.setText(null);
        edt_bank.setText(null);
        edt_bank_acc.setText(null);
    }

}
