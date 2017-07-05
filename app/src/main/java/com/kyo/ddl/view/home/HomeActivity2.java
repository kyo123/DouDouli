package com.kyo.ddl.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kyo.ddl.R;
import com.kyo.ddl.model.bean.TestBean;
import com.kyo.ddl.presenter.base.KyoBasePresenter;
import com.kyo.ddl.presenter.test.TestPresenter;
import com.kyo.ddl.presenter.test.TestView;
import com.kyo.ddl.presenter.test2.TestPresenter2;
import com.kyo.ddl.presenter.test2.TestView2;
import com.kyo.ddl.utils.LogUtil;
import com.kyo.ddl.view.base.KyoBaseActivity;

public class HomeActivity2 extends KyoBaseActivity implements TestView, TestView2, View.OnClickListener {
    TestPresenter testPresenter = new TestPresenter(this);
    TestPresenter2 testPresenter2 = new TestPresenter2(this);
    private TextView tv;
    public final static String EX_TEST = "test";
    private Bundle b;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected KyoBasePresenter[] getPresenters() {
        return new KyoBasePresenter[]{testPresenter, testPresenter2};
    }

    @Override
    protected boolean isHome() {
        return false;
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    protected void initEvent() {
        tv = (TextView) findViewById(R.id.tv_login);
        tv.setText("我是act2");
        tv.setOnClickListener(this);
        b = getIntent().getExtras();
        LogUtil.e("ccc", b.toString());

    }

    @Override
    protected void setFullScreen() {

    }

    @Override
    public void showResult(Object result) {

    }

    @Override
    public void showResult2(Object response) {

//        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//        jump(HomeActivity2.this,HomeActivity.class,0,0);
    }

    @Override
    public void onClick(View v) {
//        testPresenter.login(HomeActivity2.this);
//        testPresenter2.login(HomeActivity2.this);
        jump(HomeActivity2.this, HomeActivity.class, 0, 0);
//        Bundle bundle=new Bundle();
//        bundle.putSerializable(EX_TEST,new TestBean("a","b"));
//        jump2(HomeActivity2.this,HomeActivity.class,bundle,0,0);
    }


}
