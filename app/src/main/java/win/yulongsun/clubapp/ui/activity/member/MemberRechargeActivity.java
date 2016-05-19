package win.yulongsun.clubapp.ui.activity.member;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.net.response.NullResponse;
import win.yulongsun.uiframework.BaseToolbarActivity;
import win.yulongsun.utils.GsonUtils;
import win.yulongsun.utils.ToastUtils;
import win.yulongsun.utils.ValidateUtils;
import win.yulongsun.component.cache.ACache;

//充值
public class MemberRechargeActivity extends BaseToolbarActivity {

    private static final String TAG = MemberRechargeActivity.class.getSimpleName();
    @Bind(R.id.tl_member_recharge)        Toolbar  mTlMemberRecharge;
    @Bind(R.id.et_member_recharge_mobile) EditText mEtMemberRechargeMobile;
    @Bind(R.id.et_member_recharge_num)    EditText mEtMemberRechargeNum;

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "充值";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMemberRecharge;
    }

    @Override public int getLayoutResId() {
        return R.layout.activity_member_recharge;
    }

    /*充值*/
    public void btnRecharge(View view) {
        String mobile = mEtMemberRechargeMobile.getText().toString();
        String num    = mEtMemberRechargeNum.getText().toString();
        if (!ValidateUtils.isPwdValid(mobile)) {
            ToastUtils.showMessage(MemberRechargeActivity.this, "手机账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(num)) {
            ToastUtils.showMessage(MemberRechargeActivity.this, "充值金额不能为空");
            return;
        }
        ACache aCache  = ACache.get(this);
        String user_id = aCache.getAsString("user_id");
        showLoading("充值中...");
        OkHttpUtils.post().url(Api.HOST + Api.MEMBER + "recharge")
                .addParams("member_mobile", mobile)
                .addParams("member_money", num)
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {
                        hideLoading();
                    }

                    @Override public void onResponse(String response) {
                        hideLoading();
                        Log.d(TAG, response);
                        NullResponse nullResponse = GsonUtils.parseToBean(response, NullResponse.class);
                        if (nullResponse.error) {
                            ToastUtils.showMessage(MemberRechargeActivity.this, nullResponse.errorMsg);
                        } else {
                            ToastUtils.showMessage(MemberRechargeActivity.this, "充值成功");
                            MemberRechargeActivity.this.finish();
                        }
                    }
                });

    }
}
