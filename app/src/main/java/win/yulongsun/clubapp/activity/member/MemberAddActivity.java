package win.yulongsun.clubapp.activity.member;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.activity.BaseToolbarActivity;

public class MemberAddActivity extends BaseToolbarActivity {

    private Toolbar tl_member_add;

    @Override public int getLayoutResId() {
        return R.layout.activity_member_add;
    }

    @Override protected void initView() {
        super.initView();
        tl_member_add = (Toolbar) findViewById(R.id.tl_member_add);
    }

    @Override protected String getToolbarTitle() {
        return "添加会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_member_add;
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
