package win.yulongsun.clubapp.ui.view;


import win.yulongsun.yulongsunutils.common.IBaseView;

/*
 * PACKAGE_NAME :cn.ace.android_mvp.view
 * VERSION :[V 1.0.0]
 * AUTHOR :  yulongsun
 * CREATE AT : 8/1/2015 3:50 PM
 * COPYRIGHT : InSigma HengTian Software Ltd.
 * E-MAIL: yulongsun@hengtiansoft.com
 * NOTE : 视图层：登陆界面接口
 */
public interface IUserLoginView extends IBaseView {
    String getMobile();

    String getPwd();

    void toMainView();

    void showFailedError(String msg);

    void toRegView();

    void toForgetPwdView();

}
