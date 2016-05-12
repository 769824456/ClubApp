package win.yulongsun.clubapp.ui.activity.member;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.common.Constants;
import win.yulongsun.clubapp.entity.MemberVo;
import win.yulongsun.clubapp.response.MemberVoResponseList;
import win.yulongsun.clubapp.ui.adapter.MemberRVAdapter;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.GsonUtils;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

//历史
public class MemberHistoryActivity extends BaseToolbarActivity {

    private static final String TAG = MemberHistoryActivity.class.getSimpleName();
    @Bind(R.id.tl_member_history)  Toolbar            mTlMemberHistory;
    @Bind(R.id.rv_member_history)  RecyclerView       mRvMemberHistory;
    @Bind(R.id.srf_member_history) SwipeRefreshLayout mSrfMemberHistory;
    private int page_num = 1;
    private int mPageNum = 1;

    @Override public int getLayoutResId() {
        return R.layout.activity_member_history_activty;
    }

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "历史";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMemberHistory;
    }

    @Override protected void initDatas() {
        super.initDatas();
        loadDataFromCloud();
    }

    private void loadDataFromCloud() {
        String user_c_id = ACache.get(MemberHistoryActivity.this).getAsString("user_c_id");
        mSrfMemberHistory.setRefreshing(true);
        OkHttpUtils.post().url(Api.HOST + Api.MEMBER + "history")
                .addParams("c_id", user_c_id)
                .addParams("page_num", page_num + "")
                .addParams("page_size", Constants.PAGE_SIZE + "")
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {
                        mSrfMemberHistory.setRefreshing(false);
                    }

                    @Override public void onResponse(String response) {
                        mSrfMemberHistory.setRefreshing(false);
                        Log.d(TAG, "onResponse: " + response);
                        MemberVoResponseList memberVoResponseList = GsonUtils.changeGsonToBean(response, MemberVoResponseList.class);
                        if (memberVoResponseList.errorCode == 0) {
                            mPageNum++;
                            List<MemberVo> mMemberVos = memberVoResponseList.result;
                            MemberRVAdapter mMemberRVAdapter = new MemberRVAdapter(MemberHistoryActivity.this, mMemberVos);
                            mRvMemberHistory.setAdapter(mMemberRVAdapter);
                        } else {
                            ToastUtils.showMessage(MemberHistoryActivity.this, memberVoResponseList.errorMsg);
                        }

                    }
                });
    }
}
