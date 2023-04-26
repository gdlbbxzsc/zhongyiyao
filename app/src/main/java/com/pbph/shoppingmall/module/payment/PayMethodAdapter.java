package com.pbph.shoppingmall.module.payment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.PayMethodResponse;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/10/18 16:42
 * @ email：gdutxiaoxu@163.com
 */
public class PayMethodAdapter extends RecyclerView.Adapter<PayMethodAdapter.PayMethodAdaptereListHolder> implements View.OnClickListener {

    private List<PayMethodResponse.DataBean> payMethodBeans;
    private Context context;
    private OnMethodItemClickListener mOnItemClickListener = null;

    public PayMethodAdapter(Context context, List<PayMethodResponse.DataBean> list) {
        this.context = context;
        this.payMethodBeans = list;
    }

    public PayMethodAdapter(Context context) {
        this.context = context;
    }

    public interface OnMethodItemClickListener {
        void onPayMethodItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            int position = Integer.parseInt(v.getTag().toString());
            mOnItemClickListener.onPayMethodItemClick(v, position);
        }
    }

    public void setOnMethodItemClickListener(OnMethodItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public PayMethodAdaptereListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pay_method, parent, false);
        return new PayMethodAdaptereListHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(PayMethodAdaptereListHolder holder, final int position) {
        holder.tvPayDescribe.setText(payMethodBeans.get(position).getPayDescribe());
        holder.tvPayName.setText(payMethodBeans.get(position).getPayName());
        if (payMethodBeans.get(position).isChecked()) {
            holder.cbPay.setChecked(true);
        } else {
            holder.cbPay.setChecked(false);
        }
//        Glide.with(context).load(payMethodBeans.get(position).getPayImage()).asBitmap().centerCrop().error(R.mipmap.ic_launcher)
//                .placeholder(R.mipmap.ic_launcher).into(new BitmapImageViewTarget(holder.ivPayImage) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(false);
//                holder.ivPayImage.setImageDrawable(circularBitmapDrawable);
//            }
//        });

        Glide.with(context).load(payMethodBeans.get(position).getPayImage()).into(holder.ivPayImage);

        holder.card.setOnClickListener(view -> {
            mOnItemClickListener.onPayMethodItemClick(view, position);
        });
    }

    @Override
    public int getItemCount() {
        return payMethodBeans.size();
    }

    static class PayMethodAdaptereListHolder extends RecyclerView.ViewHolder {
        View card;
        CheckBox cbPay;
        ImageView ivPayImage;
        TextView tvPayName, tvPayDescribe;

        public PayMethodAdaptereListHolder(View view) {
            super(view);
            card = view;
            tvPayName = view.findViewById(R.id.tv_pay_name);
            tvPayDescribe = view.findViewById(R.id.tv_pay_describe);
            ivPayImage = view.findViewById(R.id.iv_pay_image);
            cbPay = view.findViewById(R.id.cb_pay);
        }
    }
}
