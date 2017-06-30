package com.kyo.ddl.model.wifi;

import android.content.Context;

import com.kyo.ddl.annotation.KyoAnnotation;
import com.kyo.ddl.model.base.KyoBaseModel;

import org.json.JSONObject;
import java.net.MalformedURLException;
import java.util.Map;

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
              power by 你强哥!!!
     */
public class WifiModel extends KyoBaseModel {

    @KyoAnnotation("http://www.baidu.com")
    private String url;
    public String get(){
        return "123";
    }

    public void login(){

    }

}
