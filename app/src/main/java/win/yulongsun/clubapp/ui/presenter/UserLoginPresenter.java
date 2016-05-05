package win.yulongsun.clubapp.ui.presenter;

import android.content.Context;

import win.yulongsun.clubapp.ui.view.IUserLoginView;
import win.yulongsun.yulongsunutils.ValidateUtils;
import win.yulongsun.yulongsunutils.common.BasePresenter;
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

    public UserLoginPresenter(Context context, IUserLoginView iView) {
        super(context, iView);
    }

    /*登陆*/
    public void login() {
//        String phone = iView.getPhone();
//        String pwd   = iView.getPwd();
//        if (!ValidateUtils.isMobilePattern(phone)) {
//            ToastUtils.showMessage(context, ToastUtils.ERROR_PHONE);
//            return;
//        }
//        if (!ValidateUtils.isPwdValid(pwd)) {
//            ToastUtils.showMessage(context, ToastUtils.ERROR_PWD);
//            return;
//        }

        //// TODO: 2016/5/3
        iView.toMainView();
    }

    public void register() {
        iView.toRegView();
    }

    public void forgetPwd() {
        iView.toForgetPwdView();
    }
}
