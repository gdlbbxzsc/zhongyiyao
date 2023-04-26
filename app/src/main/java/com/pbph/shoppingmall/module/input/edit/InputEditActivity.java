package com.pbph.shoppingmall.module.input.edit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

import java.util.regex.Pattern;

public class InputEditActivity extends BaseActivity<Presenter> implements Contract.View {


    private static final String KEY_EDIT_DATA = "KEY_EDIT_DATA";
    public static final String KEY_EDIT_TEXT = "KEY_EDIT_TEXT";

    CommonTitlebar commonTitlebar;

    private EditText edt_edit;
    private ImageView edtDel;
    private TextView tv_toast;


    AEditData editData;
    String edit_text;


    public static final void startActivityForResult(Activity context, Class<? extends AEditData> clz, String text, int requestCode) {
        Intent intent = new Intent(context, InputEditActivity.class);
        intent.putExtra(KEY_EDIT_DATA, clz.getName());
        intent.putExtra(KEY_EDIT_TEXT, text);
        context.startActivityForResult(intent, requestCode);
    }

    public static final void startActivityForResult(Fragment context, Class<? extends AEditData> clz, String text, int requestCode) {
        Intent intent = new Intent(context.getContext(), InputEditActivity.class);
        intent.putExtra(KEY_EDIT_DATA, clz.getName());
        intent.putExtra(KEY_EDIT_TEXT, text);
        context.startActivityForResult(intent, requestCode);
    }


    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_inputedit;
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        edit_text = intent.getStringExtra(KEY_EDIT_TEXT);

        String cls = intent.getStringExtra(KEY_EDIT_DATA);
        try {
            editData = (AEditData) Class.forName(cls).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, editData.input_edit_title, false);

        edt_edit = findViewById(R.id.edt_edit);

        edtDel = findViewById(R.id.edt_del);

        tv_toast = findViewById(R.id.tv_toast);

        edt_edit.setHint(editData.input_edit_hint);
        //设置字符过滤
        if (editData.keyListener != null) edt_edit.setKeyListener(editData.keyListener);

        if (editData.inputFilters != null && editData.inputFilters.size() > 0) {
            InputFilter[] filters = new InputFilter[editData.inputFilters.size()];
            for (int i = 0, c = editData.inputFilters.size(); i < c; i++) {
                filters[i] = editData.inputFilters.get(i);
            }
            edt_edit.setFilters(filters);
        }


        edt_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                edtDel.setVisibility(s.length() <= 0 ? View.GONE : View.VISIBLE);
            }
        });

        edtDel.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                edt_edit.setText(null);
            }
        });

        findViewById(R.id.titlebar_right2).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

                edit_text = edt_edit.getText().toString();
                if (StringUtils.isEmpty(edit_text)) {
                    if (!StringUtils.isEmpty(editData.error_empty_string)) {
                        toastShort(editData.error_empty_string);
                        return;
                    }
                }

                if (!matcher()) {
                    toastShort(editData.error_string);
                    return;
                }
                setResult();
            }
        });

        edt_edit.setText(edit_text);
        tv_toast.setText(editData.input_edit_toast);
    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    boolean matcher() {
        if (StringUtils.isEmpty(editData.pattern_string)) return true;
        try {
            return Pattern.compile(editData.pattern_string).matcher(edit_text).matches();
        } catch (Exception ignored) {
            return false;
        }
    }

    private void setResult() {
        Intent intent = new Intent();
        intent.putExtra(KEY_EDIT_TEXT, edit_text);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
