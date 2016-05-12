package win.yulongsun.clubapp.ui.activity.daily;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.daily
 * USER : yulongsun on 2016/4/13
 * NOTE :日结
 */
public class DailyActivity extends BaseToolbarActivity {
    @Bind(R.id.tl_daily)             Toolbar  mTlDaily;
    @Bind(R.id.et_daily_order_num)   EditText mEtDailyOrderNum;
    @Bind(R.id.et_daily_sell_in)     EditText mEtDailySellIn;//销售收入
    @Bind(R.id.et_daily_net_income)  EditText mEtDailyNetIncome;//净收入
    @Bind(R.id.et_daily_operator_id) EditText mEtDailyOperatorId;//操作员

    @Override protected String getToolbarTitle() {
        return "日结";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlDaily;
    }


    @Override public int getLayoutResId() {
        return R.layout.activity_daily;
    }


    @Override protected int getMenuResId() {
        return R.menu.menu_done;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /*提交*/
    public void btnDailySubmit(View view) {
        String order_num   = mEtDailyOrderNum.getText().toString();
        String sell_in     = mEtDailySellIn.getText().toString();
        String net_income  = mEtDailyNetIncome.getText().toString();
        String operator_id = mEtDailyOperatorId.getText().toString();
        if (TextUtils.isEmpty(order_num)) {
            ToastUtils.showMessage(DailyActivity.this, "订单数不能为空!");
            return;
        }
        if (TextUtils.isEmpty(sell_in)) {
            ToastUtils.showMessage(DailyActivity.this, "销售收入不能为空!");
            return;
        }
        if (TextUtils.isEmpty(net_income)) {
            ToastUtils.showMessage(DailyActivity.this, "净收入不能为空!");
            return;
        }
        if (TextUtils.isEmpty(operator_id)) {
            ToastUtils.showMessage(DailyActivity.this, "工号不能为空!");
            return;
        }
        OkHttpUtils.post().url(Api.HOST + Api.SELL)
                .addParams("order_num", order_num)
                .addParams("sell_in", sell_in)
                .addParams("net_income", net_income)
                .addParams("operator_id", operator_id)
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {

                    }

                    @Override public void onResponse(String response) {

                    }
                });


    }

}
