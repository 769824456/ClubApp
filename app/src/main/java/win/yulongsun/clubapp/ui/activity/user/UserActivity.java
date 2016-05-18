package win.yulongsun.clubapp.ui.activity.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.common.Constants;
import win.yulongsun.clubapp.net.entity.UserVo;
import win.yulongsun.clubapp.net.response.NullResponse;
import win.yulongsun.clubapp.net.response.UserVoResponseList;
import win.yulongsun.clubapp.ui.adapter.UserRVAdapter;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.GsonUtils;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.user
 * USER : yulongsun on 2016/4/13
 * NOTE :店员
 */
public class UserActivity extends BaseToolbarActivity implements OnItemClickListener {
    private static final String TAG = UserActivity.class.getSimpleName();
    @Bind(R.id.tl_user)  Toolbar               mTlUser;
    @Bind(R.id.rcv_user) RecyclerView          mRcvUser;
    @Bind(R.id.mrl_user) MaterialRefreshLayout mMrlUser;
    private              List<UserVo>          mUserVoList;
    private              UserRVAdapter         mUserRVAdapter;
    private              LinearLayoutManager   mLayoutManager;
    private              String                user_c_id;
    private int pageNum = 1;

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

    @Override protected void initViews() {
        super.initViews();
        mMrlUser.autoRefresh();
    }

    @Override protected void initDatas() {
        super.initDatas();
        user_c_id = ACache.get(this).getAsString("user_c_id");
        mUserVoList = new ArrayList<UserVo>();
        mUserRVAdapter = new UserRVAdapter(UserActivity.this, mUserVoList, R.layout.item_rv_user);
        mRcvUser.setAdapter(mUserRVAdapter);
        mUserRVAdapter.setOnItemClickListener(this);
        mMrlUser.setLoadMore(false);
    }

    @Override protected void initListeners() {
        super.initListeners();
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRcvUser.setLayoutManager(mLayoutManager);
        mMrlUser.setMaterialRefreshListener(new MaterialRefreshListener() {

            @Override public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
//                loadMore();
            }

            @Override public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                loadDataFromCloud();
            }
        });
    }

    private void loadMore() {
        OkHttpUtils.post().url(Api.HOST + Api.USER + "listUser")
                .addParams("user_c_id", user_c_id)
                .addParams("page_num", String.valueOf(pageNum))
                .addParams("page_size", String.valueOf(Constants.PAGE_SIZE))
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {

                    }

                    @Override public void onResponse(String response) {
                        Log.d(TAG, "" + response);
                        mMrlUser.finishRefreshLoadMore();
                        UserVoResponseList userVoResponse = GsonUtils.parseToBean(response, UserVoResponseList.class);
                        if (userVoResponse.error) {
                            ToastUtils.showMessage(UserActivity.this, userVoResponse.errorMsg);
                        } else {
                            mUserVoList = userVoResponse.result;
                            pageNum++;
                            mUserRVAdapter.addAll(mUserVoList);
                            if (mUserVoList.size() == 0) {
                                ToastUtils.showMessage(UserActivity.this, "没有更多数据了");
                            }
                        }
                    }
                });
    }


    private void loadDataFromCloud() {
        pageNum = 1;
        OkHttpUtils.post().url(Api.HOST + Api.USER + "listUser")
                .addParams("user_c_id", user_c_id)
                .addParams("page_num", String.valueOf(pageNum))
                .addParams("page_size", String.valueOf(Constants.PAGE_SIZE))
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {

                    }

                    @Override public void onResponse(String response) {
                        Log.d(TAG, "" + response);
                        mMrlUser.finishRefresh();
                        UserVoResponseList userVoResponse = GsonUtils.parseToBean(response, UserVoResponseList.class);
                        if (userVoResponse.error) {
                            ToastUtils.showMessage(UserActivity.this, userVoResponse.errorMsg);
                        } else {
                            mUserVoList = userVoResponse.result;
                            pageNum++;
                            mUserRVAdapter.replaceAll(mUserVoList);
                            if (mUserVoList.size() == 0) {
                                ToastUtils.showMessage(UserActivity.this, "没有店员");
                            }
                        }
                    }
                });

    }


    @Override public void onItemClick(View itemView, int viewType, final int position) {
        new AlertDialog.Builder(UserActivity.this).setMessage("你确定要删除" + mUserVoList.get(position).name + "吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        OkHttpUtils.post().url(Api.HOST + Api.MEMBER + "deleteMember")
                                .addParams("member_id", mUserVoList.get(position).id + "")
                                .build()
                                .execute(new StringCallback() {
                                    @Override public void onError(Call call, Exception e) {

                                    }

                                    @Override public void onResponse(String response) {
                                        Log.d(TAG, "onResponse: " + response);
                                        NullResponse nullResponse = GsonUtils.parseToBean(response, NullResponse.class);
                                        if (nullResponse.error) {
                                            ToastUtils.showMessage(UserActivity.this, nullResponse.errorMsg);
                                        } else {
                                            ToastUtils.showMessage(UserActivity.this, "删除成功");
                                            mUserRVAdapter.remove(position);
                                        }
                                    }
                                });


                    }
                }).setNegativeButton("取消", null).show();

    }
}
