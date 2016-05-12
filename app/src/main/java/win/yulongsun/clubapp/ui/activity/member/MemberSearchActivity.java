package win.yulongsun.clubapp.ui.activity.member;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

//搜索
public class MemberSearchActivity extends BaseToolbarActivity implements TextView.OnEditorActionListener {

    @Bind(R.id.tl_member_search)  Toolbar  mTlMemberSearch;
    @Bind(R.id.et_search_content) EditText mEtSearchContent;

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
        OkHttpUtils.post()
                .url(Api.HOST + Api.USER + "search")
                .addParams("c_id", user_c_id)
                .addParams("name", name)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                    }
                });
    }
}
