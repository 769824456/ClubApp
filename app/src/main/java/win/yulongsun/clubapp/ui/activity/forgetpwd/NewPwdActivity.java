package win.yulongsun.clubapp.ui.activity.forgetpwd;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.net.response.NullResponse;
import win.yulongsun.yulongsunutils.MD5Utils;
import win.yulongsun.yulongsunutils.ValidateUtils;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.GsonUtils;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

//输入新密码
public class NewPwdActivity extends BaseToolbarActivity {

    private static final String TAG = NewPwdActivity.class.getSimpleName();
    @Bind(R.id.tl_new_pwd) Toolbar mTlNewPwd;
    @Bind(R.id.et_reset_new_pwd)       EditText mEtResetNewPwd;
    @Bind(R.id.et_reset_new_pwd_again) EditText mEtResetNewPwdAgain;
    private                            String   user_mobile;

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

    @Override protected void initDatas() {
        super.initDatas();
        user_mobile = getIntent().getStringExtra("user_mobile");
    }

    @OnClick(R.id.btn_reset) public void onClick() {
        String pwd       = mEtResetNewPwd.getText().toString().trim();
        String pwd_again = mEtResetNewPwdAgain.getText().toString().trim();
        if (!ValidateUtils.isPwdValid(pwd) || !ValidateUtils.isPwdValid(pwd_again)) {
            ToastUtils.showMessage(NewPwdActivity.this, "密码长度不正确");
            return;
        } else if (!pwd.equals(pwd_again)) {
            ToastUtils.showMessage(NewPwdActivity.this, "两次输入的密码不一致");
            return;
        } else {
            showLoading("重置中....");
            OkHttpUtils.post().url(Api.HOST + Api.MANAGER+"resetPwd")
                    .addParams("user_mobile", user_mobile)
                    .addParams("user_new_pwd", MD5Utils.getMD5Str(pwd_again))
                    .build()
                    .execute(new StringCallback() {
                        @Override public void onError(Call call, Exception e) {

                        }

                        @Override public void onResponse(String response) {
                            hideLoading();
                            Log.d(TAG, response);
                            NullResponse nullResponse = GsonUtils.changeGsonToBean(response, NullResponse.class);
                            if (nullResponse.error) {
                                ToastUtils.showMessage(NewPwdActivity.this, nullResponse.errorMsg);
                            } else {
                                ToastUtils.showMessage(NewPwdActivity.this, "修改成功");
                                NewPwdActivity.this.finish();
                            }
                        }
                    });
        }

    }
}
