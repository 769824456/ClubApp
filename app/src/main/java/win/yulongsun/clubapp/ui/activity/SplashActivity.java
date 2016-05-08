package win.yulongsun.clubapp.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseActivity;

public class SplashActivity extends BaseActivity {

    private ImageView iv_splash_logo;
    private TextView  tv_splash_version;

    @Override public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override protected void initViews() {
        super.initViews();
        iv_splash_logo = (ImageView) findViewById(R.id.iv_splash_logo);
        tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
    }

    @Override protected void initDatas() {
        super.initDatas();
        try {
            PackageInfo mPackageInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            String      versionName  = mPackageInfo.versionName;
            tv_splash_version.setText("版权所有\t" + versionName);
        } catch (PackageManager.NameNotFoundException mE) {
            mE.printStackTrace();
        }
    }

    @Override protected void initListeners() {
        super.initListeners();
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                String is_login    = ACache.get(SplashActivity.this).getAsString("is_login");
                Class  targetClass = null;
                if ("1".equals(is_login)) {
                    targetClass = LoginActivity.class;
                } else {
                    targetClass = HomeActivity.class;
                }
                Intent mIntent = new Intent(SplashActivity.this, targetClass);
                startActivity(mIntent);
                finish();

            }
        }, 3000);
    }
}
