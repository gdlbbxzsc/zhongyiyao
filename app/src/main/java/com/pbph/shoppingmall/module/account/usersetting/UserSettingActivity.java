package com.pbph.shoppingmall.module.account.usersetting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.request.UpdateCustomerInfoRequest;
import com.pbph.shoppingmall.model.request.UpdateCustomerRequest;
import com.pbph.shoppingmall.model.response.GetMyCustomerResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.module.account.updateaccount.UpdateActActivity;
import com.pbph.shoppingmall.module.account.updatepassword.UpdatePwdActivity;
import com.pbph.shoppingmall.module.addressmng.list.AddressMngActivity;
import com.pbph.shoppingmall.module.bill.list.BillMngActivity;
import com.pbph.shoppingmall.module.input.choose.ChooseDataVo;
import com.pbph.shoppingmall.module.input.choose.InputChooseActivity;
import com.pbph.shoppingmall.module.input.choose.type.SexData;
import com.pbph.shoppingmall.module.input.edit.InputEditActivity;
import com.pbph.shoppingmall.module.input.edit.type.NickNameData;
import com.pbph.shoppingmall.utils.GlideCatchUtil;
import com.pbph.shoppingmall.utils.GlideImageLoaderUtils;
import com.pbph.shoppingmall.utils.dialog.YesNoDialog;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.DateUtils;
import com.utils.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;

public class UserSettingActivity extends BaseActivity<Presenter> implements Contract.View {


    private final int REQUEST_CODE_PHOTO = 100;
    private final int REQUEST_CODE_NAME = 101;
    private final int REQUEST_CODE_SEX = 102;

    CommonTitlebar commonTitlebar;


    private LinearLayout layoutRoot;

    private ImageView ivPhoto;
    private TextView tvName;
    private TextView tvSex;
    private TextView tvBirthday;


    TimePickerView pvTime;
    DateUtils dateUtils;


    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_usersetting;
    }


    @Override
    protected void initData() {
        initImagePager();
//        initSexOptionsPickerView();
        initTimePicker();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "账户设置", false);

        layoutRoot = findViewById(R.id.layout_root);

        ivPhoto = findViewById(R.id.iv_photo);


        tvName = findViewById(R.id.tv_name);
        tvSex = findViewById(R.id.tv_sex);
        tvBirthday = findViewById(R.id.tv_birthday);

        ivPhoto.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                start2GetImg();
            }
        });

        findViewById(R.id.ll_name).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                InputEditActivity.startActivityForResult(UserSettingActivity.this, NickNameData.class, (String) tvName.getTag(), REQUEST_CODE_NAME);
            }
        });
        findViewById(R.id.ll_sex).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
//                show2GetSex();
                Integer sex_id = (Integer) tvSex.getTag();
                InputChooseActivity.startActivityForResult(UserSettingActivity.this, SexData.class, sex_id, REQUEST_CODE_SEX);
            }
        });

        findViewById(R.id.ll_birthday).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                pvTime.show();
            }
        });



        findViewById(R.id.ll_updage_pwd).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                startActivity(new Intent(context, UpdatePwdActivity.class)
                        .putExtra("isForget", false));
            }
        });
        findViewById(R.id.ll_updage_act).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                startActivity(new Intent(context, UpdateActActivity.class));
            }
        });

        findViewById(R.id.button).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                YesNoDialog.show(context, "确定退出登录?", 0, position -> presenter.logout());
            }
        });

    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        result2SetImg(requestCode, resultCode, data);
        result2SetName(requestCode, resultCode, data);
        result2SetSex(requestCode, resultCode, data);
    }

    ImagePicker imagePicker;

    private void initImagePager() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoaderUtils());
        imagePicker.setShowCamera(true);
        imagePicker.setMultiMode(false);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(true);
        imagePicker.setStyle(CropImageView.Style.CIRCLE);
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        imagePicker.setFocusWidth(width);
        imagePicker.setFocusHeight(height);
        imagePicker.setSelectLimit(1);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setOutPutX(width);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(height);                         //保存文件的高度。单位像素
    }


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
        img_url = imgs.get(0).path;

//            Glide.with(this).load(url).error(R.drawable.sztouxiang)
//                    .placeholder(R.drawable.sztouxiang).into(ivPhoto);

        setRoundImg(img_url, ivPhoto, R.drawable.sztouxiang);

        presenter.saveImg2AliOss(img_url);

    }

    private void setRoundImg(String url, ImageView imageView, int defaultImg) {
        Glide.with(this).load(url)
                .asBitmap()
//                .centerCrop()
                .error(defaultImg)
                .placeholder(defaultImg)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

//    OptionsPickerView pvOptions;
//    List<String> sexList = new ArrayList<>();
//
//    private void initSexOptionsPickerView() {
//        sexList.add("男");
//        sexList.add("女");
//        pvOptions = new OptionsPickerView.Builder(this, (int options1, int option2, int options3, View v) -> {
//            tvSex.setText(sexList.get(options1));
//        }).build();
//        pvOptions.setPicker(sexList);
//    }
//
//    private void show2GetSex() {
//        pvOptions.show();
//    }


    void result2SetName(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_NAME || resultCode != RESULT_OK) return;

//        tvName.setTag(null);
        if (data == null) return;

        String str = data.getStringExtra(InputEditActivity.KEY_EDIT_TEXT);
        if (StringUtils.isEmpty(str)) return;

        tvName.setTag(str);
        tvName.setText(str);

        UpdateCustomerRequest<ResultResponse> request = new UpdateCustomerRequest<>();
        request.customerNickname = str;
        presenter.updateCustomerRequest(request);
    }

    void result2SetSex(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SEX || resultCode != RESULT_OK) return;

//        tvSex.setTag(null);
        if (data == null) return;

        ChooseDataVo vo = (ChooseDataVo) data.getSerializableExtra(InputChooseActivity.KEY_CHOOSE_ITEM);
        if (vo == null) return;

        tvSex.setTag(vo.id);
        tvSex.setText(vo.text);

        UpdateCustomerInfoRequest<ResultResponse> request = new UpdateCustomerInfoRequest<>();
        request.sex = vo.id;
        presenter.updateCustomerInfoRequest(request);
    }


    void initTimePicker() {

        //设置起始年份
        Calendar startDate = Calendar.getInstance();
        startDate.set(1949, 10, 1);
        //设置结束年份
        Calendar endDate = Calendar.getInstance();
//        endDate.set(2020, 1, 1);

        pvTime = new TimePickerView.Builder(this, (date, v) -> {//选中事件回调
            dateUtils = new DateUtils(date);
            String dateStr = dateUtils.getString(DateUtils.PATTERN_5);
            tvBirthday.setTag(dateStr);
            tvBirthday.setText(dateStr);

            UpdateCustomerInfoRequest<ResultResponse> request = new UpdateCustomerInfoRequest<>();
            request.customerBirthday = dateStr;
            presenter.updateCustomerInfoRequest(request);

        })
                .setRangDate(startDate, endDate)
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .isCyclic(true)//是否循环滚动
                .build();
    }


//                        .setCancelText("取消")//取消按钮文字
//                        .setSubmitText("确定")//确认按钮文字
////                        .setContentSize(18)//滚轮文字大小
////                        .setTitleSize(20)//标题文字大小
////                        .setTitleText("选择时间")//标题文字
////                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
////                        .isCyclic(true)//是否循环滚动
////                        .setTitleColor(Color.BLACK)//标题文字颜色
////                        .setSubmitColor(R.color.hui)//确定按钮文字颜色
////                        .setCancelColor(R.color.hui)//取消按钮文字颜色
////                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
////                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                        .setLabel("年","月","日","时","分","秒")
//                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .isDialog(false)//是否显示为对话框样式

    String img_url;

    @Override
    public void setMyCustomer(GetMyCustomerResponse.DataBean dataBean) {
        GetMyCustomerResponse.DataBean.CustomerBean customerBean = dataBean.getCustomer();
        img_url = customerBean.getCustomerImg();
//        Glide.with(this).load(img_url).error(R.drawable.morentouxiang)
//                .placeholder(R.drawable.morentouxiang).into(ivPhoto);
        setRoundImg(img_url, ivPhoto, R.drawable.morentouxiang);


        tvName.setText(customerBean.getCustomerNickname());
        tvName.setTag(customerBean.getCustomerNickname());

        tvSex.setTag(dataBean.getCustomerInfo().getSex());
        switch (dataBean.getCustomerInfo().getSex()) {
            case 1: {
                tvSex.setText("男");
            }
            break;
            case 2: {
                tvSex.setText("女");
            }
            break;
            default: {
                tvSex.setText("保密");
            }
            break;
        }
        tvBirthday.setTag(dataBean.getCustomerInfo().getCustomerBirthday());
        tvBirthday.setText(dataBean.getCustomerInfo().getCustomerBirthday());
    }

    @Override
    public void onLogout() {
        finish();
    }
}
