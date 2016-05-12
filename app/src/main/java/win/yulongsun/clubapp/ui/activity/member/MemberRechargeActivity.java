package win.yulongsun.clubapp.ui.activity.member;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.yulongsunutils.ValidateUtils;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

//充值
public class MemberRechargeActivity extends BaseToolbarActivity {

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
        if (ValidateUtils.isPwdValid(mobile)) {
            ToastUtils.showMessage(MemberRechargeActivity.this, "手机账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(num)) {
            ToastUtils.showMessage(MemberRechargeActivity.this, "充值金额不能为空");
            return;
        }
        ACache aCache  = ACache.get(this);
        String user_id = aCache.getAsString("user_id");
        OkHttpUtils.post().url(Api.HOST + Api.MEMBER + "recharge")
                .addParams("mobile", mobile)
                .addParams("num", num)
                .addParams("operator_id", user_id)
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {

                    }

                    @Override public void onResponse(String response) {

                    }
                });

    }
}
