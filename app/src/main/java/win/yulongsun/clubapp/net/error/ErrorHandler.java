package win.yulongsun.clubapp.net.error;

import android.content.Context;
import android.util.Log;

import okhttp3.Call;
import win.yulongsun.utils.ToastUtils;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.exception
 * USER : yulongsun on 2016/5/7
 * NOTE :
 */
public class ErrorHandler {
    private static final String TAG = ErrorHandler.class.getSimpleName();

    public static void onError(Context context, Call call, Exception e) {
        Log.d(TAG, " call:" + call.toString() + ",Exception:" + e.toString());
        ToastUtils.showMessage(context, e.getMessage());
    }
}
