package win.yulongsun.clubapp.ui.activity.member;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

//历史
public class MemberHistoryActivity extends BaseToolbarActivity {

    @Bind(R.id.tl_member_history) Toolbar mTlMemberHistory;

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
}
