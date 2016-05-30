/*
 *  Copyright (c) 2015 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.yuntongxun.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package win.yulongsun.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import win.yulongsun.utils.LogUtils;


public class ToastUtils {

    public static final String ERROR_PWD    = "密码长度为6-16位！";
    public static final String ERROR_NAME   = "用户名长度不正确！";
    public static final String ERROR_EMAIL  = "邮箱格式不正确！";
    public static final String ERROR_MOBILE = "手机号码不正确！";

    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Toast toast = null;


    public static void showMessage(Context context, final String msg) {
        showMessage(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 根据设置的文本显示
     * @param msg
     */
    public static void showMessage(Context context, final int msg) {
        showMessage(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个文本并且设置时长
     * @param msg
     * @param len
     */
    public static void showMessage(final Context context, final CharSequence msg, final int len) {
        if (msg == null || msg.equals("")) {
            LogUtils.w("[ToastUtils] response message is null.");
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (ToastUtils.class) { //加上同步是为了每个toast只要有机会显示出来
                    if (toast != null) {
                        //toast.cancel();
                        toast.setText(msg);
                        toast.setDuration(len);
                    } else {
                        toast = Toast.makeText(context, msg, len);
                    }
                    toast.show();
                }
            }
        });
    }

    /**
     * 资源文件方式显示文本
     * @param msg
     * @param len
     */
    public static void showMessage(final Context context, final int msg, final int len) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (ToastUtils.class) {
                    if (toast != null) {
                        //toast.cancel();
                        toast.setText(msg);
                        toast.setDuration(len);
                    } else {
                        toast = Toast.makeText(context, msg, len);
                    }
                    toast.show();
                }
            }
        });
    }
}
