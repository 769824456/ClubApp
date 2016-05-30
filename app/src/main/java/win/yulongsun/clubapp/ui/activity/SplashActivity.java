package win.yulongsun.clubapp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.TextView;

import win.yulongsun.clubapp.R;
import win.yulongsun.component.cache.ACache;
import win.yulongsun.uiframework.BaseActivity;

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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }
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
                if (is_login==null||"0".equals(is_login)) {
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
