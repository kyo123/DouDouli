package com.kyo.ddl.presenter.test2;

import android.content.Context;

import com.kyo.ddl.model.test.TestModel;
import com.kyo.ddl.presenter.base.KyoBasePresenter;
import com.kyo.ddl.utils.LogUtil;
import com.kyo.ddl.utils.http.HttpListener;
import com.yolanda.nohttp.rest.Response;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

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
                    Created by kyo on 17-7-3.
 */
public class TestPresenter2<V> extends KyoBasePresenter{

    private V testView;
    private TestModel testModel=new TestModel();
    private final static int REQ_LOGIN=0;
    public TestPresenter2(V testView){
        this.testView=testView;
    }

    public void login(Context context){
        StringBuilder sb=new StringBuilder();
        sb.append("http://www.baidu.com");
        Map<String,String> map=new HashMap<String,String>();
        testModel.login(context,sb,map,REQ_LOGIN,new CustomHttpListener(this,testView));
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
        attachView(testView);
        attachModel(testModel);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e(getClass().getSimpleName(),"destorys");
    }

    public class CustomHttpListener<P,V> implements HttpListener{
        WeakReference<P> weakPresenter;
        WeakReference<V> weakView;
        CustomHttpListener(P presenter,V view){
            weakPresenter=new WeakReference<P>(presenter);
            weakView=new WeakReference<V>(view);
        }


        @Override
        public void onSucceed(int what, Response response) {
            LogUtil.e("tag",response.get()+"");

            ((TestView2)weakView.get()).showResult2(response);
        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            LogUtil.e(getClass().getSimpleName(),exception.getMessage());
        }
    }
}
