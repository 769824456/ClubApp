package win.yulongsun.clubapp.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.entity.UserVo;
import win.yulongsun.clubapp.ui.adapter.UserRVAdapter;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.user
 * USER : yulongsun on 2016/4/13
 * NOTE :店员
 */
public class UserActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = UserActivity.class.getSimpleName();
    @Bind(R.id.tl_user)  Toolbar             mTlUser;
    @Bind(R.id.rcv_user) RecyclerView        mRcvUser;
    @Bind(R.id.srf_user) SwipeRefreshLayout  mSrfUser;
    private              ArrayList<UserVo>   mUserVoList;
    private              UserRVAdapter       mUserRVAdapter;
    private              LinearLayoutManager mLayoutManager;
    private boolean isLoadingMore = false;

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

    @Override protected void initListeners() {
        super.initListeners();
        mSrfUser.setOnRefreshListener(this);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRcvUser.setLayoutManager(mLayoutManager);
        mRcvUser.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItemCount  = mLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                    if (isLoadingMore) {
                        Log.d(TAG, "ignore manually update!");
                    } else {
                        loadMore();//这里多线程也要手动控制isLoadingMore
                        isLoadingMore = false;
                    }
                }
            }
        });
    }

    private void loadMore() {
        isLoadingMore = true;

    }

    @Override protected void initDatas() {
        super.initDatas();
        mUserVoList = new ArrayList<UserVo>();
        for (int i = 0; i < 10; i++) {
            UserVo userVo = new UserVo();
            userVo.id = i;
            userVo.name = "员工" + i;
            userVo.phone = "13067509781" + i;
            userVo.create_time = "2016-1-1";
            userVo.gender = 1;
            userVo.job_id = 10000 + i;
            mUserVoList.add(userVo);
        }

        mUserRVAdapter = new UserRVAdapter(UserActivity.this, mUserVoList);
        mRcvUser.setAdapter(mUserRVAdapter);
    }

    @Override public void onRefresh() {

    }
}
