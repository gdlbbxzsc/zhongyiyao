package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.choice;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.utils.Logger;
import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.ProductDetailResponse;
import com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.choice.weight
        .ShoppingSelectView;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/24.
 */

public class ChoicePopup extends PopupWindow implements View.OnClickListener {

    private LayoutInflater inflater;
    View showView;
    private ImageView iv_choice_chose;
    private ShoppingSelectView shoppingSelectView;
    private ImageView iv_choice_remove, iv_choice_add;
    private int goodsNum = 1;
    private TextView tv_choice_num;
    private Button btn_choice_confirm;
    StringBuilder sbChildId = new StringBuilder();
    StringBuilder sbChildName = new StringBuilder();
    StringBuilder sbTypeId = new StringBuilder();
    ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean smallAttr;
    List<ProductDetailResponse.DataBean
            .GoodsDetailBean.GoodsInfoBean.GoodsSpecBean> openSpecBeans;

    private ChoiceInfo choiceInfo;
    private Context context;
    private ImageView iv_goods_pic;
    private TextView tv_goods_money,tv_goods_code;

    public ChoicePopup(Context context, View showView, List<ProductDetailResponse.DataBean
            .GoodsDetailBean.GoodsInfoBean.GoodsSpecBean> openSpecBeans) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
        setBackgroundDrawable(new BitmapDrawable());
        // 设置SelectPicPopupWindow弹出窗体的宽
        // MainActivity.screenWidth / 4
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.Popupwindow);
        this.openSpecBeans = openSpecBeans;
        View view = inflater.inflate(R.layout.pop_list_choice, null);
        shoppingSelectView = view.findViewById(R.id.shoppingSelectView);
        iv_choice_chose = view.findViewById(R.id.iv_choice_chose);
        iv_choice_remove = view.findViewById(R.id.iv_choice_remove);
        tv_choice_num = view.findViewById(R.id.tv_choice_num);
        iv_choice_add = view.findViewById(R.id.iv_choice_add);
        btn_choice_confirm = view.findViewById(R.id.btn_choice_confirm);
        iv_goods_pic = view.findViewById(R.id.iv_goods_pic);
        tv_goods_money = view.findViewById(R.id.tv_goods_money);
        tv_goods_code = view.findViewById(R.id.tv_goods_code);

        btn_choice_confirm.setOnClickListener(this);
        iv_choice_add.setOnClickListener(this);
        iv_choice_remove.setOnClickListener(this);
        iv_choice_chose.setOnClickListener(this);
        shoppingSelectView.setOnSelectedListener((rb, title, titleId, smallTitle, id) -> {
            sbTypeId = new StringBuilder();
            sbChildId = new StringBuilder();
            smallAttr = (ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean) rb.getTag();
            Logger.e(rb.getId() + " " + id + " " + rb.getTag());
            smallAttr.choose_child_id = id;
            smallAttr.choose_child_name = smallTitle;
            for (ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean vo : openSpecBeans) {
                sbChildId.append(",");
                sbChildId.append(vo.choose_child_id);
                sbTypeId.append(",");
                sbTypeId.append(vo.getSpecId());
            }

            int goodsId = smallAttr.getSpecDetails().get(0).getGoodsId();
            choiceInfo.getGoodsInfoId(sbChildId.toString().substring(1, sbChildId.length()),
                    sbTypeId.toString().substring(1,sbTypeId.length()),goodsId);
        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        view.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
//                dismiss();

            }
        });
        // 刷新状态
        this.update();
        this.showView = showView;

        shoppingSelectView.setData(openSpecBeans);


    }

    /**
     * 设置内容数据
     * 图片 价格 编号
     */
    public void setContentData(String imgUrl,String price ,String code){
        Glide.with(context).load(imgUrl).into(iv_goods_pic);
        int prices = Integer.valueOf(price);
        tv_goods_money.setText("¥"+ StringUtils.moneyFen2Yuan(prices));
        tv_goods_code.setText("商品编号:"+code);

    }

    public void show() {
        show(showView);
    }

    public void show(View view) {
        this.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public void setChoiceInfo(ChoiceInfo choiceInfo) {
        this.choiceInfo = choiceInfo;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_choice_chose:
                dismiss();
                break;
            case R.id.iv_choice_add:
                goodsNum++;
                tv_choice_num.setText(String.valueOf(goodsNum));
                break;
            case R.id.iv_choice_remove:
                if (goodsNum == 1) return;
                goodsNum--;
                tv_choice_num.setText(String.valueOf(goodsNum));
                break;
            case R.id.btn_choice_confirm:

                sbChildId = new StringBuilder();
                sbChildName = new StringBuilder();
                for (ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean vo :
                        openSpecBeans) {
                    sbChildId.append(",");
                    sbChildId.append(vo.choose_child_id);

                    sbChildName.append(",");
                    sbChildName.append(vo.getChoose_child_name());

                    sbTypeId.append(",");
                    sbTypeId.append(vo.getSpecId());
                }
//                for (ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean vo :
//                        openSpecBeans) {
//
//                }
                choiceInfo.getShopId(sbChildId.toString() + "," + tv_choice_num.getText(),
                        sbTypeId.toString(),sbChildName.toString().substring(1,sbChildName.length()),
                        Integer.valueOf
                                (tv_choice_num
                                .getText().toString()));
                dismiss();
                break;
        }
    }

    public interface ChoiceInfo {
        void getShopId(String goodsId,String typeId,String goodsName,int goodsNum);

        void getGoodsInfoId(String childId,String typeId,int goodsId);

    }

}
