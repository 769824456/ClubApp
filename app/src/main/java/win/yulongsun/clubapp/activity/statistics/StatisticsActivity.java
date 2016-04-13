package win.yulongsun.clubapp.activity.statistics;

import android.support.v7.widget.Toolbar;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.activity.BaseActivity;
import win.yulongsun.clubapp.activity.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.activity.statistics
 * USER : yulongsun on 2016/4/13
 * NOTE : 统计
 */
public class StatisticsActivity extends BaseToolbarActivity {
    private Toolbar tl_statistics;

    @Override public int getLayoutResId() {
        return R.layout.activity_statistics;
    }

    @Override protected void initView() {
        super.initView();
        tl_statistics = (Toolbar) findViewById(R.id.tl_statistics);
    }

    @Override protected String getToolbarTitle() {
        return "统计";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_statistics;
    }
}
