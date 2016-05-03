package win.yulongsun.clubapp.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.entity.MemberVo;
import win.yulongsun.clubapp.ui.adapter.MemberRVAdapter;
import win.yulongsun.yulongsunpulltorefresh.PullToRefreshListView;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.common.CommonAdapter;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.user
 * USER : yulongsun on 2016/4/13
 * NOTE :店员
 */
public class UserActivity extends BaseToolbarActivity {
    @Bind(R.id.tl_user)  Toolbar               mTlUser;
    @Bind(R.id.plv_user) PullToRefreshListView mPlvUser;
    private              ArrayList<MemberVo>   mMemberVos;
    private              MemberRVAdapter       mMemberRVAdapter;

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

    @Override protected void initDatas() {
        super.initDatas();
        mMemberVos = new ArrayList<>();
        MemberVo mMemberVo = null;
        for (int i = 0; i < 10; i++) {
            mMemberVo = new MemberVo(1, "yulongsun" + i, "130675097" + i);
            mMemberVos.add(mMemberVo);
        }
//        mMemberRVAdapter = new CommonAdapter(this, mMemberVos);
//        ListView mPlvUserListView = mPlvUser.getListView();
//        mPlvUserListView.setAdapter(mMemberRVAdapter);
    }

}
