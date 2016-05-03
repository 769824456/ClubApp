package win.yulongsun.clubapp.ui.view;

import win.yulongsun.yulongsunutils.common.IBaseView;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.view
 * USER : yulongsun on 2016/4/14
 * NOTE :
 */
public interface IUserAddView extends IBaseView {

    String getMobile();

    String getPassword();

    String getJobId();

    String getUserName();

    String getUserAddr();

    int getGender();
}
