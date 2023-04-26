package com.pbph.shoppingmall.module.input.edit;

import android.text.InputFilter;
import android.text.method.BaseKeyListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public abstract class AEditData {

    public AEditData() {

        input_edit_title = initTitle();
        input_edit_hint = initHint();
        input_edit_toast = initToast();

        pattern_string = initPattern();
        error_string = initError();
        error_empty_string = initEmptyError();

        List<InputFilter> list = initFilters();
        if (list != null && list.size() > 0) {
            inputFilters.addAll(list);
        }
        int len = initMaxLength();
        if (len >= 0) {
            inputFilters.add(new InputFilter.LengthFilter(len));
        }


        keyListener = initKeyListener();
    }


    ///////////// 标题
    public String input_edit_title = "请输入";

    public abstract String initTitle();


    ///////////// 输入框提示文字
    public String input_edit_hint;

    public String initHint() {
        return "请输入...";
    }

    ///////////// 输入提示
    public String input_edit_toast = "";

    public String initToast() {
        return "";
    }

    ////////////// 正则表达式 最终验证
    public String pattern_string = null;

    public String initPattern() {
        return null;
    }

    //////////////验证错误提示文字
    public String error_string = null;

    public String initError() {
        return "请输入正确字符";
    }

    //如果此字段为空，代表输入限制可为空，否则不为空
    public String error_empty_string = null;

    public String initEmptyError() {
        return "请输入";
    }


    ////////////// 过滤器 可以 编写自定义过滤器设置字符串的样式等，如 moneyfilter那样
//    filter 功能比较强大可以限制任何你想限制的 长度 输入类型输入样式等等
    public List<InputFilter> inputFilters = new ArrayList<>();

    public List<InputFilter> initFilters() {
        return null;
    }

    //////////////
    public int initMaxLength() {
        return -1;
    }

    ///////////// 输入限制，可以限制 键盘弹出样式/输入类型 以及可输入字符类型
    public BaseKeyListener keyListener;

    public BaseKeyListener initKeyListener() {
        return null;
    }


//    //字符过滤功能

//    NumberKeyListener myKeyListener = new NumberKeyListener() {
//        public int getInputType() {
//            //指定键盘类型
//            return InputType.TYPE_CLASS_TEXT;
//        }
//
//        protected char[] getAcceptedChars() {
//            //指定你所接受的字符
//            return getString("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-").toCharArray();
//        }
//    };


    //    以下侦听器类型将用于编写此类型的应用程序。
//    BaseKeyListener这是一个关键侦听器的抽象基类。
//    DateTimeKeyListener用于在同一文本字段中输入日期和时间。
//    MetaKeyKeyListener此基类封装了用于跟踪元键（如SHIFT，ALT和SYM）状态以及选择文本的伪状态的行为。
//    NumberKeyListener这是用于数字文本输入。
//    TextKeyListener这是键入正常文本的关键侦听器。
//    TimeKeyListener这是用于在文本字段中输入时间。

//    //只接受整数输入
//    KeyListener l = new DigitsKeyListener(fasle,false);
//    //接受有符号整数输入
//    KeyListener l = new DigitsKeyListener(true,false);
//    //接受小数，整数输入
//    KeyListener l = new DigitsKeyListener(false,true);
//    //接受有符号整数/小数输入
//    KeyListener l = new DigitsKeyListener(true,true);


//    //allows digits with positive/negative signs and decimal points
//    DigitsKeyListener digkl1 = DigitsKeyListener.getInstance(true, true);
//
//    //allows positive integer only (no decimal values allowed)
//    DigitsKeyListener digkl2 = DigitsKeyListener.getInstance();
//
//    //allows date only
//    DateKeyListener dtkl = new DateKeyListener();
//
//    //allows multitap with 12-key keypad layout
//    MultiTapKeyListener multitapkl = new MultiTapKeyListener(TextKeyListener.Capitalize.WORDS, true);
//
//    //allows qwerty layout for typing
//    QwertyKeyListener qkl = new QwertyKeyListener(TextKeyListener.Capitalize.SENTENCES, true);
}
