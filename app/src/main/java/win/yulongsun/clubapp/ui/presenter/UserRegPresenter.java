package win.yulongsun.clubapp.ui.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.net.error.ErrorHandler;
import win.yulongsun.clubapp.ui.view.IUserRegView;
import win.yulongsun.uiframework.BasePresenter;
import win.yulongsun.utils.MD5Utils;
import win.yulongsun.utils.ToastUtils;
import win.yulongsun.utils.ValidateUtils;
import win.yulongsun.component.response.ResponseObject;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.presenter
 * USER : yulongsun on 2016/5/2
 * NOTE :注册
 */
public class UserRegPresenter extends BasePresenter<IUserRegView> {
    private static final String TAG = UserRegPresenter.class.getSimpleName();

    public UserRegPresenter(Context context, IUserRegView iView) {
        super(context, iView);
    }

    /*注册*/
    public void register() {
        String clubName      = iView.getClubName();
        String clubAddr      = iView.getClubAddr();
        String clubScale     = iView.getClubScale();
        String clubPwd       = iView.getClubPwd();
        String managerName   = iView.getManagerName();
        String managerMobile = iView.getManagerMobile();
        //validate
        if (ValidateUtils.isTextNull(clubName)) {
            ToastUtils.showMessage(context, "会所名不能为空");
            return;
        }
        if (ValidateUtils.isTextNull(clubAddr)) {
            ToastUtils.showMessage(context, "会所地址不能为空");
            return;
        }
        if (!ValidateUtils.isMobilePattern(managerMobile)) {
            ToastUtils.showMessage(context, ToastUtils.ERROR_PHONE);
            return;
        }
        if (ValidateUtils.isTextNull(clubAddr)) {
            ToastUtils.showMessage(context, "店面经理不能为空");
            return;
        }
        if (!ValidateUtils.isPwdValid(clubPwd)) {
            ToastUtils.showMessage(context, ToastUtils.ERROR_PWD);
            return;
        }
        iView.showLoading("注册中...");
        OkHttpUtils.post()
                .url(Api.HOST + Api.MANAGER + "register")
                .addParams("club_name", clubName)
                .addParams("club_addr", clubAddr)
                .addParams("club_scale", clubScale)
                .addParams("user_pwd", MD5Utils.getMD5Str(clubPwd))
                .addParams("user_name", managerName)
                .addParams("user_mobile", managerMobile)
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {
                        iView.hideLoading();
                        ErrorHandler.onError(context, call, e);
                    }

                    @Override public void onResponse(String response) {
                        iView.hideLoading();
                        Log.d(TAG, "onResponse: " + response);
                        ResponseObject responseObject = new Gson().fromJson(response, ResponseObject.class);
                        if (responseObject.errorCode == 0) {
                            ToastUtils.showMessage(context, "注册成功");
                            iView.toLoginView();
                        } else {
                            iView.showRegFailure("注册失败,该手机号已被注册");
                        }
                    }
                });

    }
}
