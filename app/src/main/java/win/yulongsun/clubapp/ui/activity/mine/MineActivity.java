package win.yulongsun.clubapp.ui.activity.mine;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.image.ImageLoadManager;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.mine
 * USER : yulongsun on 2016/4/14
 * NOTE :我的
 */
public class MineActivity extends BaseToolbarActivity {
    private static final String TAG = MineActivity.class.getSimpleName();
    @Bind(R.id.tl_mine)
    Toolbar mTlMine;
    @Bind(R.id.iv_mine_bg)      ImageView               mIvMineBg;
    @Bind(R.id.civ_mine_avatar) CircleImageView         mCivMineAvatar;
    @Bind(R.id.ctl_mine)        CollapsingToolbarLayout mCtlMine;
    @Bind(R.id.abl_mine)        AppBarLayout            mAblMine;
    @Bind(R.id.tv_mine_name)    TextView                mTvMineName;
    @Bind(R.id.tv_mine_mobile)  TextView                mTvMineMobile;
    @Bind(R.id.tv_mine_role)    TextView                mTvMineRole;
    @Bind(R.id.tv_mine_gender)  TextView                mTvMineGender;
    @Bind(R.id.tv_mine_job_id)  TextView                mTvMineJobId;
    @Bind(R.id.tv_mine_addr)    TextView                mTvMineAddr;
    private                     ACache                  mACache;

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "我的";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMine;
    }

    @Override public int getLayoutResId() {
        return R.layout.activity_mine;
    }

    @Override protected void initDatas() {
        super.initDatas();
        //avatar
        mACache = ACache.get(MineActivity.this);
        String user_id        = mACache.getAsString("user_id");
        String user_name      = mACache.getAsString("user_name");
        String user_avatar    = mACache.getAsString("user_avatar");
        String user_addr      = mACache.getAsString("user_addr");
        String user_mobile    = mACache.getAsString("user_mobile");
        String user_gender    = mACache.getAsString("user_gender");
        String user_job_id    = mACache.getAsString("user_job_id");
        String user_c_id      = mACache.getAsString("user_c_id");
        String user_r_id      = mACache.getAsString("user_r_id");
        String user_is_enable = mACache.getAsString("user_is_enable");
        Log.d(TAG, "initDatas: user_r_id"+user_r_id);
        //avatar
        ImageLoadManager.getInstance().with(MineActivity.this).load(user_avatar).into(mCivMineAvatar);
        //name
        mTvMineName.setText(user_name);
        //gender
        mTvMineGender.setText(user_gender);
        //jod_id
        mTvMineJobId.setText(user_job_id);
        //mobile
        mTvMineMobile.setText(user_mobile);
        //addr
        mTvMineAddr.setText(user_addr);
        //r_id
        mTvMineRole.setText(user_r_id.equals("1") ? "店面经理" : "店员");

    }

}
