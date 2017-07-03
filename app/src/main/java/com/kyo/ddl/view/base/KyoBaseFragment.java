package com.kyo.ddl.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyo.ddl.presenter.base.KyoBasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyo on 17-7-3.
 */

public abstract class KyoBaseFragment extends Fragment implements View.OnTouchListener{
    public List<KyoBasePresenter> mAllPresenters = new ArrayList<KyoBasePresenter>();

    /**
     * 需要子类来实现，获取子类的Presenter，一个fragment有可能有多个Presenter
     */
    protected abstract KyoBasePresenter[] getPresenters();

    protected abstract View getKyoView();

    private void addPresenters() {
        KyoBasePresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (int i = 0; i < presenters.length; i++) {
                mAllPresenters.add(presenters[i]);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPresenters();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getKyoView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁所有的presenter
        for (KyoBasePresenter presenter : mAllPresenters) {
            if (presenter != null) {
                presenter.onDestroy();
            }
        }
    }
}
