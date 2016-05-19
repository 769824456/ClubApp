package win.yulongsun.clubapp.ui.activity.member;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.common.Constants;
import win.yulongsun.clubapp.net.entity.MemberVo;
import win.yulongsun.clubapp.net.response.MemberVoResponseList;
import win.yulongsun.clubapp.ui.adapter.MemberHistoryRVAdapter;
import win.yulongsun.component.cache.ACache;
import win.yulongsun.utils.common.BaseToolbarActivity;
import win.yulongsun.utils.utils.GsonUtils;
import win.yulongsun.utils.utils.ToastUtils;

//历史
public class MemberHistoryActivity extends BaseToolbarActivity {

    private static final String TAG = MemberHistoryActivity.class.getSimpleName();
    @Bind(R.id.tl_member_history) Toolbar      mTlMemberHistory;
    @Bind(R.id.rv_member_history) RecyclerView mRvMemberHistory;
    private int mPageNum = 1;
    private MemberHistoryRVAdapter mMemberRVAdapter;
    private ArrayList<MemberVo>    mMemberVoList;

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

    @Override protected void initViews() {
        super.initViews();
        mMemberVoList = new ArrayList<MemberVo>();
        mRvMemberHistory.setLayoutManager(new LinearLayoutManager(MemberHistoryActivity.this));
        mMemberRVAdapter = new MemberHistoryRVAdapter(MemberHistoryActivity.this, mMemberVoList, R.layout.item_rv_member_history);
        mRvMemberHistory.setAdapter(mMemberRVAdapter);
    }

    @Override protected void initDatas() {
        super.initDatas();
        loadDataFromCloud();
    }

    private void loadDataFromCloud() {
        String user_c_id = ACache.get(MemberHistoryActivity.this).getAsString("user_c_id");
        showLoading("加载中...");
        OkHttpUtils.post().url(Api.HOST + Api.ORDER + "listOrder")
                .addParams("user_c_id", user_c_id)
                .addParams("page_num", mPageNum + "")
                .addParams("page_size", Constants.PAGE_SIZE + "")
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {
                        hideLoading();
                    }

                    @Override public void onResponse(String response) {
                        hideLoading();
                        Log.d(TAG, "onResponse: " + response);
                        MemberVoResponseList memberVoResponseList = GsonUtils.parseToBean(response, MemberVoResponseList.class);
                        if (memberVoResponseList.errorCode == 0) {
                            mPageNum++;
                            mMemberVoList = (ArrayList<MemberVo>) memberVoResponseList.result;
                            mMemberRVAdapter.addAll(mMemberVoList);

                            if (mMemberVoList.size() == 0) {
                                ToastUtils.showMessage(MemberHistoryActivity.this, "没有历史纪录");
                            }

                        } else {
                            ToastUtils.showMessage(MemberHistoryActivity.this, memberVoResponseList.errorMsg);
                        }

                    }
                });
    }
}
