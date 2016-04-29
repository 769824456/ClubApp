package win.yulongsun.clubapp.activity.mine;

import android.support.v7.widget.Toolbar;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.activity.mine
 * USER : yulongsun on 2016/4/14
 * NOTE :我的
 */
public class MineActivity extends BaseToolbarActivity {
    private Toolbar tl_mine;

    @Override protected String getToolbarTitle() {
        return "我的";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_mine;
    }

    @Override public int getLayoutResId() {
        return R.layout.activity_mine;
    }

    @Override protected void initView() {
        super.initView();
        tl_mine = (Toolbar) findViewById(R.id.tl_mine);
    }
}
