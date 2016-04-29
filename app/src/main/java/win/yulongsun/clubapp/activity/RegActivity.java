package win.yulongsun.clubapp.activity;

import android.support.v7.widget.Toolbar;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.activity
 * USER : yulongsun on 2016/4/12
 * NOTE :
 */
public class RegActivity extends BaseToolbarActivity {
    private Toolbar tl_reg;

    @Override public int getLayoutResId() {
        return R.layout.activity_reg;
    }

    @Override protected void initView() {
        super.initView();
        tl_reg = (Toolbar) findViewById(R.id.tl_reg);
    }

    @Override protected String getToolbarTitle() {
        return "注册";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_reg;
    }
}
