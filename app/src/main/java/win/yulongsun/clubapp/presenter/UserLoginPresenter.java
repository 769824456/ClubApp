package win.yulongsun.clubapp.presenter;

import android.os.Handler;

import win.yulongsun.clubapp.entity.UserVo;
import win.yulongsun.clubapp.biz.inter.IUserBiz;
import win.yulongsun.clubapp.biz.inter.OnLoginListener;
import win.yulongsun.clubapp.biz.impl.UserBiz;
import win.yulongsun.clubapp.view.IUserLoginView;

/*
 * PACKAGE_NAME :cn.ace.android_mvp.presenter
 * VERSION :[V 1.0.0]
 * AUTHOR :  yulongsun
 * CREATE AT : 8/1/2015 3:50 PM
 * COPYRIGHT : InSigma HengTian Software Ltd.
 * E-MAIL: yulongsun@hengtiansoft.com
 * NOTE : Presenter是用作Model和View之间交互的桥梁
 */
public class UserLoginPresenter {
    private IUserBiz userBiz;   //业务类
    private IUserLoginView userLoginView; //视图类
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userBiz = new UserBiz();
        this.userLoginView = userLoginView;
    }


    public void Login() {
        userLoginView.showLoading();

        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void LoginSuccess(final UserVo mUserVo) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(mUserVo);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void LoginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }


    public void clear() {
        userLoginView.cleanUserName();
        userLoginView.cleanPassword();
    }
}
