package win.yulongsun.clubapp.ui.activity.member;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

//添加会员
public class MemberAddActivity extends BaseToolbarActivity {

    @Bind(R.id.tl_member_add)     Toolbar  mTlMemberAdd;
    @Bind(R.id.et_member_name)    EditText mEtMemberName;
    @Bind(R.id.et_member_mobile)  EditText mEtMemberMobile;
    @Bind(R.id.et_member_score)   EditText mEtMemberScore;
    @Bind(R.id.sp_member_name)    Spinner  mSpMemberName;
    @Bind(R.id.et_member_card_id) EditText mEtMemberCardId;
    @Bind(R.id.et_member_addr)    EditText mEtMemberAddr;

    @Override public int getLayoutResId() {
        return R.layout.activity_member_add;
    }


    @Override protected int getMenuResId() {
        return R.menu.menu_done;
    }

    @Override protected String getToolbarTitle() {
        return "添加会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMemberAdd;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
