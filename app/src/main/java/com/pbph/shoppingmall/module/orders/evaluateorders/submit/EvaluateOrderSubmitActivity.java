package com.pbph.shoppingmall.module.orders.evaluateorders.submit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.request.CommentRequest;
import com.pbph.shoppingmall.model.response.GetCommentDetailResponse;
import com.pbph.shoppingmall.utils.GlideImageLoaderUtils;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.pbph.shoppingmall.utils.ui.ScrollGridView;
import com.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateOrderSubmitActivity extends BaseActivity<Presenter> implements Contract.View {

    CommonTitlebar commonTitlebar;

    private LinearLayout layoutRoot;

    private RatingBar rbarService;
    private RatingBar rbarLogistics;

    LinearLayout ll_goods_evaluate;
    List<GoodsCard> goodsCards;

    private CheckBox cb_hid;

    private Button button;

    int line_nums = 4;
    int img_wh = 0;

    String orderId;
    private String pathss;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_evaluateordersubmit;
    }


    @Override
    protected void initData() {

        orderId = getIntent().getStringExtra("orderId");

        scaleWH();
        initImagePager();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "评价订单", true);

        layoutRoot = findViewById(R.id.layout_root);

        ll_goods_evaluate = findViewById(R.id.ll_goods_evaluate);

        rbarService = findViewById(R.id.rbar_service);
        rbarLogistics = findViewById(R.id.rbar_logistics);


        cb_hid = findViewById(R.id.cb_hid);

        button = findViewById(R.id.button);
        button.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

                if (!canEdit && !canUpload) {
                    return;
                }

                CommentRequest request = new CommentRequest();

                int rating = (int) (rbarService.getRating() * 20f);
                request.commentDesk = rating;

                rating = (int) (rbarLogistics.getRating() * 20f);
                request.commentLogistics = rating;

//                是否匿名(0否1是)
//                request.isAnonymous = cb_hid.isChecked() ? 1 : 0;
                request.isAnonymous = 0;


                JSONArray jsonArray = new JSONArray();
                for (GoodsCard card : goodsCards) {
                    JSONObject jsonObject = card.toJSONObject();
                    if (jsonObject == null) {
                        toastShort("系统错误");
                        return;
                    }
                    jsonArray.put(jsonObject);
                }
                //不可编辑代表 第二次进入评论 那么 就要判断 是否 上传图片了。
                if (!canEdit) {
//                    如果第二次进入没有上传图片则不可以提交。
                    if (!hasUpLoadImg) {
                        toastShort("请添加晒单图片");
                        return;
                    }
                }

                request.bacrOrderDtoListJson = jsonArray.toString();

                presenter.submit(request);
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

        if (goodsCards == null || goodsCards.size() <= 0) return;
        for (GoodsCard card : goodsCards) {
            card.onImgsResult(requestCode, resultCode, data);
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

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public void setCommentDetail(GetCommentDetailResponse.DataBean vo) {

        float rating = vo.getCommentDesk() / 20f;
        if (rating <= 0) {
            rating = 5;
            rbarService.setIsIndicator(false);
        } else {
            rbarService.setIsIndicator(true);
        }
        rbarService.setRating(rating);

        rating = vo.getCommentLogistics() / 20f;
        if (rating <= 0) {
            rating = 5;
            rbarLogistics.setIsIndicator(false);
        } else {
            rbarLogistics.setIsIndicator(true);
        }
        rbarLogistics.setRating(rating);

        cb_hid.setChecked(vo.getIsAnonymous() == 1);

        setGoodsDetail(vo.getCommentDTOList());

    }

    @Override
    public void onSaveImg2AliOss(int id, List<String> urls) {


//      Map<String, String> map = new HashMap<>();
//            map.put(String.valueOf(i),urls.get(i));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < urls.size(); i++) {
            String path = urls.get(i);
            if (StringUtils.isEmpty(path)) continue;
            sb.append(",");
            sb.append(path);
        }
        if (sb.length() > 0) {
            hasUpLoadImg = true;
            pathss = sb.substring(1);
        }

      /*  temps = urls;


        handler.sendEmptyMessage(id);
*/


    }

    @Override
    public void onSaveImg2AliOssonFail() {
        for (GoodsCard card : goodsCards) {
            card.adapter.clearDatas();
            card.adapter.notifyDataSetChanged();
            break;
        }
    }

 /*   List<String> temps;
 Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            for (GoodsCard card : goodsCards) {
                if (card.id != msg.what) continue;

                card.adapter.clearDatas();

                for (int i = 0, c = temps.size(); i < c; i++) {
                    card.adapter.addData(i, temps.get(i));
                }
                card.adapter.notifyDataSetChanged();
                WaitUI.Cancel();
                break;
            }
            return true;
        }
    });*/

    @Override
    public void onSubmit() {
        setResult(1, new Intent().putExtra("isFinish", true));
        finish();
    }

    void setGoodsDetail(List<GetCommentDetailResponse.DataBean.CommentDTOListBean> goodsList) {

        goodsCards = new ArrayList<>(goodsList.size());

        LayoutInflater inflater = LayoutInflater.from(context);
        int i = 0;
        for (GetCommentDetailResponse.DataBean.CommentDTOListBean goodVo : goodsList) {

            if (i != 0) {
                View view = inflater.inflate(R.layout.layout_evaluateorder_line, null);
                ll_goods_evaluate.addView(view);
            }

            View view = inflater.inflate(R.layout.layout_evaluateorder_goods, null);
            GoodsCard goodsCard = new GoodsCard(view, goodVo, i++);

            ll_goods_evaluate.addView(view);
            goodsCards.add(goodsCard);
        }

        for (GoodsCard card : goodsCards) {
            card.setCanEdit(canEdit);
            card.setCanUpload(canUpload);
        }

        if (canEdit || canUpload) return;
//如果不可编辑文字 星级 和 图片 代表 这次评价只能看 不能操作了
        cb_hid.setEnabled(false);

        button.setVisibility(View.GONE);
        button.setEnabled(false);
    }

    //    true 第一次进入，则可以对商品进行评价，otherwise 则不可以。
    boolean canEdit = true;
    //    如果上传过图片 就不可以 再次上传 图片，否则就可以
    boolean canUpload = true;

    boolean hasUpLoadImg = false;

    public class GoodsCard {

        public int id;
        public int IMG_REQUEST_CODE = 100;

        public GetCommentDetailResponse.DataBean.CommentDTOListBean goodVo;
        public View view;

        public ImageView ivPic;
        public TextView tvName;

        public TextView tvPrice;

        public RatingBar rbarDesc;

        public EditText edt_content;

        public ScrollGridView gridView;
        public EvaluateOrderSubmitDataAdapter adapter;
        public ArrayList<ImageItem> imgs;

        public GoodsCard(View view, GetCommentDetailResponse.DataBean.CommentDTOListBean goodVo, int code) {

            this.goodVo = goodVo;
            id = code;
            IMG_REQUEST_CODE += code;

            this.view = view;

            ivPic = view.findViewById(R.id.iv_pic);
            tvName = view.findViewById(R.id.tv_name);
            tvPrice = view.findViewById(R.id.tv_price);

            rbarDesc = view.findViewById(R.id.rbar_desc);

            edt_content = view.findViewById(R.id.edt_content);

            gridView = view.findViewById(R.id.gridView);
            gridView.setNumColumns(line_nums);

            adapter = new EvaluateOrderSubmitDataAdapter(activity, gridView, EvaluateOrderSubmitViewHolder.class);
            adapter.wh = img_wh;

            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener((parent, view1, position, id) -> {

                if (!StringUtils.isEmpty((String) adapter.getItem(position))) return;

                Intent intent = new Intent(context, ImageGridActivity.class);

                intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, imgs);
                startActivityForResult(intent, IMG_REQUEST_CODE);
            });

            setDatas();
        }

        public void setDatas() {

            Glide.with(adapter.context).load(goodVo.getGoodsInfoImgUrl()).into(ivPic);

            tvName.setText(goodVo.getGoodsInfoName());

            tvPrice.setText(StringUtils.moneyFen2Yuan(goodVo.getGoodsInfoPreferPrice()));

//
            float rating = goodVo.getProductDescription() / 20f;
            if (rating <= 0) {
                rating = 5;
            } else {
                canEdit = false;
            }
            rbarDesc.setRating(rating);
            rbarDesc.setOnRatingBarChangeListener((ratingBar, rating1, fromUser) -> {
                if (rating1 >= 1) return;
                rbarDesc.setRating(1);
            });
//
            edt_content.setText(goodVo.getCommentContent());


            List<GetCommentDetailResponse.DataBean.CommentDTOListBean.ShareImgListBean> imgs = goodVo.getShareImgList();
            adapter.clearDatas();
            if (null == imgs || imgs.isEmpty()) {
                return;
            }
            canUpload = false;

            for (int i = 0, c = imgs.size(); i < c; i++) {
                GetCommentDetailResponse.DataBean.CommentDTOListBean.ShareImgListBean vo = imgs.get(i);
                adapter.addData(i, vo.getShareImage());
            }
            adapter.notifyDataSetChanged();
        }

        public void setCanEdit(boolean b) {
            rbarDesc.setIsIndicator(!b);
            edt_content.setEnabled(b);
        }

        public void setCanUpload(boolean b) {
            adapter.canEdit = b;
            adapter.notifyDataSetChanged();
        }

        public void onImgsResult(int requestCode, int resultCode, Intent data) {

            if (requestCode != IMG_REQUEST_CODE || resultCode != ImagePicker.RESULT_CODE_ITEMS) {
                return;
            }

            if (data == null) {
                return;
            }
            adapter.clearDatas();

            imgs = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

            if (null == imgs || imgs.isEmpty()) return;

            List<String> list = new ArrayList<>(imgs.size());
            for (ImageItem vo : imgs) {
                list.add(vo.path);
            }

            for (GoodsCard card : goodsCards) {
                card.adapter.clearDatas();

                for (int i = 0, c = list.size(); i < c; i++) {
                    card.adapter.addData(i, list.get(i));
                }
                card.adapter.notifyDataSetChanged();
                break;
            }

            presenter.saveImg2AliOss(id, list);

        }

        public JSONObject toJSONObject() {

            try {
                int rating = (int) (rbarDesc.getRating() * 20f);
                if (rating <= 0) {
                    toastShort("请对产品描述进行评价");
                    return null;
                }
                String content = edt_content.getText().toString().trim();
//                if (StringUtils.isEmpty(content)) {
//                    toastShort("请输入评价内容");
//                    return null;
//                }

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("goodsInfoId", goodVo.getGoodsInfoId());//  Y	货品id
                jsonObject.put("goodsId", goodVo.getGoodsId());//  Y	商品id

                jsonObject.put("productDescription", rating);//		Y	产品描述(一个星20分)

                jsonObject.put("commentContent", content);//	Y	评论内容

               /* StringBuilder sb = new StringBuilder();
                for (int i = 0, c = adapter.getCount(); i < c; i++) {

                    String path = (String) adapter.getItem(i);
                    if (StringUtils.isEmpty(path)) continue;

                    sb.append(",");
                    sb.append(path);
                }
                if (sb.length() > 0) {
                    hasUpLoadImg = true;
                    String paths = sb.substring(1);
                    jsonObject.put("sharePhotos", paths);//  N	评价晒单图片（多张以逗号隔开）
                }*/
                jsonObject.put("sharePhotos", pathss);//  N	评价晒单图片（多张以逗号隔开）

                return jsonObject;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


}
