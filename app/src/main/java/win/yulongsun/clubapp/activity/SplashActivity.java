package win.yulongsun.clubapp.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.BaseActivity;

public class SplashActivity extends BaseActivity {

    private ImageView iv_splash_logo;
    private TextView  tv_splash_version;

    @Override public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override protected void initView() {
        super.initView();
        iv_splash_logo = (ImageView) findViewById(R.id.iv_splash_logo);
        tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
    }

    @Override protected void initData() {
        super.initData();
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

                Intent mIntent = new Intent(SplashActivity.this, LoginActivty.class);
                startActivity(mIntent);
                finish();

            }
        }, 3000);
    }
}
