package win.yulongsun.clubapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

public class ForgetPwdActivity extends BaseToolbarActivity {


    @Bind(R.id.tl_forget_pwd) Toolbar mTlForgetPwd;

    @Override public int getLayoutResId() {
        return R.layout.activity_forget_pwd;
    }

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "忘记密码";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlForgetPwd;
    }
}
