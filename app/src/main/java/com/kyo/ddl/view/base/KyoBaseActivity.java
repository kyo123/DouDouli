package com.kyo.ddl.view.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.kyo.ddl.application.DDApplication;
import com.kyo.ddl.presenter.base.KyoBasePresenter;
import com.kyo.ddl.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class KyoBaseActivity extends FragmentActivity{
    public static final int VIEW_COMPLETE = 1;
    //显示断网
    public static final int VIEW_WIFIFAILUER = 2;
    //显示加载数据失败
    public static final int VIEW_LOADFAILURE = 3;
    //显示正在加载
    public static final int VIEW_LOADING = 4;
    //显示无数据
    public static final int VIEW_NO_DATA = 5;

    public List<KyoBasePresenter> mAllPresenters = new ArrayList<KyoBasePresenter>();
    /**
     * 获取layout的id，具体由子类实现
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 需要子类来实现，获取子类的IPresenter，一个activity有可能有多个IPresenter
     */
    protected abstract KyoBasePresenter[] getPresenters();

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
        KyoBasePresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (int i = 0; i < presenters.length; i++) {
                mAllPresenters.add(presenters[i]);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(getLayoutResId());
        isHome();
        addPresenters();
        onInitPresenters();
        initEvent();
        DDApplication.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (KyoBasePresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onResume();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (KyoBasePresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onStop();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (KyoBasePresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onPause();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (KyoBasePresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onStart();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (KyoBasePresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onDestroy();
            }
        }
        DDApplication.removeActivity(this);
    }

    //点击back的时候如果不是主页就直接finish掉,其他如果还有不同的可以重新super自定义
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(!isHome()){
            finish();
            LogUtil.e("不是主页就finish","不是主页就finish");
            return false;
        }
        else
            return super.onKeyDown(keyCode, event);
    }
}
