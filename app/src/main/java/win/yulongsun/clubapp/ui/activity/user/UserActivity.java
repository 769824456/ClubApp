package win.yulongsun.clubapp.ui.activity.user;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.user
 * USER : yulongsun on 2016/4/13
 * NOTE :店员
 */
public class UserActivity extends BaseToolbarActivity {
    @Bind(R.id.tl_user) Toolbar mTlUser;

    @Override protected String getToolbarTitle() {
        return "店员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlUser;
    }

    @Override public int getLayoutResId() {
        return R.layout.actvity_user;
    }

    @Override protected int getMenuResId() {
        return R.menu.menu_add;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int    id     = item.getItemId();
        Intent intent = null;
        if (id == R.id.action_add) {
            intent = new Intent(UserActivity.this, UserAddActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
