package win.yulongsun.clubapp.ui.activity.member;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.common.Constants;
import win.yulongsun.clubapp.net.entity.MemberVo;
import win.yulongsun.clubapp.net.response.MemberVoResponseList;
import win.yulongsun.clubapp.ui.adapter.MemberRVAdapter;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.GsonUtils;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

//会员
public class MemberActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MemberActivity.class.getSimpleName();
    @Bind(R.id.rv_member)  RecyclerView        mRvMember;
    @Bind(R.id.srf_member) SwipeRefreshLayout  mSrfMember;
    @Bind(R.id.tl_member)  Toolbar             mTlMember;
    private                ArrayList<MemberVo> mMemberVosList;
    private                MemberRVAdapter     mMemberRVAdapter;
    private                LinearLayoutManager mLayoutManager;
    private boolean isLoadingMore = false;
    private int     mPageNum      = 1;

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
        mMemberVosList=new ArrayList<>();
        mSrfMember.setOnRefreshListener(null);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvMember.setLayoutManager(mLayoutManager);
        mMemberRVAdapter = new MemberRVAdapter(MemberActivity.this, mMemberVosList, R.layout.item_rv_member);
        mRvMember.setAdapter(mMemberRVAdapter);
    }

    @Override protected void initDatas() {
        super.initDatas();
        mPageNum = 1;
        loadDataFromCloud(mPageNum);

    }

    private void loadDataFromCloud(int page_num) {
        isLoadingMore = true;
        String user_c_id = ACache.get(MemberActivity.this).getAsString("user_c_id");
        mSrfMember.setRefreshing(true);
        OkHttpUtils.post().url(Api.HOST + Api.MEMBER + "listMember")
                .addParams("user_c_id", user_c_id)
                .addParams("page_num", page_num + "")
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
                            mPageNum++;
                            List<MemberVo> mMemberVos = memberVoResponseList.result;
                            mMemberRVAdapter.addAll(mMemberVos);
                        } else {
                            ToastUtils.showMessage(MemberActivity.this, memberVoResponseList.errorMsg);
                        }

                    }
                });
    }

    @Override public void onRefresh() {
        mPageNum = 1;
        loadDataFromCloud(mPageNum);
    }

}
