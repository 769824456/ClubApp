package win.yulongsun.clubapp.ui.activity.daily;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.daily
 * USER : yulongsun on 2016/4/13
 * NOTE :日结
 */
public class DailyActivity extends BaseToolbarActivity {
    private Toolbar tl_daily;

    @Override protected String getToolbarTitle() {
        return "日结";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_daily;
    }

    @Override protected void initViews() {
        super.initViews();
        tl_daily = (Toolbar) findViewById(R.id.tl_daily);
    }

    @Override public int getLayoutResId() {
        return R.layout.activity_daily;
    }


    @Override protected int getMenuResId() {
        return R.menu.menu_done;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
