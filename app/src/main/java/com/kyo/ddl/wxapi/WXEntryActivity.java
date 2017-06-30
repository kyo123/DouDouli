package com.kyo.ddl.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.kyo.ddl.application.DDApplication;
import com.kyo.ddl.utils.LogUtil;
import com.kyo.ddl.utils.ToastUtils;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{

//    public final static String APP_ID = "wx70332280afbe1f2b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DDApplication.getWXApi(getApplicationContext()).handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        DDApplication.getWXApi(getApplicationContext()).handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp baseResp) {
        LogUtil.i("baseResp", "baseResp.getType" + baseResp.getType());
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK://同意
                ToastUtils.show(this, "授权成功");
                String code = ((SendAuth.Resp) baseResp).code;
                LogUtil.i("info", "用户授权成功 ，code" + code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                ToastUtils.show(this, "用户拒绝授权");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                ToastUtils.show(this, "取消登录");
                break;
        }
    }

}
