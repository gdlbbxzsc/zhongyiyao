package com.pbph.shoppingmall.model.request;

import com.android.utils.Logger;
import com.pbph.mvp.base.model.BaseModel;
import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.mvp.rxjava2.Rx2Helper;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.http.HttpMgr;
import com.pbph.shoppingmall.http.HttpService;
import com.pbph.shoppingmall.utils.MD5Utils;
import com.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/3/7.
 */

public abstract class BaseRequest<T extends BaseResponesModel> extends BaseModel {

    public String sign;
    public String token = MyApplication.userInfo.getToken();
    public String clientType = "1";


    public abstract String getRequestPath();

    public Observable<T> request() {
        try {
            HttpService service = HttpMgr.createService();

            Method method = service.getClass().getMethod(getRequestPath(), Map.class);
            Object obj = method.invoke(service, this.toMap());

            return Rx2Helper.applySchedulers((Observable<T>) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    ////////////
    private final String connectStr = "$cxd$";

    public Map<String, Object> toMap() {

        Field[] fields = this.getClass().getFields();
        Arrays.sort(fields, new FieldComparator());


        Map<String, Object> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (Field field : fields) {
            try {
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                if (isStatic) continue;

                Object v = field.get(this);
                if (v == null) continue;

                String k = field.getName();
                if (k.equals("sign")) continue;

                map.put(k, v);

                String val = v.toString().trim();
                if (StringUtils.isEmpty(val)) continue;

                sb.append(k).append("=").append(val.trim()).append(connectStr);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Logger.e("加密前签名："+sb.toString());

        try {
            sign = MD5Utils.encrypt(sb.toString().getBytes("utf-8")).toUpperCase();
            Logger.e("加密后签名："+sign);
            map.put("sign", sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    class FieldComparator implements Comparator<Field> {
        public int compare(Field o1, Field o2) {
            return (o1.getName()).compareTo(o2.getName());
        }
    }
}
