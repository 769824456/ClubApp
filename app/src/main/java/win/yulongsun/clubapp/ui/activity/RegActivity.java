package win.yulongsun.clubapp.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.ui.presenter.UserRegPresenter;
import win.yulongsun.clubapp.ui.view.IUserRegView;
import win.yulongsun.utils.common.BaseToolbarActivity;
import win.yulongsun.utils.utils.ToastUtils;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity
 * USER : yulongsun on 2016/4/12
 * NOTE :
 */
public class RegActivity extends BaseToolbarActivity implements IUserRegView {

    @Bind(R.id.et_reg_club_name)    EditText         mEtRegClubName;
    @Bind(R.id.et_reg_club_addr)    EditText         mEtRegClubAddr;
    @Bind(R.id.et_reg_club_manager) EditText         mEtRegClubManager;
    @Bind(R.id.sp_reg_club_scale)   Spinner          mSpRegClubScale;
    @Bind(R.id.et_reg_club_phone)   EditText         mEtRegClubPhone;
    @Bind(R.id.tl_reg)              Toolbar          mTlReg;
    @Bind(R.id.et_reg_club_pwd)     EditText         mEtRegClubPwd;
    private                         UserRegPresenter mUserRegPresenter;
    private int mSpRegClubScalePosi = 0;

    @Override public int getLayoutResId() {
        return R.layout.activity_reg;
    }

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "注册";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlReg;
    }

    @Override protected void initViews() {
        super.initViews();
        mUserRegPresenter = new UserRegPresenter(this, this);
    }

    @Override protected void initListeners() {
        super.initListeners();
        mSpRegClubScale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpRegClubScalePosi = position;
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override public String getClubName() {
        return mEtRegClubName.getText().toString();
    }

    @Override public String getClubAddr() {
        return mEtRegClubAddr.getText().toString();
    }

    @Override public String getManagerName() {
        return mEtRegClubManager.getText().toString();
    }


    @Override public String getClubScale() {
        return String.valueOf(mSpRegClubScalePosi);
    }

    @Override public String getManagerMobile() {
        return mEtRegClubPhone.getText().toString();
    }

    @Override public String getClubPwd() {
        return mEtRegClubPwd.getText().toString();
    }

    @Override public void toLoginView() {
        this.finish();
    }

    @Override public void showRegFailure(String msg) {
        ToastUtils.showMessage(RegActivity.this, msg);
    }

    public void btnReg(View view) {
        mUserRegPresenter.register();
    }

}
