package com.pbph.shoppingmall.utils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.util.AttributeSet;
import android.widget.TextView;

import com.pbph.shoppingmall.R;

/**
 * Created by Administrator on 2018/4/26.
 */

@SuppressLint("AppCompatCustomView")
public class LinearGradientTextView extends TextView {


    int startColor, endColor;


    public LinearGradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinearGradientTextView);

        startColor = typedArray.getColor(R.styleable.LinearGradientTextView_startColor, Color.BLACK);

        endColor = typedArray.getColor(R.styleable.LinearGradientTextView_endColor, Color.BLACK);

        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w1, int h1, int oldw, int oldh) {
        super.onSizeChanged(w1, h1, oldw, oldh);

        LinearGradient mLinearGradient = new LinearGradient(
                0, 0,
                getMeasuredWidth(), getMeasuredHeight(),
                new int[]{startColor, endColor},
                new float[]{0.1f,  1},
                LinearGradient.TileMode.CLAMP);
        getPaint().setShader(mLinearGradient);
    }


}
