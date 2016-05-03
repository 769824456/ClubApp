package win.yulongsun.clubapp.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.ui.presenter.UserLoginPresenter;
import win.yulongsun.clubapp.ui.view.IUserLoginView;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity
 * USER : yulongsun on 2016/4/12
 * NOTE :
 */
public class LoginActivity extends BaseToolbarActivity implements IUserLoginView {
    @Bind(R.id.iv_splash_logo)  ImageView          mIvSplashLogo;
    @Bind(R.id.tv_splash_title) TextView           mTvSplashTitle;
    @Bind(R.id.tl_login)        Toolbar            mTlLogin;
    @Bind(R.id.et_login_phone)  EditText           mEtLoginPhone;
    @Bind(R.id.et_login_pwd)    EditText           mEtLoginPwd;
    private                     UserLoginPresenter userLoginPresenter;
    private                     Intent             mIntent;

    @Override public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override protected void initViews() {
        super.initViews();
        userLoginPresenter = new UserLoginPresenter(this, this);
    }


    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "登录";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlLogin;
    }


    @OnClick(R.id.btn_login_go) void doLogin() {
        userLoginPresenter.login();
    }

    @OnClick(R.id.tv_login_reg) void doReg() {
        userLoginPresenter.register();
    }


    @Override public String getPhone() {
        return mEtLoginPhone.getText().toString();
    }

    @Override public String getPwd() {
        return mEtLoginPwd.getText().toString();
    }

    @Override public void toMainView() {
        mIntent = new Intent(this, HomeActivity.class);
        startActivity(mIntent);
        finish();
    }


    @Override public void showFailedError() {

    }

    @Override public void toRegView() {
        mIntent = new Intent(this, RegActivity.class);
        startActivity(mIntent);
    }
}
