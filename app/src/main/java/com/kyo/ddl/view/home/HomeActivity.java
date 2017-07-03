package com.kyo.ddl.view.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.kyo.ddl.R;
import com.kyo.ddl.application.DDApplication;
import com.kyo.ddl.presenter.base.KyoBasePresenter;
import com.kyo.ddl.presenter.base.KyoBaseView;
import com.kyo.ddl.presenter.test.TestPresenter;
import com.kyo.ddl.presenter.test.TestView;
import com.kyo.ddl.presenter.test2.TestPresenter2;
import com.kyo.ddl.presenter.test2.TestView2;
import com.kyo.ddl.utils.LogUtil;
import com.kyo.ddl.view.base.KyoBaseActivity;

public class HomeActivity extends KyoBaseActivity implements TestView,TestView2,View.OnClickListener {
    TestPresenter testPresenter=new TestPresenter(this);
    TestPresenter2 testPresenter2=new TestPresenter2(this);
    private TextView tv;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected KyoBasePresenter[] getPresenters() {
        return new KyoBasePresenter[]{testPresenter,testPresenter2};
    }

    @Override
    protected boolean isHome() {
        LogUtil.e("我是主页","我是主页");
        return true;
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    protected void initEvent() {
        tv= (TextView) findViewById(R.id.tv_login);
        tv.setOnClickListener(this);
    }

    @Override
    protected void setFullScreen() {

    }

    @Override
    public void showResult(Object result) {

    }

    @Override
    public void showResult2(Object response) {
       startActivity(new Intent(getApplicationContext(),HomeActivity2.class));
    }

    @Override
    public void onClick(View v) {
       testPresenter.login(HomeActivity.this);
        testPresenter2.login(HomeActivity.this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        DDApplication.finishAll();
        return super.onKeyDown(keyCode, event);
    }
}
