package com.pbph.shoppingmall.module.addressmng.submit;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.request.SaveAddressRequest;
import com.pbph.shoppingmall.model.request.UpdateAddressRequest;
import com.pbph.shoppingmall.model.response.GetAddressListResponse;
import com.pbph.shoppingmall.model.response.GetAddressResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.model.response.SaveAddressResponse;
import com.pbph.shoppingmall.model.response.SelectSysAddressListResponse;
import com.pbph.shoppingmall.utils.AMUtils;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AddOrEditAddressActivity extends BaseActivity<Presenter> implements Contract.View {

    public static final int RESPONSE_CODE_ADD = 0x103;
    public static final int RESPONSE_CODE_EDIT = 0x104;

    //标题栏
    CommonTitlebar commonTitlebar;

    private LinearLayout layoutRoot;

    EditText edt_name;
    EditText edt_call;

    private LinearLayout llArea;
    private TextView tvArea;

    EditText edt_address;

    CheckBox cb_all;

    int address_id;

    OptionsPickerView optionsPickerView;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_add_or_edit_address;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        address_id = intent.getIntExtra("address_id", -1);

        initOptionsPickerView();
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, address_id == -1 ? "新建收货地址" : "编辑收货地址", false);

        layoutRoot = findViewById(R.id.layout_root);
        llArea = findViewById(R.id.ll_area);
        llArea.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

                if (provinceBeans==null||provinceBeans.size()==0){
                    Toast.makeText(AddOrEditAddressActivity.this,"正在获取数据",Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                optionsPickerView.show();
            }
        });
        tvArea = findViewById(R.id.tv_area);

        edt_name = findViewById(R.id.edt_name);
        edt_call = findViewById(R.id.edt_call);
        edt_address = findViewById(R.id.edt_address);
        cb_all = findViewById(R.id.cb_all);

        findViewById(R.id.button).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {

                if (address_id == -1) {//添加操作

                    SaveAddressRequest<SaveAddressResponse> vo = new SaveAddressRequest<>();

                    String name = validateName();
                    if (name == null) return;
                    String call = validateCall();
                    if (call == null) return;
                    String area = validateArea();
                    if (area == null) return;
                    String add = validateAddress();
                    if (add == null) return;

                    vo.addressName = name;
                    vo.addressMoblie = call;

                    vo.addressProvince = provAdd;
                    vo.addressCity = cityAdd;
                    vo.addressCounty = contAdd;

                    vo.addressProvinceStr = provAddStr;
                    vo.addressCityStr = cityAddStr;
                    vo.addressCountyStr = contAddStr;

                    vo.addressDetail = add;

                    vo.defaultVal = cb_all.isChecked() ? 1 : 0;

                    presenter.submitHttpAddress(vo);
                } else {
                    UpdateAddressRequest<ResultResponse> vo = new UpdateAddressRequest<>();

                    String name = validateName();
                    if (name == null) return;
                    String call = validateCall();
                    if (call == null) return;
                    String area = validateArea();
                    if (area == null) return;
                    String add = validateAddress();
                    if (add == null) return;

                    vo.addressName = name;
                    vo.addressMoblie = call;

                    vo.addressProvince = provAdd;
                    vo.addressCity = cityAdd;
                    vo.addressCounty = contAdd;

                    vo.addressProvinceStr = provAddStr;
                    vo.addressCityStr = cityAddStr;
                    vo.addressCountyStr = contAddStr;

                    vo.addressDetail = add;

                    vo.defaultVal = cb_all.isChecked() ? 1 : 0;

                    vo.id = address_id;
                    presenter.updateHttpAddress(vo);
                }
            }
        });
    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
////            setResultBack();
//            return false;
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getId() {
        return address_id;
    }

    @Override
    public void onSucc(GetAddressListResponse.DataBean vo) {
        Intent intent = new Intent();
        intent.putExtra("result", vo);

        setResult(address_id == -1 ? RESPONSE_CODE_ADD : RESPONSE_CODE_EDIT, intent);

        finish();
    }

    @Override
    public void onGetHttpAddressById(GetAddressResponse.DataBean response) {
        edt_name.setText(response.getAddressName());
        edt_call.setText(response.getAddressMoblie());

        tvArea.setText(response.getProvinceName());
        tvArea.append(",");
        tvArea.append(response.getCityName());
        tvArea.append(",");
        tvArea.append(response.getDistrictName());

        provAddStr = response.getProvinceName();
        cityAddStr = response.getCityName();
        contAddStr = response.getDistrictName();

        provAdd = response.getAddressProvince();
        cityAdd = response.getAddressCity();
        contAdd = response.getAddressCounty();

        llArea.setTag(tvArea.getText().toString());

        edt_address.setText(response.getAddressDetail());
        cb_all.setChecked(response.getDefaultVal() == 1);

        setSelectOptions();
    }

    @Override
    public void setSysAddressList(List<SelectSysAddressListResponse.DataBean.ProvinceListBean> litst) {
        provinceBeans = litst;

        List<String> options1Items = new ArrayList<>(provinceBeans.size());
        List<List<String>> options2Items = new ArrayList<>(provinceBeans.size());
        List<List<List<String>>> options3Items = new ArrayList<>(provinceBeans.size());

        for (SelectSysAddressListResponse.DataBean.ProvinceListBean pvo : provinceBeans) {
            //
            List<SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean> cityList = pvo.getCityList();

            List<String> cityStrs = new ArrayList<>(cityList.size());
            List<List<String>> areaLists = new ArrayList<>(cityList.size());

            for (SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean cvo : cityList) {
                cityStrs.add(cvo.getCityName());

                List<SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean.DistrictListBean> temps = cvo.getDistrictList();
                if (temps == null) {
                    areaLists.add(new ArrayList<>());
                } else {
                    List<String> dlist = new ArrayList<>();
                    for (SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean.DistrictListBean dvo : temps) {
                        dlist.add(dvo.getDistrictName());
                    }
                    areaLists.add(dlist);
                }

            }
            options1Items.add(pvo.getProvinceName());
            options2Items.add(cityStrs);
            options3Items.add(areaLists);
        }

        optionsPickerView.setPicker(options1Items, options2Items, options3Items);

        setSelectOptions();
    }


//    void setDatas4OptionsPickerView() {
//        String JsonData = AMUtils.readFileFromAsset2String(this, "province.json");
//        provinceBeans = new Gson().fromJson(JsonData, new TypeToken<List<ProvinceBean>>() {
//        }.getType());
//
//        List<String> options1Items = new ArrayList<>(provinceBeans.size());
//        List<List<String>> options2Items = new ArrayList<>(provinceBeans.size());
//        List<List<List<String>>> options3Items = new ArrayList<>(provinceBeans.size());
//
//        for (ProvinceBean pvo : provinceBeans) {
//            //
//            List<CityBean> cityList = pvo.getCity();
//
//            List<String> cityStrs = new ArrayList<>(cityList.size());
//            List<List<String>> areaLists = new ArrayList<>(cityList.size());
//
//            for (CityBean cvo : cityList) {
//                cityStrs.add(cvo.getName());
//
//                List<String> temps = cvo.getArea();
//                if (temps == null)
//                    areaLists.add(new ArrayList<>());
//                else
//                    areaLists.add(cvo.getArea());
//
//            }
//            options1Items.add(pvo.getName());
//            options2Items.add(cityStrs);
//            options3Items.add(areaLists);
//        }
//
//        optionsPickerView.setPicker(options1Items, options2Items, options3Items);
//
//        setSelectOptions();
//    }

    //    这个方法在两个地方 调用只有两个 return 判断条件都成立了 才执行
    private void setSelectOptions() {
        if (provinceBeans == null) return;
        if (provAdd == -1) return;
        int x = 0, y = 0, z = 0;
        for (int i = 0, ci = provinceBeans.size(); i < ci; i++) {
            SelectSysAddressListResponse.DataBean.ProvinceListBean pvo = provinceBeans.get(i);
            if (pvo.getPpid() != provAdd) continue;

            x = i;
            List<SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean> clist = pvo.getCityList();
            for (int j = 0, cj = clist.size(); j < cj; j++) {
                SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean cvo = clist.get(j);
                if (cvo.getPpid() != cityAdd) continue;

                y = j;
                List<SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean.DistrictListBean> alist = cvo.getDistrictList();
                for (int k = 0, ck = alist.size(); k < ck; k++) {
                    SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean.DistrictListBean avo = alist.get(k);
                    if (avo.getPpid() != contAdd) continue;

                    z = k;
                    break;
                }
                break;
            }
            break;
        }
        optionsPickerView.setSelectOptions(x, y, z);
    }

    int provAdd = -1;
    int cityAdd = -1;
    int contAdd = -1;
    String provAddStr;
    String cityAddStr;
    String contAddStr;

    List<SelectSysAddressListResponse.DataBean.ProvinceListBean> provinceBeans;

    private void initOptionsPickerView() {// 弹出选择器

        optionsPickerView = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            //设置默认文字
            tvArea.setText("选择所在地区");
            llArea.setTag(null);

            provAdd = -1;
            cityAdd = -1;
            contAdd = -1;
            provAddStr = null;
            cityAddStr = null;
            contAddStr = null;

            //设置省
            if (provinceBeans == null || provinceBeans.size() <= 0) return;
            if (options1 >= provinceBeans.size()) return;
            SelectSysAddressListResponse.DataBean.ProvinceListBean pvo = provinceBeans.get(options1);
            if (pvo == null) return;
            tvArea.setText(pvo.getProvinceName());
            llArea.setTag(tvArea.getText().toString());
            provAdd = pvo.getPpid();
            provAddStr = pvo.getProvinceName();

            //设置市
            List<SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean> cityList = pvo.getCityList();
            if (cityList == null || cityList.size() <= 0) return;
            if (options2 >= cityList.size()) return;
            SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean cvo = cityList.get(options2);
            if (cvo == null) return;
            tvArea.append(",");
            tvArea.append(cvo.getCityName());
            llArea.setTag(tvArea.getText().toString());
            cityAdd = cvo.getPpid();
            cityAddStr = cvo.getCityName();

            List<SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean.DistrictListBean> areaList = cvo.getDistrictList();
            if (areaList == null || areaList.size() <= 0) return;
            if (options3 >= areaList.size()) return;
            SelectSysAddressListResponse.DataBean.ProvinceListBean.CityListBean.DistrictListBean avo = areaList.get(options3);
            if (avo == null) return;
            tvArea.append(",");
            tvArea.append(avo.getDistrictName());
            llArea.setTag(tvArea.getText().toString());
            contAdd = avo.getPpid();
            contAddStr = avo.getDistrictName();
        })
                .setTitleText("城市选择")
                .build();
    }

    String validateName() {
        String str = edt_name.getText().toString();
        if (StringUtils.isEmpty(str)) {
            toastShort("请输入收货人姓名");
            return null;
        }
        return str;
    }

    String validateCall() {
        String str = edt_call.getText().toString();
        if (StringUtils.isEmpty(str)) {
            toastShort("请输入收货人电话");
            return null;
        }

        if (!AMUtils.isMobile(str)) {
            toastShort("请输入正确手机号码");
            return null;
        }
        return str;
    }

    String validateArea() {
        String str = (String) llArea.getTag();
        if (StringUtils.isEmpty(str)) {
            toastShort("请选择地址");
            return null;
        }
        return str;
    }

    String validateAddress() {

        String str = edt_address.getText().toString();
        if (StringUtils.isEmpty(str)) {
            toastShort("请输入收货地址");
            return null;
        }
        return str;
    }
}
