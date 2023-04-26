package com.pbph.shoppingmall.module.suggest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.GlideImageLoaderUtils;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.pbph.shoppingmall.utils.ui.ScrollGridView;
import com.utils.StringUtils;

import java.util.ArrayList;

public class SuggestActivity extends BaseActivity<Presenter> implements Contract.View {


    CommonTitlebar commonTitlebar;


    private LinearLayout layoutRoot;

    private RadioButton radioButton1;


    EditText edt_content;
    EditText edt_call;


    int line_nums = 5;
    int img_wh = 0;
    public ScrollGridView gridView;
    public SuggestDataAdapter adapter;
    ArrayList<ImageItem> imgs;


    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_suggest;
    }


    @Override
    protected void initData() {
        scaleWH();
        initImagePager();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "意见反馈", false);

        layoutRoot = findViewById(R.id.layout_root);

        radioButton1 = findViewById(R.id.radioButton1);

        edt_content = findViewById(R.id.edt_content);
        edt_call = findViewById(R.id.edt_call);


        gridView = findViewById(R.id.gridView);
        gridView.setNumColumns(line_nums);

        adapter = new SuggestDataAdapter(activity, gridView, SuggestViewHolder.class);
        adapter.wh = img_wh;

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {

            if (!StringUtils.isEmpty((String) adapter.getItem(position))) return;

            Intent intent = new Intent(this, ImageGridActivity.class);

            intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, imgs);
            startActivityForResult(intent, IMG_REQUEST_CODE);
        });


        findViewById(R.id.button).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

            }
        });


    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    private final int IMG_REQUEST_CODE = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST_CODE && resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data == null) {
                return;
            }
            imgs = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

            adapter.clearDatas();
            if (null != imgs && !imgs.isEmpty()) {
                for (int i = 0, c = imgs.size(); i < c; i++) {
                    ImageItem vo = imgs.get(i);
                    adapter.addData(i, vo.path);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    ImagePicker imagePicker;

    private void initImagePager() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoaderUtils());
        imagePicker.setShowCamera(true);
        imagePicker.setMultiMode(true);
        imagePicker.setCrop(false);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
        imagePicker.setFocusWidth(width);
        imagePicker.setFocusHeight(height);
        imagePicker.setSelectLimit(3);
    }


    private void scaleWH() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        img_wh = outMetrics.widthPixels;
        int wh = context.getResources().getDimensionPixelSize(R.dimen.dp_16) * (line_nums - 1 + 2);
        img_wh -= wh;
        img_wh /= line_nums;
    }
}
