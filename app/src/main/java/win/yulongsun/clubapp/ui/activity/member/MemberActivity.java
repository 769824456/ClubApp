package win.yulongsun.clubapp.ui.activity.member;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.entity.MemberVo;
import win.yulongsun.clubapp.ui.adapter.MemberRVAdapter;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

//会员
public class MemberActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rv_member)  RecyclerView        mRvMember;
    @Bind(R.id.srf_member) SwipeRefreshLayout  mSrfMember;
    @Bind(R.id.tl_member)  Toolbar             mTlMember;
    private                ArrayList<MemberVo> mMemberVos;
    private                MemberRVAdapter     mMemberRVAdapter;

    @Override public int getLayoutResId() {
        return R.layout.activity_member;
    }

    @Override protected void initListeners() {
        super.initListeners();
        mSrfMember.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvMember.setLayoutManager(layoutManager);
    }

    @Override protected String getToolbarTitle() {
        return "会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMember;
    }


    @Override protected int getMenuResId() {
        return R.menu.menu_add;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int    id      = item.getItemId();
        Intent mIntent = null;
        if (id == R.id.action_add) {
            mIntent = new Intent(MemberActivity.this, MemberAddActivity.class);
            startActivity(mIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected void initDatas() {
        super.initDatas();
        mMemberVos = new ArrayList<>();
        MemberVo mMemberVo = null;
        for (int i = 0; i < 10; i++) {
            mMemberVo = new MemberVo(1, "yulongsun" + i, "130675097" + i);
            mMemberVos.add(mMemberVo);
        }
        mMemberRVAdapter = new MemberRVAdapter(this, mMemberVos);
        mRvMember.setAdapter(mMemberRVAdapter);
    }

    @Override public void onRefresh() {

    }

}
