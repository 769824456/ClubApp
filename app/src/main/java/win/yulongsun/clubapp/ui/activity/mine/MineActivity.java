package win.yulongsun.clubapp.ui.activity.mine;

import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.mine
 * USER : yulongsun on 2016/4/14
 * NOTE :我的
 */
public class MineActivity extends BaseToolbarActivity {
    @Bind(R.id.tl_mine)
    Toolbar mTlMine;

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "我的";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMine;
    }

    @Override public int getLayoutResId() {
        return R.layout.activity_mine;
    }

}
