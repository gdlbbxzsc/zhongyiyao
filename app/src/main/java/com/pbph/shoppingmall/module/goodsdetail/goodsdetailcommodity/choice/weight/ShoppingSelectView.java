package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.choice.weight;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.ProductDetailResponse;

import java.util.List;


/*
 */
public class ShoppingSelectView extends LinearLayout {
    /**
     * 数据源
     */
    private List<ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean> openSpecBeans;
    /**
     * 上下文
     */
    private Context context;

    /**
     * 规格标题栏的文本间距
     */
    private int titleMargin = 8;
    /**
     * 整个商品属性的左右间距
     */
    private int flowLayoutMargin = 16;
    /**
     * 属性按钮的高度
     */
    private int buttonHeight = 25;
    /**
     * 属性按钮之间的左边距
     */
    private int buttonLeftMargin = 10;
    /**
     * 属性按钮之间的上边距
     */
    private int buttonTopMargin = 8;
    /**
     * 文字与按钮的边距
     */
    private int textPadding = 10;
    /**
     * 选择后的回调监听
     */
    private OnSelectedListener listener;


    public ShoppingSelectView(Context context) {
        super(context);
        initView(context);
    }

    public ShoppingSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        this.context = context;
    }

    FlowLayout layout;

    public void getView() {
        if (openSpecBeans==null){
            return;
        }

        if (openSpecBeans.size() < 0) {
            return;
        }
        for (ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean attr : openSpecBeans) {
            //设置规格分类的标题
            TextView textView = new TextView(context);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            int margin = dip2px(context, titleMargin);
            textView.setText(attr.getSpecName());
            textView.setTextColor(Color.parseColor("#000000"));
            params.setMargins(margin, margin, margin, margin);
            textView.setLayoutParams(params);
            addView(textView);
            //设置一个大规格下的所有小规格
            layout = new FlowLayout(context);
            layout.setTitle(attr.getSpecName());
            layout.setTilteId(attr.getSpecId());
            layout.setPadding(dip2px(context, flowLayoutMargin), 0, dip2px(context,
                    flowLayoutMargin), 0);
            //设置选择监听
            if (listener != null) {
                layout.setListener(listener);
            }
            for (int i = 0; i < attr.getSpecDetails().size(); i++) {
                ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean.SpecDetailsBean smallAttr = attr.getSpecDetails()
                        .get(i);
                //属性按钮
                RadioButton button = new RadioButton(context);
                //默认选中第一个
//                if (i == 0) {
//                    attr.choose_child_id=Integer.valueOf(smallAttr.getSpecValueId());
//                    attr.choose_child_name = smallAttr.getSpecValue();
//                    button.setChecked(true);
//                }

             if (1==smallAttr.getIsPitchOn()){
                 attr.choose_child_id = Integer.valueOf(smallAttr.getSpecValueId());
                 attr.choose_child_name = smallAttr.getSpecValue();
                 button.setChecked(true);
             }
                //设置按钮的参数
                LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                        dip2px(context, buttonHeight));
                //设置文字的边距
                int padding = dip2px(context, textPadding);
                button.setPadding(padding, 0, padding, 0);
                //设置margin属性，需传入LayoutParams否则会丢失原有的布局参数
                MarginLayoutParams marginParams = new MarginLayoutParams(buttonParams);
                marginParams.leftMargin = dip2px(context, buttonLeftMargin);
                marginParams.topMargin = dip2px(context, buttonTopMargin);

                button.setLayoutParams(marginParams);
                button.setGravity(Gravity.CENTER);
                button.setTextColor(Color.parseColor("#000000"));
                button.setBackgroundResource(R.drawable.tv_sel);
                button.setButtonDrawable(android.R.color.background_dark);
                button.setText(smallAttr.getSpecValue());//设置规格内容
                button.setId(Integer.valueOf(smallAttr.getSpecValueId()));//设置规格ID

                button.setTag(attr);
                layout.addView(button);
            }
            addView(layout);
        }

    }


    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setData(List<ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean> openSpecBeans) {
        this.openSpecBeans = openSpecBeans;
        getView();
    }

    public void setOnSelectedListener(OnSelectedListener listener) {
        this.listener = listener;
    }
}
