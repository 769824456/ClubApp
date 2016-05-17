package win.yulongsun.clubapp.ui.activity.forgetpwd;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.net.error.ErrorHandler;
import win.yulongsun.clubapp.net.response.NullResponse;
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


    @Bind(R.id.tl_forget_pwd)           Toolbar  mTlForgetPwd;
    @Bind(R.id.et_forget_pwd_mobile)    EditText mEtForgetPwdMobile;
    @Bind(R.id.et_forget_pwd_code)      EditText mEtForgetPwdCode;
    @Bind(R.id.btn_forget_pwd_get_code) Button mBtnForgetPwdGetCode;
    private              int     i                   = 60;     //60秒
    private static final int     BUTTON_SEND_SUCCESS = -9;     //倒计时
    private static final int     BUTTON_RESET        = -8;     //重置Button
    private              int     tmp                 = 0;
    //更新btn
    private              Handler mHandler            = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BUTTON_SEND_SUCCESS:    //通过空消息改变主线程的倒计时
                    if (mBtnForgetPwdGetCode != null) {
                        mBtnForgetPwdGetCode.setText("重新发送(" + i + ")");
                    }
                    break;
                case BUTTON_RESET:    //重置Button
                    if (mBtnForgetPwdGetCode != null) {
                        mBtnForgetPwdGetCode.setText("获取验证码");
                        mBtnForgetPwdGetCode.setClickable(true);
                        i = 60;
                    }
                    break;
            }
        }
    };
    private String user_mobile;

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
        user_mobile = mEtForgetPwdMobile.getText().toString();
        if (!ValidateUtils.isMobilePattern(user_mobile)) {
            ToastUtils.showMessage(ForgetPwdActivity.this, "手机号码不正确");
            return;
        }
        //  把按钮变成不可点击，并且显示倒计时（正在获取）
        mBtnForgetPwdGetCode.setClickable(false);
        mBtnForgetPwdGetCode.setText("重新发送(" + i + ")");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i > 0; i--) {
                    mHandler.sendEmptyMessage(BUTTON_SEND_SUCCESS);//改变倒计时
                    if (i <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mHandler.sendEmptyMessage(BUTTON_RESET);
            }
        }).start();
        OkHttpUtils.post().url(Api.HOST + Api.MANAGER + "sendVerifyCode")
                .addParams("user_mobile", user_mobile)
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
                            tmp = Integer.parseInt(nullResponse.result);
                        }
                    }
                });
    }

    /*重置密码*/
    public void btnResetPwd(View view) {
        String code    = mEtForgetPwdCode.getText().toString().trim();
        int    codeInt = Integer.parseInt(code);
        if (codeInt == 0 || codeInt != tmp) {
            ToastUtils.showMessage(ForgetPwdActivity.this, "验证码不正确");
            return;
        } else if (tmp == codeInt) {
            Intent intent = new Intent(ForgetPwdActivity.this, NewPwdActivity.class);
            intent.putExtra("user_mobile", user_mobile);
            startActivity(intent);
            finish();
        }
    }

}
