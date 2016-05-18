package win.yulongsun.clubapp.ui.activity.daily;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.common.Constants;
import win.yulongsun.clubapp.net.response.NullResponse;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.GsonUtils;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.daily
 * USER : yulongsun on 2016/4/13
 * NOTE :日结
 */
public class DailyActivity extends BaseToolbarActivity {
    private static final String TAG = DailyActivity.class.getSimpleName();
    @Bind(R.id.tl_daily)             Toolbar  mTlDaily;
    @Bind(R.id.et_daily_order_num)   EditText mEtDailyOrderNum;
    @Bind(R.id.et_daily_sell_in)     EditText mEtDailySellIn;//销售收入
    @Bind(R.id.et_daily_profit_in)   EditText mEtDailyProfitIn;//净收入
    @Bind(R.id.et_daily_operator_id) EditText mEtDailyOperatorId;//操作员
    private                          String   user_c_id;
    private                          String   user_id;

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
        return 0;
    }

    @Override protected void initViews() {
        super.initViews();
        user_c_id = ACache.get(DailyActivity.this).getAsString(Constants.USER_C_ID);
        user_id = ACache.get(DailyActivity.this).getAsString(Constants.USER_ID);
    }

//    @Override public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_done) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    /*提交*/
    public void btnDailySubmit(View view) {
        String user_order_num = mEtDailyOrderNum.getText().toString();
        String user_sell_in   = mEtDailySellIn.getText().toString();
        String user_profit_in = mEtDailyProfitIn.getText().toString();
//        String operator_id = mEtDailyOperatorId.getText().toString();
        if (TextUtils.isEmpty(user_order_num)) {
            ToastUtils.showMessage(DailyActivity.this, "订单数不能为空!");
            return;
        }
        if (TextUtils.isEmpty(user_profit_in)) {
            ToastUtils.showMessage(DailyActivity.this, "销售收入不能为空!");
            return;
        }
        if (TextUtils.isEmpty(user_profit_in)) {
            ToastUtils.showMessage(DailyActivity.this, "净收入不能为空!");
            return;
        }
//        if (TextUtils.isEmpty(operator_id)) {
//            ToastUtils.showMessage(DailyActivity.this, "工号不能为空!");
//            return;
//        }
        OkHttpUtils.post().url(Api.HOST + Api.BIll + "addBill")
                .addParams("user_id", user_id)
                .addParams("user_order_num", user_order_num)
                .addParams("user_sell_in", user_sell_in)
                .addParams("user_profit_in", user_profit_in)
                .addParams("user_c_id", user_c_id)
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {

                    }

                    @Override public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        NullResponse nullResponse = GsonUtils.parseToBean(response, NullResponse.class);
                        if (nullResponse.error) {
                            ToastUtils.showMessage(DailyActivity.this, nullResponse.errorMsg);
                        } else {
                            ToastUtils.showMessage(DailyActivity.this, "提交成功");
                            DailyActivity.this.finish();
                        }
                    }
                });


    }

}
