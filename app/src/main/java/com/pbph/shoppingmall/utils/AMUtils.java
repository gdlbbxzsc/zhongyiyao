package com.pbph.shoppingmall.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.android.utils.Logger;
import com.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AMUtils {

    /**
     * 手机号正则表达式
     **/
//    public final static String MOBLIE_PHONE_PATTERN = "^((13[0-9])|(15[0-9])|(18[0-9])|(14[7])|(17[0|6|7|8]))\\d{8}$";
    public final static String MOBLIE_PHONE_PATTERN = "^1[3-9]\\d{9}$";
    public final static String URL_PATTERN = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
    public final static String PUBLIC_NUMBER_PATTERN = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]{2,50}$";
    public final static String NUMBER_PATTERN = "^[1-9]\\d*$";
    public final static String LENGTH_PATTERN = "^.{12,50}$";
    //    public final static String ID_CARD_PATTERN = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
//    public final static String ID_CARD_PATTERN = "(^\\d{18}$)|(^\\d{15}$)";
//    public final static String ID_CARD_PATTERN = "^\\d{15}|^\\d{17}([0-9]|X|x)$";

    public final static String CODE_PATTERN = "[0-9]{6}";

    /**
     * 通过正则验证是否是合法手机号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isMobile(String phoneNumber) {
        Pattern p = Pattern.compile(MOBLIE_PHONE_PATTERN);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }


    public static boolean isNumCode(String code) {
        Pattern p = Pattern.compile(CODE_PATTERN);
        Matcher m = p.matcher(code);
        return m.matches();
    }
//    /**
//     * 通过正则验证是否是合法手机号码
//     *
//     * @param yzmStr
//     * @return
//     */
//    public static boolean isYZm(String yzmStr) {
//        if (StringUtils.isEmpty(yzmStr)) return false;
//        if (yzmStr.length() != 6) return false;
//        return StringUtils.isNumber(yzmStr);
//    }

    /**
     * 通过验证
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 验证editText里是否有表情
     *
     * @param content
     * @return
     */

    public static boolean hasEmoji(String content) {

        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {

            return true;
        }
        return false;
    }

    public static String readFileFromAsset2String(Context context, String fileName) {

        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    context.getAssets().open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
