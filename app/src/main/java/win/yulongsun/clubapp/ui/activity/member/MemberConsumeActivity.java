package win.yulongsun.clubapp.ui.activity.member;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

//消费
public class MemberConsumeActivity extends BaseToolbarActivity {

    @Bind(R.id.tl_member_consume) Toolbar mTlMemberConsume;

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
}
