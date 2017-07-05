package com.kyo.ddl.application;
/*


                                    ..
                                   NB@u
                               :@Z@B@@@
                               @B@BM0OBM
                          ,: .@BG0GE00M@@.
                         q@@B@B80E0GEG0OB@Bi
                       .OB@M@OGEGEGEGEG08O@B@Y, ,
                     ;O@BO0ZqEEZZGEGE8EZEZE8B@B@@@5
                   v@B@MZEZE8ZGEZZGE8EGZGEGEZEOB@B@@
                 UB@BOEZEGEZEGEGEGZZZGEGEGEG0GE0EZO@B.
               ,@B@ZZEGZGEGE8EGE8E8ZZZGE8EGEGZGEGEZZ@B7
              L@BGEZEG0GEG0ZZGEZZ8EGEZEGEZEZEGEZ0ZZZE@B5
              @BZ0ZEZ0GZO8O0ZE80GEZEZEZZGEGZOGG0ZEZ0GN@B:
             BBG0ZEZEMB@@@@@B@OOZGZGZO8BB@@@B@B@ZZEZEZ0@B
            @BBqGZZ0@BU:,,iLZB@B@B@B@B@B@F7:,,vB@OZEGE0M@B
           .BB0ZZGq8BS        你  强  哥         M@EZEGEEM@:
           .@MZEG0MB@,                          :B@EEEZ0@@.
            q@0EZ@B@O52ur:     . .   . .   ,ijFqu1B@ZZ0OBS
            .B@N@B. .:LSBB@MY . ....... 78@B@qYi:  @@0GB@
           2@ZB@B:         ,,. ....... .,,         .B@BM8@i
          MB: ,B@   iBBO@BZu. ..... ...  ;SB@BGBF.  vBL  7@j
         i@.   .Bi      JS:rv  ... . .  :Y.vSi .    U@    u@
         Y@     @S  .         .   .                 @O    :B:
         .@v    @B   . .   .   :i    ;.    . . . .  BU    M@
          7Bq   PB  . ... ...  B8.  :Bu ..... ...  .@:  :B@.
            B@BqB@   ... ..... ,7SZZY7   ... ..... ,BMP@@5
              ::jB.   ... .      .j:      ..... .  i@vi.
                 @,  ..... ..,7X52rLuSL:,. . ...   vB
                 BJ     ... v0Ui :X7.:LX5.. .      B@
                 7BN.          :1uiv5v           :M@,
                  .0@BMPv.                  :Y8M@BX
                      i7r@BMUr.        :7XB@Er7i
                        iB@B@B@B@@MO@@@@@B@BZ
                       UB@PZBBq@Bu  .@B0Z@OZB@,
                      2B@XXk0B@B@PJvuB@B@8Xk0@@.
                      B@PNXPkN0@O2PZ58BZqPq0q0B@
                     B@qqXMOqSqB07Lv7F@qXq0N0PqB@
                    v@B@O8B@kPE@5vLj7uBOX0X0E@B@B:
                    @J:u0B@8XS8BjrLvv7@OkkEB@PYr@i
                   ZB,   MBMNEM@1U1FuUBB0GO@r   Bq                           
 */


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.kyo.ddl.utils.LogUtil;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yolanda.nohttp.NoHttp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DDApplication extends Application {
    private static IWXAPI api;
    public static DDApplication instance;
    private static List<Activity> activities = new ArrayList<Activity>();
    private static Set<Activity> activitySet=new HashSet<Activity>();
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
    }

    public static synchronized IWXAPI getWXApi(Context context) {
        //注册到微信
        String APP_ID = "wx44448941879976964364";//随便写的，后期填充正式的id
        synchronized (IWXAPI.class){
            if(api==null)
            {
                api = WXAPIFactory.createWXAPI(context, APP_ID, true);
                //将应用的appId注册到微信
                api.registerApp(APP_ID);
            }
        }
        return api;
    }

    public static boolean containActivity(Activity activity) {
        boolean isContain=false;
        for (Activity a:activitySet) {
               if(a.getClass().getSimpleName().equals(activity.getClass().getSimpleName())){
                   isContain=true;
                   break;
               }else
                   continue;

        }
        LogUtil.e("是否包含", "是否包含-->"+isContain);
        return isContain;
    }

    public static synchronized void addActivity(Activity activity) {
        if(!containActivity(activity)){
            activitySet.add(activity);
            LogUtil.e("add activity", "添加-->"+activity.getClass().getSimpleName());
        }
    }

    public static synchronized void removeActivity(Activity activity) {
        if(containActivity(activity))
            activitySet.remove(activity);
        LogUtil.e("remove activity", "删除-->"+activity.getClass().getSimpleName());
    }

    public static synchronized void finishAll() {
        LogUtil.e("finish all", "还有谁-->"+activitySet.size());
        for (Activity activity : activitySet) {
            if (containActivity(activity) && activity!=null) {
                activity.finish();
            }
        }
        LogUtil.e("finish all", "还有谁-->"+activitySet.size());
    }



}
