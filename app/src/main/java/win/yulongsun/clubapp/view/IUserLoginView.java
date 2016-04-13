package win.yulongsun.clubapp.view;


import win.yulongsun.clubapp.bean.User;

/*
 * PACKAGE_NAME :cn.ace.android_mvp.view
 * VERSION :[V 1.0.0]
 * AUTHOR :  yulongsun
 * CREATE AT : 8/1/2015 3:50 PM
 * COPYRIGHT : InSigma HengTian Software Ltd.
 * E-MAIL: yulongsun@hengtiansoft.com
 * NOTE : 视图层：登陆界面接口
 */
public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void cleanUserName();

    void cleanPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);//登陆成功

    void showFailedError();

}
