package win.yulongsun.clubapp.biz.inter;


import win.yulongsun.clubapp.bean.User;

/*
 * PACKAGE_NAME :cn.ace.android_mvp.biz.inter
 * VERSION :[V 1.0.0]
 * AUTHOR :  yulongsun
 * CREATE AT : 8/1/2015 3:52 PM
 * COPYRIGHT : InSigma HengTian Software Ltd.
 * E-MAIL: yulongsun@hengtiansoft.com
 * NOTE : 登录回调接口
 */
public interface OnLoginListener {
    void LoginSuccess(User user);
    void LoginFailed();
}
