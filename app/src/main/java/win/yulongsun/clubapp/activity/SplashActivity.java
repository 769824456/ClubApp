package win.yulongsun.clubapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import win.yulongsun.clubapp.R;

public class SplashActivity extends BaseActivity {

    private ImageView iv_splash_logo;

    @Override public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override protected void initView() {
        super.initView();
        iv_splash_logo = (ImageView) findViewById(R.id.iv_splash_logo);
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
