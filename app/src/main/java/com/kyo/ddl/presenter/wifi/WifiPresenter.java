package com.kyo.ddl.presenter.wifi;

import com.kyo.ddl.model.wifi.WifiModel;
import com.kyo.ddl.presenter.base.KyoBasePresenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WifiPresenter<V,M> extends KyoBasePresenter {
    private final static int DEFAULT_COUNT=3;
    private static ExecutorService service= Executors.newFixedThreadPool(DEFAULT_COUNT);
    private void get(){
        ((WifiModel)getModel()).get();
        ((WifiModel)getModel()).login();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStart() {

    }
}
