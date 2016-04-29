package win.yulongsun.clubapp.activity.user;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.activity.user
 * USER : yulongsun on 2016/4/13
 * NOTE :店员
 */
public class UserActivity extends BaseToolbarActivity {
    private Toolbar tl_user;

    @Override protected String getToolbarTitle() {
        return "店员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_user;
    }

    @Override protected void initView() {
        super.initView();
        tl_user = (Toolbar) findViewById(R.id.tl_user);
    }

    @Override public int getLayoutResId() {
        return R.layout.actvity_user;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int    id      = item.getItemId();
        Intent mIntent = null;
        if (id == R.id.action_add) {
            mIntent = new Intent(UserActivity.this, UserAddActivity.class);
            startActivity(mIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
