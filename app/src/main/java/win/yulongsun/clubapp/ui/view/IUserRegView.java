package win.yulongsun.clubapp.ui.view;

import win.yulongsun.utils.common.IBaseView;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.view
 * USER : yulongsun on 2016/4/21
 * NOTE :
 */
public interface IUserRegView extends IBaseView {

    String getClubName();

    String getClubAddr();

    String getManagerName();

    String getClubScale();

    String getManagerMobile();

    String getClubPwd();

    void toLoginView();

    void showRegFailure(String msg);
}
