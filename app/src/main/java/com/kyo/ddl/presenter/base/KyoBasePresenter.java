package com.kyo.ddl.presenter.base;
/*
                        _ooOoo_
                       o8888888o
                       88" . "88
                       (| -_- |)
                       O\  =  /O
                    ____/`---'\____
                  .'  \\|     |//  `.
                 /  \\|||  :  |||//  \
                /  _||||| -:- |||||-  \
                |   | \\\  -  /// |   |
                | \_|  ''\---/''  |   |
                \  .-\__  `-`  ___/-. /
              ___`. .'  /--.--\  `. . __
           ."" '<  `.___\_<|>_/___.'  >'"".
          | | :  `- \`.;`\ _ /`;.`/ - ` : | |
          \  \ `-.   \_ __\ /__ _/   .-` /  /
     ======`-.____`-.___\_____/___.-`____.-'======
                        `=---='
     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
              佛祖保佑       永无BUG
     */

import com.kyo.ddl.model.base.KyoBaseModel;
import java.lang.ref.WeakReference;

public abstract class KyoBasePresenter<V extends KyoBaseView,M extends KyoBaseModel> {
    /*
    base presenter
     */
    private WeakReference<V> weak4View;
    private WeakReference<M> weak4Model;
    public KyoBasePresenter(){

    }

    public void attachModel(M model){
        if (weak4Model==null)
            weak4Model=new WeakReference<M>(model);
    }

    public void attachView(V view){
        if(weak4View==null)
            weak4View=new WeakReference<V>(view);
    }

    public void disattachModel(){
        M model=weak4Model.get();
        if(model!=null)
            weak4Model.clear();
    }
    public void disattachView(){
            V view=weak4View.get();
        if(view!=null)
            weak4View.clear();
    }

    protected M getModel(){
        return weak4Model.get();
    }
    protected V getView(){
        return  weak4View.get();
    }
    public abstract void onStop();

    public abstract void onResume();

    public void onDestroy(){
        disattachModel();
        disattachView();
    }

    public abstract void onPause();

    public abstract void onStart();

}
