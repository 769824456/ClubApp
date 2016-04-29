package win.yulongsun.clubapp.view;

import win.yulongsun.clubapp.common.IBaseView;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.view
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
