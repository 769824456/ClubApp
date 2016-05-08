package win.yulongsun.clubapp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.entity.HomeRV;
import win.yulongsun.clubapp.ui.activity.daily.DailyActivity;
import win.yulongsun.clubapp.ui.activity.member.MemberActivity;
import win.yulongsun.clubapp.ui.activity.mine.MineActivity;
import win.yulongsun.clubapp.ui.activity.statistics.StatisticsActivity;
import win.yulongsun.clubapp.ui.activity.user.UserActivity;
import win.yulongsun.clubapp.ui.adapter.HomeRVAdapter;
import win.yulongsun.clubapp.utils.LocalImageHolderView;
import win.yulongsun.yulongsunutils.DividerItemDecoration;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseActivity;
import win.yulongsun.yulongsunutils.image.ImageLoadManager;
import win.yulongsun.yulongsunutils.utils.ResUtils;

/**
 * 主页
 */
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    @Bind(R.id.tl_main)     Toolbar          mTlMain;
    @Bind(R.id.cb_home)     ConvenientBanner mCbHome;
    @Bind(R.id.nav_view)    NavigationView   mNavView;
    @Bind(R.id.drawer_home) DrawerLayout     mDrawerHome;
    @Bind(R.id.rv_home)     RecyclerView     mRvHome;
    LinearLayout mLlNavHeader;
    ImageView    mIvNavHeaderAvatar;
    ImageView    mIvNavHeaderLogout;
    TextView     mTvNavHeaderName;
    private ArrayList<Integer> mLocalImages;
    private MenuItem           mMenuItemNavUser;

    @Override public int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override protected void initViews() {
        super.initViews();
        setSupportActionBar(mTlMain);
        getSupportActionBar().setTitle("主页");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerHome, mTlMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerHome.setDrawerListener(toggle);
        toggle.syncState();
        //findViews
        View headerView = mNavView.getHeaderView(0);
        mMenuItemNavUser = mNavView.getMenu().findItem(R.id.nav_user);
        mLlNavHeader = (LinearLayout) headerView.findViewById(R.id.ll_nav_header);
        mIvNavHeaderAvatar = (ImageView) headerView.findViewById(R.id.iv_nav_header_avatar);
        mTvNavHeaderName = (TextView) headerView.findViewById(R.id.tv_nav_header_name);
        mIvNavHeaderLogout = (ImageView) headerView.findViewById(R.id.iv_nav_header_logout);
    }

    @Override protected void initListeners() {
        super.initListeners();
        mNavView.setNavigationItemSelectedListener(this);
        mLlNavHeader.setOnClickListener(this);
        mIvNavHeaderLogout.setOnClickListener(this);

    }


    @Override protected void initDatas() {
        super.initDatas();
        //本地图片集合
        mLocalImages = new ArrayList<>();
        for (int position = 1; position < 5; position++)
            mLocalImages.add(ResUtils.getResId("bg_home_" + position, R.mipmap.class));

        mCbHome.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, mLocalImages)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //girdview
        ArrayList<HomeRV> mDatas  = new ArrayList<>();
        HomeRV            homeRV1 = new HomeRV();
        homeRV1.rl_item_home = R.color.colorAccent;
        homeRV1.iv_item_home = R.mipmap.ic_home_searcher;
        homeRV1.tv_item_home = "搜索";
        HomeRV homeRV2 = new HomeRV();
        homeRV2.rl_item_home = R.color.mediumturquoise;
        homeRV2.iv_item_home = R.mipmap.ic_home_consume;
        homeRV2.tv_item_home = "消费";
        HomeRV homeRV3 = new HomeRV();
        homeRV3.rl_item_home = R.color.lightsteelblue;
        homeRV3.iv_item_home = R.mipmap.ic_home_recharge;
        homeRV3.tv_item_home = "充值";
        HomeRV homeRV4 = new HomeRV();
        homeRV4.rl_item_home = R.color.darkorange;
        homeRV4.iv_item_home = R.mipmap.ic_home_history;
        homeRV4.tv_item_home = "历史";


        mDatas.add(homeRV1);
        mDatas.add(homeRV2);
        mDatas.add(homeRV3);
        mDatas.add(homeRV4);
        mRvHome.setLayoutManager(new GridLayoutManager(this, 2));
        mRvHome.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        HomeRVAdapter homeRVAdapter = new HomeRVAdapter(this, mDatas);
        mRvHome.setAdapter(homeRVAdapter);

        //left menu
        String user_name   = ACache.get(HomeActivity.this).getAsString("user_name");
        String user_avatar = ACache.get(HomeActivity.this).getAsString("user_avatar");
        String user_r_id   = ACache.get(HomeActivity.this).getAsString("user_r_id");

        ImageLoadManager.getInstance().with(HomeActivity.this).load(user_avatar).setError(R.mipmap.ic_launcher).into(mIvNavHeaderAvatar);
        mTvNavHeaderName.setText(user_name);
        mMenuItemNavUser.setVisible("1".equals(user_r_id) ? true : false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int    id      = item.getItemId();
        Intent mIntent = null;
        if (id == R.id.nav_member) {
            mIntent = new Intent(HomeActivity.this, MemberActivity.class);
        } else if (id == R.id.nav_daily) {
            mIntent = new Intent(HomeActivity.this, DailyActivity.class);
        } else if (id == R.id.nav_statistics) {
            mIntent = new Intent(HomeActivity.this, StatisticsActivity.class);
        } else if (id == R.id.nav_user) {
            mIntent = new Intent(HomeActivity.this, UserActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        drawer.closeDrawer(GravityCompat.START);


        if (mIntent != null) {
            startActivity(mIntent);
        }
        return true;
    }


    @Override public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.iv_nav_header_logout:
                new AlertDialog.Builder(HomeActivity.this).setMessage("你确定要退出应用吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                                HomeActivity.this.finish();
                                ACache.get(HomeActivity.this).put("is_login", "0");
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.ll_nav_header:
                intent = new Intent(HomeActivity.this, MineActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

}
