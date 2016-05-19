package win.yulongsun.clubapp.ui.activity.member;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.net.entity.MemberVo;
import win.yulongsun.clubapp.net.response.MemberVoResponseList;
import win.yulongsun.clubapp.ui.adapter.MemberRVAdapter;
import win.yulongsun.component.cache.ACache;
import win.yulongsun.uiframework.BaseToolbarActivity;
import win.yulongsun.utils.GsonUtils;
import win.yulongsun.utils.ToastUtils;

//搜索
public class MemberSearchActivity extends BaseToolbarActivity implements TextView.OnEditorActionListener {

    private static final String TAG = MemberSearchActivity.class.getSimpleName();
    @Bind(R.id.tl_member_search)  Toolbar             mTlMemberSearch;
    @Bind(R.id.et_search_content) EditText            mEtSearchContent;
    @Bind(R.id.rv_search)         RecyclerView        mRvSearch;
    private                       ArrayList<MemberVo> mMemberVoList;
    private                       LinearLayoutManager mLayoutManager;
    private                       MemberRVAdapter     mSearchRVAdapter;

    @Override public int getLayoutResId() {
        return R.layout.activity_member_search;
    }

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "会员搜索";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMemberSearch;
    }


    @Override protected void initViews() {
        super.initViews();
        mMemberVoList = new ArrayList<MemberVo>();
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvSearch.setLayoutManager(mLayoutManager);
        mSearchRVAdapter = new MemberRVAdapter(MemberSearchActivity.this, mMemberVoList, R.layout.item_rv_member);
        mRvSearch.setAdapter(mSearchRVAdapter);

    }

    @Override protected void initListeners() {
        super.initListeners();
        mEtSearchContent.setOnEditorActionListener(this);
    }

    @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String name = mEtSearchContent.getText().toString();//关键字
            loadDataFromCloud(name);
        }
        return true;
    }

    private void loadDataFromCloud(String name) {
        ACache aCache    = ACache.get(this);
        String user_c_id = aCache.getAsString("user_c_id");
        showLoading("搜索中...");
        OkHttpUtils.post()
                .url(Api.HOST + Api.MEMBER + "queryMember")
                .addParams("member_c_id", user_c_id)
                .addParams("member_name", name)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        hideLoading();
                        Log.d(TAG, response);
                        MemberVoResponseList memberVoResponseList = GsonUtils.parseToBean(response, MemberVoResponseList.class);
                        if (memberVoResponseList.error) {
                            ToastUtils.showMessage(MemberSearchActivity.this, memberVoResponseList.errorMsg);
                        } else {
                            mMemberVoList = (ArrayList<MemberVo>) memberVoResponseList.result;
                            if (mMemberVoList.size() == 0) {
                                ToastUtils.showMessage(MemberSearchActivity.this, "查询不到该用户");
                            } else {
                                mSearchRVAdapter.replaceAll(mMemberVoList);
                            }
                        }
                    }
                });
    }

}
