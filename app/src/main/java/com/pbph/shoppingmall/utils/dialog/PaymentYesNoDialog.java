package com.pbph.shoppingmall.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.pbph.shoppingmall.R;


/**
 * Created by Administrator on 2018/3/12.
 */

public class PaymentYesNoDialog extends Dialog {

    String text;
    String title;
    public PaymentYesNoDialog(Context context,String title, String text, int position, OnClickRateDialog onClickRateListener) {
        this(context, R.style.Dialog,title, text, position, onClickRateListener);
    }

    public PaymentYesNoDialog(Context context, int themeResId,String title, String text, int position, OnClickRateDialog onClickRateListener) {
        super(context, themeResId);
        //        super(context, R.style.MyRateDialog);
        this.text = text;
        this.title = title;
        this.onClickRateListener = onClickRateListener;
        setCustomDialog(position);
    }


    private void setCustomDialog(int position) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_payment_yesno, null);

        TextView textView = mView.findViewById(R.id.textView);
        textView.setText(text);
        TextView tvTitle = mView.findViewById(R.id.title);
        tvTitle.setText(title);
        Button positiveButton = mView.findViewById(R.id.button2);
        Button negativeButton = mView.findViewById(R.id.button1);
        if (positiveButton != null) positiveButton.setOnClickListener(v -> {
            if (onClickRateListener != null)
                onClickRateListener.onClickRight(position);
            dismiss();
        });
        if (negativeButton != null) negativeButton.setOnClickListener(v -> {
//                if (onClickRateListener != null)
//                    onClickRateListener.onClickLeft();
            dismiss();
        });
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(mView);
    }

    OnClickRateDialog onClickRateListener;

    public interface OnClickRateDialog {
        //        void onClickLeft();
        void onClickRight(int position);
    }

    public PaymentYesNoDialog showAndReturn() {
        show();
        return this;
    }

    @Override
    public void show() {
        super.show();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        /////////获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        /////////设置高宽
        lp.width = (int) (screenWidth * 0.75); // 宽度
        lp.height = (int) (lp.width * 0.60);     // 高度
        dialogWindow.setAttributes(lp);
    }

    public static PaymentYesNoDialog show(Context context, String title,String text ,int position, OnClickRateDialog onClickRateListener) {
        return new PaymentYesNoDialog(context, title,text, position, onClickRateListener).showAndReturn();
    }
}
