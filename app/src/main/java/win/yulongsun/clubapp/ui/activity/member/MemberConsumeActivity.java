package win.yulongsun.clubapp.ui.activity.member;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.common.Constants;
import win.yulongsun.yulongsunutils.ValidateUtils;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

//消费
public class MemberConsumeActivity extends BaseToolbarActivity {

    @Bind(R.id.tl_member_consume)  Toolbar  mTlMemberConsume;
    @Bind(R.id.et_consume_card_id) EditText mEtConsumeCardId;
    @Bind(R.id.et_consume_num)     EditText mEtConsumeNum;
    private                        String   user_id;
    private                        String   user_c_id;

    @Override public int getLayoutResId() {
        return R.layout.activity_member_consume;
    }

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "消费";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMemberConsume;
    }

    @Override protected void initDatas() {
        super.initDatas();
        user_id = ACache.get(MemberConsumeActivity.this).getAsString(Constants.USER_ID);
        user_c_id = ACache.get(MemberConsumeActivity.this).getAsString(Constants.USER_C_ID);

    }

    @OnClick(R.id.btn_consume_submit) public void onClick() {
        String card_id = mEtConsumeCardId.getText().toString().trim();
        String num     = mEtConsumeNum.getText().toString().trim();
        if (ValidateUtils.isTextNull(card_id)) {
            ToastUtils.showMessage(MemberConsumeActivity.this, "消费卡编号不能为空");
            return;
        }
        if (ValidateUtils.isTextNull(num)) {
            ToastUtils.showMessage(MemberConsumeActivity.this, "消费金额不能为空");
            return;
        }
        showLoading("扣费中...");
        OkHttpUtils.post().url(Api.HOST + Api.ORDER + "addOrder")
                .addParams("user_id", user_id)
                .addParams("user_c_id", user_c_id)
                .addParams("card_id", card_id)
                .addParams("num", num)
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {

                    }

                    @Override public void onResponse(String response) {
                        hideLoading();

                    }
                });
    }
}
