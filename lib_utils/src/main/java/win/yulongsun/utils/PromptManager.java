package win.yulongsun.utils.utils;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.Toast;


/**
 * 提示管理工具
 */
public class PromptManager {

    private static ProgressDialog mProgressDialog;

    /**
     * 显示对话框
     *
     * @param context
     * @param message
     */
    public static void showDialog(Context context, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);

        }
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    /**
     * 关闭对话框
     */
    public static void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    /**
     * 当判断当前手机没有网络时使用
     *
     * @param context
     */
    public static void showNoNetWork(final Context context) {
        Builder builder = new Builder(context);
        builder.setTitle("当前无网络")
                .setPositiveButton("设置", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到系统的网络设置界面
                        Intent intent = new Intent("/");
                        ComponentName cm = new ComponentName(
                                "com.android.settings",
                                "com.android.settings.WirelessSettings");
                        intent.setComponent(cm);
                        intent.setAction("android.intent.action.VIEW");
                        if (android.os.Build.VERSION.SDK_INT > 10) {
                            context.startActivity(new Intent(
                                    android.provider.Settings.ACTION_SETTINGS));
                        } else {
                            context.startActivity(new Intent(
                                    android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                        }

                    }
                }).setNegativeButton("知道了", null).show();
    }

    /**
     * 退出系统
     *
     * @param context
     */
    public static void showExitSystem(Context context) {
        Builder builder = new Builder(context);
        builder.setMessage("是否退出应用")
                .setPositiveButton("确定", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process
                                .myPid());

                    }
                }).setNegativeButton("取消", null)//
                .show();

    }

    /**
     * 显示土司
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


}
