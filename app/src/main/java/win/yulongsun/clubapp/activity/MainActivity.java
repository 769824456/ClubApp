package win.yulongsun.clubapp.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.entity.UserVo;
import win.yulongsun.clubapp.common.BaseActivity;
import win.yulongsun.clubapp.presenter.UserLoginPresenter;
import win.yulongsun.clubapp.view.IUserLoginView;


/*
 * PACKAGE_NAME :cn.ace.android_mvp
 * VERSION :[V 1.0.0]
 * AUTHOR :  yulongsun
 * CREATE AT : 8/1/2015 3:51 PM
 * COPYRIGHT : InSigma HengTian Software Ltd.
 * E-MAIL: yulongsun@hengtiansoft.com
 * NOTE :
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, IUserLoginView {

    private EditText etUsername;
    private EditText etPassword;
    private Button   btnLogin;
    private Button   btnReset;
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override protected void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset);

        btnLogin.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        if (v == btnLogin) {
            // Handle clicks for btnLogin
            mUserLoginPresenter.Login();
        } else if (v == btnReset) {
            // Handle clicks for btnReset
            mUserLoginPresenter.clear();
        }
    }


    /* 获取用户名*/
    @Override public String getUserName() {
        return etUsername.getText().toString();
    }

    /*获取密码*/
    @Override public String getPassword() {
        return etPassword.getText().toString();
    }

    /*清除用户名*/
    @Override public void cleanUserName() {
        etUsername.setText("");
    }

    /*清除密码*/
    @Override public void cleanPassword() {
        etPassword.setText("");
    }

    /*登录成功，进入主页*/
    @Override
    public void toMainActivity(UserVo mUserVo) {
        Toast.makeText(this, mUserVo.getUsername() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    /*登录失败*/
    @Override public void showFailedError() {
        Toast.makeText(this,
                "login failed", Toast.LENGTH_SHORT).show();
    }


}
