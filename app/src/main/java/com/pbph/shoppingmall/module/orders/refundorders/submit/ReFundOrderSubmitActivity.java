package com.pbph.shoppingmall.module.orders.refundorders.submit;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.request.RefundRequest;
import com.pbph.shoppingmall.model.response.GetApplyCredentialsListResponse;
import com.pbph.shoppingmall.model.response.GetBackOrderReasonListResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.utils.AMUtils;
import com.pbph.shoppingmall.utils.GlideImageLoaderUtils;
import com.pbph.shoppingmall.utils.pop.CommonChooseListPop;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReFundOrderSubmitActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener {

    //退款退货
    public static int TYPE_REFUND_GOODS = 1;
    public static int TYPE_REFUND_MONEY = 2;

    //是否收货
    public static int TYPE_UNTAKE_GOODS = 0;
    public static int TYPE_TAKE_GOODS = 1;

    View layout_root;

    CommonTitlebar commonTitlebar;

    RadioButton radioButton1;
    RadioButton radioButton2;

    TextView tv_reason;
    CommonChooseListPop resonPop;

    TableRow tr_according;
    View view_line;
    TextView tv_according;
    CommonChooseListPop accordingPop;


    TextView tv_name_pre;
    EditText edt_name;
    EditText edt_call;
    EditText edt_content;

    TableRow tr_upload;
    ImageView iv_up;
    Button button;


    int refund_type = TYPE_REFUND_GOODS;
    int goods_type = TYPE_UNTAKE_GOODS;
    //是否可编辑。这个逻辑我也不懂是为了个啥不过产品提出来了就这样吧。
    boolean canEdit = false;


    String orderId;

    String img_url;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_refoundordersubmit;
    }


    @Override
    protected void initData() {
        canEdit = getIntent().getBooleanExtra("canEdit", false);
        goods_type = getIntent().getIntExtra("goods_type", TYPE_UNTAKE_GOODS);
        refund_type = getIntent().getIntExtra("refund_type", TYPE_REFUND_GOODS);

        orderId = getIntent().getStringExtra("orderId");

        initImagePager();
    }

    @Override
    protected void initView() {

        layout_root = findViewById(R.id.layout_root);

        commonTitlebar = new CommonTitlebar(this, refund_type == TYPE_REFUND_GOODS ? "申请退货" : "申请退款", true);

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);

        tv_reason = findViewById(R.id.tv_reason);
        resonPop = new CommonChooseListPop(this, layout_root, onReasonListener, GetBackOrderReasonListViewHolder.class);
        tv_reason.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                resonPop.show();
            }
        });

        tr_according = findViewById(R.id.tr_according);
        view_line = findViewById(R.id.view_line);

        tv_according = findViewById(R.id.tv_according);
        accordingPop = new CommonChooseListPop(this, layout_root, onAccordingListener, GetApplyCredentialsListViewHolder.class);
        tv_according.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                accordingPop.show();
            }
        });

        tv_name_pre = findViewById(R.id.tv_name_pre);
        tv_name_pre.setText(refund_type == TYPE_REFUND_GOODS ? "退货" : "退款");
        tv_name_pre.append("联系人");

        edt_name = findViewById(R.id.edt_name);
        edt_call = findViewById(R.id.edt_call);
        edt_content = findViewById(R.id.edt_content);
        button = findViewById(R.id.button);

        tr_upload = findViewById(R.id.tr_upload);
        iv_up = findViewById(R.id.iv_up);
        iv_up.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                start2GetImg();
            }
        });

        if (refund_type == TYPE_REFUND_GOODS) {
            button.setText("申请退货");
        } else {
            button.setText("申请退款");
        }

        button.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {


                GetBackOrderReasonListResponse.DataBean.ResonListBean rvo = (GetBackOrderReasonListResponse.DataBean.ResonListBean) tv_reason.getTag();
                if (rvo == null) {
                    toastShort("请选择原因");
                    return;
                }

                String call = edt_call.getText().toString().trim();
                if (!StringUtils.isEmpty(call) && !AMUtils.isMobile(call)) {
                    toastShort("请输入正确手机号");
                    return;
                }


                RefundRequest<ResultResponse> request = new RefundRequest<>();
                request.orderId = orderId;

                request.isBack = refund_type;//是否退货(1 退货 2 退款)
                request.isCollect = goods_type;//是否收货(0：未收货；1：已收货)

                request.backReason = rvo.getReason();

                //
                GetApplyCredentialsListResponse.DataBean.ApplyCredentialsListBean avo = (GetApplyCredentialsListResponse.DataBean.ApplyCredentialsListBean) tv_according.getTag();
                if (avo != null) request.applyCredentials = avo.getValue();

                request.backPerson = edt_name.getText().toString().trim();
                request.backPhone = call;
                request.questionRemark = edt_content.getText().toString().trim();

                request.uploadDocuments = img_url;

                presenter.refund(request);
            }
        });

        radioButton1.setOnCheckedChangeListener(this);
        radioButton2.setOnCheckedChangeListener(this);

        if (goods_type == TYPE_UNTAKE_GOODS) {
            radioButton1.setChecked(true);
            tr_according.setVisibility(View.GONE);
            view_line.setVisibility(View.GONE);

            tr_upload.setVisibility(View.GONE);
        } else {
            radioButton2.setChecked(true);
            tr_according.setVisibility(View.VISIBLE);
            view_line.setVisibility(View.VISIBLE);

            tr_upload.setVisibility(View.VISIBLE);
        }

        if (!canEdit) {
            radioButton1.setEnabled(false);
            radioButton2.setEnabled(false);
        }
    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) return;
        switch (buttonView.getId()) {
            case R.id.radioButton1: {
                goods_type = TYPE_UNTAKE_GOODS;
                tr_according.setVisibility(View.GONE);
                view_line.setVisibility(View.GONE);

                tr_upload.setVisibility(View.GONE);
            }
            break;
            case R.id.radioButton2: {
                goods_type = TYPE_TAKE_GOODS;
                tr_according.setVisibility(View.VISIBLE);
                view_line.setVisibility(View.VISIBLE);

                tr_upload.setVisibility(View.VISIBLE);
            }
            break;
        }
    }

    CommonChooseListPop.OnItemChooseListener onReasonListener = new CommonChooseListPop.OnItemChooseListener() {
        @Override
        public void onItemChoose(Object obj) {
            GetBackOrderReasonListResponse.DataBean.ResonListBean vo = (GetBackOrderReasonListResponse.DataBean.ResonListBean) obj;

            tv_reason.setText(vo.getReason());
            tv_reason.setTag(vo);
        }
    };

    CommonChooseListPop.OnItemChooseListener onAccordingListener = new CommonChooseListPop.OnItemChooseListener() {
        @Override
        public void onItemChoose(Object obj) {
            GetApplyCredentialsListResponse.DataBean.ApplyCredentialsListBean vo = (GetApplyCredentialsListResponse.DataBean.ApplyCredentialsListBean) obj;

            tv_according.setText(vo.getValue());
            tv_according.setTag(vo);
        }
    };


    @Override
    public int getType() {
        return refund_type;
    }

    @Override
    public void setBackOrderReasonList(List<GetBackOrderReasonListResponse.DataBean.ResonListBean> list) {
        resonPop.adapter.setDatas(list);
    }

    @Override
    public void setApplyCredentialsList(List<GetApplyCredentialsListResponse.DataBean.ApplyCredentialsListBean> list) {
        accordingPop.adapter.setDatas(list);
    }

    // 获取图片


    ImagePicker imagePicker;

    private void initImagePager() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoaderUtils());
        imagePicker.setShowCamera(true);//照相
        imagePicker.setMultiMode(false); //单选多选
        imagePicker.setCrop(true); //是否剪裁
        imagePicker.setSaveRectangle(true);//是否按矩形区域保存
        imagePicker.setStyle(CropImageView.Style.RECTANGLE); //裁剪框的形状
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
        imagePicker.setFocusWidth(width);
        imagePicker.setFocusHeight(height);
        imagePicker.setOutPutX(width);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(height);                         //保存文件的高度。单位像素
        imagePicker.setSelectLimit(1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        result2SetImg(requestCode, resultCode, data);
    }

    private final int REQUEST_CODE_PHOTO = 100;

    void start2GetImg() {
        Intent intent = new Intent(this, ImageGridActivity.class);

//        intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, null);
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

    void result2SetImg(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_PHOTO || resultCode != ImagePicker.RESULT_CODE_ITEMS)
            return;
        if (data == null) return;

        ArrayList<ImageItem> imgs = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
        if (imgs == null || imgs.size() <= 0) {
            return;
        }
        WaitUI.Show(context);
        presenter.saveImg2AliOss(imgs.get(0).path);

//        Glide.with(context).load(imgs.get(0).path)
////                .error(R.drawable.shangchuan)
////                .placeholder(R.drawable.shangchuan)
//                .into(iv_up);
    }

    @Override
    public void onSaveImg2AliOss(String url) {
        img_url = url;
// fuck  ali
        handler.sendEmptyMessage(0);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            WaitUI.Cancel();
            Glide.with(context).load(img_url)
                    //                .error(R.drawable.shangchuan)
                    //                .placeholder(R.drawable.shangchuan)
                    .into(iv_up);
            return false;
        }
    });

    @Override
    public void onRefund() {
        toastShort("申请成功");
        setResult(1, new Intent().putExtra("isFinish", true));
        finish();
    }
}
