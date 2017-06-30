package com.kyo.ddl;

import com.kyo.ddl.presenter.base.KyoBasePresenter;
import com.kyo.ddl.view.base.KyoBaseActivity;

public class HomeActivity extends KyoBaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected KyoBasePresenter[] getPresenters() {
        return new KyoBasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void setFullScreen() {

    }

}
