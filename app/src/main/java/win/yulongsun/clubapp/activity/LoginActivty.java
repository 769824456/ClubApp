package win.yulongsun.clubapp.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import win.yulongsun.clubapp.R;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.activity
 * USER : yulongsun on 2016/4/12
 * NOTE :
 */
public class LoginActivty extends BaseToolbarActivity implements View.OnClickListener {
    private Button   btn_login;
    private TextView tv_login_reg;
    private Toolbar  tl_login;

    @Override public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override protected void initView() {
        super.initView();
        tl_login = (Toolbar) findViewById(R.id.tl_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_login_reg = (TextView) findViewById(R.id.tv_login_reg);
    }

    @Override protected String getToolbarTitle() {
        return "登录";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_login;
    }

    @Override protected void initListeners() {
        super.initListeners();
        btn_login.setOnClickListener(this);
        tv_login_reg.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        Intent mIntent = null;
        switch (v.getId()) {
            case R.id.btn_login:
                mIntent = new Intent(this, HomeActivity.class);
                break;
            case R.id.tv_login_reg:
                mIntent = new Intent(this, RegActivity.class);
                break;
        }
        if (mIntent != null) {
            startActivity(mIntent);
            finish();
        }
    }
}
