package win.yulongsun.clubapp.ui.activity.member;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.byteam.superadapter.OnItemClickListener;
import org.byteam.superadapter.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.common.Constants;
import win.yulongsun.clubapp.net.entity.MemberVo;
import win.yulongsun.clubapp.net.response.MemberVoResponseList;
import win.yulongsun.clubapp.net.response.NullResponse;
import win.yulongsun.clubapp.ui.adapter.MemberRVAdapter;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.GsonUtils;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

//会员
public class MemberActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener, OnItemLongClickListener {

    private static final String TAG = MemberActivity.class.getSimpleName();
    @Bind(R.id.rv_member)  RecyclerView        mRvMember;
    @Bind(R.id.srf_member) SwipeRefreshLayout  mSrfMember;
    @Bind(R.id.tl_member)  Toolbar             mTlMember;
    private                ArrayList<MemberVo> mMemberVosList;
    private                MemberRVAdapter     mMemberRVAdapter;
    private                LinearLayoutManager mLayoutManager;
    private                String              user_c_id;

    @Override public int getLayoutResId() {
        return R.layout.activity_member;
    }


    @Override protected String getToolbarTitle() {
        return "会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMember;
    }


    @Override protected int getMenuResId() {
        return R.menu.menu_add;
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

    @Override protected void initViews() {
        super.initViews();
        mMemberVosList = new ArrayList<>();
        mSrfMember.setOnRefreshListener(this);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvMember.setLayoutManager(mLayoutManager);
        mMemberRVAdapter = new MemberRVAdapter(MemberActivity.this, mMemberVosList, R.layout.item_rv_member);
        mRvMember.setAdapter(mMemberRVAdapter);
        mMemberRVAdapter.setOnItemClickListener(this);
        mMemberRVAdapter.setOnItemLongClickListener(this);
    }

    @Override protected void initDatas() {
        super.initDatas();
        user_c_id = ACache.get(MemberActivity.this).getAsString("user_c_id");
        loadDataFromCloud();
    }

    private void loadDataFromCloud() {
        mSrfMember.setRefreshing(true);
        OkHttpUtils.post().url(Api.HOST + Api.MEMBER + "listMember")
                .addParams("user_c_id", user_c_id)
                .addParams("page_num", 1 + "")
                .addParams("page_size", Constants.PAGE_SIZE + "")
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {
                        mSrfMember.setRefreshing(false);
                    }

                    @Override public void onResponse(String response) {
                        mSrfMember.setRefreshing(false);
                        Log.d(TAG, "onResponse: " + response);
                        MemberVoResponseList memberVoResponseList = GsonUtils.parseToBean(response, MemberVoResponseList.class);
                        if (memberVoResponseList.errorCode == 0) {
                            mMemberVosList = (ArrayList<MemberVo>) memberVoResponseList.result;
                            mMemberRVAdapter.replaceAll(mMemberVosList);
                            if (mMemberVosList.size() == 0) {
                                ToastUtils.showMessage(MemberActivity.this, "没有会员");
                            }

                        } else {
                            ToastUtils.showMessage(MemberActivity.this, memberVoResponseList.errorMsg);
                        }

                    }
                });
    }

    @Override public void onRefresh() {
        loadDataFromCloud();
    }

    @Override public void onItemClick(View itemView, int viewType, int position) {
        Log.d(TAG, "onItemClick: " + position);
    }

    @Override public void onItemLongClick(View itemView, int viewType, final int position) {
        Log.d(TAG, "onItemLongClick: " + position + "," + mMemberVosList.get(position).name);
        new AlertDialog.Builder(MemberActivity.this).setMessage("你确定要删除当前会员吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        OkHttpUtils.post().url(Api.HOST + Api.MEMBER + "deleteMember")
                                .addParams("member_id", mMemberVosList.get(position).id + "")
                                .build()
                                .execute(new StringCallback() {
                                    @Override public void onError(Call call, Exception e) {

                                    }

                                    @Override public void onResponse(String response) {
                                        Log.d(TAG, "onResponse: " + response);
                                        NullResponse nullResponse = GsonUtils.parseToBean(response, NullResponse.class);
                                        if (nullResponse.error) {
                                            ToastUtils.showMessage(MemberActivity.this, nullResponse.errorMsg);
                                        } else {
                                            ToastUtils.showMessage(MemberActivity.this, "删除成功");
                                            mMemberRVAdapter.remove(position);
                                        }
                                    }
                                });


                    }
                }).setNegativeButton("取消", null).show();


    }
}
