package win.yulongsun.clubapp.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.common
 * USER : yulongsun on 2016/5/2
 * NOTE :
 */
public class App extends Application {
    private static App     application;
    private static int     mainThreadTid;
    private static Handler handler;

    @Override public void onCreate() {
        super.onCreate();
        application = this;
        mainThreadTid = android.os.Process.myTid();
        handler = new Handler();

    }

    public static Context getApplication() {
        return application;
    }

    public static int getMainThreadId() {
        return mainThreadTid;
    }

    public static Handler getMainThreadHandler() {
        return handler;
    }

}
