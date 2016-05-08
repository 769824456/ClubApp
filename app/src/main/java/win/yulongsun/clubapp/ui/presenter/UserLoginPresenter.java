package win.yulongsun.clubapp.ui.presenter;

import android.content.Context;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.entity.UserVo;
import win.yulongsun.clubapp.response.UserVoResponse;
import win.yulongsun.clubapp.ui.view.IUserLoginView;
import win.yulongsun.yulongsunutils.MD5Utils;
import win.yulongsun.yulongsunutils.ValidateUtils;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BasePresenter;
import win.yulongsun.yulongsunutils.response.ResponseObject;
import win.yulongsun.yulongsunutils.utils.GsonUtils;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

/*
 * PACKAGE_NAME :cn.ace.android_mvp.presenter
 * VERSION :[V 1.0.0]
 * AUTHOR :  yulongsun
 * CREATE AT : 8/1/2015 3:50 PM
 * COPYRIGHT : InSigma HengTian Software Ltd.
 * E-MAIL: yulongsun@hengtiansoft.com
 * NOTE : Presenter是用作Model和View之间交互的桥梁
 */
public class UserLoginPresenter extends BasePresenter<IUserLoginView> {

    private static final String TAG = UserLoginPresenter.class.getSimpleName();
    private UserVo         mUserVo;
    private UserVoResponse mUserVoResponse;

    public UserLoginPresenter(Context context, IUserLoginView iView) {
        super(context, iView);
    }

    /*登陆*/
    public void login() {
        String mobile = iView.getMobile();
        String pwd    = iView.getPwd();
        if (!ValidateUtils.isMobilePattern(mobile)) {
            ToastUtils.showMessage(context, ToastUtils.ERROR_PHONE);
            return;
        }
        if (!ValidateUtils.isPwdValid(pwd)) {
            ToastUtils.showMessage(context, ToastUtils.ERROR_PWD);
            return;
        }
        iView.showLoading("登陆中...");
        OkHttpUtils.post().url(Api.HOST + Api.MANAGER + "login")
                .addParams("user_mobile", mobile)
                .addParams("user_pwd", MD5Utils.getMD5Str(pwd))
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {
                        iView.hideLoading();
                    }

                    @Override public void onResponse(String response) {
                        iView.hideLoading();
                        Log.d(TAG, "onResponse: " + response);
                        mUserVoResponse = GsonUtils.changeGsonToBean(response, UserVoResponse.class);
                        if (mUserVoResponse.errorCode == 0) {
                            mUserVo = mUserVoResponse.result;
                            if (mUserVo.is_enable == 0) {
                                iView.showFailedError("当前账户未启用");
                                return;
                            }
                            //缓存数据到本地
                            cacheResponse();
                            iView.toMainView();
                        } else {
                            iView.showFailedError(mUserVoResponse.errorMsg);
                        }
                    }
                });

    }

    private void cacheResponse() {
        ACache aCache = ACache.get(context);
        aCache.put("user_id", mUserVo.id + "");
        aCache.put("user_name", mUserVo.name);
        aCache.put("user_mobile", mUserVo.mobile);
        aCache.put("user_avatar", mUserVo.avatar);
        aCache.put("user_addr", mUserVo.addr);
        aCache.put("user_gender", mUserVo.gender + "");
        aCache.put("user_job_id", mUserVo.job_id + "");
        aCache.put("user_c_id", mUserVo.c_id + "");
        aCache.put("user_r_id", mUserVo.r_id + "");
        aCache.put("user_is_enable", mUserVo.is_enable + "");
        aCache.put("is_login", "1");
    }

    public void register() {
        iView.toRegView();
    }

    public void forgetPwd() {
        iView.toForgetPwdView();
    }
}
