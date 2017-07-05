package com.kyo.ddl.view.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kyo.ddl.R;
import com.kyo.ddl.application.DDApplication;
import com.kyo.ddl.model.bean.TestBean;
import com.kyo.ddl.presenter.base.KyoBasePresenter;
import com.kyo.ddl.presenter.base.KyoBaseView;
import com.kyo.ddl.presenter.test.TestPresenter;
import com.kyo.ddl.presenter.test.TestView;
import com.kyo.ddl.presenter.test2.TestPresenter2;
import com.kyo.ddl.presenter.test2.TestView2;
import com.kyo.ddl.utils.LogUtil;
import com.kyo.ddl.utils.ToastUtils;
import com.kyo.ddl.view.base.KyoBaseActivity;

public class HomeActivity extends KyoBaseActivity implements TestView,TestView2,View.OnClickListener {
    TestPresenter testPresenter=new TestPresenter(this);
    TestPresenter2 testPresenter2=new TestPresenter2(this);
    private TextView tv;
    public final static String EX_TEST="test";
    private static final int MY_PERMISSION_REQUEST_CODE = 10000;
    private String[] permissions = {Manifest.permission.RECEIVE_BOOT_COMPLETED};
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected KyoBasePresenter[] getPresenters() {
        return new KyoBasePresenter[]{testPresenter,testPresenter2};
    }

    @Override
    protected boolean isHome() {
        LogUtil.e("我是主页","我是主页");
        return true;
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    protected void initEvent() {
        tv= (TextView) findViewById(R.id.tv_login);
        tv.setOnClickListener(this);
//        checkPermission();
    }

    @Override
    protected void setFullScreen() {

    }

    @Override
    public void showResult(Object result) {

    }

    @Override
    public void showResult2(Object response) {
//        startActivity(new Intent(getApplicationContext(),HomeActivity2.class));
        jump(HomeActivity.this,HomeActivity2.class,0,0);

    }

    @Override
    public void onClick(View v) {
//        testPresenter.login(HomeActivity.this);
//        testPresenter2.login(HomeActivity.this);
//        jump(HomeActivity.this,HomeActivity2.class,0,0);
//        Bundle bundle=new Bundle();
//        bundle.putSerializable(EX_TEST,new TestBean("a","b"));
//        jump2(HomeActivity.this,HomeActivity2.class,bundle,0,0);
        /**
         * 第 1 步: 检查是否有相应的权限
         */
//        boolean isAllGranted = checkPermissionAllGranted(
//                new String[] {
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                }
//        );
//        // 如果这3个权限全都拥有, 则直接执行备份代码
//        if (isAllGranted) {
//            doBackup();
//            return;
//        }
//
//        /**
//         * 第 2 步: 请求权限
//         */
//        // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
//        ActivityCompat.requestPermissions(
//                this,
//                new String[] {
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                },
//                MY_PERMISSION_REQUEST_CODE
//        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                showDialogTipUserRequestPermission();
            }else
                LogUtil.e("已经授权了","已经授权了");
        }

    }

    // 提示用户该请求权限的弹出框
    private void showDialogTipUserRequestPermission() {

        new AlertDialog.Builder(this)
                .setTitle("存储权限不可用")
                .setMessage("由于支付宝需要获取存储空间，为你存储个人信息；\n否则，您将无法正常使用支付宝")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRequestPermission();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }

    // 开始提交请求权限
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        DDApplication.finishAll();
        return super.onKeyDown(keyCode, event);
    }

    //6.0以上权限请求回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtil.e("permission response", requestCode + "-->" + permissions[0] + "-->" + grantResults[0]);
//        if (requestCode == 100) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                ToastUtils.show(this,"权限已经获得");
//            }
//        }
//        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
//            boolean isAllGranted = true;
//
//            // 判断是否所有的权限都已经授予了
//            for (int grant : grantResults) {
//                if (grant != PackageManager.PERMISSION_GRANTED) {
//                    isAllGranted = false;
//                    break;
//                }
//            }
//
//            if (isAllGranted) {
//                // 如果所有的权限都授予了, 则执行备份代码
//                doBackup();
//
//            } else {
//                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
//                openAppDetails();
//            }
//        }
        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
//                        openAppDetails();
                        goToAppSetting();
                    } else
                        finish();
                } else {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 第 4 步: 备份通讯录操作
     */
    private void doBackup() {
        // 本文主旨是讲解如果动态申请权限, 具体备份代码不再展示, 就假装备份一下
        ToastUtils.show(this,"正在备份通讯录...");
    }


    /**
     * 打开 APP 的详情设置
     */
    private void openAppDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("备份通讯录需要访问 “通讯录” 和 “外部存储器”，请到 “应用信息 -> 权限” 中授予！");
        builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.setData(Uri.parse("package:" + getPackageName()));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                startActivity(intent);
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 123);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    // 提示用户应该去应用设置界面手动开启权限
//                    showDialogTipUserGoToAppSettting();
                    openAppDetails();
                } else {
//                    if (dialog != null && dialog.isShowing()) {
//                        dialog.dismiss();
//                    }
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
