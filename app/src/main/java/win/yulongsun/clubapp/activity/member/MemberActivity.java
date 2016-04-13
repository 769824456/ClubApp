package win.yulongsun.clubapp.activity.member;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.activity.BaseToolbarActivity;
import win.yulongsun.clubapp.activity.user.UserAddActivity;

public class MemberActivity extends BaseToolbarActivity {

    private Toolbar tl_member;

    @Override public int getLayoutResId() {
        return R.layout.activity_member;
    }

    @Override protected void initView() {
        super.initView();
        tl_member = (Toolbar) findViewById(R.id.tl_member);
    }

    @Override protected String getToolbarTitle() {
        return "会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_member;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int    id      = item.getItemId();
        Intent mIntent = null;
        if (id == R.id.action_add) {
            mIntent = new Intent(MemberActivity.this, MemberAddActivity.class);
            startActivity(mIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
