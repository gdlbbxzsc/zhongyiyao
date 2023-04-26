package com.pbph.shoppingmall.utils.ui.goodstypechoosedrawer;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.message.FilterDatasResultMsg;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.pbph.shoppingmall.utils.ui.ScrollGridView;
import com.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/27.
 */

public class GoodsTypeChooseDrawer implements DrawerLayout.DrawerListener {

    Object activityOrFragment;
    Context context;
    LayoutInflater inflater;


    OnGoodsTypeChooseListener onGoodsTypeChooseListener;

    DrawerLayout drawerLayout;
    View showView;
    private LinearLayout llContainer;

    //选中数据 备份
    public String startPriceData;
    public String endPriceData;

    public Map<String, Boolean> brandDataMap = new HashMap<>();

    public Map<String, Map<String, Boolean>> paramsDataMap = new HashMap<>();

    public GoodsTypeChooseDrawer(Object activityOrFragment, DrawerLayout drawerLayout, View showView, OnGoodsTypeChooseListener onGoodsTypeChooseListener) {
        this.activityOrFragment = activityOrFragment;
        this.drawerLayout = drawerLayout;
        this.showView = showView;

        if (activityOrFragment instanceof Activity) {
            context = (Activity) activityOrFragment;
        } else {
            context = ((Fragment) activityOrFragment).getActivity();
        }
        inflater = LayoutInflater.from(context);

        this.onGoodsTypeChooseListener = onGoodsTypeChooseListener;

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        llContainer = showView.findViewById(R.id.drawerlayout_showview_container);


//        initServiceType();
        initPriceType();


        showView.findViewById(R.id.btn_reset).setOnClickListener(listener);
        showView.findViewById(R.id.btn_ok).setOnClickListener(listener);

        drawerLayout.addDrawerListener(this);
    }


    OnSingleClickListener listener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.btn_reset: {
                    edt_start_prise.setText(null);
                    edt_end_prise.setText(null);
                    if (brandChooseItemView != null)
                        brandChooseItemView.adapter.clearChoices();

                    for (ChooseItemView itemView : paramsItemViewList) {
                        itemView.adapter.clearChoices();
                    }
                    sendMsg();
                }
                break;
                case R.id.btn_ok: {
                    boolean b = sendMsg();
                    if (!b) return;
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                break;
            }
        }
    };


    boolean sendMsg() {

        FilterDatasResultMsg msg = new FilterDatasResultMsg();

        msg.startPrice = startPriceData = edt_start_prise.getText().toString();
        msg.endPrice = endPriceData = edt_end_prise.getText().toString();


        if (!StringUtils.isEmpty(msg.startPrice) && !StringUtils.isEmpty(msg.endPrice)) {
            if (!StringUtils.isNumber(msg.startPrice)) {
                Toast.makeText(context, "请输入正确的价格", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!StringUtils.isNumber(msg.endPrice)) {
                Toast.makeText(context, "请输入正确的价格", Toast.LENGTH_SHORT).show();
                return false;
            }
            try {
                int start = Integer.parseInt(msg.startPrice);
                int end = Integer.parseInt(msg.endPrice);

                if (start >= end) {
                    Toast.makeText(context, "开始价格不能大于结束价格", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(context, "请输入正确的价格", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        msg.brand = null;
        brandDataMap.clear();
        if (brandChooseItemView != null && !brandChooseItemView.adapter.choiceMap.isEmpty()) {

            StringBuilder sb = new StringBuilder();

            Iterator<Map.Entry<Integer, Object>> iterator = brandChooseItemView.adapter.choiceMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Object> kv = iterator.next();
                sb.append(",");
                sb.append(String.valueOf(kv.getValue()));

                brandDataMap.put(String.valueOf(kv.getValue()), false);
            }
            msg.brand = sb.substring(1);
        }

        msg.params = null;
        paramsDataMap.clear();
        if (!paramsItemViewList.isEmpty()) {

            JSONArray paramsArray = new JSONArray();
            for (ChooseItemView view : paramsItemViewList) {
                try {
                    if (view.adapter.choiceMap.isEmpty()) continue;


                    //[{"paramName": "属性名11","valueVoList": [{"valueName": "aaaa"},{"valueName": "属性值2"}]}]

                    Map<String, Boolean> map = new HashMap<>();
                    paramsDataMap.put(view.title, map);

                    JSONArray jsonArray = new JSONArray();

                    Iterator<Map.Entry<Integer, Object>> iterator = view.adapter.choiceMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        try {
                            Map.Entry<Integer, Object> kv = iterator.next();
                            String val = String.valueOf(kv.getValue());
//                                    将选中项备份
                            map.put(val, false);
//生成 参数json
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("valueName", val);
                            jsonArray.put(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("paramName", view.title);
                    jsonObject.put("valueVoList", jsonArray);

                    paramsArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            msg.params = paramsArray.toString();
        }

        onGoodsTypeChooseListener.onChoose(msg);
        return true;
    }

//    private ScrollGridView gvServiceType;
//    public GoodsTypeChooseDataAdapter serviceTypeAdapter;
//    void initServiceType() {
//        gvServiceType = showView.findViewById(R.id.gv_service_type);
//        serviceTypeAdapter = new GoodsTypeChooseDataAdapter(activityOrFragment, gvServiceType, GoodsTypeChooseViewHolder.class);
//        serviceTypeAdapter.setChoice_type(ListView.CHOICE_MODE_MULTIPLE);
//        gvServiceType.setAdapter(serviceTypeAdapter);
//        gvServiceType.setOnItemClickListener((parent, view1, position, id) -> {
//            serviceTypeAdapter.putChoicedNotify(position);
//        });
//    }

    //    CheckBox cb_price;
//    private ScrollGridView gvPriceType;
//    public GoodsTypeChooseDataAdapter priceTypeAdapter;

    EditText edt_start_prise;
    EditText edt_end_prise;

    void initPriceType() {
        //        cb_price = showView.findViewById(R.id.cb_price);
//        cb_price.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            priceTypeAdapter.spread = isChecked;
//            priceTypeAdapter.notifyDataSetChanged();
//        });
//        gvPriceType = showView.findViewById(R.id.gv_price_type);
//        priceTypeAdapter = new GoodsTypeChooseDataAdapter(activityOrFragment, gvPriceType, GoodsTypeChooseViewHolder.class);
//        priceTypeAdapter.setChoice_type(ListView.CHOICE_MODE_SINGLE);
//        gvPriceType.setAdapter(priceTypeAdapter);
//        gvPriceType.setOnItemClickListener((parent, view1, position, id) -> {
//            priceTypeAdapter.putChoicedNotify(position);
//        });

        edt_start_prise = showView.findViewById(R.id.edt_start_prise);
        edt_end_prise = showView.findViewById(R.id.edt_end_prise);

        edt_start_prise.addTextChangedListener(textWatcher);
        edt_end_prise.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString();
            int len = s.toString().length();
            if (len == 1 && text.equals("0")) {
                s.clear();
            }
        }
    };

    public void showDrawer() {
        if (!drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.openDrawer(Gravity.RIGHT);
        } else {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        }
    }

    ChooseItemView brandChooseItemView;

    List<ChooseItemView> paramsItemViewList = new ArrayList<>();

    public synchronized void setFilterDatas(List<SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean> params, List<SearchProductResponse.DataBeanX.GoodsInfoBean.BrandsBean> brands, boolean isClearData) {

        if (brandChooseItemView != null) {
            llContainer.removeView(brandChooseItemView.itemView);
            brandChooseItemView = null;
        }
        for (ChooseItemView view : paramsItemViewList) {
            llContainer.removeView(view.itemView);
        }
        paramsItemViewList.clear();

        if (isClearData) {
            startPriceData = null;
            endPriceData = null;
            edt_start_prise.setText(null);
            edt_end_prise.setText(null);


            brandDataMap.clear();
            paramsDataMap.clear();
            return;
        }

        if (brands != null && !brands.isEmpty()) {
            brandChooseItemView = addBrands(brands);
        }

        if (params != null && !params.isEmpty()) {
            for (SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean vo : params) {
                ChooseItemView itemView = addParams(vo);
                paramsItemViewList.add(itemView);
            }
        }

        putChoicedLast();
    }

    void putChoicedLast() {
//            选中上次数据
        edt_start_prise.setText(startPriceData);
        edt_end_prise.setText(endPriceData);

        if (brandChooseItemView != null && brandChooseItemView.adapter.getCount() > 0) {
            brandChooseItemView.adapter.clearChoices();
            for (int i = 0; i < brandChooseItemView.adapter.getCount(); i++) {
                String str = String.valueOf(brandChooseItemView.adapter.getItem(i));

                if (brandDataMap.get(str) == null) continue;

                brandChooseItemView.adapter.putChoiced(i);
            }
        }
        if (!paramsItemViewList.isEmpty()) {

            for (ChooseItemView view : paramsItemViewList) {

                view.adapter.clearChoices();

                if (view.adapter.getCount() <= 0) continue;

                Map<String, Boolean> map = paramsDataMap.get(view.title);
                if (map == null) continue;

                for (int i = 0; i < view.adapter.getCount(); i++) {

                    String str = String.valueOf(view.adapter.getItem(i));

                    if (map.get(str) == null) continue;

                    view.adapter.putChoiced(i);
                }
            }
        }
    }


    private ChooseItemView addBrands(List<SearchProductResponse.DataBeanX.GoodsInfoBean.BrandsBean> brands) {
        ChooseItemView itemView = new ChooseItemView();
        List<String> temps = new ArrayList<>();
        for (SearchProductResponse.DataBeanX.GoodsInfoBean.BrandsBean vo : brands) {
            temps.add(vo.getBrandName());
        }
        itemView.setDatas("品牌", temps);

        llContainer.addView(itemView.itemView);

        return itemView;
    }

    private ChooseItemView addParams(SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean params) {
        ChooseItemView itemView = new ChooseItemView();
        List<String> temps = new ArrayList<>();
        for (SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean.ValueVoListBean vo : params.getValueVoList()) {
            temps.add(vo.getValueName());
        }
        itemView.setDatas(params.getParamName(), temps);

        llContainer.addView(itemView.itemView);

        return itemView;
    }


    public boolean isOpen() {
        return drawerLayout.isDrawerOpen(Gravity.RIGHT);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        putChoicedLast();
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }


    public class ChooseItemView implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemClickListener {

        public View itemView;

        public TextView textView;
        public CheckBox checkBox;
        public ScrollGridView gridView;
        public GoodsTypeChooseDataAdapter adapter;

        public String title;

        public ChooseItemView() {
            itemView = inflater.inflate(R.layout.layout_goodstypechoosedrawer_item, null);

            textView = itemView.findViewById(R.id.tv_choose);
            checkBox = itemView.findViewById(R.id.cb_choose);
            checkBox.setOnCheckedChangeListener(this);

            gridView = itemView.findViewById(R.id.sgv_choose);
            adapter = new GoodsTypeChooseDataAdapter(activityOrFragment, gridView, GoodsTypeChooseViewHolder.class);
            adapter.setChoice_type(ListView.CHOICE_MODE_MULTIPLE);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);
        }

        public void setDatas(String title, List<String> list) {
            this.title = title;
            textView.setText(title);

            adapter.setDatas(list);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            adapter.spread = isChecked;
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            adapter.putChoicedNotify(position);
        }
    }

    public interface OnGoodsTypeChooseListener {
        void onChoose(FilterDatasResultMsg msg);
    }
}
