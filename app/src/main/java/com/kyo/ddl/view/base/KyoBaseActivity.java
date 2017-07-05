package com.kyo.ddl.view.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Window;

import com.kyo.ddl.application.DDApplication;
import com.kyo.ddl.presenter.base.KyoBasePresenter;
import com.kyo.ddl.utils.LogUtil;
import com.kyo.ddl.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class KyoBaseActivity<P extends KyoBasePresenter> extends FragmentActivity {
    public static final int VIEW_COMPLETE = 1;
    //显示断网
    public static final int VIEW_WIFIFAILUER = 2;
    //显示加载数据失败
    public static final int VIEW_LOADFAILURE = 3;
    //显示正在加载
    public static final int VIEW_LOADING = 4;
    //显示无数据
    public static final int VIEW_NO_DATA = 5;

    public List<P> mAllPresenters = new ArrayList<P>();

    /**
     * 获取layout的id，具体由子类实现
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 需要子类来实现，获取子类的IPresenter，一个activity有可能有多个IPresenter
     */
    protected abstract P[] getPresenters();

    protected abstract boolean isHome();

    /**
     * 初始化presenters
     */
    protected abstract void onInitPresenters();

    /**
     * 事件监听
     */
    protected abstract void initEvent();

    /*
    设置全屏
     */
    protected abstract void setFullScreen();

    private void addPresenters() {
        P[] presenters = getPresenters();
        if (presenters != null) {
            for (int i = 0; i < presenters.length; i++) {
                mAllPresenters.add(presenters[i]);
            }
        }
    }

    private void seNoTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seNoTitle();
        setFullScreen();
        setContentView(getLayoutResId());
        isHome();
        addPresenters();
        onInitPresenters();
        initEvent();
//        if (!DDApplication.containActivity(this)) {
//            LogUtil.e("启动", getClass().toString());
//        }
        DDApplication.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (P presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onResume();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (P presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onStop();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (P presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onPause();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (P presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onStart();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (P presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onDestroy();
            }
        }
        DDApplication.removeActivity(this);
    }

    //点击back的时候如果不是主页就直接finish掉,其他如果还有不同的可以重新super自定义
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isHome()) {
            finish();
            LogUtil.e("不是主页就finish", "不是主页就finish");
            return false;
        } else
            return super.onKeyDown(keyCode, event);
    }

    //无参数跳转
    protected void jump(Activity currentAct, Class targetAct, int transaction_start, int transaction_end) {
        startActivity(new Intent(currentAct, targetAct));
//        finish();
        if (transaction_start != 0 && transaction_end != 0)
            overridePendingTransition(transaction_start, transaction_end);//activity切换的动画效果

    }

    //有参数跳转
    protected void jump2(Activity currentAct, Class targetAct, Bundle bundle, int transaction_start, int transaction_end) {
        startActivity(new Intent(currentAct, targetAct).putExtras(bundle));
        if (transaction_start != 0 && transaction_end != 0)
            overridePendingTransition(transaction_start, transaction_end);//activity切换的动画效果
    }

    /**
     * 6.0 权限申请
     */
    protected void checkPermission() {
        if (checkCallingOrSelfPermission(Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.show(this,"没有权限正在申请");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 100);
        }else
            ToastUtils.show(this,"已经有权限了");
    }



}
