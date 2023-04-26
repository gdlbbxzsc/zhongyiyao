package com.pbph.shoppingmall.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.utils.Logger;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.model.message.PayResultMsg;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.pbph.shoppingmall.constant.Constant.Data.WX_APP_ID;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    IWXAPI api = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, WX_APP_ID, false);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent paramIntent) {
        api.handleIntent(paramIntent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.e("=============onResp, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            String respString = null;
//            if (resp.errCode == 0) {
//                respString = "支付成功";
//                RxBusF.getInstance().post(resp);
//            } else if (resp.errCode == -1) {
//                respString = "支付错误";
//            } else if (resp.errCode == -2) {
//                respString = "取消支付";
//            }
//            Toast.makeText(this, respString, Toast.LENGTH_SHORT).show();
            PayResultMsg msg = new PayResultMsg();
            msg.errCode = resp.errCode;
            RxBusF.getInstance().post(msg);
        }
        finish();
    }
}