package com.pbph.shoppingmall.module.firmorder.invoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetBillListResponse;
import com.pbph.shoppingmall.model.response.GetDefaultBillResponse;
import com.pbph.shoppingmall.module.bill.list.BillMngActivity;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;

import java.util.ArrayList;
import java.util.List;

import static com.pbph.shoppingmall.module.bill.list.BillMngActivity.RESULT_CODE_BACK;

public class InvoiceActivity extends BaseActivity<Presenter> implements Contract.View, View.OnClickListener, InvoiceTypeAdapter.OnTypeItemClickListener, InvoiceContentAdapter.OnContentItemClickListener {

    //标题栏
    CommonTitlebar commonTitlebar;
    RecyclerView rvInvoiceType;
    private TextView tvInvoiceContent, tvInvoiceCommit, tvPersonalBillTitle, tvCompanyBillTitle, tvCompanyBillParagraph, tvNoDefaultBill;
    private ConstraintLayout cyInvoicePersonal, cyInvoiceCompany, cyBillMng;
    private ImageView ivCompanyMr, ivPersonalMr;
    private int billType = 0;
    private int serviceId = -1;
    private List<InvoiceTypeBean> invoiceTypeBeans;
    private List<InvoiceContentBean> invoiceContentBeans;
    private InvoiceTypeAdapter invoiceTypeAdapter;
    private String invoiceContent = "不开发票";
    private String customerContent = "";
    private int billOpen = 0;//0：不开，1：开

    //    private InvoiceContentAdapter invoiceContentAdapter;
    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_invoice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(this, "设置发票信息", false);
        rvInvoiceType = findViewById(R.id.rv_invoice_type);
//        rvInvoiceContent = findViewById(R.id.rv_invoice_content);
        cyInvoicePersonal = findViewById(R.id.ly_invoice_personal);
        cyInvoiceCompany = findViewById(R.id.ly_invoice_company);
        cyBillMng = findViewById(R.id.cy_bill_mng);
        tvInvoiceContent = findViewById(R.id.tv_invoice_content);
        tvInvoiceCommit = findViewById(R.id.tv_invoice_commit);
        tvPersonalBillTitle = findViewById(R.id.tv_personal_bill_title);
        tvCompanyBillTitle = findViewById(R.id.tv_company_bill_title);
        tvCompanyBillParagraph = findViewById(R.id.tv_company_bill_paragraph);
        tvNoDefaultBill = findViewById(R.id.tv_no_default_bill);
        ivCompanyMr = findViewById(R.id.iv_company_mr);
        ivPersonalMr = findViewById(R.id.iv_personal_mr);
        tvInvoiceCommit.setOnClickListener(this);
        cyBillMng.setOnClickListener(this);
        invoiceTypeBeans = new ArrayList<>();
        invoiceTypeBeans.add(new InvoiceTypeBean("不开发票", true, 0));
        invoiceTypeBeans.add(new InvoiceTypeBean("普通发票", false, 1));
//        invoiceContentBeans = new ArrayList<InvoiceContentBean>();
//        for (int i = 0; i < 5 ;i++){
//            invoiceContentBeans.add(new InvoiceContentBean("发票内容" + i,false));
//        }
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvInvoiceType.setLayoutManager(layoutManager);
        layoutManager = new GridLayoutManager(this, 3);
//        rvInvoiceContent.setLayoutManager(layoutManager);
        invoiceTypeAdapter = new InvoiceTypeAdapter(this, invoiceTypeBeans);
//        invoiceContentAdapter = new InvoiceContentAdapter(this,invoiceContentBeans);
//        invoiceContentAdapter.setOnContentItemClickListener(this);
        invoiceTypeAdapter.setOnTypeItemClickListener(this);
        rvInvoiceType.setAdapter(invoiceTypeAdapter);
//        rvInvoiceContent.setAdapter(invoiceContentAdapter);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_invoice_commit: {
                if (billOpen == 0) {
                    Intent intent = new Intent();
                    intent.putExtra("content", invoiceContent);
                    intent.putExtra("customerContent", customerContent);
                    intent.putExtra("billId", serviceId);
                    intent.putExtra("billOpen", billOpen);
                    setResult(222, intent);
                    InvoiceActivity.this.finish();
                } else {
                    if (serviceId == -1 || serviceId == 0) {
                        Toast.makeText(context, "请选择发票", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("content", invoiceContent);
                    intent.putExtra("customerContent", customerContent);
                    intent.putExtra("billId", serviceId);
                    intent.putExtra("billOpen", billOpen);
                    setResult(222, intent);
                    InvoiceActivity.this.finish();
                }

            }
            break;
            case R.id.cy_bill_mng: {
                Intent intent = new Intent(InvoiceActivity.this, BillMngActivity.class);
                intent.putExtra("need_result", true);
                intent.putExtra("service_id", serviceId);
                startActivityForResult(intent, 333);
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            GetBillListResponse.DataBean vo = null;
            if (resultCode == RESULT_OK || resultCode == RESULT_CODE_BACK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    vo = (GetBillListResponse.DataBean) bundle.get("result");
                } else {
                    tvNoDefaultBill.setVisibility(View.VISIBLE);
                    cyInvoicePersonal.setVisibility(View.GONE);
                    cyInvoiceCompany.setVisibility(View.GONE);
                }
            }
            if (vo != null) {
                serviceId = vo.getPpid();
                billType = Integer.parseInt(vo.getBillType());
                if (billType == 0) {
                    cyInvoicePersonal.setVisibility(View.VISIBLE);
                    tvNoDefaultBill.setVisibility(View.GONE);
                    cyInvoiceCompany.setVisibility(View.GONE);
                    tvPersonalBillTitle.setText(vo.getBillTitle());
                    if (vo.getDefaultVal() == 0) {
                        ivPersonalMr.setVisibility(View.GONE);
                    } else if (vo.getDefaultVal() == 1) {
                        ivPersonalMr.setVisibility(View.VISIBLE);
                    }
                } else if (billType == 1) {
                    cyInvoicePersonal.setVisibility(View.GONE);
                    cyInvoiceCompany.setVisibility(View.VISIBLE);
                    tvCompanyBillTitle.setText(vo.getBillTitle());
                    tvCompanyBillParagraph.setText(vo.getBillParagraph());
                    if (vo.getDefaultVal() == 0) {
                        ivCompanyMr.setVisibility(View.GONE);
                    } else if (vo.getDefaultVal() == 1) {
                        ivCompanyMr.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    @Override
    public void onTypeItemClick(View view, int position) {
        for (InvoiceTypeBean bean : invoiceTypeBeans) {
            bean.setChecked(false);
        }
        invoiceTypeBeans.get(position).setChecked(true);
        if (invoiceTypeAdapter != null) {
            invoiceTypeAdapter.notifyDataSetChanged();
        }
        invoiceContent = invoiceTypeBeans.get(position).getInvoiceTypeName();
        if (position == 1) {
            tvInvoiceContent.setBackgroundResource(R.drawable.invoice_type_item_sel_bg);
            tvInvoiceContent.setTextColor(context.getResources().getColor(R.color.white));
            invoiceContent += " " + tvInvoiceContent.getText().toString().trim();
            customerContent = " " + tvInvoiceContent.getText().toString().trim();
            billOpen = 1;
        } else if (position == 0) {
            billOpen = 0;
            tvInvoiceContent.setBackgroundResource(R.drawable.invoice_type_item_not_sel_bg);
            tvInvoiceContent.setTextColor(context.getResources().getColor(R.color.black_gray));
        }
    }

    @Override
    public void onContentItemClick(View view, int position) {
        for (InvoiceContentBean bean : invoiceContentBeans) {
            bean.setChecked(false);
        }
        invoiceContentBeans.get(position).setChecked(true);
//        if (invoiceContentAdapter != null){
//            invoiceContentAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void setDefaultBill(GetDefaultBillResponse defaultBill) {
        billType = Integer.parseInt(defaultBill.getData().getBillType());
        serviceId = defaultBill.getData().getPpid();
        int defaultVal = defaultBill.getData().getDefaultVal();
        if (defaultVal == 0) {
            tvNoDefaultBill.setVisibility(View.VISIBLE);
            cyInvoicePersonal.setVisibility(View.GONE);
            cyInvoiceCompany.setVisibility(View.GONE);
        } else if (defaultVal == 1) {
            tvNoDefaultBill.setVisibility(View.GONE);
            if (billType == 0) {
                cyInvoicePersonal.setVisibility(View.VISIBLE);
                cyInvoiceCompany.setVisibility(View.GONE);
                tvPersonalBillTitle.setText(defaultBill.getData().getBillTitle());
            } else if (billType == 1) {
                cyInvoicePersonal.setVisibility(View.GONE);
                cyInvoiceCompany.setVisibility(View.VISIBLE);
                tvCompanyBillTitle.setText(defaultBill.getData().getBillTitle());
                tvCompanyBillParagraph.setText(defaultBill.getData().getBillParagraph());
            }
        }
    }
}
