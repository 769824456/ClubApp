package win.yulongsun.clubapp.ui.activity.user;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

//添加店员
public class UserAddActivity extends BaseToolbarActivity {

    private Toolbar tl_user_add;

    @Override public int getLayoutResId() {
        return R.layout.activity_user_add;
    }

    @Override protected void initViews() {
        super.initViews();
        tl_user_add = (Toolbar) findViewById(R.id.tl_user_add);
    }

    @Override protected String getToolbarTitle() {
        return "添加会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_user_add;
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
