package win.yulongsun.clubapp.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.error.ErrorHandler;
import win.yulongsun.clubapp.response.NullResponse;
import win.yulongsun.yulongsunutils.ValidateUtils;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.GsonUtils;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.daily
 * USER : yulongsun on 2016/4/13
 * NOTE :忘记密码
*/
public class ForgetPwdActivity extends BaseToolbarActivity {


    @Bind(R.id.tl_forget_pwd)        Toolbar  mTlForgetPwd;
    @Bind(R.id.et_forget_pwd_mobile) EditText mEtForgetPwdMobile;
    @Bind(R.id.et_forget_pwd_code)   EditText mEtForgetPwdCode;

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

    /*获取验证码*/
    public void btnGetCode(View view) {
        String mobile = mEtForgetPwdMobile.getText().toString();
        if (!ValidateUtils.isMobilePattern(mobile)) {
            ToastUtils.showMessage(ForgetPwdActivity.this, "手机号码不正确");
            return;
        }
        OkHttpUtils.post().url(Api.HOST + Api.USER)
                .addParams("mobile", mobile)
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {
                        ErrorHandler.onError(ForgetPwdActivity.this, call, e);
                    }

                    @Override public void onResponse(String response) {
                        NullResponse nullResponse = GsonUtils.changeGsonToBean(response, NullResponse.class);
                        if (nullResponse.error) {
                            ToastUtils.showMessage(ForgetPwdActivity.this, nullResponse.errorMsg);
                        } else {
                            Intent intent = new Intent(ForgetPwdActivity.this, NewPwdActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    /*重置密码*/
    public void btnResetPwd(View view) {

    }
}
