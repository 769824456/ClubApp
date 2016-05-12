package win.yulongsun.clubapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

//输入新密码
public class NewPwdActivity extends BaseToolbarActivity {

    @Bind(R.id.tl_new_pwd) Toolbar mTlNewPwd;

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "新密码";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlNewPwd;
    }

    @Override public int getLayoutResId() {
        return R.layout.activity_new_pwd;
    }



}
