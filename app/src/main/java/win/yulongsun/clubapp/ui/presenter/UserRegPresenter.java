package win.yulongsun.clubapp.ui.presenter;

import android.content.Context;

import win.yulongsun.clubapp.ui.view.IUserRegView;
import win.yulongsun.yulongsunutils.ValidateUtils;
import win.yulongsun.yulongsunutils.common.BasePresenter;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.presenter
 * USER : yulongsun on 2016/5/2
 * NOTE :注册
 */
public class UserRegPresenter extends BasePresenter<IUserRegView> {
    public UserRegPresenter(Context context, IUserRegView iView) {
        super(context, iView);
    }

    /*注册*/
    public void register() {
        String clubName     = iView.getClubName();
        String clubAddr     = iView.getClubAddr();
        String clubScale    = iView.getClubScale();
        String clubPwd      = iView.getClubPwd();
        String managerName  = iView.getManagerName();
        String managerPhone = iView.getManagerPhone();
        //validate
        if (ValidateUtils.isTextNull(clubName)) {
            ToastUtils.showMessage(context, "会所名不能为空");
            return;
        }
        if (ValidateUtils.isTextNull(clubAddr)) {
            ToastUtils.showMessage(context, "会所地址不能为空");
            return;
        }
        if (!ValidateUtils.isMobilePattern(managerPhone)) {
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

    }
}
