package win.yulongsun.clubapp.activity.user;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.BaseToolbarActivity;

//添加店员
public class UserAddActivity extends BaseToolbarActivity {

    private Toolbar tl_user_add;

    @Override public int getLayoutResId() {
        return R.layout.activity_user_add;
    }

    @Override protected void initView() {
        super.initView();
        tl_user_add = (Toolbar) findViewById(R.id.tl_user_add);
    }

    @Override protected String getToolbarTitle() {
        return "添加会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_user_add;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
